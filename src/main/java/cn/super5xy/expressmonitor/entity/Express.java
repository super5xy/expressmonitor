package cn.super5xy.expressmonitor.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (Express)实体类
 *
 * @author makejava
 * @since 2020-08-09 08:54:59
 */
public class Express implements Serializable {
    private static final long serialVersionUID = -16200168904429402L;
    /**
     * 快递单号
     */
    private String expressNumber;
    /**
     * 快递公司
     */
    private String expressName;
    /**
     * 记录当前的状态
     */
    private Integer size;
    /**
     * server酱key
     */
    private String sckey;
    /**
     * 酷推key
     */
    private String skey;
    /**
     * 记录key种类 0 server酱 1酷推
     */
    private Integer type;
    /**
     * 时间
     */
    private Date date;
    /**
     * 状态 1已签收 0未签收
     */
    private Integer state;


    public String getExpressNumber() {
        return expressNumber;
    }

    public void setExpressNumber(String expressNumber) {
        this.expressNumber = expressNumber;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getSckey() {
        return sckey;
    }

    public void setSckey(String sckey) {
        this.sckey = sckey;
    }

    public String getSkey() {
        return skey;
    }

    public void setSkey(String skey) {
        this.skey = skey;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Express{" +
                "expressNumber=" + expressNumber +
                ", expressName='" + expressName + '\'' +
                ", size=" + size +
                ", sckey='" + sckey + '\'' +
                ", skey='" + skey + '\'' +
                ", type=" + type +
                ", date=" + date +
                ", state=" + state +
                '}';
    }
}