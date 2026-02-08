package com.shanyangcode.infinitechat.ContactService.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanyangcode.infinitechat.ContactService.mapper.UserSessionMapper;
import com.shanyangcode.infinitechat.ContactService.model.entity.UserSession;
import com.shanyangcode.infinitechat.ContactService.service.UserSessionService;
import org.springframework.stereotype.Service;

@Service
public class UserSessionServiceImpl extends ServiceImpl<UserSessionMapper, UserSession> implements UserSessionService {

}