package com.cxc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cxc.domain.Content;

@Mapper
public interface ContentMapper {
    void save(Content content);

    List<Content> queryListByUid(Integer uid);

    int deleteById(Integer id);

    int update(Content content);

    Content getById(Integer id);
}
