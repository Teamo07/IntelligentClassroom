package org.gec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.gec.model.Asset;
import org.gec.model.PageModel;
import org.springframework.stereotype.Repository;

public interface AssetMapper {

	/**
	 * 分页查询资产数据
	 * @param pageModel 封装分页信息
	 * @return
	 */
	@Select("select * from asset limit #{start}, #{pageSize}")
	@ResultType(Asset.class)
	List<Asset> getAsset(PageModel pageModel);
	
	/**
	 * 统计总的记录数
	 * @return
	 */
	@Select("select count(*) from asset")
	int count();
	
	/**
	 * 添加设备
	 * @param asset
	 */
	@Insert("insert into asset values(#{id}, #{name}, #{number}, #{rfid}, #{status})")
	int addAsset(Asset asset);
	
	/**
	 * 根据ID查询设备
	 * @param asset
	 * @return
	 */
	@Select("select * from asset where id = #{id}")
	Asset getAssetById(Asset asset);
	
	/**
	 * 修改设备
	 * @param asset
	 */
	@Update("update asset set name = #{name}, number = #{number}, rfid = #{rfid}, status = #{status} where id = #{id}")
	void updateAsset(Asset asset);
	
	@Delete("delete from asset where id = #{id}")
	void deleteAsset(Asset asset);

	@Select("select * from asset")
	List<Asset> getAllAsset();
}
