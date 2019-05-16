package com.ruoyi.generator.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.generator.domain.ColumnInfo;
import com.ruoyi.generator.domain.GenQo;
import com.ruoyi.generator.domain.TableInfo;
import com.ruoyi.generator.mapper.GenMapper;
import com.ruoyi.generator.service.IGenService;
import com.ruoyi.generator.util.Constants;
import com.ruoyi.generator.util.GenUtils;
import com.ruoyi.generator.util.VelocityInitializer;


/**
 * 代码生成 服务层处理
 * 
 * @author ruoyi
 */
@Service
public class GenServiceImpl implements IGenService
{
    private static final Logger log = LoggerFactory.getLogger(GenServiceImpl.class);

    @Autowired
    private GenMapper           genMapper;

    /**
     * 查询ry数据库表信息
     * 
     * @param tableInfo 表信息
     * @return 数据库表列表
     */
    @Override
    public List<TableInfo> selectTableList(TableInfo tableInfo)
    {
        return genMapper.selectTableList(tableInfo);
    }

    /**
     * 生成代码
     * 
     * @param tableName 表名称
     * @return 数据
     */
    @Override
    public byte[] generatorCode(String tableName, GenQo gq)
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        // 查询表信息
        TableInfo table = genMapper.selectTableByName(tableName);
        // 查询列信息
        List<ColumnInfo> columns = genMapper.selectTableColumnsByName(tableName);
        generatorCode(table, columns, gq, zip);
        if (gq.getZippack())
        {
            // 生成代码
            IOUtils.closeQuietly(zip);
            return outputStream.toByteArray();
        }
        return null;
    }

    /**
     * 批量生成代码
     * 
     * @param tableNames 表数组
     * @return 数据
     */
    @Override
    public byte[] generatorCode(String[] tableNames, GenQo gq)
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableName : tableNames)
        {
            // 查询表信息
            TableInfo table = genMapper.selectTableByName(tableName);
            // 查询列信息
            List<ColumnInfo> columns = genMapper.selectTableColumnsByName(tableName);
            // 生成代码
            generatorCode(table, columns, gq, zip);
        }
        if (gq.getZippack())
        {
            IOUtils.closeQuietly(zip);
            return outputStream.toByteArray();
        }
        return null;
    }

    /**
     * 生成代码
     */
    public void generatorCode(TableInfo table, List<ColumnInfo> columns, GenQo gq, ZipOutputStream zip)
    {
        // 表名转换成Java属性名
        String className = GenUtils.tableToJava(table.getTableName(), gq);
        table.setClassName(className);
        table.setClassname(StringUtils.uncapitalize(className));
        // 列信息
        table.setColumns(GenUtils.transColums(columns));
        // 设置主键
        table.setPrimaryKey(table.getColumnsLast());
        VelocityInitializer.initVelocity();
        VelocityContext context = GenUtils.getVelocityContext(table, gq);
        // 获取模板列表
        List<String> templates = GenUtils.getTemplates(gq);
        for (String template : templates)
        {
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, Constants.UTF8);
            tpl.merge(context, sw);
            try
            {
                String filename = GenUtils.getFileName(template, table, gq);
                if (gq.getZippack())
                {
                    // 添加到zip
                    zip.putNextEntry(new ZipEntry(filename));
                    IOUtils.write(sw.toString(), zip, Constants.UTF8);
                    IOUtils.closeQuietly(sw);
                    zip.closeEntry();
                }
                else
                {
                    FileUtils.writeStringToFile(new File(filename), sw.toString(), Constants.UTF8);
                }
            }
            catch (IOException e)
            {
                log.error("渲染模板失败，表名：" + table.getTableName(), e);
            }
        }
    }
}
