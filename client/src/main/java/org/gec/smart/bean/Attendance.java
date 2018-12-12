package org.gec.smart.bean;

import java.util.Date;

public class Attendance {
    private String id;
    private String rfid;
    private Date createtime;
    private Boolean status;

    public Attendance() {}

    public Attendance(String id, String rfid, Date createtime, Boolean status) {
        this.id = id;
        this.rfid = rfid;
        this.createtime = createtime;
        this.status = status;
    }

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

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Attendance [id=" + id + ", rfid=" + rfid + ", createtime="
                + createtime + ", status=" + status + "]";
    }
}

