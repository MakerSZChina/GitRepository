package com.example.demo.modules.service;

import com.example.demo.common.PageUtils;
import com.example.demo.modules.entity.TbOrder;

import java.util.Map;

public interface OrderService {

    TbOrder queryOrder(TbOrder tbOrder);

    int saveOrder(TbOrder tbOrder);

    PageUtils queryOrderPage(Map<String, Object> param);
}
