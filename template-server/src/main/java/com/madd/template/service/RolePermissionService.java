package com.madd.template.service;


import com.madd.template.pojo.RolePermission;
import com.madd.template.utils.BaseQuery;
import com.madd.template.utils.QueryResult;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 */
public interface RolePermissionService {

    RolePermission findById(Serializable id);

    int insertBatch(List<RolePermission> testRolePermissions);

    int insert(RolePermission testRolePermission);

    int delete(Serializable id);

    int remove(Serializable id);

    QueryResult<RolePermission> findPageInfo(BaseQuery baseQuery);

    Set<String> findByRoleId(Set<String> testRoleSet);
}
