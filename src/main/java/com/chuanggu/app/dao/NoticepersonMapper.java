package com.chuanggu.app.dao;

import java.util.List;

import com.chuanggu.app.entity.Notice;
import com.chuanggu.app.entity.Noticeperson;

public interface NoticepersonMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Noticeperson record);

    int insertSelective(Noticeperson record);

    Noticeperson selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Noticeperson record);

    int updateByPrimaryKey(Noticeperson record);
    
    List<Noticeperson> noticePersonList(Long noticeId);
    
    int deleteByNoticeId(Long noticeId);
    
    List<Noticeperson> findPersonBynoticeId(Noticeperson record);
    
    int updateIsreadById(Long id);
}