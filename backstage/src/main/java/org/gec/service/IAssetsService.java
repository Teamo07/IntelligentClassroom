package org.gec.service;

import org.gec.model.Asset;
import org.gec.model.PageModel;

import java.util.List;

/**
 * @Author LZM
 * @Date 2018/12/5 15:43
 **/
public interface IAssetsService {

    PageModel findAsset(int page, int rows) ;

    boolean addAsset(Asset asset);

    Asset getAsset(String id);

    void updateAsset(Asset asset);

    void deleteAsset(String id);

    List<Asset> findAsset();

    /**
     批量添加设备
     @param assets
     */
    boolean addAsset(List<Asset> assets);
}
