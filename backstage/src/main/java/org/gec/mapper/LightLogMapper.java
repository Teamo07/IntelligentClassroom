package org.gec.mapper;


import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.gec.model.LightLog;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 灯光控制日志读取
 */

@Repository
public interface LightLogMapper {

    @Select("select * from light_log order by createtime desc limit #{start}, #{size}")
    @ResultType(LightLog.class)
    List<LightLog> getLightLog(Map map);

    @Select("select count(*) from light_log")
    int count();

}
