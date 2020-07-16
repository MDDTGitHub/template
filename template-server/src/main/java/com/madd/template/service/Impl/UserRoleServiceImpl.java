package com.madd.template.service.Impl;

import com.madd.template.mapper.RoleMapper;
import com.madd.template.mapper.UserRoleMapper;
import com.madd.template.pojo.UserRole;
import com.madd.template.service.UserRoleService;
import com.madd.template.utils.BaseQuery;
import com.madd.template.utils.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {


   @Autowired
   UserRoleMapper userRoleMapper;
   @Autowired
   RoleMapper roleMapper;

   @Override
   public UserRole findById(Serializable id) {
     return userRoleMapper.findById(id);
   }

    @Override
    public int insertBatch(List<UserRole> UserRoles) {
      return userRoleMapper.insertBatch(UserRoles);
    }

    @Override
    public int insert(UserRole UserRole) {
      return userRoleMapper.insert(UserRole);
    }

    @Override
    public int delete(Serializable id) {
      return userRoleMapper.delete(id);
    }

    @Override
    public int remove(Serializable id) {
      return userRoleMapper.remove(id);
    }

    @Override
    public QueryResult<UserRole> findPageInfo(BaseQuery baseQuery) {
        QueryResult<UserRole> result = new QueryResult<UserRole>();
        result.setQuery(baseQuery);
        Map
        <String, Object> params = result.getQuery().build();
        Integer amount = this.userRoleMapper.countPageInfo(params);
        result.setTotalRecord(amount);
        if (amount == 0) {
        return result;
        }
        List<UserRole> list = userRoleMapper.findPageInfo(params);
        if (!CollectionUtils.isEmpty(list)) {
        result.setResultList(list);

        }
        return result;
    }
}
