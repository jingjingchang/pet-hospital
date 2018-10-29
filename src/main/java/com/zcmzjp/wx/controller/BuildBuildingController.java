package com.zcmzjp.wx.controller;

import com.zcmzjp.wx.entity.BuildBuilding;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.BuildBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/sys/buildBuilding")
public class BuildBuildingController extends BaseController<BuildBuilding> {

    @Autowired
    BuildBuildingService buildBuildingService;


    @Override
    public BaseService<BuildBuilding> getService() {
        return buildBuildingService;
    }

    @Override
    public String getViewPrefix() {
        return "buildBuilding";
    }

    @RequestMapping("/showBuildInfoPageByBuildingId/{id}")
    public String showBuildInfoPageByBuildingId(Model view, @PathVariable String id){
        view.addAttribute("buildingId",id);
        return getViewPrefix()+"/buildingInfo";
    }
}
