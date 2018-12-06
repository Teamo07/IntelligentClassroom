package org.gec.mapper;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.gec.model.Attendance;
import org.gec.model.LightLog;

import java.util.List;
import java.util.Map;

/**
 * @Author LZM
 * @Date 2018/12/6 15:37
 **/
public interface AttendLogMapper {
    @Select("select * from attendance order by createtime desc limit #{start}, #{size}")
    @ResultType(Attendance.class)
    List<Attendance> getAttendLog(Map map);

    @Select("select count(*) from attendance")
    int count();

}
