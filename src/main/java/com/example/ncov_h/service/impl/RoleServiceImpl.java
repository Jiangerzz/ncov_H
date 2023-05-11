package com.example.ncov_h.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ncov_h.common.Result;
import com.example.ncov_h.controller.request.RoleRequest;
import com.example.ncov_h.entity.Menu;
import com.example.ncov_h.entity.Role;
import com.example.ncov_h.entity.RoleMenu;
import com.example.ncov_h.exception.ServiceException;
import com.example.ncov_h.mapper.RoleMapper;
import com.example.ncov_h.mapper.RoleMenuMapper;
import com.example.ncov_h.service.MenuService;
import com.example.ncov_h.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    
    @Autowired
    RoleMapper roleMapper;
    
    @Autowired
    RoleMenuMapper roleMenuMapper;

    @Resource
    private MenuService menuService;

    @Override
    public Result getRole(RoleRequest request) {
        try{
            PageHelper.startPage(request.getPageNum(),request.getPageSize());
            List<Role> roleList = roleMapper.getRoleList(request.getName());
            PageInfo<Role> page = new PageInfo<>(roleList);
            return Result.success(page);
        }catch (Exception e){
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Integer saveRole(Role role) {
        try{
            return roleMapper.save(role);
        }catch (Exception e){
            throw new ServiceException("添加失败！");
        }
    }

    @Override
    public Integer deleteRole(String id) {
        try{
            return roleMapper.delete(id);
        }catch (Exception e){
            throw new ServiceException("删除失败！");
        }
    }

    @Override
    public Integer updateRole(Role role) {
        try {
            return roleMapper.update(role);
        }catch (Exception e){
            throw new ServiceException("更新失败");
        }
    }

    @Transactional
    @Override
    public void setRoleMenu(Integer roleId, List<Integer> menuIds) {
        // 先删除当前角色id所有的绑定关系
        roleMenuMapper.deleteByRoleId(roleId);

        // 再把传过来的菜单id数组绑定到当前的这个角色id上去
        List<Integer> menuIdsCopy = CollUtil.newArrayList(menuIds);
        for (Integer menuId : menuIds) {
            Menu menu = menuService.getById(menuId);
            if (menu.getPid() != null && !menuIdsCopy.contains(menu.getPid())) { // 二级菜单 并且传过来的menuId数组里面没有它的父级id
                // 补上这个父级id
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(menu.getPid());
                roleMenuMapper.insert(roleMenu);
                menuIdsCopy.add(menu.getPid());
            }
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenuMapper.insert(roleMenu);
        }
    }

    @Override
    public List<Integer> getRoleMenu(String roleId) {

        return roleMenuMapper.selectByRoleId(roleId);
    }
}
