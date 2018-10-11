package com.zcmzjp.wx.controller;

import com.zcmzjp.wx.entity.BuildBuildingInfo;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.BuildBuildingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/sys/buildBuildingInfo")
public class BuildBuildingInfoController extends BaseController<BuildBuildingInfo> {

    @Autowired
    BuildBuildingInfoService buildBuildingInfoService;


    @Override
    public BaseService<BuildBuildingInfo> getService() {
        return buildBuildingInfoService;
    }

    @Override
    public String getViewPrefix() {
        return "buildBuildingInfo";
    }
}
