package com.madd.template.mapper;

import com.madd.template.pojo.RolePermission;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Mapper
public interface RolePermissionMapper {

       RolePermission findById(Serializable id);

        int insertBatch(List<RolePermission> userInfos);

        int insert(RolePermission userInfo);

        int update(Map<String, Object> param);

        int updateSelective(Map<String, Object> param);

        RolePermission findByNamedParam(Map<String, Object> param);

        List<RolePermission> findByNamedParamList(Map<String, Object> param);

        int delete(Serializable id);

        int remove(Serializable id);

        List<RolePermission> findPageInfo(Map<String, Object> params);

        int countPageInfo(Map<String, Object> parameter);
}