package com.chris.pethospital.controller;

import com.chris.pethospital.controller.BaseController;
import com.chris.pethospital.entity.JsonResult;
import com.chris.pethospital.entity.Wall;
import com.chris.pethospital.service.BaseService;
import com.chris.pethospital.service.WallService;
import com.chris.pethospital.utils.CurrentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/wall")
public class WallController extends BaseController<Wall> {

    @Autowired
    WallService wallService;

    @Override
    public BaseService<Wall> getService() {
        return wallService;
    }

    @Override
    public String getViewPrefix() {
        return "wall";
    }

    @RequestMapping("/view")
    public String view(Model view){
       List<Wall> walls =  wallService.getListByParams();
       view.addAttribute("walls",walls);
        return  getViewPrefix()+"/view";
    }

    @Override
    public JsonResult create(Wall obj) throws Exception {
        obj.setUserId( CurrentUtil.getCurrentUser().getId());
        return super.create(obj);
    }
}
