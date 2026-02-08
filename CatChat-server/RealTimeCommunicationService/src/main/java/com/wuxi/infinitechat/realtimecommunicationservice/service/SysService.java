package com.wuxi.infinitechat.realtimecommunicationservice.service;

import com.wuxi.infinitechat.realtimecommunicationservice.module.vo.sys.OnlineUserVo;

import java.util.List;

/**
 * @InterfaceName SysService
 * @Description 系统接口服务
 * @Author WangKun
 * @Date 2024/11/23 17:08
 */
public interface SysService {
    OnlineUserVo getOnlineUser();
}
