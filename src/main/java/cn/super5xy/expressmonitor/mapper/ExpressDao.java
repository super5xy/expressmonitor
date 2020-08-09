package cn.super5xy.expressmonitor.mapper;

import cn.super5xy.expressmonitor.entity.Express;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Express)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-09 08:55:03
 */
public interface ExpressDao {

    /**
     * 通过ID查询单条数据
     *
     * @param expressNumber 主键
     * @return 实例对象
     */
    Express queryById(String expressNumber);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Express> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param express 实例对象
     * @return 对象列表
     */
    List<Express> queryAll(Express express);

    /**
     * 新增数据
     *
     * @param express 实例对象
     * @return 影响行数
     */
    int insert(Express express);

    /**
     * 修改数据
     *
     * @param express 实例对象
     * @return 影响行数
     */
    int update(Express express);

    /**
     * 通过主键删除数据
     *
     * @param expressNumber 主键
     * @return 影响行数
     */
    int deleteById(String expressNumber);

}