package com.ruoyi.activiti.mapper;

import java.util.List;
import java.util.Map;

public interface ProcessMapper {

    List<Map<String, Object>> selectModels();

    List<Map<String, Object>> selectProcess();

}