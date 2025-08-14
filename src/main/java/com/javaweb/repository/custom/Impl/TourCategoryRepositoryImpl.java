package com.javaweb.repository.custom.Impl;

import com.javaweb.builder.TourCategorySearchBuilder;
import com.javaweb.builder.TourSearchBuilder;
import com.javaweb.repository.custom.TourCategoryRepositoryCustom;
import com.javaweb.repository.entity.TourCategoryEntity;
import com.javaweb.repository.entity.TourEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.lang.reflect.Field;
import java.util.List;

public class TourCategoryRepositoryImpl implements TourCategoryRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public void queryNormal(TourCategorySearchBuilder tourCategorySearchBuilder, StringBuilder where) {
        try{
            Field[] fields = TourCategorySearchBuilder.class.getDeclaredFields();
            for(Field item : fields) {
                item.setAccessible(true);
                String fieldName = item.getName();
                if(!fieldName.equals("subCategory")){
                    Object value = item.get(tourCategorySearchBuilder);
                    if(value != null){
                        if(item.getType().getName().equals("java.lang.Long") || item.getType().getName().equals("java.lang.Integer")){
                            where.append(" And c." + fieldName + " = " + value + " ");
                        } else if (item.getType().getName().equals("java.lang.String")) {
                            where.append(" AND c." + fieldName + " Like '%" + value + "%' ");
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void querySpecial(TourCategorySearchBuilder tourCategorySearchBuilder, StringBuilder where) {
        Integer subCategory = tourCategorySearchBuilder.getSubCategory();
        if (subCategory != null) {
            if(subCategory == 0){
                where.append(" AND c.parent_id IS NULL ");
            }else{
                where.append(" AND c.parent_id IS NOT NULL ");
            }
        }
    }

    @Override
    public List<TourCategoryEntity> findAll(TourCategorySearchBuilder tourCategorySearchBuilder) {
        StringBuilder sql = new StringBuilder("Select * from tour_category c");
        StringBuilder where = new StringBuilder(" Where c.deleted = false ");
        queryNormal(tourCategorySearchBuilder, where);
        querySpecial(tourCategorySearchBuilder, where);
        sql.append(where);
        Query query = entityManager.createNativeQuery(sql.toString(), TourCategoryEntity.class);
        return query.getResultList();
    }

    @Override
    public void deleteCategory(List<Long> ids) {
        for(Long id : ids) {
            TourCategoryEntity existingEntity = entityManager.find(TourCategoryEntity.class, id);
            if (existingEntity != null) {
                existingEntity.setDeleted(true);
            }
        }
    }
}
