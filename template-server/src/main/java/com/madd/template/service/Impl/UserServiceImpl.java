package com.madd.template.service.Impl;

import com.madd.template.config.redis.single.JedisClientSingle;
import com.madd.template.mapper.UserMapper;
import com.madd.template.pojo.User;
import com.madd.template.service.UserService;
import com.madd.template.utils.CookieUtils;
import com.madd.template.utils.JsonUtils;
import com.madd.template.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JedisClientSingle jedisClientSingle;

    /**
     * type 代表 1 用户名 2，电话号码
     * @param context
     * @param type
     * @return
     */
    @Override
    public Result checkData(String context, Integer type) {
        Result result = new Result();
        User user = new User();
        if(type==1){
            user.setUsername(context);
        }else if(type==2){
            user.setPhone(context);
        }
        List<User> userList=userMapper.findByNamedParamList(user);
        if (userList == null || userList.size()==0) {
            result.setSuccess(true);
            return result;
        }
        if(type==1){
            result.setMessage("此用户名已经占用，请重新录入!");
        }else{
            result.setMessage("该手机号已经注册!");
        }
        result.setSuccess(false);
        return result;
    }

    @Override
    public Result createUser(User user) {
        user.setCreated(new Date());
        user.setUpdated(new Date());
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()).toUpperCase());
        userMapper.insert(user);
        return new Result(true);
    }

    @Override
    public Result userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        Result result = new Result();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        System.out.println("令牌:"+token);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            User user=(User) subject.getSession().getAttribute("currentUser");
            user.setPassword(null);
            String Token = UUID.randomUUID().toString().toUpperCase();
            jedisClientSingle.set("USER_SESSION_KEY"+ ":" + Token, JsonUtils.objectToJson(user));
            jedisClientSingle.expire("USER_SESSION_KEY"+ ":" + Token,900);
            CookieUtils.setCookie(request,response,"SSO_TOKEN",Token);
            result.addModel(Collections.singletonMap("token",Token));
            result.setSuccess(true);
            result.setMessage("登录成功！");
        }catch (AuthenticationException e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("用户名和密码不正确！");
            return result;
        }
        return result;
    }

    @Override
    public Result userToken(String token) {
        Result result = new Result();
        String json = jedisClientSingle.get("USER_SESSION_KEY"+ ":" + token);
        if(StringUtils.isBlank(json)){
            result.setSuccess(false);
            result.setMessage("此session已过期，请重新登录！");
            return result;
        }
        jedisClientSingle.expire("USER_SESSION_KEY"+ ":" + token,900);
        result.addModel(Collections.singletonMap("data",JsonUtils.jsonToPoJo(json,User.class)));
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result userLogout(String token) {
        Result result = new Result();
        Long delStr = jedisClientSingle.del("USER_SESSION_KEY"+ ":" + token);
        if(delStr == 0){
            result.setSuccess(false);
            result.setMessage("此session无效，无法登出！");
            return result;
        }
        result.setSuccess(true);
        result.setMessage("登出成功！");
        return result;
    }

    @Override
    public User findByNamedParam(Map<String, Object> param) {
        return userMapper.findByNamedParam(param);
    }

    @Override
    public List<User> findByNamedParamList(User userInfo) {
        return userMapper.findByNamedParamList(userInfo);
    }
}
