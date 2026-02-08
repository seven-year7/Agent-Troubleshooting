package com.shanyangcode.infinitechat.messagingservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanyangcode.infinitechat.messagingservice.model.dto.SendRedPacketRequest;
import com.shanyangcode.infinitechat.messagingservice.model.entity.RedPacket;
import com.shanyangcode.infinitechat.messagingservice.model.vo.ResponseMsgVo;

public interface RedPacketService extends IService<RedPacket> {
    /**
     * 发送红包
     * @param request
     * @return
     * @throws Exception
     */
    ResponseMsgVo sendRedPacket(SendRedPacketRequest request) throws Exception;

    /**
     * 红包过期处理
     *
     * @param redPacketId 红包Id
     */
    void handleExpiredRedPacket(Long redPacketId);
}