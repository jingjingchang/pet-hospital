package com.zcmzjp.wx.controller;

import com.zcmzjp.wx.entity.Assets;
import com.zcmzjp.wx.entity.AssetsCategory;
import com.zcmzjp.wx.service.AssetsCategoryService;
import com.zcmzjp.wx.service.AssetsService;
import com.zcmzjp.wx.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/sys/car/assets")
public class AssetsController extends BaseController<Assets> {
    @Autowired
    AssetsService assetsService;

    @Autowired
    AssetsCategoryService assetsCategoryService;
    @Override
    public BaseService<Assets> getService() {
        return assetsService;
    }

    @Override
    public String getViewPrefix() {
        return "assets";
    }

    @Override
    @RequestMapping("/edit/{id}")
    public String edit(Model view,@PathVariable Integer id) {
        Assets assets = assetsService.getById(id);
        List<AssetsCategory> list = assetsCategoryService.getAllAssetsCategorys();
        view.addAttribute("category",list);
        view.addAttribute("assets",assets);
        return getViewPrefix()+"/edit";
    }

    @Override
    @RequestMapping("/add")
    public String add(String menuId, Model view) {
        List<AssetsCategory> list = assetsCategoryService.getAllAssetsCategorys();
        view.addAttribute("category",list);
        return getViewPrefix()+"/add";
    }
}
