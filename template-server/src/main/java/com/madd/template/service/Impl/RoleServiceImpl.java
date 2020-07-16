package com.madd.template.service.Impl;

import com.madd.template.mapper.RoleMapper;
import com.madd.template.pojo.Role;
import com.madd.template.service.RoleService;
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
public class RoleServiceImpl implements RoleService {


   @Autowired
   RoleMapper roleMapper;

   @Override
   public Role findById(Serializable id) {
     return roleMapper.findById(id);
   }

    @Override
    public int insertBatch(List<Role> Roles) {
      return roleMapper.insertBatch(Roles);
    }

    @Override
    public int insert(Role Role) {
      return roleMapper.insert(Role);
    }

    @Override
    public int delete(Serializable id) {
      return roleMapper.delete(id);
    }

    @Override
    public int remove(Serializable id) {
      return roleMapper.remove(id);
    }

    @Override
    public QueryResult<Role> findPageInfo(BaseQuery baseQuery) {
        QueryResult<Role> result = new QueryResult<Role>();
        result.setQuery(baseQuery);
        Map
        <String, Object> params = result.getQuery().build();
        Integer amount = this.roleMapper.countPageInfo(params);
        result.setTotalRecord(amount);
        if (amount == 0) {
        return result;
        }
        List<Role> list = roleMapper.findPageInfo(params);
        if (!CollectionUtils.isEmpty(list)) {
        result.setResultList(list);

        }
        return result;
    }

    @Override
    public List<Role> findByUsername(String username) {
        Set<String> roleCodeSet = new HashSet<>();
        List<Role> RoleList = roleMapper.findByUserName(username);
        return RoleList;
    }

    @Override
    public Set<String> findByUserName(String username) {
        Set<String> roleCodeSet = new HashSet<>();
       List<Role> RoleList = roleMapper.findByUserName(username);
       if(RoleList==null || RoleList.size()==0)return roleCodeSet;
       for(Role role : RoleList){
           roleCodeSet.add(role.getRoleCode());
       }
        return roleCodeSet;
    }

}
