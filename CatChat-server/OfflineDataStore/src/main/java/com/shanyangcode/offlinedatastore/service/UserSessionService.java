package com.shanyangcode.offlinedatastore.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.shanyangcode.offlinedatastore.model.entity.UserSession;

import java.util.Set;

/**
* @author Zzw
* @description 针对表【user_session】的数据库操作Service
* @createDate 2024-09-20 16:41:50
*/
public interface UserSessionService extends IService<UserSession> {


     Set<Long> findSessionIdByUserId(Long userId);
}
