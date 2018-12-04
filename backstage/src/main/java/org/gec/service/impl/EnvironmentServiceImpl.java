package org.gec.service.impl;

import org.gec.model.Environment;
import org.gec.mapper.EnvironmentMapper;
import org.gec.service.IEnvironmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EnvironmentServiceImpl implements IEnvironmentService {

    @Autowired
    private EnvironmentMapper environmentMapper;

    @Override
    public List<Environment> findEnv(int page, int size) {
        int start = (page - 1) * size;
        Map map = new HashMap();
        map.put("start", start);
        map.put("size", size);
        return environmentMapper.getEnv(map);
    }

    @Override
    public int getTotal() {
        return environmentMapper.count();
    }
}
