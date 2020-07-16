package com.madd.template.service.Impl;

import com.madd.template.mapper.RolePermissionMapper;
import com.madd.template.pojo.RolePermission;
import com.madd.template.service.RolePermissionService;
import com.madd.template.utils.BaseQuery;
import com.madd.template.utils.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {


   @Autowired
   RolePermissionMapper rolePermissionMapper;

   @Override
   public RolePermission findById(Serializable id) {
     return rolePermissionMapper.findById(id);
   }

    @Override
    public int insertBatch(List<RolePermission> RolePermissions) {
      return rolePermissionMapper.insertBatch(RolePermissions);
    }

    @Override
    public int insert(RolePermission RolePermission) {
      return rolePermissionMapper.insert(RolePermission);
    }

    @Override
    public int delete(Serializable id) {
      return rolePermissionMapper.delete(id);
    }

    @Override
    public int remove(Serializable id) {
      return rolePermissionMapper.remove(id);
    }

    @Override
    public QueryResult<RolePermission> findPageInfo(BaseQuery baseQuery) {
        QueryResult<RolePermission> result = new QueryResult<RolePermission>();
        result.setQuery(baseQuery);
        Map
        <String, Object> params = result.getQuery().build();
        Integer amount = this.rolePermissionMapper.countPageInfo(params);
        result.setTotalRecord(amount);
        if (amount == 0) {
        return result;
        }
        List<RolePermission> list = rolePermissionMapper.findPageInfo(params);
        if (!CollectionUtils.isEmpty(list)) {
        result.setResultList(list);

        }
        return result;
    }

    @Override
    public Set<String> findByRoleId(Set<String> testRoleSet) {
       for(String roleCode:testRoleSet){

       }
        return null;
    }
}
