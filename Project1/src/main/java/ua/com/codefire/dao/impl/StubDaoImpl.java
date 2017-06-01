package ua.com.codefire.dao.impl;

import ua.com.codefire.dao.StubDao;
import ua.com.codefire.dao.entity.StubEntity;

import javax.persistence.*;

/**
 * Created by gleb on 31.05.17.
 */
public class StubDaoImpl implements StubDao {

    private static StubDao stubDao = null;

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ua.com.codefire");

    public synchronized static StubDao getInstance() {
        if (stubDao == null) {
            stubDao = new StubDaoImpl();
        }
        return stubDao;
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
    public StubEntity saveStub2(String stubValue, Integer firstValue) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createNamedQuery("insertStub");
        query.setParameter("stubValue", stubValue);
        query.setParameter("firstValue", firstValue);
        query.executeUpdate();
        transaction.commit();
        return null;
    }
}
