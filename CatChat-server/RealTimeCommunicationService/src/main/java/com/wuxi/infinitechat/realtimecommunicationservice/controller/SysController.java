package com.wuxi.infinitechat.realtimecommunicationservice.controller;

import com.wuxi.infinitechat.realtimecommunicationservice.common.Result;
import com.wuxi.infinitechat.realtimecommunicationservice.common.ResultGenerator;
import com.wuxi.infinitechat.realtimecommunicationservice.module.vo.sys.OnlineUserVo;
import com.wuxi.infinitechat.realtimecommunicationservice.service.SysService;
import com.wuxi.infinitechat.realtimecommunicationservice.service.impl.SysServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName SysController
 * @Description 系统相关接口
 * @Author WangKun
 * @Date 2024/11/23 16:52
 */
@RestController
@Slf4j
@RequestMapping("/api/v1/sys")
public class SysController {

    // sysService 系统服务
    @Resource
    private SysService sysService;

    /**
     * @MethodName getOnlineUser
     * @Description 获取在线用户信息
     * @return: Result<List<OnlineUserVo>>
     * @Date 2024/11/23 17:17
     */
    @GetMapping("/onlineUser")
    public Result<OnlineUserVo> getOnlineUser(){
        OnlineUserVo onlineUser = sysService.getOnlineUser();

        return ResultGenerator.genSuccessResult(onlineUser);
    }
}
