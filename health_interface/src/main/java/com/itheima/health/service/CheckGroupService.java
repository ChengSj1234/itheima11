package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.CheckGroup;

import java.util.List;

/**
 *
 */
public interface CheckGroupService {

    void add(CheckGroup checkgroup, Integer[] checkitemIds);

    PageResult<CheckGroup> findPage(QueryPageBean queryPageBean);

    /**
     * 通过id查询检查组
     * @param id
     * @return
     */
    CheckGroup findById(int id);

    /**
     * 通过检查组id来查询选中的检查项id，绑定到checkitemIds
     * @param id
     * @return
     */
    List<CheckGroup> findCheckItemIdsByCheckGroupId(int id);

    /**
     *  * 修改检查组
     *      * @param checkgroup  检查组信息
     *      * @param checkitemIds  选中的检查项信息
     */

    void update(CheckGroup checkgroup, Integer[] checkitemIds);
}
