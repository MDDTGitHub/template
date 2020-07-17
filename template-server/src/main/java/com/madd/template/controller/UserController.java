package com.madd.template.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.madd.template.pojo.User;
import com.madd.template.service.UserService;
import com.madd.template.utils.ImageUtil;
import com.madd.template.utils.Result;
import com.madd.template.utils.ResultHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 校验用户信息，返回true 代表 无用户 可以注册
     * @param param
     * @param type
     * @return
     */
    @RequestMapping(value = "user/check/{param}/{type}",method = RequestMethod.GET)
    public JSONObject checkData(@PathVariable("param") String param,
                                @PathVariable("type") Integer type){
        Result result = userService.checkData(param,type);
        System.out.println("check---"+result);
        return ResultHelper.renderAsJson(result);
    }

    /**
     * 注册用户信息
     * @param user
     * @return
     */
    @RequestMapping(value = "user/register",method = RequestMethod.POST)
    public JSONObject createUser(User user){
        Result result = userService.createUser(user);
        System.out.println("register---"+result);
        return ResultHelper.renderAsJson(result);
    }
    /**
     * 显示登录验证码
     */
    @RequestMapping(value = "user/captcha",method = RequestMethod.GET)
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setContentType("image/jpeg");
        //禁止图像缓存
        response.setHeader("Pragma","no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        HttpSession session = request.getSession();
        ImageUtil imageUtil = new ImageUtil(120, 40, 4,30);
        session.setAttribute("code", imageUtil.getCode());
        Date now = new Date();
        session.setAttribute("codeTime",now.getTime());
        imageUtil.write(response.getOutputStream());
        System.out.println("验证码生成时间-------------"+now.getTime());
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "user/login",method = RequestMethod.POST)
    public JSONObject userLogin(HttpSession session,
                                @RequestParam("code") String code,
                                @RequestParam("username") String username,
                                @RequestParam("password") String password,
//                                String code,String username,String password,
                                HttpServletRequest request, HttpServletResponse response){
        Result result = new Result();
        // 验证码有效时长为1分钟
        Date now = new Date();
        Long codeTime = Long.valueOf(session.getAttribute("codeTime") + "");
        System.out.println("验证码验证时间-------------"+now.getTime());
        if((now.getTime() - codeTime) / 1000 / 60 > 1) {
            result.setMessage("验证码已失效，请重新输入！");
            result.setSuccess(false);
        }else {
            Object obj = session.getAttribute("code");
            String captcha = obj.toString();
            if (code.equalsIgnoreCase(captcha)) {
                result = userService.userLogin(username, password,request,response);
                System.out.println("login---" + result.toString());
            } else {
                result.setMessage("验证码错误");
                result.setSuccess(false);
            }
        }
        return ResultHelper.renderAsJson(result);
    }

    /**
     * 校验是否已登录，获取用户信息
     * @param token
     * @return
     */
    @RequestMapping(value = "user/info",method = RequestMethod.GET)
    public JSONObject userToken(@RequestParam("token") String token){
        Result result = userService.userToken(token);
        return ResultHelper.renderAsJson(result);
    }

    /**
     * 校验是否已登录，获取用户信息
     * @param token
     * @return
     */
    @RequestMapping(value = "user/token/{token}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object userToken(@PathVariable("token") String token, String callback){
        Result result = userService.userToken(token);
        System.out.println("token---"+result);
        if(StringUtils.isBlank(callback)){
            return ResultHelper.renderAsJson(result);
        }
        JSONPObject jp = new JSONPObject(callback);
        jp.addParameter(ResultHelper.renderAsJson(result));
        return jp.toJSONString();
    }

    /**
     * 退出登录，返回主页面，或者登录页面
     * @param token
     * @throws IOException
     */
    @RequestMapping(value = "user/logout",method = RequestMethod.GET)
    public JSONObject userLogout(@RequestParam("token") String token) {
        Result result = userService.userLogout(token);
        return ResultHelper.renderAsJson(result);
    }
}
