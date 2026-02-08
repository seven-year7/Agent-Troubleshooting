package com.shanyangcode.infinitechat.messagingservice.model.vo;

import lombok.Data;
import java.util.Date;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResponseMsgVo {

    private String sessionId;

    private Integer sessionType;

    private Integer type;

    private Long messageId;

    private Object body;

    private String createdAt;
}
