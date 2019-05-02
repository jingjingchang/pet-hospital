package com.chris.pethospital.controller;

import com.chris.pethospital.entity.JsonResult;
import com.chris.pethospital.entity.Page;
import com.chris.pethospital.service.BaseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * TODO
 *
 * @author Administrator
 */
public abstract class BaseController<T> {

    private ObjectMapper jsonMapper = new ObjectMapper();

    public abstract BaseService<T> getService();

    public abstract String getViewPrefix();

    @GetMapping(value = "/list")
    public String list(String menuId) {

        return getViewPrefix() + "/list";
    }

    @RequestMapping("/listByPage")
    @ResponseBody
    public Object listByPage(Page page) {
        //根据不同的参数配置,有些传递的是offset
        Map<String,Object> map = new HashMap<>();
//        params.put("pageNum", params.get("pageNum"));
//        params.put("pageSize",  params.get("pageSize"));
        map.put("pageNum", Integer.valueOf(page.getOffset() + 1));
        map.put("pageSize", Integer.valueOf(page.getLimit()));
        map.put("keyword", page.getSearch());

        PageInfo info = getService().listByPage(map);

        Map<String,Object> jsonMap = new HashMap<>();
        jsonMap.put("rows", info.getList());
        jsonMap.put("total", info.getTotal());

        return jsonMap;
    }

    //进入添加页面
    @GetMapping("/add")
    public String add(String menuId, Model view) {
        return getViewPrefix() + "/add";
    }

    //增加
    @PostMapping("/create")
    @ResponseBody
    public JsonResult create(T obj)  throws Exception {
        return getService().add(obj);
    }

    //进入编辑页面
    @GetMapping("/edit/{id}")
    public String edit(Model view, @PathVariable Integer id) {
        T obj = getService().getById( id );
        view.addAttribute(getViewPrefix(),obj);
        return getViewPrefix() + "/edit";
    }

    //进入编辑页面2
    @GetMapping("/edit")
    public String editEntity(Model view, T obj) {
        view.addAttribute(getViewPrefix(), obj);
        return getViewPrefix() + "/edit";
    }

    //更新
    @PostMapping("/update")
    @ResponseBody
    public JsonResult update(T obj) {
        return getService().update(obj);
    }

    //进入详情页面
    @GetMapping("/view/{id}")
    public String view(Model view, @PathVariable Integer id) {
        T obj = getService().getById( id );
        view.addAttribute("readonly","readonly");
        view.addAttribute(getViewPrefix(), obj);
        return getViewPrefix()  + "/view";
    }
    //删除
    @GetMapping("/delete/{id}")
    @ResponseBody
    public JsonResult remove(@PathVariable Integer id) {
        return getService().deleteById(id);
    }

    //获取一条数据
    @ResponseBody
    @GetMapping("/getById/{id}")
    public Object getById(@PathVariable Integer id) {
        return getService().getById(id);
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }


}
