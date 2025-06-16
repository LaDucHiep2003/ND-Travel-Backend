package com.javaweb.repository.custom.Impl;

import com.javaweb.builder.TourSearchBuilder;
import com.javaweb.repository.custom.TourRepositoryCustom;
import com.javaweb.repository.entity.TourEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.lang.reflect.Field;
import java.util.List;

public class TourRepositoryImpl implements TourRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public static void queryNormal(TourSearchBuilder tourSearchBuilder, StringBuilder where) {
        try{
            Field[] fields = TourSearchBuilder.class.getDeclaredFields();
            for(Field item : fields) {
                item.setAccessible(true);
                String fieldName = item.getName();
                if(!fieldName.equals("departure_date") && !fieldName.equals("price_adult") && !fieldName.equals("status")){
                    Object value = item.get(tourSearchBuilder);
                    if(value != null){
                        if(item.getType().getName().equals("java.lang.Long") || item.getType().getName().equals("java.lang.Integer")){
                            where.append(" And t." + fieldName + " = " + value + " ");
                        } else if (item.getType().getName().equals("java.lang.String")) {
                            where.append(" AND t." + fieldName + " Like '%" + value + "%' ");
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void querySpecial(TourSearchBuilder tourSearchBuilder, StringBuilder where) {
        Integer price_adultTo = tourSearchBuilder.getPrice_adultTo();
        Integer price_adultFrom = tourSearchBuilder.getPrice_adultFrom();
        if(price_adultFrom != null || price_adultTo != null){
            if(price_adultTo != null){
                where.append(" And t.price_adult >= '" + price_adultTo + "'");
            }
            if(price_adultFrom != null){
                where.append(" And t.price_adult <= '" + price_adultFrom + "'");
            }
        }
        String status = tourSearchBuilder.getStatus();
        if(status != null && !status.equals("all")){
            where.append(" And t.status = '" + status + "'");
        }else{
            where.append(" And 1 = 1");
        }
    }

    @Override
    public List<TourEntity> findAll(TourSearchBuilder tourSearchBuilder) {
        StringBuilder sql = new StringBuilder("Select t.* from tours t ");
        StringBuilder where = new StringBuilder("Where deleted = false ");
        queryNormal(tourSearchBuilder, where);
        querySpecial(tourSearchBuilder, where);
        sql.append(where);
        Query query = entityManager.createNativeQuery(sql.toString(), TourEntity.class);
        return query.getResultList();
    }

    @Override
    public void deleteTour(List<Long> ids) {
        for(Long id : ids){
            TourEntity existingEntity = entityManager.find(TourEntity.class, id);
            if(existingEntity != null){
                existingEntity.setDeleted(true);
            }
        }
    }

}
