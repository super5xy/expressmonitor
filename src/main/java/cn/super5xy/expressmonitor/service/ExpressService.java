package cn.super5xy.expressmonitor.service;

import cn.super5xy.expressmonitor.entity.Express;

import java.util.List;

/**
 * (Express)表服务接口
 *
 * @author makejava
 * @since 2020-08-09 08:55:05
 */
public interface ExpressService {

    /**
     * 通过ID查询单条数据
     *
     * @param expressNumber 主键
     * @return 实例对象
     */
    Express queryById(String expressNumber);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Express> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param express 实例对象
     * @return 实例对象
     */
    Express insert(Express express);

    /**
     * 修改数据
     *
     * @param express 实例对象
     * @return 实例对象
     */
    Express update(Express express);

    /**
     * 通过主键删除数据
     *
     * @param expressNumber 主键
     * @return 是否成功
     */
    boolean deleteById(String expressNumber);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param express 实例对象
     * @return 对象列表
     */
    List<Express> queryAll(Express express);

}