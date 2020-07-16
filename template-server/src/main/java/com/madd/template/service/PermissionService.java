package com.madd.template.service;


import com.madd.template.pojo.Permission;
import com.madd.template.utils.BaseQuery;
import com.madd.template.utils.QueryResult;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 */
public interface PermissionService {

    Permission findById(Serializable id);

    int insertBatch(List<Permission> testPermissions);

    int insert(Permission testPermission);

    int delete(Serializable id);

    int remove(Serializable id);

    QueryResult<Permission> findPageInfo(BaseQuery baseQuery);

    Set<String> findByUserName(String username);
}
