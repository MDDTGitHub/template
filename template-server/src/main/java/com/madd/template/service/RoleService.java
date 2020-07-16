package com.madd.template.service;

import com.madd.template.pojo.Role;
import com.madd.template.utils.BaseQuery;
import com.madd.template.utils.QueryResult;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 */
public interface RoleService {

    Role findById(Serializable id);

    int insertBatch(List<Role> testRoles);

    int insert(Role testRole);

    int delete(Serializable id);

    int remove(Serializable id);

    QueryResult<Role> findPageInfo(BaseQuery baseQuery);

    Set<String> findByUserName(String username);

    List<Role> findByUsername(String username);
}
