package com.javaweb.repository.custom.Impl;

import java.util.List;

import com.javaweb.repository.custom.UserRepositoryCustom;
import com.javaweb.repository.entity.UserEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class UserRepositoryImpl implements UserRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

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
