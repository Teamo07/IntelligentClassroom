package org.gec.mapper;


import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.gec.model.AirConditionerLog;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AirConditionerLogMapper {
    @Select("select * from air_conditioner_log order by createtime desc limit #{start}, #{size}")
    @ResultType(AirConditionerLog.class)
    List<AirConditionerLog> getAir(Map map);

    @Select("select count(*) from air_conditioner_log")
    int count();

}
