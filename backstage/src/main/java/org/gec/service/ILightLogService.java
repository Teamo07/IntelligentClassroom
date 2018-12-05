package org.gec.service;

import org.gec.model.LightLog;
import java.util.List;

public interface ILightLogService {

    /**
     *
     * @param page 第几页
     * @param rows  每页的行数
     * @return  返回灯光控制日志
     */

    List<LightLog> findLightLog(int page, int rows);

    /**
     *
     * @return 数据库灯光控制日志记录总数
     */
    int getTotal();

}
