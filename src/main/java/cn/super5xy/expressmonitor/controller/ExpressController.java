package cn.super5xy.expressmonitor.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import cn.super5xy.expressmonitor.entity.Express;
import cn.super5xy.expressmonitor.service.ExpressService;
import cn.super5xy.expressmonitor.util.KuaiDi;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * (Express)表控制层
 *
 * @author makejava
 * @since 2020-08-09 08:55:07
 */
@RestController
@RequestMapping("express")
public class ExpressController {
    /**
     * 服务对象
     */
    @Resource
    private ExpressService expressService;

    /**
     * 通过主键查询单条数据
     *
/*    @GetMapping("selectOne")
    public Express selectOne(String id) {
        return this.expressService.queryById(id);
    }*/

    @PostMapping("add")
    public String addExpress(@RequestParam String expressNumber, String scKey, String sKey) {
        if (StringUtils.isEmpty(scKey)&&StringUtils.isEmpty(sKey)) {
            return "请填写信息";
        }
        if (KuaiDi.check(expressNumber)){
            return "快递单不存在";
        }

        if (StringUtils.isEmpty(scKey)^StringUtils.isEmpty(sKey)){
            Express express = new Express();
            if (StringUtils.isEmpty(scKey)){
                String body = HttpRequest.post("https://push.xuthus.cc/send/" + sKey).form("c", "收到这条消息代表绑定成功 单号"+expressNumber)
                        .execute().body();
                if ("400".equals(JSONUtil.parseObj(body).getStr("code"))){
                    return "请检查skey";
                }
                express.setType(1);

            }else {
                Map<String,Object> map =new HashMap<>();
                map.put("text","绑定成功通知");
                map.put("desp","收到这条消息代表绑定成功 单号"+expressNumber);
                String body = HttpRequest.post("https://sc.ftqq.com/" + scKey + ".send")
                        .form(map)
                        .execute().body();
                if (body.contains("bad pushtoken")){
                    return "请检查sckey";
                }
                express.setType(0);
            }
            express.setExpressNumber(expressNumber);
            express.setExpressName(KuaiDi.autoComNum(expressNumber));
            express.setSckey(scKey);
            express.setSkey(sKey);
            express.setSize(0);
            express.setState(0);
            express.setDate(new Date());
            this.expressService.insert(express);
            return "添加成功";
        }
        return "不可同时填写sckey和skey";
    }
}