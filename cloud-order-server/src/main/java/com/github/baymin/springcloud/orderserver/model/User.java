package com.github.baymin.springcloud.orderserver.model;

import lombok.Data;

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

}
