package org.gec.smart.bean;


public class Student {

    private String id;

    private String name;

    private String number;

    private String rfid;

    private Integer status;

    public Student() {
    }

    public Student(String id, String name, String number, String rfid, Integer status) {
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
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
