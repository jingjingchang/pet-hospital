package com.zcmzjp.wx.controller;


import com.zcmzjp.wx.dto.MenuDto;
import com.zcmzjp.wx.entity.Menu;
import com.zcmzjp.wx.entity.Message;
import com.zcmzjp.wx.entity.RoleMenu;
import com.zcmzjp.wx.entity.User;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.MenuService;
import com.zcmzjp.wx.service.RoleMenuService;
import com.zcmzjp.wx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Created by Chris on 2017-08-03.
 */
@Controller
@RequestMapping("/admin/sys/menu")
public class MenuController extends BaseController<Menu> {
    @Autowired
    MenuService menuService;

    @Autowired
    RoleMenuService roleMenuService;

    @Autowired
    UserService userService;

    @Override
    public BaseService<Menu> getService() {
        return menuService;
    }

    @Override
    public String getViewPrefix() {
        return "menu";
    }

    @ResponseBody
    @RequestMapping("/getAllMenus")
    public List<MenuDto> getAllMenus(){
        List<MenuDto> menus = menuService.getAllMenus();
        List<MenuDto> menuList = new ArrayList<>();

        // 先找到所有的一级菜单
        for(int i=0;i<menus.size();i++){
            // 一级菜单没有parentId
            if(menus.get(i).getParentId()==null){
                menuList.add(menus.get(i));
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for(MenuDto menu : menuList){
            menu.setChildren(getChild(menu.getId(),menus));
        }

        Map<String ,Object> jsonMap = new HashMap();
        jsonMap.put("menu",menuList);

        return menuList;
    }

    @ResponseBody
    @RequestMapping("/getMenusByUserId")
    public List<MenuDto> getMenusByUserId(Integer id, HttpServletRequest request){
        User user;
        if(id==null){
            user = (User)request.getSession().getAttribute("user");
        }else{
            user = userService.getById(id);
        }
        List<MenuDto> allMenus = menuService.getAllMenus();
        List<MenuDto> userMenus = menuService.getMenusByUserId(user.getId());
        List<MenuDto> menuList = new ArrayList<>();
       /* for(int i=0;i<userMenus.size();i++){
            for(int j=0;j<userMenus.size();j++){
                if(userMenus.get(i).getParentId()==userMenus.get(j).getParentId()){
                    Predicate<MenuDto> predicate = (s) -> s.getId().equals(userMenus.get(j).getParentId());
                    userMenus.removeIf(predicate);
                }
            }
        }*/

        for(MenuDto md:userMenus){
            Predicate<MenuDto> predicate = (s) -> s.getId().equals(md.getId());
            allMenus.removeIf(predicate);
        }

        //将所有已经授权的菜单和通过父级ID找到的菜单放入此list备用
        List<MenuDto> list = new ArrayList<>();
        for(MenuDto md:userMenus){
            list.add(md);
            if(md.getParentId()!=null){
                for(MenuDto amd:allMenus){
                    boolean flag = false;
                    //如果找到父级菜单，则添加一个父级菜单
                    if (amd.getId()==md.getParentId()){
                        //循环list查询是否已经添加此父级菜单
                        for(MenuDto mdList:list){
                            if(mdList.getId().equals(amd.getId())){
                                flag = true;
                            }
                        }
                        if (!flag){
                            list.add(amd);
                        }
                    }
                }
            }
        }
        // 先找到所有的一级菜单
        for(int i=0;i<list.size();i++){
            // 一级菜单没有parentId
            if(list.get(i).getParentId()==null){
                MenuDto md = list.get(i);
                menuList.add(md);
            }
        }
        for(MenuDto md:menuList){
            md.setChildren(getChild(md.getId(),list));
        }
        return menuList;
    }


    @ResponseBody
    @RequestMapping("/getAllMenusByRoleId")
    public List<MenuDto> getAllMenusByRoleId(Integer id){
        List<MenuDto> menus = menuService.getAllMenusByRoleId(id);
        List<MenuDto> menuList = new ArrayList<>();

        // 先找到所有的一级菜单
        for(int i=0;i<menus.size();i++){
            // 一级菜单没有parentId
            if(menus.get(i).getParentId()==null){
                MenuDto md = menus.get(i);
                if(md.getrId()!=null){
                    md.setOpen(true);
                    md.setChecked(true);
                }
                menuList.add(md);
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for(MenuDto menu : menuList){
            menu.setChildren(getChild(menu.getId(),menus));
        }

        Map<String ,Object> jsonMap = new HashMap();
        jsonMap.put("menu",menuList);

        return menuList;
    }

    @RequestMapping("/addRoleMenus")
    @ResponseBody
    public Message addRoleMenus(Integer id, String ids){
        boolean flag = false;
        flag = roleMenuService.delRoleMenusByRoleId(id);
        if(ids!=null&&ids!=""){
            String rId[] = ids.split(",");
            for (int i = 0;i<rId.length;i++){
                RoleMenu rm = new RoleMenu();
                rm.setMenuId(Integer.valueOf(rId[i]));
                rm.setRoleId(id);
                rm.setStatus(1);
                flag = roleMenuService.add(rm);
            }
        }
        return getMessage(flag);
    }

    private List<MenuDto> getChild(Integer id,List<MenuDto> menuList){
        // 子菜单
        List<MenuDto> childList = new ArrayList<>();
        //遍历所有节点，将父级菜单ID与传过来的ID做比较
        for(MenuDto menu:menuList){
            if(menu.getrId()!=null){
                menu.setOpen(true);
                menu.setChecked(true);
            }
            if(menu.getParentId()!=null){
                if (menu.getParentId().equals(id)){
                    menu.setChildren(getChild(menu.getId(),menuList));
                    childList.add(menu);
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
       /* for(MenuDto menu:menuList){
            // 没有url子菜单还有子菜单
            if(menu.getUri()==null){
                menu.setChildMenu(getChild(menu.getId(),menuList));
            }
        }*/
        if (childList.size()==0){
            return null;
        }
        return childList;
    }
}
