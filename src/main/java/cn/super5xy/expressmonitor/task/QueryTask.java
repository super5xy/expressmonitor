package cn.super5xy.expressmonitor.task;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.super5xy.expressmonitor.entity.Express;
import cn.super5xy.expressmonitor.service.ExpressService;
import cn.super5xy.expressmonitor.util.KuaiDi;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: super5xy
 * @Date: 2020/8/9 8:58
 */
@Component
public class QueryTask {
    @Resource
    private ExpressService expressService;

    /**
     * 定时任务 每三十分钟执行一次 如果有多个任务建议使用线程池和@Async注解
     */
    @Scheduled(cron = "0 0/30 * * * ? ")
    public void monitor() {
        System.out.println("开始执行任务");
        Express record = new Express();
        record.setState(0);
        //查询未签收的快递 state=0
        List<Express> expresses = expressService.queryAll(record);
        if (expresses == null || expresses.size() == 0) {
            return;
        }
        expresses.forEach(express -> {
//            System.out.println(express);
            List query = KuaiDi.query(express.getExpressNumber(), express.getExpressName());
            //listsize不同说明物流更新了
            if (query.size() != express.getSize()) {
                //发送消息
                sendMsg(express, (JSONArray) query);
                if (query.toString().contains("签收")) {
                    //已签收，设定state为1
                    express.setState(1);
                }
                express.setSize(query.size());
                expressService.update(express);
            }
        });
    }

    /**
     * 发送消息
     *
     * @param express
     * @param query   查询结果
     */
    private void sendMsg(Express express, JSONArray query) {
        String url;
        String text;
        //酷推有字符限制，只推送最新的两条
        List<String> collect = query.stream().map(o -> {
            JSONObject jsonObject = (JSONObject) o;
            String context = jsonObject.getStr("context");
            String time = jsonObject.getStr("time");
            return "\n\n" + "最新信息：" + context + "\n" + "时间：" + time;
        }).collect(Collectors.toList());
        if (collect.size() >= 2){
            text = "您的快递单号是：" + express.getExpressNumber() + collect.get(0) + collect.get(1)+
                    "\n\n由于字符限制只推送最新两条。\n程式由 super5xy.cn 所有";
        }else {
            text = "您的快递单号是：" + express.getExpressNumber() + collect.get(0) +
                    "\n\n由于字符限制只推送最新两条。\n程式由 super5xy.cn 所有";
        }

        System.out.println(text);
        //server酱
        if (express.getType() == 0) {
            url = "https://sc.ftqq.com/" + express.getSckey() + ".send";
            Map<String,Object> map =new HashMap<>();
            map.put("text","快递通知");
            map.put("desp",text);
            HttpRequest.post(url).form(map).execute();
            //酷推
        } else if (express.getType() == 1) {
            url = "https://push.xuthus.cc/send/" + express.getSkey();
            HttpRequest.post(url).form("c", text).execute();
        }


    }


}
