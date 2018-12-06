package org.gec.service;

import org.gec.model.Attendance;

import java.util.List;

/**
 * @ClassName IAttendLogService
 * @Author LZM
 * @Date 2018/12/6 15:35
 * @Version 1.0
 **/
public interface IAttendLogService {

    public int getTotal() ;

    public List<Attendance> findAttendLog(Integer page, Integer rows) ;
}
