package com.itheima.health.service.impl;






import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.dao.CheckItemDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
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
public class CheckItemServiceImpl<findPage> implements CheckItemService {

    @Autowired  //使用框架 spring，注入CheckItemDao对象
    private CheckItemDao checkItemDao;

    /**
     * 该方法：查询所有的方法
     * //实现：调用CheckItemDao中的方法findAll实现
     */
    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }

    /**
     * 添加检查项
     *
     * @param checkItem
     */
    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    @Override
    public PageResult<CheckItem> findPage(QueryPageBean queryPageBean){
         /* //pageSize 是当前页数，pageSize的值是从前端传来的，这个传进来的值可能很大 比如100万
           然而后台是不能够查询出100万条信息给客户的，
           故后台需要进行大小限制
           //限制例子：如果 也是大于50 则显示50
            */
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
       /* //条件查询
           if 判断查询条件是否为空，不为空则有查询条件*/
        if(StringUtils.isNotEmpty(queryPageBean.getQueryString()) ){
            //来到这里表示有查询条件，进行模糊查询
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }
        /*
        Page 类 继承(extends)了ArrayList  因此可以接受List集合的数据
        */
        Page<CheckItem> page = checkItemDao.findByCondition(queryPageBean.getQueryString());
        PageResult<CheckItem> pageResult = new PageResult<CheckItem>(page.getTotal(),page.getResult());
        return pageResult;
    }

}
