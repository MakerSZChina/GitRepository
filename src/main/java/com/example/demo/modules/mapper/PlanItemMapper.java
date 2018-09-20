package com.example.demo.modules.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.modules.entity.TbPlanItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PlanItemMapper extends BaseMapper<TbPlanItem>{

}
