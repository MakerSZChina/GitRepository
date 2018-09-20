package com.example.demo.modules.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.modules.entity.TbPlanItem;
import com.example.demo.modules.mapper.PlanItemMapper;
import com.example.demo.modules.service.PlanItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanItemServiceImpl extends ServiceImpl<PlanItemMapper,TbPlanItem> implements PlanItemService{

    @Autowired
    private PlanItemMapper planItemMapper;
}
