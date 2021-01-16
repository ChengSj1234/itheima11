package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.service.CheckGroupService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {

    @Reference  // @Reference 这个是为了订阅service服务
     //快捷键：alt+enter  创建一个CheckGroupService接口
    private CheckGroupService checkGroupService;

    /**
     * 添加检查组
     * @param checkgroup  检查组信息
     * @param checkitemIds  选中的检查项信息
     * @return
     */
    @PostMapping("/add")
    //  型参Integer [] checkitemIds没有加
    public Result add(@RequestBody CheckGroup checkgroup,Integer [] checkitemIds){
        //调用 业务层 服务，实现添加检查组
        checkGroupService.add(checkgroup,checkitemIds);
        //  成功，则返回成功的响应
        return  new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);

    }

}
