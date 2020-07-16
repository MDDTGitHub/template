package com.madd.template.service;

import com.madd.template.pojo.User;
import com.madd.template.utils.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface UserService {
    Result checkData(String context, Integer type);
    Result createUser(User user);
    Result userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);
    Result userToken(String token);
    Result userLogout(String token);

    User findByNamedParam(Map<String, Object> param);

    List<User> findByNamedParamList(User userInfo);
}
