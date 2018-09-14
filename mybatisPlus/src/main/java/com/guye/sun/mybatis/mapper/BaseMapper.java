package com.guye.sun.mybatis.mapper;

import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by suneee on 2018/5/22.
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T>, ConditionMapper {
}
