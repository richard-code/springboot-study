package com.study.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.study.Constants;
import com.study.entity.cluster.City;
import com.study.entity.master.User;
import com.study.service.ICityService;
import com.study.service.IDemoService;
import com.study.service.IUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/17 0017.
 */
@RestController
@RequestMapping(value = "/user")
public class DemoController {
    private static Map<Long, User> users = Maps.newHashMap();

    @Autowired
    private IUserService userService;
    @Autowired
    private ICityService cityService;
    @Autowired
    private IDemoService demoService;

    @ApiOperation(value = "查询所有用户")
    @GetMapping(value =  "getAll")
    public List<User> getAll(){
        return Lists.newArrayList(users.values());
    }

    @ApiOperation(value = "增加一个用户")
    @ApiImplicitParam(name = "user", value = "用户信息实体", required = true, dataType = "User")
    @PostMapping(value = "add")
    public String addUser(User user){
        users.put(user.getId(), user);
        return "success";
    }

    @ApiOperation(value = "增加一个用户和一个城市")
    @ApiImplicitParam(name = "user", value = "用户信息实体", required = true, dataType = "User")
    @PostMapping(value = "addUserAndCity")
    public String addUserAndCity(String userName, String cityName){
        demoService.addUserAndCity(userName, cityName);
        return "success";
    }

    @GetMapping(value = "findUserByName")
    public String findUserByName(String userName){
        User user = userService.findUserByName(userName);
        return Constants.GSON.toJson(user);
    }

    @GetMapping(value = "findCityByName")
    public String findCityByName(String cityName){
        City city = cityService.findCityByName(cityName);
        return Constants.GSON.toJson(city);
    }

}
