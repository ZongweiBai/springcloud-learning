package com.github.baymin.springcloud.userserver.model;

import lombok.Data;

/**
 * Created by Baymin on 2018/5/1.
 */
@Data
public class User {

    private String id;

    private String username;

    /**
     * 用户密码
     */
    private String password = "";

    /**
     * 用户昵称
     */
    private String nickName = "";

    public User(String id, String username, String password, String nickName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickName = nickName;
    }

}
