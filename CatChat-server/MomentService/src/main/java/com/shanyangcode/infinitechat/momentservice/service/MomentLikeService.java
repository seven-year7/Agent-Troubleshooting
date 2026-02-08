package com.shanyangcode.infinitechat.momentservice.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.shanyangcode.infinitechat.momentservice.model.entity.MomentLike;

/**
* @author Zzw
* @description 针对表【moment_like(朋友圈点赞)】的数据库操作Service
* @createDate 2024-10-08 15:50:26
*/
@SuppressWarnings({"all"})
public interface MomentLikeService extends IService<MomentLike> {



    public Long createLike(Long momentId, Long userId);

    public boolean deleteLike(Long momentId, Long LikeId, Long userId);
}
