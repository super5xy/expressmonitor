package cn.super5xy.expressmonitor.exception;

import cn.super5xy.expressmonitor.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author super5xy
 * @date 2020/8/9 18:46
 * 统一异常处理
 */
@RestControllerAdvice
public class ExpressExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());


    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e){
        logger.error(e.getMessage(), e);
        return Result.error("数据库中已存在该记录");
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        logger.error(e.getMessage(), e);
        return Result.error("系统繁忙");
    }
}
