package com.madd.template.service;

import com.madd.template.pojo.UserRole;
import com.madd.template.utils.BaseQuery;
import com.madd.template.utils.QueryResult;

import java.io.Serializable;
import java.util.List;

/**
 */
public interface UserRoleService {

    UserRole findById(Serializable id);

    int insertBatch(List<UserRole> testUserRoles);

    int insert(UserRole testUserRole);

    int delete(Serializable id);

    int remove(Serializable id);

    QueryResult<UserRole> findPageInfo(BaseQuery baseQuery);

}
