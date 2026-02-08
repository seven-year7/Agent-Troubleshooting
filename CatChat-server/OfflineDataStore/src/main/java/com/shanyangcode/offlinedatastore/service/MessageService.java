package com.shanyangcode.offlinedatastore.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.shanyangcode.offlinedatastore.model.entity.Message;
import com.shanyangcode.offlinedatastore.model.vo.OfflineMsgDetail;

import java.util.List;

/**
* @author Zzw
* @description 针对表【message】的数据库操作Service
* @createDate 2024-09-20 16:39:30
*/
public interface MessageService extends IService<Message> {

    List<OfflineMsgDetail>  findOfflineMsgBySessionId(Long sessionId, String time);

}
