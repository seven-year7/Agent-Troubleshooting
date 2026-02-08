package com.shanyangcode.infinitechat.ContactService.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanyangcode.infiniteChat.common.ServiceException;
import com.shanyangcode.infinitechat.ContactService.mapper.UserBalanceMapper;
import com.shanyangcode.infinitechat.ContactService.model.dto.UserBalanceResponse;
import com.shanyangcode.infinitechat.ContactService.model.entity.UserBalance;
import org.springframework.stereotype.Service;

@Service
public class UserBalanceService extends ServiceImpl<UserBalanceMapper, UserBalance> {

    public UserBalanceResponse getUserBalance(Long userId) {
        // 查询余额
        UserBalance userBalance = this.getById(userId);
        if (userBalance == null) {
            throw new ServiceException("用户余额信息未找到");
        }

        return new UserBalanceResponse(userBalance.getBalance());
    }
}
