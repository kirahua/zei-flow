package com.zei.flow;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 *
 * </p>
 *
 * @author lvyouqiang
 * @since 2020-09-28 16:39
 */
@SpringBootApplication(scanBasePackages = {"com.zei.flow"})
@MapperScan({"com.zei.**.dao"})
public class ZeiFlowApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZeiFlowApplication.class, args);
    }
}
