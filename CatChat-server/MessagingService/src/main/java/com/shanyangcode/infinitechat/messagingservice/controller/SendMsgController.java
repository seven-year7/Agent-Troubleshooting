package com.shanyangcode.infinitechat.messagingservice.controller;


import com.shanyangcode.infinitechat.messagingservice.model.dto.SendMsgRequest;
import com.shanyangcode.infinitechat.messagingservice.common.Result;
import com.shanyangcode.infinitechat.messagingservice.common.ResultGenerator;
import com.shanyangcode.infinitechat.messagingservice.model.vo.ResponseMsgVo;
import com.shanyangcode.infinitechat.messagingservice.service.MessagingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api")
public class SendMsgController {

    @Autowired
    private MessagingService messagingService;

    @PostMapping("/v1/chat/session")
    public Result<ResponseMsgVo> sendMsg(@RequestBody SendMsgRequest sendMsgRequest) throws Exception {
        ResponseMsgVo response = messagingService.sendMessage(sendMsgRequest);
        log.info(sendMsgRequest.toString());
        return ResultGenerator.genSuccessResult(response);
    }

}
