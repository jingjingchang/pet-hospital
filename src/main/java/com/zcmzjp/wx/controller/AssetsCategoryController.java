package com.zcmzjp.wx.controller;

import com.zcmzjp.wx.entity.ArticleCategory;
import com.zcmzjp.wx.entity.AssetsCategory;
import com.zcmzjp.wx.service.ArticleCategoryService;
import com.zcmzjp.wx.service.AssetsCategoryService;
import com.zcmzjp.wx.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/sys/car/assetsCategory")
public class AssetsCategoryController extends BaseController<AssetsCategory> {
    @Autowired
    AssetsCategoryService assetsCategoryService;

    @Override
    public BaseService<AssetsCategory> getService() {
        return assetsCategoryService;
    }

    @Override
    public String getViewPrefix() {
        return "assetsCategory";
    }

    @RequestMapping("/add")
    @Override
    public String add(String menuId, Model view) {
        List<AssetsCategory> list = assetsCategoryService.getAllAssetsCategorys();
        view.addAttribute("category",list);
        return super.add(menuId, view);
    }

    @RequestMapping("/edit/{id}")
    @Override
    public String edit(Model view,@PathVariable Integer id) {
        List<AssetsCategory> list = assetsCategoryService.getAllAssetsCategorys();
        view.addAttribute("category",list);
        AssetsCategory assetsCategory = findById(id);
        view.addAttribute(getViewPrefix(), assetsCategory);
        String test = getViewPrefix();
        System.out.println(test);
        return getViewPrefix() + "/edit";
    }
}
