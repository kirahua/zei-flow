package com.zei.flow.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * </p>
 *
 * @author lvyouqiang
 * @since 2020-05-20 11:18
 */
@Configuration
public class MybatisConfig {

    @Bean
    public DateMetaObjectHandler metaObjectHandler() {
        return new DateMetaObjectHandler();
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
