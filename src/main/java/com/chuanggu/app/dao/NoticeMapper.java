package com.chuanggu.app.dao;

import java.util.List;

import com.chuanggu.app.entity.Notice;
import com.chuanggu.app.entity.User;

public interface NoticeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);
    
    List<Notice> noticeReceiveList(Notice record);
    
    List<Notice> noticeSendList(Notice record);
    
    List<Notice> findDaftList(Notice record);
    
    int updatePubStateById(Long id);
}