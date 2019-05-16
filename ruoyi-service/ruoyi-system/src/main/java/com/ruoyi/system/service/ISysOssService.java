package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysOss;
import java.util.List;

/**
 * 文件上传 服务层
 * 
 * @author zmr
 * @date 2019-05-16
 */
public interface ISysOssService 
{
	/**
     * 查询文件上传信息
     * 
     * @param id 文件上传ID
     * @return 文件上传信息
     */
	public SysOss selectSysOssById(Long id);
	
	/**
     * 查询文件上传列表
     * 
     * @param sysOss 文件上传信息
     * @return 文件上传集合
     */
	public List<SysOss> selectSysOssList(SysOss sysOss);
	
	/**
     * 新增文件上传
     * 
     * @param sysOss 文件上传信息
     * @return 结果
     */
	public int insertSysOss(SysOss sysOss);
	
	/**
     * 修改文件上传
     * 
     * @param sysOss 文件上传信息
     * @return 结果
     */
	public int updateSysOss(SysOss sysOss);
		
	/**
     * 删除文件上传信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSysOssByIds(String ids);
	
}
