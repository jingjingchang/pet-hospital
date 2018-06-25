package com.zcmzjp.wx.mapper;


import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.ArticleCategory;
import com.zcmzjp.wx.entity.AssetsCategory;

import java.util.List;

public interface AssetsCategoryMapper extends BaseMapper<AssetsCategory> {

    List<AssetsCategory> getAllAssetsCategorys();
}
