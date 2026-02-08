package com.shanyangcode.infinitechat.momentservice.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.shanyangcode.infinitechat.momentservice.model.entity.Moment;
import com.shanyangcode.infinitechat.momentservice.model.vo.MomentOfflineVO;
import com.shanyangcode.infinitechat.momentservice.model.vo.MomentRTCVO;
import com.shanyangcode.infinitechat.momentservice.model.vo.MomentVO;
import com.shanyangcode.infinitechat.momentservice.model.vo.MomentsListVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
* @author Zzw
* @description 针对表【moment(朋友圈)】的数据库操作Service
* @createDate 2024-10-08 11:59:32
*/
@SuppressWarnings({"all"})
public interface MomentService extends IService<Moment> {

    public List<String> saveMomentMedias(MultipartFile[] files);


    public MomentVO saveMoment(Long userId, String text, List<String> urls);


    public boolean deleteMoment(Long momentId, Long userId);



    public MomentsListVO getMoments(List<Long> userIds, String time);

    List<MomentOfflineVO> getOfflineNotice(Long userId, String time);

    public Long getMomentIdByUserId(Long userId);


}
