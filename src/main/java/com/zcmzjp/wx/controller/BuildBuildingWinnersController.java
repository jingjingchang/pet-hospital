package com.zcmzjp.wx.controller;

import com.zcmzjp.wx.entity.BuildBuildingWinners;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.BuildBuildingWinnersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/sys/buildBuildingWinners")
public class BuildBuildingWinnersController extends BaseController<BuildBuildingWinners> {

    @Autowired
    BuildBuildingWinnersService buildBuildingWinnersService;


    @Override
    public BaseService<BuildBuildingWinners> getService() {
        return buildBuildingWinnersService;
    }

    @Override
    public String getViewPrefix() {
        return "buildBuildingWinners";
    }
}
