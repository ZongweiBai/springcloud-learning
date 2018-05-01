package com.baymin.model;

/**
 * Created by Baymin on 2018/5/1.
 */
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

}
