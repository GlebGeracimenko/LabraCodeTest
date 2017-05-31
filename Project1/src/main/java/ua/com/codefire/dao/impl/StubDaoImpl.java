package ua.com.codefire.dao.impl;

import ua.com.codefire.dao.StubDao;
import ua.com.codefire.dao.entity.StubEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
}
