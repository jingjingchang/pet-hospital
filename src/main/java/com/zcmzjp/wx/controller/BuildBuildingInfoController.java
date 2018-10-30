package com.zcmzjp.wx.controller;

import com.github.pagehelper.PageInfo;
import com.zcmzjp.wx.entity.BuildBuildingInfo;
import com.zcmzjp.wx.entity.JsonResult;
import com.zcmzjp.wx.entity.Page;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.BuildBuildingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

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

    @RequestMapping({"/listQueryByPage"})
    @ResponseBody
    public Object listQueryByPage(Page page, @RequestParam Map<String,Object> map){

        map.put("pageNum", Integer.valueOf(page.getOffset() + 1));
        map.put("pageSize", Integer.valueOf(page.getLimit()));
        map.put("keyword", page.getSearch());
        PageInfo info = getService().listByPage(map);

        Map<String, Object> jsonMap = new HashMap();
        jsonMap.put("rows", info.getList());
        jsonMap.put("total", Long.valueOf(info.getTotal()));
        return jsonMap;
    }

    @ResponseBody
    @RequestMapping("/changeStatus")
    public JsonResult changeStatus(String id){
        BuildBuildingInfo buildBuildingInfo = new BuildBuildingInfo();
        buildBuildingInfo.setId(id);
        buildBuildingInfo.setSuccess(true);
        buildBuildingInfo.setStatus(1);
        boolean flag = buildBuildingInfoService.updatePart(buildBuildingInfo);
        return new JsonResult(flag,flag?"操作成功！":"操作失败！");
    }


}
