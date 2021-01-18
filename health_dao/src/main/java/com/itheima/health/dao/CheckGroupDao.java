package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckGroupDao {
    /**
     *添加检查组
     * @param checkgroup
     */
    void add(CheckGroup checkgroup);

    /**
     * 添加检查组和检查项之间的关系
     * @param checkgroupId
     * @param checkitemId
     */
    //因为2个形参的参数类型一样，所以要取别名(2个参数的类型是基础类，并且类型一样必须取别名)
    // 【因为反射时是根据类型来区分的】
    //
    void addCheckGroupCheckItem(@Param("checkgroupId") Integer checkgroupId, @Param("checkitemId")Integer checkitemId);

    /**
     * 检查组的条件查询
     * @param queryString
     * @return
     */
    /*
    <!-- 分页查询   参数类型String，因为传来实参的是查询条件 查询条件是String类型-->
    <!--返回值类型为 checkgroup,实际上是自定义类型 Javabean ：CheckGroup-->
    Page是 分页插件 jar包pageHepler中的JavaBean，它继承了ArrayList<>  可以接受数据集合
    */
    Page<CheckGroup> findByCondition(String queryString);

    /**
     * 通过id查询检查组
     * @param id
     * @return
     */
    CheckGroup findById(int id);

    /**
     *通过检查组id来查询选中的检查项id，绑定到checkitemIds
     * @param id
     * @return
     */
    List<CheckGroup> findCheckItemIdsByCheckGroupId(int id);

    /**
     * 先更新检查组
     * @param checkgroup
     */
    void update(CheckGroup checkgroup);

    /**
     *  删除 旧的检查组和检查项之间的关系
     * @param id
     */
    void deleteCheckGroupCheckItem(Integer id);
}
