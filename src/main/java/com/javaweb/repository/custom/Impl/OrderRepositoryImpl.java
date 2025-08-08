package com.javaweb.repository.custom.Impl;

import com.javaweb.builder.OrderSearchBuilder;
import com.javaweb.repository.custom.OrderRepositoryCustom;
import com.javaweb.repository.entity.OrderEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.lang.reflect.Field;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public static  void joinTable(OrderSearchBuilder orderSearchBuilder, StringBuilder sql) {
        Long Id = orderSearchBuilder.getId();
        if(Id != null) {
            sql.append(" inner join order_items on orders.id = order_items.order_id ");
        }
    }

    public static void queryNormal(OrderSearchBuilder orderSearchBuilder, StringBuilder where){
        try{
            Field[] fields = orderSearchBuilder.getClass().getDeclaredFields();
            for(Field item : fields){
                item.setAccessible(true);
                String fieldName = item.getName();
                Object value = item.get(orderSearchBuilder);
                if(value != null) {
                    if(item.getType().getName().equals("java.lang.Long") || item.getType().getName().equals("java.lang.Integer")){
                        where.append(" And u." + fieldName + " = :" + fieldName + " ");
                    } else if (item.getType().getName().equals("java.lang.String")) {
                        where.append(" AND u." + fieldName + " Like :" + fieldName + " ");
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<OrderEntity> findALl(OrderSearchBuilder orderSearchBuilder) {
        StringBuilder sql = new StringBuilder("Select orders.* from orders ");
        joinTable(orderSearchBuilder, sql);
        StringBuilder where = new StringBuilder(" Where deleted = false ");
        queryNormal(orderSearchBuilder, where);
        sql.append(where);
        Query query = entityManager.createNativeQuery(sql.toString(), OrderEntity.class);
        
        // Set parameters to prevent SQL injection
        try {
            Field[] fields = orderSearchBuilder.getClass().getDeclaredFields();
            for(Field item : fields){
                item.setAccessible(true);
                String fieldName = item.getName();
                Object value = item.get(orderSearchBuilder);
                if(value != null) {
                    if(item.getType().getName().equals("java.lang.String")) {
                        query.setParameter(fieldName, "%" + value + "%");
                    } else {
                        query.setParameter(fieldName, value);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return query.getResultList();
    }

    @Override
    public void delete(List<Long> ids) {
        for(Long id : ids){
            OrderEntity existingEntity = entityManager.find(OrderEntity.class, id);
            if(existingEntity != null){
                existingEntity.setDeleted(true);
            }
        }
    }
}
