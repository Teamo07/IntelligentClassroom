package org.gec.smart.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.gec.smart.bean.*;

public class AnalysisUtil {

    public static Map<String,Object> analysis(String data){
        data = data.replaceAll("\\s*", "");
        Map<String,Object> map = null;
        int indexEF = data.indexOf("FE");
        if(indexEF == -1){
            map = new HashMap<String,Object>();
            System.out.println("return -1;");
            map.put("FE", -1);
            return map;
        }
        while(indexEF != -1){
            System.out.println("indexEF ->"+data.indexOf("FE"));
            int dataLen = Integer.valueOf(data.substring(indexEF + 2, indexEF + 4), 16) * 2;
            System.out.println("length ->"+dataLen);
            if(data.length() >= indexEF + dataLen){
                String dataRec = data.substring(indexEF, indexEF + dataLen);
                String device = dataRec.substring(4, 8);
                //灯光 控制解析
                if(device.equals("010C")) {
                    map = new HashMap<String,Object>();
                    LightLog light = new LightLog();
                    light.setId(UUID.randomUUID().toString());
                    light.setDeviceNo(1);
                    light.setCreateTime(new Date());
                    Integer d = Integer.valueOf(dataRec.substring(12, 14), 16) ;
                    if(d == 1) {
                        light.setOperation(true);
                    }else {
                        light.setOperation(false);
                    }
                    map.put("light", light);
                    return map;
                }
                //空调控制解析
                if(device.equals("010A")) {
                    map = new HashMap<String,Object>();
                    AirConditionerLog airConditioner = new AirConditionerLog();
                    airConditioner.setId(UUID.randomUUID().toString());
                    airConditioner.setDeviceNo(1);
                    airConditioner.setCreatetime(new Date());
                    Integer temperature = Integer.valueOf(dataRec.substring(12, 14), 16) ;
                    Integer d = Integer.valueOf(dataRec.substring(16, 18), 16) ;

                    if(d == 1) {
                        if(airConditioner.getHumidification()) {
                            airConditioner.setHumidification(false);
                        }else {
                            airConditioner.setHumidification(true);
                        }
                    }else if(d == 2) {
                        if(airConditioner.getHeating()) {
                            airConditioner.setHeating(false);
                        }else {
                            airConditioner.setHeating(true);
                        }
                    }else if(d == 3) {
                        if(airConditioner.getAeration()) {
                            airConditioner.setAeration(false);
                        }else {
                            airConditioner.setAeration(true);
                        }
                    }else if(d == 4) {
                        if(airConditioner.getOxygen()) {
                            airConditioner.setOxygen(false);
                        }else {
                            airConditioner.setOxygen(true);
                        }
                    }else if(d == 5) {
                        if(airConditioner.getFreshness()) {
                            airConditioner.setFreshness(false);
                        }else {
                            airConditioner.setFreshness(true);
                        }
                    }else if(d == 6) {
                        if(airConditioner.getSleeping()) {
                            airConditioner.setSleeping(false);
                        }else {
                            airConditioner.setSleeping(true);
                        }
                    }else if(d == 7) {
                        if(airConditioner.getTimer()<23) {
                            airConditioner.setTimer(airConditioner.getTimer()+1);
                        }else {
                            airConditioner.setTimer(0);
                        }
                    }else if(d == 8) {
                        airConditioner.setTemperature(temperature);
                    }
                    map.put("airConditioner", airConditioner);
                    return map;
                }

            } else {
                return null;
            }
        }
        return null;
    }
}
