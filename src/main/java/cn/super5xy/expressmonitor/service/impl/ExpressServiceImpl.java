package cn.super5xy.expressmonitor.service.impl;

import cn.super5xy.expressmonitor.mapper.ExpressDao;
import cn.super5xy.expressmonitor.entity.Express;
import cn.super5xy.expressmonitor.service.ExpressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Express)表服务实现类
 *
 * @author makejava
 * @since 2020-08-09 08:55:06
 */
@Service("expressService")
public class ExpressServiceImpl implements ExpressService {
    @Resource
    private ExpressDao expressDao;

    /**
     * 通过ID查询单条数据
     *
     * @param expressNumber 主键
     * @return 实例对象
     */
    @Override
    public Express queryById(String expressNumber) {
        return this.expressDao.queryById(expressNumber);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Express> queryAllByLimit(int offset, int limit) {
        return this.expressDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param express 实例对象
     * @return 实例对象
     */
    @Override
    public Express insert(Express express) {
        this.expressDao.insert(express);
        return express;
    }

    /**
     * 修改数据
     *
     * @param express 实例对象
     * @return 实例对象
     */
    @Override
    public Express update(Express express) {
        this.expressDao.update(express);
        return this.queryById(express.getExpressNumber());
    }

    /**
     * 通过主键删除数据
     *
     * @param expressNumber 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String expressNumber) {
        return this.expressDao.deleteById(expressNumber) > 0;
    }



    @Override
    public List<Express> queryAll(Express express) {
        return this.expressDao.queryAll(express);
    }

}