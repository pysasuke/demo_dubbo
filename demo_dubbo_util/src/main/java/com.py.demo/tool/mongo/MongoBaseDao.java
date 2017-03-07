/**
 * AbstractRepository.java
 * ��Ȩ����(C) 2012
 * ����:cuiran 2012-12-12 11:40:40
 */
package com.py.demo.tool.mongo;

import com.py.demo.model.User;

import java.util.List;


/**
 * Mongo接口公共类
 */
public interface MongoBaseDao<T> {

    public void insert(T t);

    public User findOne(Object id);

    public List<T> findAll();

    public List<T> findByRegex(String regex);

    public void removeOne(Object id);

    public void removeAll();

    public void findAndModify(Object id);


}
