package org.gec.smart.bean;

import java.util.Date;

public class AssetLog {

    private String id;
    private String rfid;
    private Date createTime;
    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer isStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public AssetLog() {
    }

    public AssetLog(String id, String rfid, Date createTime, Integer status) {
        this.id = id;
        this.rfid = rfid;
        this.createTime = createTime;
        this.status = status;
    }
}
