package com.ruoyi.generator.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.generator.domain.GenQo;
import com.ruoyi.generator.domain.TableInfo;
import com.ruoyi.generator.service.IGenService;
import com.ruoyi.generator.util.Page;
import com.ruoyi.generator.util.R;

/**
 * 代码生成 操作处理
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/tool/gen")
public class GenController
{
    @Autowired
    private IGenService genService;

    @PostMapping("/list")
    @ResponseBody
    public Page list(TableInfo tableInfo, Page page)
    {
        PageHelper.startPage(page.getPage(), page.getPageSize(), "create_time desc");
        List<TableInfo> list = genService.selectTableList(tableInfo);
        page.setRows(list);
        page.setTotal(new PageInfo<TableInfo>(list).getTotal());
        return page;
    }

    /**
     * 生成代码
     */
    @RequestMapping("/genCode/{tableName}")
    @ResponseBody
    public R genCode(HttpServletResponse response, @PathVariable("tableName") String tableName, GenQo gq)
            throws IOException
    {
        byte[] data = genService.generatorCode(tableName, gq);
        if (null != data)
        {
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=\"ruoyi.zip\"");
            response.addHeader("Content-Length", "" + data.length);
            response.setContentType("application/octet-stream; charset=UTF-8");
            IOUtils.write(data, response.getOutputStream());
        }
        return R.ok("代码已经生成");
    }

    /**
     * 批量生成代码
     */
    @RequestMapping("/batchGenCode")
    @ResponseBody
    public R batchGenCode(HttpServletResponse response, String tables, GenQo gq) throws IOException
    {
        byte[] data = genService.generatorCode(tables, gq);
        if (null != data)
        {
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=\"ruoyi.zip\"");
            response.addHeader("Content-Length", "" + data.length);
            response.setContentType("application/octet-stream; charset=UTF-8");
            IOUtils.write(data, response.getOutputStream());
        }
        return R.ok("代码已经生成");
    }
}
