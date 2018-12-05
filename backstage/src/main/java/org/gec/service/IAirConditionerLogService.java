package org.gec.service;

import org.gec.model.AirConditionerLog;

import java.util.List;

public interface IAirConditionerLogService {
    /**
     * 分页查询监测数据
     * @param page 第几页
     * @param size 每页记录数
     * @return
     */
    List<AirConditionerLog> findAir(int page, int size);

    /**
     * 查询总记录数
     * @return
     */
    int getTotal();
}
