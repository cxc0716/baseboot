package com.cxc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cxc.domain.Content;
import com.cxc.mapper.ContentMapper;
import com.cxc.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public boolean save(Content content) {
        contentMapper.save(content);
        return true;
    }

    @Override
    public List<Content> queryListByUid(Integer uid) {
        return contentMapper.queryListByUid(uid);
    }

    @Override
    public boolean deleteById(Integer id) {
        contentMapper.deleteById(id);
        return true;
    }

    @Override
    public boolean update(Content content) {
        contentMapper.update(content);
        return true;
    }

    @Override public Content getById(Integer id) {
        return contentMapper.getById(id);
    }
}
