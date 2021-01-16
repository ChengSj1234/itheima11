package com.itheima.health.dao;

import com.itheima.health.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

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
}
