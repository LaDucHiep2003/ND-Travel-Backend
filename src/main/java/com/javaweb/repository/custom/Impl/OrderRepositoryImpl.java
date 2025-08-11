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
        sql.append(" inner join order_items on orders.id = order_items.order_id ");
        sql.append(" inner join tours on order_items.tour_id = tours.id ");
        sql.append(" inner join user on orders.user_id = user.id ");
    }

    public static void queryNormal(OrderSearchBuilder orderSearchBuilder, StringBuilder where){
        try{
            Field[] fields = orderSearchBuilder.getClass().getDeclaredFields();
            for(Field item : fields){
                item.setAccessible(true);
                String fieldName = item.getName();
                if(!fieldName.equals("status") && !fieldName.equals("fullName")) {
                    Object value = item.get(orderSearchBuilder);
                    if (value != null) {
                        if (item.getType().getName().equals("java.lang.Long") || item.getType().getName().equals("java.lang.Integer")) {
                            where.append(" And orders." + fieldName + " = :" + fieldName + " ");
                        } else if (item.getType().getName().equals("java.lang.String")) {
                            where.append(" AND orders." + fieldName + " Like '%" + fieldName + "%' ");
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void querySpecial(OrderSearchBuilder orderSearchBuilder, StringBuilder where){
        String status = orderSearchBuilder.getStatus();
        if(status != null && !status.equals("all")){
            where.append(" And orders.status = '" + status + "' ");
        }else{
            where.append(" And 1 = 1");
        }
        String fullName = orderSearchBuilder.getFullName();
        if(fullName != null && !fullName.equals("")){
            where.append(" And user.fullName like '%" + fullName + "%' ");
        }
    }

    @Override
    public List<OrderEntity> findALl(OrderSearchBuilder orderSearchBuilder) {
        StringBuilder sql = new StringBuilder("Select orders.*, ANY_VALUE(user.fullName) AS fullName, ANY_VALUE(tours.thumbnail) from orders ");
        joinTable(orderSearchBuilder, sql);
        StringBuilder where = new StringBuilder(" Where orders.deleted = false ");
        queryNormal(orderSearchBuilder, where);
        querySpecial(orderSearchBuilder, where);
        where.append(" group by orders.id ");
        sql.append(where);
        Query query = entityManager.createNativeQuery(sql.toString(), OrderEntity.class);
        
        // Set parameters to prevent SQL injection

        
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

    @Override
    public void confirmOrders(List<Long> ids) {
        for(Long id : ids){
            OrderEntity existingEntity = entityManager.find(OrderEntity.class, id);
            if(existingEntity != null){
                existingEntity.setStatus("confirmed");
            }
        }
    }
}
