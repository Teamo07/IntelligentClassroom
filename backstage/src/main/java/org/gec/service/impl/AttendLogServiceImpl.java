package org.gec.service.impl;

import org.gec.mapper.AttendLogMapper;
import org.gec.model.Attendance;
import org.gec.service.IAttendLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AttendLogServiceImpl
 * @Author LZM
 * @Date 2018/12/6 15:36
 * @Version 1.0
 **/
@Service
public class AttendLogServiceImpl implements IAttendLogService {

    @Autowired
    private AttendLogMapper attendLogMapper;


    @Override
    public int getTotal() {

        return attendLogMapper.count();
    }

    @Override
    public List<Attendance> findAttendLog(Integer page, Integer size) {
        int start = (page - 1) * size;
        Map map = new HashMap();
        map.put("start", start);
        map.put("size", size);
        return attendLogMapper.getAttendLog(map);
    }
}
