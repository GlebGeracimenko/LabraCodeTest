package ua.com.codefire.dao.impl;

import ua.com.codefire.dao.StubDao;
import ua.com.codefire.dao.entity.StubEntity;

import javax.persistence.*;

/**
 * Created by gleb on 31.05.17.
 */
public class StubDaoImpl implements StubDao {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ua.com.codefire");
    private final String nativeInsert = "insert into stub2 (stub_value, first_value, second_value) values (:stubValue, :firstValue, LENGTH(stub_value)+first_value)";

    private static class SingletonStubDao {
        private final static StubDao stubDao = new StubDaoImpl();
    }

    public static StubDao getInstance() {
        return SingletonStubDao.stubDao;
    }

    @Override
    public StubEntity saveStub(StubEntity stubEntity) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(stubEntity);
        entityManager.getTransaction().commit();
        return stubEntity;
    }

    @Override
    public StubEntity saveStubByNativeQuery(String stubValue, Integer firstValue) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createNativeQuery(nativeInsert);
        query.setParameter("stubValue", stubValue);
        query.setParameter("firstValue", firstValue);
        query.executeUpdate();
        transaction.commit();
        return null;
    }
}
