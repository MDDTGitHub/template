package com.madd.template.mapper;

import com.madd.template.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

        User findById(Serializable id);

        int insertBatch(List<User> userInfos);

        int insert(User userInfo);

        int update(User userInfo);

        int updateSelective(Map<String, Object> param);

        User findByNamedParam(Map<String, Object> param);

        List<User> findByNamedParamList(User userInfo);

        int delete(Serializable id);

        int remove(Serializable id);

        List<User> findPageInfo(Map<String, Object> params);

        int countPageInfo(Map<String, Object> parameter);
}