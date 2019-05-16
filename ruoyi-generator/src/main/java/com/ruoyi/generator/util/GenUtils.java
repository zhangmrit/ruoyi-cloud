package com.ruoyi.generator.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.VelocityContext;

import com.ruoyi.generator.domain.ColumnInfo;
import com.ruoyi.generator.domain.GenQo;
import com.ruoyi.generator.domain.TableInfo;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 代码生成器 工具类
 */
public class GenUtils
{
    /** 项目空间路径 */
    private static final String       MAVEN_PATH  = "/src/main/java/";

    /** 类型转换 */
    public static Map<String, String> javaTypeMap = new HashMap<String, String>();

    /**
     * 设置列信息
     */
    public static List<ColumnInfo> transColums(List<ColumnInfo> columns)
    {
        // 列信息
        List<ColumnInfo> columsList = new ArrayList<>();
        for (ColumnInfo column : columns)
        {
            // 列名转换成Java属性名
            String attrName = StrUtil.toCamelCase(column.getColumnName());
            column.setAttrName(attrName);
            column.setAttrname(StringUtils.uncapitalize(attrName));
            // 列的数据类型，转换成Java类型
            String attrType = javaTypeMap.get(column.getDataType());
            column.setAttrType(attrType);
            columsList.add(column);
        }
        return columsList;
    }

    /**
     * 获取模板信息
     * 
     * @return 模板列表
     */
    public static VelocityContext getVelocityContext(TableInfo table, GenQo gq)
    {
        // java对象数据传递到模板文件vm
        VelocityContext velocityContext = new VelocityContext();
        String packageName = gq.getPackname();
        velocityContext.put("tableName", table.getTableName());
        velocityContext.put("tableComment", replaceKeyword(table.getTableComment()));
        velocityContext.put("primaryKey", table.getPrimaryKey());
        velocityContext.put("className", table.getClassName());
        velocityContext.put("classname", table.getClassname());
        velocityContext.put("moduleName", gq.getModulename());
        velocityContext.put("MODULENAME",  gq.getModulename().toUpperCase());
        velocityContext.put("columns", table.getColumns());
        velocityContext.put("package", packageName + "." + gq.getModulename());
        velocityContext.put("author", gq.getAuthor());
        velocityContext.put("datetime", DateUtil.formatDate(new Date()));
        return velocityContext;
    }

    /**
     * 获取模板信息
     * @param gq 
     * 
     * @return 模板列表
     */
    public static List<String> getTemplates(GenQo gq)
    {
        List<String> templates = new ArrayList<String>();
        templates.add("vm/java/domain.java.vm");
        templates.add("vm/java/Mapper.java.vm");
        templates.add("vm/java/Service.java.vm");
        templates.add("vm/java/ServiceImpl.java.vm");
        templates.add("vm/java/Controller.java.vm");
        templates.add("vm/java/Client.java.vm");
        templates.add("vm/java/ClientFallback.java.vm");
        templates.add("vm/java/ClientController.java.vm");
        templates.add("vm/xml/Mapper.xml.vm");
        if (gq.getPageSwitch())
        {
            templates.add("vm/html/list.html.vm");
            templates.add("vm/html/add.html.vm");
            templates.add("vm/html/edit.html.vm");
        }
        if (gq.getSqlSwitch())
        {
            templates.add("vm/sql/sql.vm");
        }
        return templates;
    }

    /**
     *  表名转换成Java类名
     * @param tableName 表名
     * @param gq 
     * @return
     */
    public static String tableToJava(String tableName, GenQo gq)
    {
        if (gq.getAutoremovepre())
        {
            tableName = tableName.substring(tableName.indexOf("_") + 1);
        }
        if (StringUtils.isNotEmpty(gq.getTableprefix()))
        {
            tableName = tableName.replace(gq.getTableprefix(), "");
        }
        return StrUtil.upperFirst(StrUtil.toCamelCase(tableName));
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String template, TableInfo table, GenQo gq)
    {
        // 小写类名
        String classname = table.getClassname();
        // 大写类名
        String className = table.getClassName();
        String moduleName = gq.getModulename();
        String javaPath = gq.getProjectpath() + MAVEN_PATH + gq.getPackname() + "/" + moduleName + "/";
        javaPath = javaPath.replace(".", "/");
        String mybatisPath = gq.getProjectpath() + "/" + gq.getMapperpath() + "/"+ moduleName;
        String htmlPath = gq.getProjectpath() + "/" + gq.getTemplatepath() + "/"+ moduleName + "/" + classname;
        if (template.contains("domain.java.vm"))
        {
            return javaPath + "entity" + "/" + className + ".java";
        }
        if (template.contains("Mapper.java.vm"))
        {
            return javaPath + "mapper" + "/" + className + "Mapper.java";
        }
        if (template.contains("Service.java.vm"))
        {
            return javaPath + "service" + "/" + "I" + className + "Service.java";
        }
        if (template.contains("ServiceImpl.java.vm"))
        {
            return javaPath + "service" + "/" + "impl/" + className + "ServiceImpl.java";
        }
        if (template.contains("Client.java.vm"))
        {
            return javaPath + "feign" + "/" + "I" + className + "Client.java";
        }
        if (template.contains("ClientFallback.java.vm"))
        {
            return javaPath + "feign" + "/" + "factory/" + className + "ClientFallbackFactory.java";
        }
        if (template.contains("ClientController.java.vm"))
        {
            return javaPath + "client" + "/" + className + "Client.java";
        }
        if (template.contains("Controller.java.vm"))
        {
            return javaPath + "controller" + "/" + className + "Controller.java";
        }
        if (template.contains("Mapper.xml.vm"))
        {
            return mybatisPath + "/" + className + "Mapper.xml";
        }
        if (template.contains("list.html.vm"))
        {
            return htmlPath + "/" + classname + ".html";
        }
        if (template.contains("add.html.vm"))
        {
            return htmlPath + "/add.html";
        }
        if (template.contains("edit.html.vm"))
        {
            return htmlPath + "/edit.html";
        }
        if (template.contains("sql.vm"))
        {
            return classname + "Menu.sql";
        }
        return null;
    }

    public static String replaceKeyword(String keyword)
    {
        String keyName = keyword.replaceAll("(?:表|信息)", "");
        return keyName;
    }

    static
    {
        javaTypeMap.put("tinyint", "Integer");
        javaTypeMap.put("smallint", "Integer");
        javaTypeMap.put("mediumint", "Integer");
        javaTypeMap.put("int", "Integer");
        javaTypeMap.put("integer", "integer");
        javaTypeMap.put("bigint", "Long");
        javaTypeMap.put("float", "Float");
        javaTypeMap.put("double", "Double");
        javaTypeMap.put("decimal", "BigDecimal");
        javaTypeMap.put("bit", "Boolean");
        javaTypeMap.put("char", "String");
        javaTypeMap.put("varchar", "String");
        javaTypeMap.put("tinytext", "String");
        javaTypeMap.put("text", "String");
        javaTypeMap.put("mediumtext", "String");
        javaTypeMap.put("longtext", "String");
        javaTypeMap.put("time", "Date");
        javaTypeMap.put("date", "Date");
        javaTypeMap.put("datetime", "Date");
        javaTypeMap.put("timestamp", "Date");
    }

    public static void main(String[] args)
    {
        System.out.println(StrUtil.toCamelCase("user_name"));
        System.out.println(replaceKeyword("岗位信息表"));
    }
}
