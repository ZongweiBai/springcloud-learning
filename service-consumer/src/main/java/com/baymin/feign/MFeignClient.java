package com.baymin.feign;

import com.baymin.hrstrix.HystrixFeignFallback;
import com.baymin.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "user-service-provider", fallback = HystrixFeignFallback.class, configuration = MFeignConfig.class)
public interface MFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
    User getUser(@PathVariable("id") String id);

    @RequestMapping(method = RequestMethod.GET, value = "/user/list")
    List<User> getUsers();
}