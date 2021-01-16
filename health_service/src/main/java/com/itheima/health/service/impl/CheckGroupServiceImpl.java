package com.itheima.health.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.health.dao.CheckGroupDao;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

//  @Service(interfaceClass = CheckGroupService.class) 这一行一定要加，忘记加了会报错  “Mapper's namespace cannot be empty”
@Service(interfaceClass = CheckGroupService.class)
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired
    private CheckGroupDao checkGroupDao;


    @Override
    @Transactional
    public void add(CheckGroup checkgroup, Integer[] checkitemIds){
        //  。先添加检查组
        checkGroupDao.add(checkgroup);

        // 。获取检查组的id
        Integer checkgroupId = checkgroup.getId();
        //	。遍历选中的检查组 id的数组（Integer[] checkitemIds）
           //有可能一个检查项都没勾上，所以需要判断
        if(null !=checkitemIds){
            for (Integer checkitemId : checkitemIds) {
                //	。添加检查组与检查项的关系（因为是1对多的关系所以需要遍历添加）
                //参数传 checkgroup检查组的id和检查项目的id
                checkGroupDao.addCheckGroupCheckItem(checkgroupId,checkitemId);
            }
        }


        //	。添加事务控制（因为是一次性添加多条记录）
        //事物控制是在方法上添加  注解   @Transactional


    }




}
