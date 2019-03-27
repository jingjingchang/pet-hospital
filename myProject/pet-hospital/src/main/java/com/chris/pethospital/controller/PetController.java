package com.chris.pethospital.controller;

import com.chris.pethospital.entity.JsonResult;
import com.chris.pethospital.entity.Pet;
import com.chris.pethospital.entity.User;
import com.chris.pethospital.service.BaseService;
import com.chris.pethospital.service.PetService;
import com.chris.pethospital.utils.CurrentUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/pet")
public class PetController extends BaseController<Pet>{

    @Autowired
    PetService petService;

    @Override
    public BaseService<Pet> getService() {
        return petService;
    }

    @Override
    public String getViewPrefix() {
        return "pet";
    }

    @ResponseBody
    @Override
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public JsonResult create(Pet obj) throws Exception {
        User user = CurrentUtil.getCurrentUser();
        obj.setUserId(user.getId());
        return petService.add(obj);
    }
    @RequestMapping("/listMyPage")
    @ResponseBody
    public Object listMyPage( Map<String,Object> params) {
        //根据不同的参数配置,有些传递的是offset
        User user = CurrentUtil.getCurrentUser();
        params.put("userId",user.getId());
        params.put("pageNum", params.get("pageNum"));
        params.put("pageSize",  params.get("pageSize"));
        params.put("keyword",  params.get("keyword"));
        PageInfo info = getService().listByPage(params);

        Map<String,Object> jsonMap = new HashMap<>();
        jsonMap.put("rows", info.getList());
        jsonMap.put("total", info.getTotal());

        return jsonMap;
    }

    @GetMapping(value = "/mylist")
    public String mylist(String menuId) {
        return getViewPrefix() + "/mylist";
    }
}
