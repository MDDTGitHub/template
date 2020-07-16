package com.madd.template.mapper;

import com.madd.template.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Mapper
public interface RoleMapper {

        Role findById(Serializable id);

        int insertBatch(List<Role> userInfos);

        int insert(Role userInfo);

        int update(Map<String, Object> param);

        int updateSelective(Map<String, Object> param);

        Role findByNamedParam(Map<String, Object> param);

        List<Role> findByNamedParamList(Map<String, Object> param);

        int delete(Serializable id);

        int remove(Serializable id);

        List<Role> findPageInfo(Map<String, Object> params);

        int countPageInfo(Map<String, Object> parameter);

        List<Role> findByUserName(String username);
}