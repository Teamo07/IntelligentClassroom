package org.gec.service.impl;

import org.gec.mapper.LightLogMapper;
import org.gec.model.LightLog;
import org.gec.service.ILightLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LightLogServiceImpl implements ILightLogService  {

    @Autowired
    private LightLogMapper lightLogMapper;

    @Override
    public List<LightLog> findLightLog(int page, int size) {
        int start = (page - 1) * size;
        Map map = new HashMap();
        map.put("start", start);
        map.put("size", size);
        return lightLogMapper.getLightLog(map);
    }

    @Override
    public int getTotal() {
        return lightLogMapper.count();
    }
}
