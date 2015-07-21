package com.chuanggu.app.dao;

import java.util.List;

import com.chuanggu.app.entity.Comment;
import com.chuanggu.app.entity.Notice;

public interface CommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
    
    List<Comment> commentSendList(Comment record);
}