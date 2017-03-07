package com.py.demo.tool.mongo;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/12/19.
 */
public class Mongo {

    @Resource
    private MongoTemplate mongoTemplate;

    public void insert(Object object) {
        mongoTemplate.insert(object);
    }

    public void findOne(Query query) {
//         imuser imuser1 = mongoTemplate.findOne(new Query(Criteria.where("id").is(70)),imuser.class);
        Object object = mongoTemplate.findOne(query, Object.class);
    }

}
