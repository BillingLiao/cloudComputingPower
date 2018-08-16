package com.ant.admin.service.impl;

import com.ant.admin.common.utils.Constant;
import com.ant.admin.common.utils.MapUtils;
import com.ant.admin.dao.MenuDao;
import com.ant.admin.entity.Menu;
import com.ant.admin.service.MenuService;
import com.ant.admin.service.RoleMenuService;
import com.ant.admin.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Billing
 * @date 2018/8/16 16:25
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements  MenuService{

    @Autowired
    private UserService userService;
    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public List<Menu> queryListParentId(Integer parentId, List<Integer> menuIdList) {
        List<Menu> menuList = queryListParentId(parentId);
        if(menuIdList == null){
            return menuList;
        }

        List<Menu> userMenuList = new ArrayList<>();
        for(Menu menu : menuList){
            if(menuIdList.contains(menu.getMenuId())){
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    public List<Menu> queryListParentId(Integer parentId) {
        return baseMapper.queryListParentId(parentId);
    }

    @Override
    public List<Menu> queryNotButtonList() {
        return baseMapper.queryNotButtonList();
    }

    @Override
    public List<Menu> getUserMenuList(Integer userId) {
        //系统管理员，拥有最高权限
        if(userId == Constant.SUPER_ADMIN){
            return getAllMenuList(null);
        }

        //用户菜单列表
        List<Integer> menuIdList = userService.queryAllMenuId(userId);
        return getAllMenuList(menuIdList);
    }

    @Override
    public void delete(Integer menuId) {
        //删除菜单
        this.deleteById(menuId);
        //删除菜单与角色关联
        roleMenuService.deleteByMap(new MapUtils().put("menu_id", menuId));
    }



    /**
     * 获取所有菜单列表
     */
    private List<Menu> getAllMenuList(List<Integer> menuIdList){
        //查询根菜单列表
        List<Menu> menuList = queryListParentId(0, menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);

        return menuList;
    }

    /**
     * 递归
     */
    private List<Menu> getMenuTreeList(List<Menu> menuList, List<Integer> menuIdList){
        List<Menu> subMenuList = new ArrayList<Menu>();

        for(Menu entity : menuList){
            //目录
            if(entity.getType() == Constant.MenuType.CATALOG.getValue()){
                entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }

        return subMenuList;
    }
}
