package com.github.greecs.plugins;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ReflectUtil;
import com.mysql.cj.ClientPreparedQuery;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.This;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.Callable;

public class MonitorMethod {

    @RuntimeType
    public static Object intercept(@This Object obj, @Origin Method method, @net.bytebuddy.implementation.bind.annotation.SuperCall Callable<?> callable, @AllArguments Object... args) throws Exception {
        try {
            return callable.call();
        } finally {
            ClientPreparedQuery clientPreparedQuery = (ClientPreparedQuery) BeanUtil.getFieldValue(obj, "query");
            String originalSql = clientPreparedQuery.getOriginalSql();
            System.out.println("原始SQL: \r\n" + originalSql);
           // String replaceSql = ReflectUtil.invoke(clientPreparedQuery, "asSql");
            String replaceSql = clientPreparedQuery.asSql();
            System.out.println("数据库名称：Mysql");
            System.out.println("线程ID: " + Thread.currentThread().getId());
            System.out.println("时间: " + new Date());
            System.out.println("替换SQL: \r\n" + replaceSql);
        }
    }

}
