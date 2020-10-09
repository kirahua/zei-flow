package com.zei.flow.api.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 分页
 * </p>
 *
 * @author lvyouqiang
 * @since 2020-09-28 16:57
 */
public class PageInfo<T> implements IPage<T>, Serializable {
    private static final long serialVersionUID = 216512450302533620L;
    @JsonProperty("rows")
    private List<T> records;
    private long total;
    @JsonProperty("pageSize")
    private long size;
    @JsonProperty("pageNo")
    private long current;
    private String[] ascs;
    private String[] descs;
    private boolean optimizeCountSql;
    private boolean isSearchCount;

    public PageInfo() {
        this.records = Collections.emptyList();
        this.total = 0L;
        this.size = 10L;
        this.current = 1L;
        this.optimizeCountSql = true;
        this.isSearchCount = true;
    }

    public PageInfo(long current, long size) {
        this(current, size, 0L);
    }

    public PageInfo(long current, long size, long total) {
        this(current, size, total, true);
    }

    public PageInfo(long current, long size, boolean isSearchCount) {
        this(current, size, 0L, isSearchCount);
    }

    public PageInfo(long current, long size, long total, boolean isSearchCount) {
        this.records = Collections.emptyList();
        this.total = 0L;
        this.size = 10L;
        this.current = 1L;
        this.optimizeCountSql = true;
        this.isSearchCount = true;
        if (current > 1L) {
            this.current = current;
        }

        this.size = size;
        this.total = total;
        this.isSearchCount = isSearchCount;
    }

    public boolean hasPrevious() {
        return this.current > 1L;
    }

    public boolean hasNext() {
        return this.current < this.getPages();
    }

    public List<T> getRecords() {
        return this.records;
    }

    public PageInfo<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }

    public long getTotal() {
        return this.total;
    }

    public PageInfo<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    public long getSize() {
        return this.size;
    }

    public PageInfo<T> setSize(long size) {
        this.size = size;
        return this;
    }

    public long getCurrent() {
        return this.current;
    }

    public PageInfo<T> setCurrent(long current) {
        this.current = current;
        return this;
    }

    public String[] ascs() {
        return this.ascs;
    }

    public PageInfo<T> setAscs(List<String> ascs) {
        if (ascs != null && !ascs.isEmpty()) {
            this.ascs = (String[]) ((String[]) ascs.toArray(new String[0]));
        }

        return this;
    }

    public PageInfo<T> setAsc(String... ascs) {
        this.ascs = ascs;
        return this;
    }

    public String[] descs() {
        return this.descs;
    }

    public PageInfo<T> setDescs(List<String> descs) {
        if (descs != null && !descs.isEmpty()) {
            this.descs = (String[]) ((String[]) descs.toArray(new String[0]));
        }

        return this;
    }

    public IPage<T> setPages(long pages) {
        return this;
    }

    public PageInfo<T> setDesc(String... descs) {
        this.descs = descs;
        return this;
    }

    public boolean optimizeCountSql() {
        return this.optimizeCountSql;
    }

    public boolean isSearchCount() {
        return this.total < 0L ? false : this.isSearchCount;
    }

    public PageInfo<T> setSearchCount(boolean isSearchCount) {
        this.isSearchCount = isSearchCount;
        return this;
    }

    public PageInfo<T> setOptimizeCountSql(boolean optimizeCountSql) {
        this.optimizeCountSql = optimizeCountSql;
        return this;
    }

    public List<OrderItem> orders() {
        return null;
    }
}

