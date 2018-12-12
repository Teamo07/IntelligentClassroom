package org.gec.service.impl;

import org.gec.mapper.AssetMapper;
import org.gec.model.Asset;
import org.gec.model.AssetLog;
import org.gec.model.PageModel;
import org.gec.service.IAssetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName IAssetsService
 * @Author LZM
 * @Date 2018/12/5 15:43
 * @Version 1.0
 **/
@Service
public class IAssetsServiceImpl implements IAssetsService {

    @Autowired
    private AssetMapper assetMapper;

    @Override
    public PageModel findAsset(int page, int rows) {
        //计算开始查询的位置
        int start = (page - 1) * rows;
        PageModel pageModel = new PageModel();
        pageModel.setStart(start);;
        pageModel.setPageSize(rows);
        List<Asset> data = assetMapper.getAsset(pageModel);
        int total = assetMapper.assetCount();

        pageModel.setTotal(total);
        pageModel.setRows(data);
        return pageModel;
    }

    @Override
    public PageModel findAssetLog(int page, int rows) {
        //计算开始查询的位置
        int start = (page - 1) * rows;
        PageModel pageModel = new PageModel();
        pageModel.setStart(start);
        pageModel.setPageSize(rows);
        List<AssetLog> data = assetMapper.getAssetLog(pageModel);
        int total = assetMapper.assetLogCount();
        pageModel.setTotal(total);
        pageModel.setRows(data);
        return pageModel;
    }

    @Override
    public boolean addAsset(Asset asset) {
        //生成UUID唯一值
        asset.setId(UUID.randomUUID().toString());
        if(assetMapper.addAsset(asset)==1){
            return true;
        }
        return false;
    }

    @Override
    public boolean addAssetLog(AssetLog assetLog) {
        assetLog.setId(UUID.randomUUID().toString());
        assetLog.setCreateTime(new Date());
        if(assetMapper.addAssetLog(assetLog)==1){
            return true;
        }
        return false;
    }

    @Override
    public Asset getAsset(String id) {
        Asset asset = new Asset();
        asset.setId(id);
        return assetMapper.getAssetById(asset);
    }

    @Override
    public void updateAsset(Asset asset) {
        assetMapper.updateAsset(asset);
    }

    @Override
    public void deleteAsset(String id) {
        Asset asset = new Asset();
        asset.setId(id);
        assetMapper.deleteAsset(asset);
    }

    @Override
    public List<Asset> findAsset() {
        return assetMapper.getAllAsset();
    }


    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean addAsset(List<Asset> assets) {
        int status=0;
        for (int i = 0; i < assets.size(); i++) {
            if (addAsset(assets.get(i))){
                status++;
            }
        }
        return status==assets.size() ? true : false;
    }
}
