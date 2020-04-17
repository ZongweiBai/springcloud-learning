package com.github.baymin.springcloud.userserver.controller;

import com.github.baymin.springcloud.userserver.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Baymin on 2018/5/1.
 */
@RequestMapping("user")
@RestController
@RefreshScope
public class UserController {

//    @Autowired
//    private UserService userService;

    /**
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public User getuser(@PathVariable String id) {
        User user = null;
        try {
            System.out.println(id);
            user = new User(id, "username" + id, "password" + id, "nickName" + id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Value("${nacos.config}")
    private String config;

    @RequestMapping("/getValue")
    public String getValue() {
        return config;
    }
}
