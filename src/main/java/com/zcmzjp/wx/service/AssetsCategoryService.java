package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.ArticleCategory;
import com.zcmzjp.wx.entity.AssetsCategory;
import com.zcmzjp.wx.mapper.ArticleCategoryMapper;
import com.zcmzjp.wx.mapper.AssetsCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetsCategoryService extends BaseService<AssetsCategory> {

    @Autowired
    AssetsCategoryMapper assetsCategoryMapper;

    @Override
    public BaseMapper<AssetsCategory> getMapper() {
        return assetsCategoryMapper;
    }

    public List<AssetsCategory> getAllAssetsCategorys(){
        return assetsCategoryMapper.getAllAssetsCategorys();
    }
}
