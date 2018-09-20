package com.example.demo.modules.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;

@TableName("tb_order")
@Data
@ApiModel("订单实体")
public class TbOrder implements Serializable{

    private static final long serialVersionUID = 1L;

    @TableId
    @ApiModelProperty("订单ID")
    private long orderId;
    @NotNull(message = "parentId不为空")
    @ApiModelProperty("父订单ID")
    private long orderParentId;
    @ApiModelProperty("订单编号")
    private String orderNo;
    @ApiModelProperty("父订单编号")
    private String orderParentNo;
    @ApiModelProperty("订单名称")
    private String orderName;
    @Future(message = "时间必须大于当前时间")
    @ApiModelProperty("订单时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderTime;
    @ApiModelProperty("订单完成时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date completeTime;
    @Email(message = "邮箱格式不对")
    @ApiModelProperty("客户名称")
    private String customeName;
    @ApiModelProperty("客户电话")
    private String customePhone;
    @ApiModelProperty("客户地址")
    private String customeAddress;
    @ApiModelProperty("订单描述")
    private String orderDesc;
    @ApiModelProperty("创建者")
    private String create;
    @ApiModelProperty("更新者")
    private String update;
    @Null
    @ApiModelProperty("创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @ApiModelProperty("更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
