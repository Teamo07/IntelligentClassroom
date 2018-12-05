package org.gec.mapper;

import org.gec.model.Environment;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EnvironmentMapper {

    @Select("select * from environment order by createtime desc limit #{start}, #{size}")
    @ResultType(Environment.class)
    List<Environment> getEnv(Map map);

    @Select("select count(*) from environment")
    int count();

}
