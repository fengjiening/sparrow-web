package com.fengjiening.pub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fengjiening.pub.entity.SysUserDepart;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface SysUserDepartMapper extends BaseMapper<SysUserDepart>{

	List<SysUserDepart> getUserDepartByUid(@Param("userId") String userId);
}
