package com.javaweb.repository.custom.Impl;

import java.lang.reflect.Field;
import java.util.List;

import com.javaweb.builder.TourSearchBuilder;
import com.javaweb.builder.UserSearchBuilder;
import com.javaweb.repository.custom.UserRepositoryCustom;
import com.javaweb.repository.entity.UserEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

public class UserRepositoryImpl implements UserRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    public static void joinTable(UserSearchBuilder userSearchBuilder, StringBuilder sql){
        Long roleId = userSearchBuilder.getRole();
        if(roleId != null){
            sql.append(" inner join user_role on u.id = user_role.userid ");
        }
    }

    public static void queryNormal(UserSearchBuilder userSearchBuilder, StringBuilder where) {
        try{
            Field[] fields = UserSearchBuilder.class.getDeclaredFields();
            for(Field item : fields) {
                item.setAccessible(true);
                String fieldName = item.getName();
                if(!fieldName.equals("role")){
                    Object value = item.get(userSearchBuilder);
                    if(value != null){
                        if(item.getType().getName().equals("java.lang.Long") || item.getType().getName().equals("java.lang.Integer")){
                            where.append(" And u." + fieldName + " = " + value + " ");
                        } else if (item.getType().getName().equals("java.lang.String")) {
                            where.append(" AND u." + fieldName + " Like '%" + value + "%' ");
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void querySpecial(UserSearchBuilder userSearchBuilder, StringBuilder where) {
        Long role = userSearchBuilder.getRole();
        if(role != null && !role.equals("all")){
            where.append(" AND user_role.roleid = " + role + " ");
        }
    }

    @Override
    public List<UserEntity> findAll(UserSearchBuilder userSearchBuilder) {
        StringBuilder sql = new StringBuilder("Select u.* from user u ");
        joinTable(userSearchBuilder, sql);
        StringBuilder where = new StringBuilder("Where u.deleted = false ");
        queryNormal(userSearchBuilder, where);
        querySpecial(userSearchBuilder, where);
        sql.append(where);
        System.out.println("SQL = " + sql);
        Query query = entityManager.createNativeQuery(sql.toString(), UserEntity.class);
        return query.getResultList();
    }

    @Override
    public void deleteUser(List<Long> ids) {
        for(Long id : ids){
            UserEntity existingEntity = entityManager.find(UserEntity.class, id);
            if(existingEntity != null){
                existingEntity.setDeleted(true);
            }
        }
    }
}
