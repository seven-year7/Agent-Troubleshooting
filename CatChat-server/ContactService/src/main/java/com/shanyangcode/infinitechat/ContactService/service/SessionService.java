package com.shanyangcode.infinitechat.ContactService.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanyangcode.infinitechat.ContactService.model.entity.Session;
import com.shanyangcode.infinitechat.ContactService.model.dto.CreateGroupRequest;
import com.shanyangcode.infinitechat.ContactService.model.dto.CreateGroupResponse;

public interface SessionService extends IService<Session> {
    CreateGroupResponse createGroup(CreateGroupRequest request);
}