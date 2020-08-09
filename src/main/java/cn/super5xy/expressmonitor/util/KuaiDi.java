package cn.super5xy.expressmonitor.util;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.List;

/**
 * @Author: super5xy
 * @Date: 2020/8/8 19:08
 * 抓包kuaidi.com
 * 容易出现封ip 建议用付费api
 */
public class KuaiDi {

    /**
     * 根据单号自动判断物流公司
     *
     * @param comNum 物流公司单号
     * @return
     */
    public static String autoComNum(String comNum) {
        String body = HttpRequest.post("http://www.kuaidi.com/index-ajaxselectinfo-" + comNum + ".html")
                .execute()
                .body();
        JSONObject obj = JSONUtil.parseObj(body);
        //获取第一个是权重最高的，也就是最有可能的。
        JSONObject jsonObject = obj.getJSONObject("0");
        String exname = jsonObject.getStr("exname");
        return exname;
    }

    /**
     * 查询物流
     *
     * @param comCode 物流公司
     * @param comNum  单号
     */
    public static List query(String comCode, String comNum) {
        String query = "http://www.kuaidi.com/index-ajaxselectcourierinfo-" + comCode + "-" + comNum + ".html";
        String body = HttpRequest.post(query)
//                .header(Header.HOST, "www.kuaidi.com")
                .header(Header.REFERER, "http://www.kuaidi.com/")
//                .header(Header.ORIGIN, "http://www.kuaidi.com")
                .execute()
                .body();
        JSONObject obj = JSONUtil.parseObj(body);
//        System.out.println(obj);
        JSONArray data = obj.getJSONArray("data");

        return data;
    }

    /**
     * 重载 自动判断物流公司
     *
     * @param comNum
     */
    public static List query(String comNum) {
        return query(autoComNum(comNum), comNum);
    }

    public static boolean check(String comNum){
        String query = "http://www.kuaidi.com/index-ajaxselectcourierinfo-" + autoComNum(comNum) + "-" + comNum + ".html";
        String body = HttpRequest.post(query)
//                .header(Header.HOST, "www.kuaidi.com")
                .header(Header.REFERER, "http://www.kuaidi.com/")
//                .header(Header.ORIGIN, "http://www.kuaidi.com")
                .execute()
                .body();
        if (body.contains("false")){
            return false;
        }
        return true;
    }


}
