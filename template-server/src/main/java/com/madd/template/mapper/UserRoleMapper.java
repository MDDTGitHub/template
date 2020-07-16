package com.madd.template.mapper;

import com.madd.template.pojo.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserRoleMapper {

       UserRole findById(Serializable id);

        int insertBatch(List<UserRole> userInfos);

        int insert(UserRole userInfo);

        int update(Map<String, Object> param);

        int updateSelective(Map<String, Object> param);

        UserRole findByNamedParam(Map<String, Object> param);

        List<UserRole> findByNamedParamList(Map<String, Object> param);

        int delete(Serializable id);

        int remove(Serializable id);

        List<UserRole> findPageInfo(Map<String, Object> params);

        int countPageInfo(Map<String, Object> parameter);
}