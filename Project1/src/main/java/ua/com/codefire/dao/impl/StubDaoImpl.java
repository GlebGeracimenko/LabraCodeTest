package ua.com.codefire.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.codefire.dao.StubDao;
import ua.com.codefire.dao.entity.StubEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 * Created by gleb on 31.05.17.
 */
@Repository
public class StubDaoImpl implements StubDao {

    @Autowired
    private EntityManagerFactory emf;
    private final String nativeInsert = "insert into stub2 (stub_value, first_value, second_value) values (:stubValue, :firstValue, LENGTH(stub_value)+first_value)";

    @Override
    public StubEntity saveStub(StubEntity stubEntity) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(stubEntity);
        entityManager.getTransaction().commit();
        return stubEntity;
    }

    @Override
    public void saveStubByNativeQuery(String stubValue, Integer firstValue) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createNativeQuery(nativeInsert);
        query.setParameter("stubValue", stubValue);
        query.setParameter("firstValue", firstValue);
        query.executeUpdate();
        transaction.commit();
    }
}
