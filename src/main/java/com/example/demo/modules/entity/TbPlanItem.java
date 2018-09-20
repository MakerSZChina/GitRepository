package com.example.demo.modules.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName("tb_plan_item")
@Data
public class TbPlanItem implements Serializable{

    private static final long serialVersionUID = 1L;

    @TableId
    private long planItemId;
    private long planId;
    @TableField(exist = false)
    private String planCode;
    @TableField(exist = false)
    private String planName;
    private long orderId;
    @TableField(exist = false)
    private String orderNo;
    @TableField(exist = false)
    private String orderName;
    private long productId;
    @TableField(exist = false)
    private String productNo;
    @TableField(exist = false)
    private String productName;
    private long amount;
    private long waitAmount;
    private long processAmount;
    private long alreadyAmount;
    private double itemPrice;
    private double demagePrice;
    private String status;
    @TableField(exist = false)
    private String itemType;
    
}
