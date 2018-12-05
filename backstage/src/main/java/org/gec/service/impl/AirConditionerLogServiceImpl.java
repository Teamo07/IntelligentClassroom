package org.gec.service.impl;


import org.gec.mapper.AirConditionerLogMapper;
import org.gec.model.AirConditionerLog;
import org.gec.service.IAirConditionerLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AirConditionerLogServiceImpl implements IAirConditionerLogService {
    @Autowired
    private AirConditionerLogMapper airConditionerLogMapper;

    @Override
    public List<AirConditionerLog> findAir(int page, int size) {
        int start = (page - 1) * size;
        Map map = new HashMap();
        map.put("start", start);
        map.put("size", size);
        return airConditionerLogMapper.getAir(map);
    }

    @Override
    public int getTotal() {
        return airConditionerLogMapper.count();
    }


}
