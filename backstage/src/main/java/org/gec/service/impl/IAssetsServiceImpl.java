package org.gec.service.impl;

import org.gec.mapper.AssetMapper;
import org.gec.model.Asset;
import org.gec.model.PageModel;
import org.gec.service.IAssetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        int total = assetMapper.count();

        pageModel.setTotal(total);
        pageModel.setRows(data);
        return pageModel;
    }

    @Override
    public void addAsset(Asset asset) {
        asset.setId(UUID.randomUUID().toString()); //生成UUID唯一值
        assetMapper.addAsset(asset);
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
}
