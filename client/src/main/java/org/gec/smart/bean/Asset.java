package org.gec.smart.bean;

public class Asset {

    private String id;

    private String name;

    private String number;

    private String rfid;

    private Integer status;

    public Asset() {}

    public Asset(String id, String name, String number, String rfid,
                 Integer status) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.rfid = rfid;
        this.status = status;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid == null ? null : rfid.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", rfid='" + rfid + '\'' +
                ", status=" + status +
                '}';
    }

}
