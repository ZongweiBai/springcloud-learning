package com.baymin.controller;

import com.baymin.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Baymin on 2018/5/1.
 */
@RequestMapping("user")
@RestController
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

//    /**
//     * @return
//     */
//    @GetMapping("list")
//    public List<User> users() {
//        try {
//            List<User> user = userService.findAll();
//            if (user != null && user.size() != 0) {
//                return user;
//            }
//            return null;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
