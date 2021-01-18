package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.service.CheckGroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 检查项分页查询
     * @param queryPageBean
     * @return
     */
    @PostMapping("/findPage")
    public  Result  findPage(@RequestBody QueryPageBean queryPageBean){
        //调用服务，分页查询
        //queryPageBean 这个是分页查询的封装数据对象即javaBean
        PageResult<CheckGroup> pageResult=checkGroupService.findPage(queryPageBean);
        return  new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,pageResult);
    }

    /**
     * 查询检查组的方法：通过id查询检查组
     * @return
     */
    @GetMapping("/findById") //get 请求，
    public Result findById(int id){
        CheckGroup checkGroup=checkGroupService.findById(id);
        //查完之后就进行让返回
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroup);

    }

    /**
     * 通过检查组id来查询选中的检查项id，绑定到checkitemIds
     */
    @GetMapping("/findCheckItemIdsByCheckGroupId")
    public Result findCheckItemIdsByCheckGroupId(int id){
        //通过检查组id来查询选中的检查项id，绑定到checkitemIds
        List<CheckGroup> checkItemIds=checkGroupService.findCheckItemIdsByCheckGroupId(id);
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItemIds);

    }

    /**
     * 修改检查组
     * @param checkgroup  检查组信息
     * @param checkitemIds  选中的检查项信息
     * @return
     */
    @PostMapping("/update")
    //  型参Integer [] checkitemIds没有加
    public Result update(@RequestBody CheckGroup checkgroup,Integer [] checkitemIds){
        //调用 业务层 服务，实现修改检查组
        checkGroupService.update(checkgroup,checkitemIds);
        //  成功，则返回成功的响应
        return  new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }

}
