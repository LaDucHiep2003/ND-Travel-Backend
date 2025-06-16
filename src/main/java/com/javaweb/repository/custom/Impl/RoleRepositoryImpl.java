package com.javaweb.repository.custom.Impl;

import com.javaweb.builder.RoleSearchBuilder;
import com.javaweb.repository.custom.RoleRepositoryCustom;
import com.javaweb.repository.entity.RoleEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.lang.reflect.Field;
import java.util.List;

public class RoleRepositoryImpl implements RoleRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public static void queryNormal(RoleSearchBuilder roleSearchBuilder, StringBuilder where) {
        try {
            Field[] fields = RoleSearchBuilder.class.getDeclaredFields();
            for (Field item : fields) {
                item.setAccessible(true);
                String fieldName = item.getName();

                Object value = item.get(roleSearchBuilder);
                if(value != null) {
                    if(item.getType().getName().equals("java.lang.Long") || item.getType().getName().equals("java.lang.Integer")){
                        where.append(" And t." + fieldName + " = " + value + " ");
                    } else if (item.getType().getName().equals("java.lang.String")) {
                        where.append(" AND t." + fieldName + " Like '%" + value + "%' ");
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<RoleEntity> findAll(RoleSearchBuilder roleSearchBuilder) {
        StringBuilder sql = new StringBuilder("Select t.* from role t ");
        StringBuilder where = new StringBuilder("Where deleted = false ");
        queryNormal(roleSearchBuilder, where);
        sql.append(where);
        Query query = entityManager.createNativeQuery(sql.toString(), RoleEntity.class);
        return query.getResultList();
    }

    @Override
    public void deleteRole(List<Long> ids) {
        for(Long id : ids){
            RoleEntity role = entityManager.find(RoleEntity.class, id);
            if(role != null){
                role.setDeleted(true);
            }
        }
    }
}
