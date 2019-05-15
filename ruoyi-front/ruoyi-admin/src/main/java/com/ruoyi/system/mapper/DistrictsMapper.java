package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Districts;
import java.util.List;	

/**
 * 地区 数据层
 * 
 * @author ruoyi
 * @date 2018-12-19
 */
public interface DistrictsMapper 
{
	/**
     * 查询地区信息
     * 
     * @param id 地区ID
     * @return 地区信息
     */
	public Districts selectDistrictsById(Integer id);
	
	/**
     * 查询地区列表
     * 
     * @param districts 地区信息
     * @return 地区集合
     */
	public List<Districts> selectDistrictsList(Districts districts);
	
	/**
     * 新增地区
     * 
     * @param districts 地区信息
     * @return 结果
     */
	public int insertDistricts(Districts districts);
	
	/**
     * 修改地区
     * 
     * @param districts 地区信息
     * @return 结果
     */
	public int updateDistricts(Districts districts);
	
	/**
     * 删除地区
     * 
     * @param id 地区ID
     * @return 结果
     */
	public int deleteDistrictsById(Integer id);
	
	/**
     * 批量删除地区
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDistrictsByIds(String[] ids);
	
}