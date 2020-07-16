package com.madd.template.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.IOException;
import java.util.Set;

/**
* JSON的驼峰和下划线互转帮助类
*
*
*/
public class JsonUtils {


	public static <T> Object toUnderlineJSONString(Class<T> clazz,Object object,String[] array) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
			@Override
			public void serialize(Object arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException, JsonProcessingException {
				arg1.writeString("");
			}
		});


		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
		simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
		mapper.registerModule(simpleModule);

		JacksonJsonFilter jacksonFilter = new JacksonJsonFilter();
		jacksonFilter.include(clazz,array);
		mapper.setFilterProvider(jacksonFilter);
		mapper.addMixIn(clazz, jacksonFilter.getClass());
		String json = mapper.writeValueAsString(object);
		return convert(json);
	}


	public static <T> Object toUnderlineJSONFilterString(Class<T> clazz,Object object,String[] array) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
			@Override
			public void serialize(Object arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException, JsonProcessingException {
				arg1.writeString("");
			}
		});

		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
		simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
		mapper.registerModule(simpleModule);

		JacksonJsonFilter jacksonFilter = new JacksonJsonFilter();
		jacksonFilter.filter(clazz,array);
		mapper.setFilterProvider(jacksonFilter);
		mapper.addMixIn(clazz, jacksonFilter.getClass());
		String json = mapper.writeValueAsString(object);
		return convert(json);
	}


	private   static void convert(Object json) {
		if (json instanceof JSONArray) {
			JSONArray arr = (JSONArray) json;
			for (Object obj : arr) {
				convert(obj);
			}
		} else if (json instanceof JSONObject) {
			JSONObject jo = (JSONObject) json;
			Set<String> keys = jo.keySet();
			String[] array = keys.toArray(new String[keys.size()]);
			for (String key : array) {
				Object value = jo.get(key);
				jo.remove(key);
				jo.put(camelToUnderline(key), value);
				convert(value);
			}
		}
	}


	private   static Object convert(String json) {
		Object obj = JSON.parse(json);
		convert(obj);
		return obj;
	}


	private static final char UNDERLINE='_';
	private static String camelToUnderline(String param){
		if (param==null||"".equals(param.trim())){
			return "";
		}
		int len=param.length();
		StringBuilder sb=new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c=param.charAt(i);
			if (Character.isUpperCase(c)){
				sb.append(UNDERLINE);
				sb.append(Character.toLowerCase(c));
			}else{
				sb.append(c);
			}
		}
		return sb.toString();
	}

	// 定义jackson对象
	private static final ObjectMapper MAPPER = new ObjectMapper();

	/**
	 * 将对象转换成json字符串。
	 */
	public static String objectToJson(Object data) {
		try {
			String string = MAPPER.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 将json结果集转化为对象
	 */
	public static <T> T jsonToPoJo(String jsonData, Class<T> beanType) {
		try {
			T t = MAPPER.readValue(jsonData, beanType);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 将json数据转换成pojo对象list
	 */
	public  static <T> T jsonToList(String jsonData, TypeReference<T> typeReference) {
		try {
			return MAPPER.readValue(jsonData, typeReference);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}