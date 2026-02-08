package com.wuxi.infinitechat.realtimecommunicationservice.module.dto.push;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AddFriendRequest {

    private String msg;
}
