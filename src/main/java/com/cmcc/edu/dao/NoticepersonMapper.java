package com.cmcc.edu.dao;

import java.util.List;

import com.cmcc.edu.entity.Notice;
import com.cmcc.edu.entity.Noticeperson;

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