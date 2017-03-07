
package com.py.demo.tool.mongo.impl;

import com.py.demo.model.User;
import com.py.demo.tool.mongo.AbstractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Mongo接口实现类
 */
public class UserRepository implements AbstractRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

	/* (non-Javadoc)
     * @see com.mongo.com.py.demo.dao.AbstractRepository#findAll()
	 */

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        return mongoTemplate.find(new Query(), User.class);

    }

    /**
     * 查询并修改
     *
     * @param id
     */
    @Override
    public void findAndModify(Long id, String key, String value) {
        // TODO Auto-generated method stub
//		new Query(Criteria.where("id").is(id)), new Update().inc("age", 3)

        mongoTemplate.updateFirst(new Query(Criteria.where("id").is(id)), new Update().set(key, value), User.class);
    }

    /**
     * 正则查询
     *
     * @param regex 包含该字符串
     * @return
     */
    @Override
    public List<User> findByRegex(String regex) {
        // TODO Auto-generated method stub
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Criteria criteria = new Criteria("username").regex(pattern.toString());
        return mongoTemplate.find(new Query(criteria), User.class);

    }

    /**
     * 查询一条
     *
     * @param id
     * @return
     */
    @Override
    public User findOne(Long id) {
        return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), User.class);

    }

    /**
     * 新增
     *
     * @param
     */
    @Override
    public void insert(Object object) {
        mongoTemplate.insert(object);
    }

    /**
     * 删除所有
     */
    @Override
    public void removeAll() {
        List<User> list = this.findAll();
        if (list != null) {
            for (User user : list) {
                mongoTemplate.remove(user);
            }
        }

    }

    /**
     * 删除一条
     *
     * @param id
     */
    @Override
    public void removeOne(Long id) {

        Criteria criteria = Criteria.where("id").in(id);
        if (criteria != null) {
            Query query = new Query(criteria);
            if (query != null && mongoTemplate.findOne(query, User.class) != null)
                mongoTemplate.remove(mongoTemplate.findOne(query, User.class));
        }

    }

    /**
     * @return the mongoTemplate
     */
//	public MongoTemplate mongoTemplate() {
//		return mongoTemplate;
//	}

    /**
     * @param mongoTemplate the mongoTemplate to set
     */
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

}
