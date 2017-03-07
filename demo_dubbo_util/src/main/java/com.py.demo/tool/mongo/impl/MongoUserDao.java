
package com.py.demo.tool.mongo.impl;

import com.py.demo.model.User;
import com.py.demo.tool.mongo.MongoBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Mongo接口User实现类
 */
public class MongoUserDao implements MongoBaseDao<User> {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void insert(User user) {
        mongoTemplate.insert(user);
    }

    @Override
    public User findOne(Object id) {
        return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), User.class);
    }

    @Override
    public List<User> findAll() {
        return mongoTemplate.find(new Query(), User.class);

    }

    @Override
    public List<User> findByRegex(String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Criteria criteria = new Criteria("username").regex(pattern.toString());
        return mongoTemplate.find(new Query(criteria), User.class);
    }

    @Override
    public void removeOne(Object id) {
        Criteria criteria = Criteria.where("id").in(id);
        if (criteria != null) {
            Query query = new Query(criteria);
            if (query != null && mongoTemplate.findOne(query, User.class) != null)
                mongoTemplate.remove(mongoTemplate.findOne(query, User.class));
        }
    }

    @Override
    public void removeAll() {
        List<User> list = this.findAll();
        if (list != null) {
            for (User user : list) {
                mongoTemplate.remove(user);
            }
        }
    }

    @Override
    public void findAndModify(Object id) {
        mongoTemplate.updateFirst(new Query(Criteria.where("id").is(id)), new Update().set("username", "test"), User.class);

    }
}
