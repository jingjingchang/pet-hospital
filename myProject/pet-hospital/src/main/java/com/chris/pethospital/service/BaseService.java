package com.chris.pethospital.service;

import com.chris.pethospital.entity.JsonResult;
import com.chris.pethospital.config.BaseMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author zj
 * @date 2017/7/19.
 */
public abstract class BaseService<T> {

    public abstract BaseMapper<T> getMapper();

    public PageInfo listByPage(Map<String, Object> params) {
        List<T> data = null;
        if(params.get("pageNum") != null){
            if(params.get("pageSize").toString().equals( "0" )){
                params.remove( "pageNum" );
                params.remove( "pageSize" );
            } else {
                PageHelper.startPage(params);
            }
        }
        data = getMapper().getListByParams(params);

        return new PageInfo(data);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public JsonResult add(T obj) {
        int insertCount = getMapper().insert(obj);
        String msg = insertCount>0?"成功添加"+insertCount+"条记录":"新增数据失败！";
        return new JsonResult(insertCount>0?true:false,null,msg,null, HttpStatus.OK);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public JsonResult update(T obj) {
        int updateCount = getMapper().updateByPrimaryKeySelective(obj);
        String msg = updateCount>0?"成功更新"+updateCount+"条记录":"数据更新失败！";
        return new JsonResult(updateCount>0?true:false,null,msg,null, HttpStatus.OK);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public JsonResult delete(T obj) {
        int deleteCount = getMapper().delete(obj);
        String msg = deleteCount>0?"成功删除"+deleteCount+"条记录":"数据删除失败！";
        return new JsonResult(deleteCount>0?true:false,null,msg,null, HttpStatus.OK);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public JsonResult deleteById(Integer id) {
        int deleteCount = getMapper().deleteByPrimaryKey(id);
        String msg = deleteCount>0?"成功删除"+deleteCount+"条记录":"数据删除失败！";
        return new JsonResult(deleteCount>0?true:false,null,msg,null, HttpStatus.OK);
    }

    public T getById(Integer hId) {
        T obj = getMapper().selectByPrimaryKey(hId);
        return obj;
    }

    public List<T> selectAll(){
        List<T> t = getMapper().selectAll();
        return t;
    }

    public List<T> getListByParams(Map<String,Object> params){
        List<T> t = getMapper().getListByParams(params);
        return t;
    }

    public List<T> select(T t){
        List<T> list = getMapper().select(t);
        return list;
    }

    @Override
    public String toString() {
        return "BaseService{}";
    }
}
