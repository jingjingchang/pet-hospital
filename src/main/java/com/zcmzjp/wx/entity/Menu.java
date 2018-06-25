package com.zcmzjp.wx.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="b_menus")
public class Menu {
    @Id
    private Integer id;
    @Column(name="parent_id")
    private Integer parentId;
    private String code;
    private String name;
    private String url;
    private Integer sequence;
    private String css;
    private Integer status;

    public Integer getId()
    {
        return this.id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getParentId()
    {
        return this.parentId;
    }

    public void setParentId(Integer parentId)
    {
        this.parentId = parentId;
    }

    public String getCode()
    {
        return this.code;
    }

    public void setCode(String code)
    {
        this.code = (code == null ? null : code.trim());
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = (name == null ? null : name.trim());
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSequence()
    {
        return this.sequence;
    }

    public void setSequence(Integer sequence)
    {
        this.sequence = sequence;
    }

    public String getCss()
    {
        return this.css;
    }

    public void setCss(String css)
    {
        this.css = (css == null ? null : css.trim());
    }

    public Integer getStatus()
    {
        return this.status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }
}
