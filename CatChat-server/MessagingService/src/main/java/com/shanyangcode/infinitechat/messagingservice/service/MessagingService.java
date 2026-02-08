package com.shanyangcode.infinitechat.messagingservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanyangcode.infinitechat.messagingservice.model.dto.SendMsgRequest;
import com.shanyangcode.infinitechat.messagingservice.model.entity.Message;
import com.shanyangcode.infinitechat.messagingservice.model.vo.ResponseMsgVo;
import org.springframework.stereotype.Service;

@Service
public interface MessagingService extends IService<Message> {

    ResponseMsgVo sendMessage(SendMsgRequest sendMsgRequest) throws Exception;
}