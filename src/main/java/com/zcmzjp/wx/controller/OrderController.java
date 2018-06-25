package com.zcmzjp.wx.controller;

import com.github.pagehelper.PageInfo;
import com.zcmzjp.wx.entity.Order;
import com.zcmzjp.wx.entity.OrderTime;
import com.zcmzjp.wx.entity.Page;
import com.zcmzjp.wx.entity.Unit;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.OrderService;
import com.zcmzjp.wx.service.OrderTimeService;
import com.zcmzjp.wx.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-02-07 23:19
 * Description:
 */
@Controller
@RequestMapping("/admin/sys/car/order")
public class OrderController extends BaseController<Order> {
    @Autowired
    OrderService orderService;

    @Autowired
    OrderTimeService orderTimeService;

    @Autowired
    UnitService unitService;

    @Override
    public BaseService<Order> getService() {
        return orderService;
    }

    @Override
    public String getViewPrefix() {
        return "order";
    }

    @RequestMapping("/newList")
    public String list(Model view, String menuId) {
        OrderTime orderTime = orderTimeService.getById(1);
        view.addAttribute("orderTime",orderTime);
        return super.list(menuId);
    }

    @Override
    @RequestMapping("/edit/{id}")
    public String edit(Model view,@PathVariable Integer id) {
        Unit unit = unitService.getById(id);
        List<Unit> list = unitService.getAllUnit();
        view.addAttribute("units",list);
        view.addAttribute("unit",unit);
        return super.edit(view, id);
    }

    @Override
    public String add(String menuId, Model view) {
        List<Unit> list = unitService.getAllUnit();
        view.addAttribute("units",list);
        return super.add(menuId, view);
    }

    @RequestMapping({"/getOrderListByUnitId/{unitId}"})
    @ResponseBody
    public Object getOrderListByUnitId(Page page,@PathVariable Integer unitId)
    {
        Map<String, Object> map = new HashMap();
        map.put("pageNum", Integer.valueOf(page.getOffset() + 1));
        map.put("pageSize", Integer.valueOf(page.getLimit()));
        map.put("keyword", page.getSearch());
        map.put("unitId",unitId);
        PageInfo info = getService().listByPage(map);

        Map<String, Object> jsonMap = new HashMap();
        jsonMap.put("rows", info.getList());
        jsonMap.put("total", Long.valueOf(info.getTotal()));

        return jsonMap;
    }

    @RequestMapping("/getOrderListPageByUnitId/{unitId}")
    public String getOrderListPageByUnitId(Model view,@PathVariable Integer unitId) {
        view.addAttribute("unitId",unitId);
        return "unit/orderList";
    }

    @RequestMapping("/unitAdd/{unitId}")
    public String unitAdd(@PathVariable Integer unitId, Model view)
    {
        view.addAttribute("unitId",unitId);
        return  "unit/unitAdd";
    }


}
