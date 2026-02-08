package com.wuxi.infinitechat.realtimecommunicationservice.controller;

import com.wuxi.infinitechat.realtimecommunicationservice.common.Result;
import com.wuxi.infinitechat.realtimecommunicationservice.common.ResultGenerator;
import com.wuxi.infinitechat.realtimecommunicationservice.module.entity.Message;
import com.wuxi.infinitechat.realtimecommunicationservice.service.NettyMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/message/user")
@Slf4j
@RequiredArgsConstructor
public class RcvMsgController {
    private final NettyMessageService nettyMessageService;

    @PostMapping
    public Result receiveMessage(@RequestBody Message message){
            log.info("message:{}",message);
            nettyMessageService.sendMessageToUser(message);
            return ResultGenerator.genSuccessResult();
    }
}
