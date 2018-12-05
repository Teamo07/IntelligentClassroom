package org.gec.service;

import org.gec.model.Asset;
import org.gec.model.PageModel;

/**
 * @Author LZM
 * @Date 2018/12/5 15:43
 **/
public interface IAssetsService {

    PageModel findAsset(int page, int rows) ;

    void addAsset(Asset asset);

    Asset getAsset(String id);

    void updateAsset(Asset asset);

    void deleteAsset(String id);
}
