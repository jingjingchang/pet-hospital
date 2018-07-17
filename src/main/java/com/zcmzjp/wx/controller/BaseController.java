package com.zcmzjp.wx.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.zcmzjp.wx.entity.Message;
import com.zcmzjp.wx.entity.Page;
import com.zcmzjp.wx.service.BaseService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseController<T>
{
    private ObjectMapper jsonMapper = new ObjectMapper();

    public abstract BaseService<T> getService();

    public abstract String getViewPrefix();

    public Message getMessage(boolean status)
    {
        Message msg = new Message();
        msg.setStatus(status);
        if (status) {
            msg.setMessage("success");
        } else {
            msg.setMessage("error");
        }
        return msg;
    }

    @RequestMapping({"/list"})
    public String list(String menuId)
    {
        return getViewPrefix() + "/list";
    }

    @RequestMapping({"/listByPage"})
    @ResponseBody
    public Object listPage(Page page)
    {
        Map<String, Object> map = new HashMap();
        map.put("pageNum", Integer.valueOf(page.getOffset() + 1));
        map.put("pageSize", Integer.valueOf(page.getLimit()));
        map.put("keyword", page.getSearch());
        PageInfo info = getService().listByPage(map);

        Map<String, Object> jsonMap = new HashMap();
        jsonMap.put("rows", info.getList());
        jsonMap.put("total", Long.valueOf(info.getTotal()));

        return jsonMap;
    }

    @RequestMapping({"/listPageById/{id}"})
    @ResponseBody
    public Object listPageById(Page page, @PathVariable Integer id)
    {
        Map<String, Object> map = new HashMap();
        map.put("pageNum", Integer.valueOf(page.getOffset() + 1));
        map.put("pageSize", Integer.valueOf(page.getLimit()));
        map.put("keyword", page.getSearch());
        map.put("id", id);
        PageInfo info = getService().listPageById(map);

        Map<String, Object> jsonMap = new HashMap();
        jsonMap.put("rows", info.getList());
        jsonMap.put("total", Long.valueOf(info.getTotal()));
        return jsonMap;
    }

    @RequestMapping({"/add"})
    public String add(String menuId, Model view)
    {
        return getViewPrefix() + "/add";
    }

    @PostMapping({"/create"})
    @ResponseBody
    public Message create(T obj)
            throws Exception
    {
        boolean ret = getService().add(obj);
        return getMessage(ret);
    }

    @RequestMapping({"/edit/{id}"})
    public String edit(Model view, @PathVariable Integer id)
    {
        T obj = findById(id);
        view.addAttribute(getViewPrefix(), obj);
        String test = getViewPrefix();
        System.out.println(test);
        return getViewPrefix() + "/edit";
    }

    @RequestMapping({"/edit"})
    public String editEntity(Model view, T obj)
    {
        view.addAttribute(getViewPrefix(), obj);
        return getViewPrefix() + "/edit";
    }

    public T findById(Integer id)
    {
        T msg = getService().getById(id);
        return msg;
    }

    @PostMapping({"/update"})
    @ResponseBody
    public Message updateDictionaryData(T obj)
    {
        boolean ret = getService().update(obj);
        return getMessage(ret);
    }

    @RequestMapping({"/view/{id}"})
    public String view(Model view, @PathVariable int id)
    {
        T obj = findById(Integer.valueOf(id));
        view.addAttribute("readonly", "readonly");
        view.addAttribute(getViewPrefix(), obj);
        return getViewPrefix() + "/view";
    }

    @PostMapping({"/remove"})
    @ResponseBody
    public Message deleteById(@RequestParam int id)
    {
        boolean ret = getService().deleteById(id);
        return getMessage(ret);
    }

    @ResponseBody
    @RequestMapping({"/getById/{id}"})
    public Object getById(@PathVariable int id)
    {
        return getService().getById(Integer.valueOf(id));
    }
}
