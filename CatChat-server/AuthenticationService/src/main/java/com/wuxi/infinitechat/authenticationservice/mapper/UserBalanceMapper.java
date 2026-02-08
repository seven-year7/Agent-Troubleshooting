package com.wuxi.infinitechat.authenticationservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wuxi.infinitechat.authenticationservice.model.entity.UserBalance;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户余额表 Mapper 接口
 */
@Mapper
public interface UserBalanceMapper extends BaseMapper<UserBalance> {
}