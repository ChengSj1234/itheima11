package com.itheima.health.service.impl;

//import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.health.dao.CheckItemDao;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 * 检查项服务
 * </p>
 *
 * @author: Eric
 * @since: 2021/1/5
 */
// 使用alibaba的包，发布服务 interfaceClass指定服务的接口类
//@Service(interfaceClass = CheckItemService.class)
  /*//注意：使用Service注释时，导的包一定要是阿里巴巴 alibaba的包，该注释的作用是用来发布服务的
     其中interfaceClass 是用来指定服务的接口。也可以不指定，因为CheckItemServiceImpl实现了接口CheckItemService
    */
    @Service(interfaceClass = CheckItemService.class)
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired  //使用框架 spring，注入CheckItemDao对象
    private CheckItemDao checkItemDao;
    /**
     * 该方法：查询所有的方法
     *  //实现：调用CheckItemDao中的方法findAll实现
     *
     */
    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }

    /**
     * 添加检查项
     * @param checkItem
     */
    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }
}
