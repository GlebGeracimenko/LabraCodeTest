package ua.com.codefire.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.codefire.dao.StubDao;
import ua.com.codefire.dao.entity.StubEntity;

import javax.persistence.Query;
import java.util.Map;

/**
 * Created by gleb on 31.05.17.
 */
@Repository
public class StubDaoImpl implements StubDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    private final String nativeInsert = "insert into stub2 (stub_value, first_value, second_value) values (:stubValue, :firstValue, LENGTH(stub_value)+first_value)";

    @Override
    @Transactional
    public StubEntity saveStub(StubEntity stubEntity) {
        hibernateTemplate.save(stubEntity);
        return stubEntity;
    }

    @Override
    @Transactional
    public void saveStubByNativeQuery(String stubValue, Integer firstValue) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createNativeQuery(nativeInsert);
        query.setParameter("stubValue", stubValue);
        query.setParameter("firstValue", firstValue);
        query.executeUpdate();
    }
}
