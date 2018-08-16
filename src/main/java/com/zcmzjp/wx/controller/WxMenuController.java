package com.zcmzjp.wx.controller;

import com.zcmzjp.wx.dto.WxMenuDto;
import com.zcmzjp.wx.entity.*;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.WxMenuService;
import com.zcmzjp.wx.utils.WeixinUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 2017-08-04.
 */
@Controller
@RequestMapping("/admin/weChat/wxMenu")
public class WxMenuController extends BaseController<WxMenu> {
    @Autowired
    WxMenuService wxMenuService;
    @Override
    public BaseService<WxMenu> getService() {
        return wxMenuService;
    }

    @Override
    public String getViewPrefix() {
        return "wxMenu";
    }

    @ResponseBody
    @RequestMapping("/editMenu")
    public JsonResult editWxMenu(Integer id){
          Boolean flag;
          WxMenu wxMenu = wxMenuService.getById(id);
          flag = wxMenu!=null?true:false;
          return new JsonResult(flag,wxMenu);
    }

    @Override
    public Message create(WxMenu obj) throws Exception {
        if(obj.getType().equals("graphic")){
            obj.setMenuValue(GlobalParameter.HOST_ADDRESS+"/wx/news/"+obj.getMenuValue());
            obj.setType("view");
        }
        return super.create(obj);
    }

    @RequestMapping("/sendMenu")
    @ResponseBody
    public JsonResult sendMenu(){
        List<WxMenuDto> dtoList = wxMenuService.getAllMenus();
        List<WxMenuAdd> wxMenuAdds = new ArrayList<>();
        JSONObject jo = new JSONObject();

        for(WxMenuDto wxMenuDto:dtoList){
            if(wxMenuDto.getParentId()==null){
                WxMenuAdd wxMenuAdd = new WxMenuAdd();
                wxMenuAdd.setKey(String.valueOf(wxMenuDto.getId()));
                wxMenuAdd.setName(wxMenuDto.getName());
                wxMenuAdd.setType(wxMenuDto.getType());
                wxMenuAdd.setUrl(wxMenuDto.getMenuValue());
                wxMenuAdd.setSub_button(getSubMenus(wxMenuDto.getId(),dtoList));
                wxMenuAdds.add(wxMenuAdd);
            }
        }
        jo.put("button",wxMenuAdds);
        int flag = WeixinUtil.createMenu(jo.toString());
        if(flag==0){
            return new JsonResult(true,jo.toString());
        }else{
            return new JsonResult(false,"创建菜单失败");
        }

    }

    @Override
    @RequestMapping("/update")
    @ResponseBody
    public Message updateDictionaryData(WxMenu obj) {
        return getMessage(wxMenuService.updatePart(obj));
    }

    @RequestMapping("/getAllMenus")
    @ResponseBody
    public List<WxMenuDto> getAllMenus(){

        List<WxMenuDto> dtoList = wxMenuService.getAllMenus();
        List<WxMenuDto> wxMenus = new ArrayList<>();

        for(WxMenuDto wxMenuDto:dtoList){
            if(wxMenuDto.getParentId()==null){
                wxMenuDto.setChildren(getChildMenus(wxMenuDto.getId(),dtoList));
                wxMenus.add(wxMenuDto);
            }
        }

        return wxMenus;
    }
    public List<WxMenuAdd> getSubMenus(Integer pid,List<WxMenuDto> list){
        List<WxMenuAdd> subList = new ArrayList<>();
        for(WxMenuDto wxMenuDto:list){
            if(wxMenuDto.getParentId()!=null)
            {
                if(wxMenuDto.getParentId().equals(pid)){
                    WxMenuAdd wxMenuAdd = new WxMenuAdd();
                    wxMenuAdd.setName(wxMenuDto.getName());
                    wxMenuAdd.setType(wxMenuDto.getType());
                    wxMenuAdd.setKey(String.valueOf(wxMenuDto.getId()));
                    wxMenuAdd.setUrl(wxMenuDto.getMenuValue());
                    wxMenuAdd.setSub_button(getSubMenus(wxMenuDto.getId(),list));
                    subList.add(wxMenuAdd);
                }
            }
        }
        return  subList;
    }
    public List<WxMenuDto> getChildMenus(Integer pid,List<WxMenuDto> list){
         List<WxMenuDto> childList = new ArrayList<>();
        for(WxMenuDto wxMenuDto:list){

            if(wxMenuDto.getParentId()!=null)
            {
                if(wxMenuDto.getParentId().equals(pid)){
                    wxMenuDto.setChildren(getChildMenus(wxMenuDto.getId(),list));
                    childList.add(wxMenuDto);
                }
            }
        }
        return  childList;
    }
}
