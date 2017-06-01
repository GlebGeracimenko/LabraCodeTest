package ua.com.codefire.dao;

import ua.com.codefire.dao.entity.StubEntity;

/**
 * Created by gleb on 31.05.17.
 */
public interface StubDao {

    StubEntity saveStub(StubEntity stubEntity);
    StubEntity saveStub2(String stubValue, Integer firstValue);

}
