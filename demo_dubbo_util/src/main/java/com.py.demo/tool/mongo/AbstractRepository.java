
package com.py.demo.tool.mongo;

import com.py.demo.model.User;

import java.util.List;


/**
 * Mongo接口
 */
public interface AbstractRepository {

    public void insert(Object object);

    public User findOne(Long id);

    public List<User> findAll();

    public List<User> findByRegex(String regex);

    public void removeOne(Long id);

    public void removeAll();

    public void findAndModify(Long id, String key, String value);


}
