package com.qianyang.dao;

/**
 * <br>
 * 基础Dao(还需在XML文件里，有对应的SQL语句)
 */
public interface BaseDao<T> {
    int save(T t);
}
