package com.gmw.repository.view;

import com.gmw.entity.View;
import com.gmw.repository.Repository;
import com.gmw.utils.HibernateEntityManagerUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class ViewRepositorySql implements Repository<View> {

    @Override
    public void create(View view) {
        EntityManager entityManager = HibernateEntityManagerUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        entityManager.persist(view);

        transaction.commit();
    }

    @Override
    public void update(View view) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<View> find(String query) {
        return null;
    }
}
