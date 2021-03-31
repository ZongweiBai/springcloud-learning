package com.github.baymin.springcloud.orderserver.feign;

import com.github.baymin.springcloud.orderserver.hrstrix.HystrixFeignFallback;
import com.github.baymin.springcloud.orderserver.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "cloud-user-server", fallback = HystrixFeignFallback.class, configuration = MFeignConfig.class)
public interface MFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
    User getUser(@PathVariable("id") String id);

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    List<User> getUsers();
}