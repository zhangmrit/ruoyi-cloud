package com.ruoyi.system.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.system.domain.SysPost;
import com.ruoyi.system.service.ISysPostService;

/**
 * 岗位 提供者
 * 
 * @author zmr
 * @date 2019-05-20
 */
@RestController
@RequestMapping("/sys/sysPost")
public class SysPostClient extends BaseController
{
	
	@Autowired
	private ISysPostService sysPostService;
	
	/**
	 * 查询岗位
	 */
	@GetMapping("get/{postId}")
	public SysPost get(@PathVariable("postId") Long postId)
	{
		return sysPostService.selectPostById(postId);
		
	}
	
	/**
	 * 查询岗位列表
	 */
	@PostMapping("list")
	public List<SysPost> list(SysPost sysPost, PageDomain page)
	{
		startPage(page);
        return sysPostService.selectPostList(sysPost);
	}
	
	
	/**
	 * 新增保存岗位
	 */
	@PostMapping("save")
	public int addSave(SysPost sysPost)
	{		
		return sysPostService.insertPost(sysPost);
	}

	/**
	 * 修改保存岗位
	 */
	@PostMapping("update")
	public int editSave(SysPost sysPost)
	{		
		return sysPostService.updatePost(sysPost);
	}
	
	/**
	 * 删除岗位
	 * @throws Exception 
	 */
	@PostMapping("remove")
	public int remove(String ids) throws Exception
	{		
		return sysPostService.deletePostByIds(ids);
	}
	
}
