package com.example.demo.modules.controller;

import com.example.demo.common.PageUtils;
import com.example.demo.common.R;
import com.example.demo.modules.entity.TbOrder;
import com.example.demo.modules.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/order")
@Api("订单管理")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    /**
     * 查询订单信息
     */
    @GetMapping("/query")
    @ApiOperation("根据orderID、orderNo查询订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId",value = "订单号",required = true,dataType = "String"),
            @ApiImplicitParam(name = "orderNo",value = "订单编号",required = true,dataType = "String")
    })
    public R queryOrder(@RequestParam Map<String,Object> param){
        TbOrder tbOrder = new TbOrder();
        if(!param.isEmpty()){
            tbOrder.setOrderId(Integer.valueOf(param.get("orderId").toString()));
            tbOrder.setOrderNo(param.get("orderNo").toString());
        }
        TbOrder order = orderService.queryOrder(tbOrder);
        return R.ok().put("order", order);
    }

    /*@GetMapping("/query")
    @ApiOperation("根据orderID、orderNo查询订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId",value = "订单号",required = true,paramType = "query"),
            @ApiImplicitParam(name = "orderNo",value = "订单编号",required = true,paramType = "query")
    })
    public R queryOrder(@RequestParam("orderId") String orderId,@RequestParam("orderNo") String orderNo){
        TbOrder tbOrder = new TbOrder();
        tbOrder.setOrderId(Integer.valueOf(orderId));
        tbOrder.setOrderNo(orderNo);
        TbOrder order = orderService.queryOrder(tbOrder);
        return R.ok().put("order", order);
    }*/

    @PostMapping("/save")
    @ApiOperation("保存订单")
    public R saveOrder(@RequestBody @Validated TbOrder tbOrder){
        logger.info(tbOrder.getCompleteTime().toString());
        int ok = orderService.saveOrder(tbOrder);
        return R.ok();
    }

    /**
     * 查询订单信息-分页
     */
    @GetMapping("/qpage")
    @ApiOperation("订单分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "当前页数",required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "limit",value = "每页大小",required = true,dataType = "Integer")
    })
    public R queryOrderPage(@RequestParam Map<String, Object> param){
        param.put("page","2");
        param.put("limit","2");
        PageUtils page = orderService.queryOrderPage(param);
        return R.ok().put("pages", page);
    }

}
