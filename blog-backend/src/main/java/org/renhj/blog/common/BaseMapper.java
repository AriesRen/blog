package org.renhj.blog.common;

import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.RowBoundsMapper;

public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T>, IdsMapper<T>, RowBoundsMapper<T> {
}
