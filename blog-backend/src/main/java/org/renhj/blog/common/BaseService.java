package org.renhj.blog.common;

import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BaseService<T> {
    T findById(Object id);

    List<T> findAll();

    T findOne(T record);

    List<T> findAllByWhere(T record);

    PageInfo<T> findPageByWhere(T record, Integer page, Integer size);

    Integer save(T record);

    Integer saveSelective(T record);

    Integer update(T record);

    Integer updateSelective(T record);

    Integer delete(T record);

    Integer deleteById(Object id);

    Integer deleteByIds(Class<T> tClass, String property, List<Object> values);

    Integer deleteByWhere(T record);
}
