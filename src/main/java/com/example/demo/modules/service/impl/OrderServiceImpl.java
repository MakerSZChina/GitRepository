package com.example.demo.modules.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.common.PageUtils;
import com.example.demo.common.Query;
import com.example.demo.config.annotation.useDataSource;
import com.example.demo.modules.entity.TbOrder;
import com.example.demo.modules.mapper.OrderMapper;
import com.example.demo.modules.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper,TbOrder> implements OrderService{

    @Autowired
    private OrderMapper orderMapper;

    @useDataSource(name = "second")
    public TbOrder queryOrder(TbOrder tbOrder) {
        return orderMapper.selectOne(tbOrder);
//        return orderMapper.selectById(tbOrder.getOrderId());
    }

    @Override
    public int saveOrder(TbOrder tbOrder) {
        return orderMapper.insert(tbOrder);
    }

    @Override
    public PageUtils queryOrderPage(Map<String, Object> param) {
        EntityWrapper<TbOrder> ew = new EntityWrapper<TbOrder>();
        Page<TbOrder> page = new Query<TbOrder>(param).getPage();

        page = this.selectPage(page,ew);

//        page.setRecords(orderMapper.selectPage(page,ew));
        return new PageUtils(page);
    }
}
