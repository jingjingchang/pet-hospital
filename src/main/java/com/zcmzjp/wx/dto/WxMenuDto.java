package com.zcmzjp.wx.dto;


import com.zcmzjp.wx.entity.WxMenu;

import java.util.List;

/**
 * Created by Chris on 2017-08-04.
 */
public class WxMenuDto extends WxMenu {

    private List<WxMenuDto> children;

    public List<WxMenuDto> getChildren() {
        return children;
    }

    public void setChildren(List<WxMenuDto> children) {
        this.children = children;
    }
}
