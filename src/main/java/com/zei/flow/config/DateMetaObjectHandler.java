package com.zei.flow.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.zei.flow.api.base.CurrentUser;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * 自定义填充公共 name 字段
 * user字段根据自己的业务填充
 */
public class DateMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("createUser", CurrentUser.userId, metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("updateUser", CurrentUser.userId, metaObject);
        this.setFieldValByName("version", 0, metaObject);
        this.setFieldValByName("isDelete", 0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("updateUser", 1L, metaObject);
    }
}