package com.ruoyi.activiti.cover;

import java.awt.Color;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.image.ProcessDiagramGenerator;

/**
 * <p>File：CustomProcessDiagramGenerator.java</p>
 * <p>Title: 自定义流程图画笔</p>
 * <p>Description:重写ProcessDiagramGenerator接口中的generateDiagram方法，增加color参数</p>
 * <p>Copyright: Copyright (c) 2020 2020年1月15日 下午6:30:17</p>
 * <p>Company: zmrit.com </p>
 * @author zmr
 * @version 1.0
 */
public interface ICustomProcessDiagramGenerator extends ProcessDiagramGenerator
{
    InputStream generateDiagram(BpmnModel bpmnModel, String imageType, List<String> highLightedActivities,
            List<String> highLightedFlows, String activityFontName, String labelFontName, String annotationFontName,
            ClassLoader customClassLoader, double scaleFactor, Color[] colors, Set<String> currIds);
}