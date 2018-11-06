package com.kaifuu.demo.mapper;

import com.kaifuu.demo.model.Role;
import com.kaifuu.demo.util.BaseMapper;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 根据用户id查询角色集合
     *
     * @param userId 用户id
     * @return set
     */
    Set<String> findRoleByUserId(String userId);

    /**
     * 根据role参数查询角色列表
     *
     * @param role role
     * @return list
     */
    List<Role> selectRoles(Role role);

    /**
     * 根据参数批量更新状态
     *
     * @param params
     * @return int
     */
    int updateStatusBatch(Map<String, Object> params);

    /**
     * 根据roleId更新角色信息
     *
     * @param params
     * @return int
     */
    int updateByRoleId(Map<String, Object> params);

}