package com.ant.admin.dao;

import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author zhaopinchao
 * @date 2018-8-15 17:06
 */
@Component
public interface FuncDao {
    Set<String> getFuncByUserName(String userName);
}
