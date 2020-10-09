package com.zei.flow.api.base;


import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *     当前用户类
 *     需用户根据自己系统的情况来使用
 * </p>
 *
 * @author lvyouqiang
 * @since 2020-09-28 15:38
 */
public class CurrentUser {

    public static Long userId = 1L;

    public static Long currentRole = 1L;

    public static List<Long> roles = Arrays.asList(1L, 2L, 3L);
}
