package com.gateway.mapper.util;

import com.gateway.entity.util.DictionaryEntity;

import java.util.List;

public interface DictionaryMapper {

    DictionaryEntity queryByCodeOnName(String code, String name);

    List<DictionaryEntity> queryByCodeOnNames(String code, String name);

    int insert(DictionaryEntity dictionary);

    int update(DictionaryEntity dictionary);

    int delete(DictionaryEntity dictionary);
}
