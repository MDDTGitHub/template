package com.madd.template.utils;


import com.alibaba.fastjson.JSONObject;

import java.util.Map;


/**
 */
public class ResultHelper {

    public static JSONObject renderAsJson(Result result) {
        JSONObject source = new JSONObject();
        source.put("code",result.getResultCode().code);
        source.put("msg",result.getResultCode().getMessage());
        source.put("success",result.isSuccess());
        if(result.getMessage() != null) {
            source.put("msg",result.getMessage());
        }
        Map<String,Object> model  = result.getAll();
        if(model.containsKey("data")){   /*删除多层data*/
            source.putAll(model);
        }else {
            source.put("data",model);
        }
        return source;
    }





    public static JSONObject renderAsJson(ResultCode resultCode) {
        return renderAsJson(resultCode,null);
    }

    public static JSONObject renderAsJson(ResultCode resultCode,String message) {
        JSONObject source = new JSONObject();
        source.put("code",resultCode.code);
        source.put("msg",message);
        return source ;
    }


}
