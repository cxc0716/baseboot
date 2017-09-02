package com.cxc.service;

import java.util.List;

import com.cxc.domain.Content;

public interface ContentService {
    boolean save(Content content);

    List<Content> queryListByUid(Integer uid);

    boolean deleteById(Integer id);

    boolean update(Content content);

    Content getById(Integer id);
}
