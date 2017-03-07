package com.py.demo.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/20.
 */
@Data
public class Admin implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id//标记为主键
    private ObjectId _id;//mongo默认主键
    @Field//标记为字段
    private Long id;
    private String username;
    private String password;
    private String email;
}
