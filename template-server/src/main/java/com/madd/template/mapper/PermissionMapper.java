package com.madd.template.mapper;

import com.madd.template.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Mapper
public interface PermissionMapper {

        Permission findById(Serializable id);

        int insertBatch(List<Permission> userInfos);

        int insert(Permission userInfo);

        int update(Map<String, Object> param);

        int updateSelective(Map<String, Object> param);

        Permission findByNamedParam(Map<String, Object> param);

        List<Permission> findByNamedParamList(Map<String, Object> param);

        int delete(Serializable id);

        int remove(Serializable id);

        List<Permission> findPageInfo(Map<String, Object> params);

        int countPageInfo(Map<String, Object> parameter);

        List<Permission> findByUserName(String username);
}