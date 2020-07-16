package com.madd.template.service.Impl;

import com.madd.template.mapper.PermissionMapper;
import com.madd.template.pojo.Permission;
import com.madd.template.service.PermissionService;
import com.madd.template.utils.BaseQuery;
import com.madd.template.utils.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 */
@Service
public class PermissionServiceImpl implements PermissionService {


   @Autowired
   PermissionMapper permissionMapper;

   @Override
   public Permission findById(Serializable id) {
     return permissionMapper.findById(id);
   }

    @Override
    public int insertBatch(List<Permission> Permissions) {
      return permissionMapper.insertBatch(Permissions);
    }

    @Override
    public int insert(Permission Permission) {
      return permissionMapper.insert(Permission);
    }

    @Override
    public int delete(Serializable id) {
      return permissionMapper.delete(id);
    }

    @Override
    public int remove(Serializable id) {
      return permissionMapper.remove(id);
    }

    @Override
    public QueryResult<Permission> findPageInfo(BaseQuery baseQuery) {
        QueryResult<Permission> result = new QueryResult<Permission>();
        result.setQuery(baseQuery);
        Map
        <String, Object> params = result.getQuery().build();
        Integer amount = this.permissionMapper.countPageInfo(params);
        result.setTotalRecord(amount);
        if (amount == 0) {
        return result;
        }
        List<Permission> list = permissionMapper.findPageInfo(params);
        if (!CollectionUtils.isEmpty(list)) {
        result.setResultList(list);

        }
        return result;
    }

    @Override
    public Set<String> findByUserName(String username) {
        Set<String> permissionCodeSet =new HashSet<>();
        List<Permission>permissionList = permissionMapper.findByUserName(username);
        if(permissionList==null || permissionList.size()==0)return permissionCodeSet;
        for(Permission permission : permissionList){
            permissionCodeSet.add(permission.getPermissionCode());
        }
        return permissionCodeSet;
    }
}
