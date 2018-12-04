package org.gec.service;

import java.util.List;
import org.gec.model.Environment;
public interface IEnvironmentService {

    /**
     *
     * @param page 第几页
     * @param size 每页记录数
     * @return 数据库查询结果
     */
    List<Environment> findEnv(int page, int size);

    /**
     *
     * @return 数据库记录数
     */
    int getTotal();

}
