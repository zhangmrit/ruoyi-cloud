package com.ruoyi.activiti.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.ruoyi.activiti.consts.ActivitiConstant;
import com.ruoyi.activiti.domain.BizBusiness;
import com.ruoyi.activiti.domain.BizPurchase;
import com.ruoyi.activiti.service.IBizBusinessService;
import com.ruoyi.activiti.service.IBizPurchaseService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.feign.RemoteUserService;

/**
 * 报销 提供者
 * 
 * @author ruoyi
 * @date 2020-01-07
 */
@RestController
@RequestMapping("purchase")
public class BizPurchaseController extends BaseController
{
    @Autowired
    private IBizPurchaseService purchaseService;

    @Autowired
    private IBizBusinessService bizBusinessService;

    @Autowired
    private RemoteUserService   remoteUserService;

    /**
     * 查询报销
     */
    @GetMapping("get/{id}")
    public R get(@PathVariable("id") String id)
    {
        return R.data(purchaseService.selectBizPurchaseById(id));
    }

    /**
     * 根据业务key获取数据
     * 
     * @param businessKey
     * @return
     * @author zmr
     */
    @GetMapping("biz/{businessKey}")
    public R biz(@PathVariable("businessKey") String businessKey)
    {
        BizBusiness business = bizBusinessService.selectBizBusinessById(businessKey);
        if (null != business)
        {
            BizPurchase purchase = purchaseService.selectBizPurchaseById(business.getTableId());
            return R.data(purchase);
        }
        return R.error("no record");
    }

    /**
     * 新增保存报销
     */
    @PostMapping("save")
    public R addSave(@RequestBody BizPurchase purchase)
    {
        int index = purchaseService.insertBizPurchase(purchase);
        if (index == 1)
        {
            BizBusiness business = initBusiness(purchase);
            bizBusinessService.insertBizBusiness(business);
            Map<String, Object> variables = Maps.newHashMap();
            // 这里可以设置各个负责人，key跟模型的代理变量一致
            // variables.put("fm", 1l);
            // variables.put("fc", 1l);
            // variables.put("gm", 1l);
            variables.put("money", purchase.getMoney());
            bizBusinessService.startProcess(business, variables);
        }
        return toAjax(index);
    }

    /**
     * biz构造业务信息
     * @param purchase
     * @return
     * @author zmr
     */
    private BizBusiness initBusiness(BizPurchase purchase)
    {
        BizBusiness business = new BizBusiness();
        business.setTableId(purchase.getId().toString());
        business.setProcDefId(purchase.getProcDefId());
        business.setTitle(purchase.getTitle());
        business.setProcName(purchase.getProcName());
        long userId = getCurrentUserId();
        business.setUserId(userId);
        SysUser user = remoteUserService.selectSysUserByUserId(userId);
        business.setApplyer(user.getUserName());
        business.setStatus(ActivitiConstant.STATUS_DEALING);
        business.setResult(ActivitiConstant.RESULT_DEALING);
        business.setApplyTime(new Date());
        return business;
    }
}
