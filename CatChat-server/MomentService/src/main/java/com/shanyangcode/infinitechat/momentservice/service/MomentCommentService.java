package com.shanyangcode.infinitechat.momentservice.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.shanyangcode.infinitechat.momentservice.model.dto.MomentCommentDTO;
import com.shanyangcode.infinitechat.momentservice.model.entity.MomentComment;
import com.shanyangcode.infinitechat.momentservice.model.vo.MomentCommentVO;

/**
* @author Zzw
* @description 针对表【moment_comment(朋友圈评论)】的数据库操作Service
* @createDate 2024-10-08 16:37:48
*/
@SuppressWarnings({"all"})
public interface MomentCommentService extends IService<MomentComment> {


    MomentCommentVO createComment(Long momentId, MomentCommentDTO momentCommentDTO);

    boolean deleteComment(Long momentId, Long commentId, Long userId);

}
