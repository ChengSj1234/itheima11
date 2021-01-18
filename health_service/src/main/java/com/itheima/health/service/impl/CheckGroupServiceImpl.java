package com.itheima.health.service.impl;


import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.dao.CheckGroupDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//  @Service(interfaceClass = CheckGroupService.class) 这一行一定要加
@Service(interfaceClass = CheckGroupService.class)
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired
    private CheckGroupDao checkGroupDao;


    @Override
    @Transactional
    public void add(CheckGroup checkgroup, Integer[] checkitemIds) {
        //  。先添加检查组
        checkGroupDao.add(checkgroup);
        // 。获取检查组的id
        Integer checkgroupId = checkgroup.getId();
        //	。遍历选中的检查组 id的数组（Integer[] checkitemIds）
        //有可能一个检查项都没勾上，所以需要判断
        if (null != checkitemIds) {
            for (Integer checkitemId : checkitemIds) {
                //	。添加检查组与检查项的关系（因为是1对多的关系所以需要遍历添加）
                //参数传 checkgroup检查组的id和检查项目的id
                checkGroupDao.addCheckGroupCheckItem(checkgroupId, checkitemId);
            }
        }
        //	。添加事务控制（因为是一次性添加多条记录）
        //事物控制是在方法上添加  注解   @Transactional
    }
    /**
     * 检查组的分页查询
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult<CheckGroup> findPage(QueryPageBean queryPageBean) {
         /* //pageSize 是当前页数，pageSize的值是从前端传来的，这个传进来的值可能很大 比如100万
           然而后台是不能够查询出100万条信息给客户的，故后台需要进行大小限制
           //限制例子：如果 也是大于50 则显示50
            */
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
       /* //条件查询
           if 判断查询条件是否为空，不为空则有查询条件
       */
        if (StringUtils.isNotEmpty(queryPageBean.getQueryString())) {
            //来到这里表示有查询条件，进行模糊查询
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }
        /*
        Page 类 继承(extends)了ArrayList  因此可以接受List集合的数据
        */
        Page<CheckGroup> page = checkGroupDao.findByCondition(queryPageBean.getQueryString());
        PageResult<CheckGroup> pageResult = new PageResult<CheckGroup>(page.getTotal(), page.getResult());
        return pageResult;
    }
    /**
     * 通过id查询检查组
     * @param id
     * @return
     */
    @Override
    public CheckGroup findById(int id) {
        return checkGroupDao.findById(id);
    }
    /**
     *
     * @param id
     * @return
     */
    @Override
    public List<CheckGroup> findCheckItemIdsByCheckGroupId(int id) {
        return checkGroupDao.findCheckItemIdsByCheckGroupId(id);
    }

    /**
     *
     * @param checkgroup
     * @param checkitemIds
     */
    @Override
    //在方法上加上@Transactional 这个 是使方法加上事务
    @Transactional
    //CheckGroup 封装检查组的pojo（javabean）  ；checkitemIds封装 勾选的检查项 id集合
    public void update(CheckGroup checkgroup, Integer[] checkitemIds) {
        // 。先更新检查组  updata()是dao持久层更新检查组的方法
        checkGroupDao.update(checkgroup);
        //。先删除旧关系.就是把原来勾选的检查项全部删除掉在添加新勾选的项
        //根据 参数检查组的id(checkgroup.getId())来删除 检查组与检查项关系表里面的数据
        checkGroupDao.deleteCheckGroupCheckItem(checkgroup.getId());
        //。遍历选择中的检查项id的数组（checkitemIds）
        if(null !=checkitemIds){
            for (Integer checkitemId : checkitemIds) {
                //	。添加检查组与检查项的关系,遍历添加到t_checkgroup_checkitem表中
                checkGroupDao.addCheckGroupCheckItem(checkgroup.getId(),checkitemId);
            }
        }
        //	。添加事务控制，因为同时添加多个关系，所以需要添加事务
        //在方法上加上@Transactional 这个 是使方法加上事务

    }




}
