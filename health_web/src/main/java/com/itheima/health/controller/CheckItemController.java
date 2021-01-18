package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.service.CheckItemService;
import org.apache.zookeeper.Op;
import org.springframework.web.bind.annotation.*;

import java.beans.MethodDescriptor;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author: Eric
 * @since: 2021/1/5
 */
@RestController
@RequestMapping("/checkitem")
public class CheckItemController {

    /**
     * 订阅检查项服务
     */
    @Reference
    private  CheckItemService checkItemService;

    @GetMapping("/findAll")
    public Result findAll(){
        //调用服务查询    调用服务查询方法
        List<CheckItem> list= checkItemService.findAll();
        //把list集合封装到Result集合中去，然后在返回
        return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,list);

    }
    /**
     * 添加检查项
     */
    @PostMapping("/add")
    public Result add(@RequestBody CheckItem checkItem){
        //调用服务添加(调用业务层代码 )
        checkItemService.add(checkItem);
         //返回操作结果，返回成功的结果
        return new Result(true,MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    /**
     * 检查项分页查询
     * @param queryPageBean
     * @return
     */
    @PostMapping("/findPage")
    public  Result  findPage(@RequestBody QueryPageBean queryPageBean){
        //调用服务，分页查询
        PageResult<CheckItem> pageResult=checkItemService.findPage(queryPageBean);
        return  new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,pageResult);

    }
}
