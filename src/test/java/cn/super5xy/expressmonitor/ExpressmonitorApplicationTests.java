package cn.super5xy.expressmonitor;

import cn.super5xy.expressmonitor.entity.Express;
import cn.super5xy.expressmonitor.service.ExpressService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class ExpressmonitorApplicationTests {
    @Resource
    private ExpressService expressService;
    @Test
    void contextLoads() {
        Express express = new Express();
        express.setState(0);
        List<Express> expresses = expressService.queryAll(express);
        System.out.println(expresses);
        System.out.println(expresses.size());
    }

}
