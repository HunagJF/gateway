package com.gateway.service.util.impl;

import com.gateway.entity.util.DictionaryEntity;
import com.gateway.mapper.util.DictionaryMapper;
import com.gateway.service.util.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 字典表操作
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {

    private final DictionaryMapper dictionaryMapper;

    @Autowired
    public DictionaryServiceImpl(DictionaryMapper dictionaryMapper) {
        this.dictionaryMapper = dictionaryMapper;
    }

    @Override
    public DictionaryEntity queryByCodeOnName(String code, String name) {
        return dictionaryMapper.queryByCodeOnName(code, name);
    }

    @Override
    public List<DictionaryEntity> queryByCodeOnNames(String code, String name) {
        return dictionaryMapper.queryByCodeOnNames(code, name);
    }

    @Override
    public int insert(DictionaryEntity dictionary) {
        return dictionaryMapper.insert(dictionary);
    }

    @Override
    public int update(DictionaryEntity dictionary) {
        return dictionaryMapper.update(dictionary);
    }

    @Override
    public int delete(DictionaryEntity dictionary) {
        return dictionaryMapper.delete(dictionary);
    }
}
