package com.ruoyi.web.controller.system;


import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysOss;
import com.ruoyi.system.feign.ISysOssClient;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 文件上传 信息操作处理
 * 
 * @author zmr
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/system/sysOss")
public class SysOssController extends BaseController
{
    private String prefix = "system/sysOss";
	
	@Autowired
	private ISysOssClient sysOssClient;
	
	@RequiresPermissions("system:sysOss:view")
	@GetMapping()
	public String sysOss()
	{
	    return prefix + "/sysOss";
	}
	
	/**
	 * 查询文件上传列表
	 */
	@RequiresPermissions("system:sysOss:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SysOss sysOss)
	{
		PageDomain page = TableSupport.buildPageRequest();
        List<SysOss> list = sysOssClient.selectSysOssList(sysOss,page);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出文件上传列表
	 */
	@RequiresPermissions("system:sysOss:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysOss sysOss)
    {
    	PageDomain page = TableSupport.buildPageRequest();
    	List<SysOss> list = sysOssClient.selectSysOssList(sysOss,page);
        ExcelUtil<SysOss> util = new ExcelUtil<SysOss>(SysOss.class);
        return util.exportExcel(list, "sysOss");
    }
	
	/**
	 * 新增文件上传
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存文件上传
	 */
	@RequiresPermissions("system:sysOss:add")
	@Log(title = "文件上传", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SysOss sysOss)
	{		
		return toAjax(sysOssClient.insertSysOss(sysOss));
	}

	/**
	 * 修改文件上传
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		SysOss sysOss = sysOssClient.selectSysOssById(id);
		mmap.put("sysOss", sysOss);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存文件上传
	 */
	@RequiresPermissions("system:sysOss:edit")
	@Log(title = "文件上传", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SysOss sysOss)
	{		
		return toAjax(sysOssClient.updateSysOss(sysOss));
	}
	
	/**
	 * 删除文件上传
	 */
	@RequiresPermissions("system:sysOss:remove")
	@Log(title = "文件上传", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sysOssClient.deleteSysOssByIds(ids));
	}
	
}
