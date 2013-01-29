package com.modoop.zerg.taipan.core.entity.page;


import com.modoop.zerg.taipan.core.util.Asserts;
import com.modoop.zerg.taipan.core.util.Strings;

import java.util.Collections;
import java.util.List;

/**
 * 分页参数封装类.
 */
public class PageRequest
{
    /**
     * 每页显示参数
     */
    public static final int MIN_PAGESIZE = 1;
    public static final int MAX_PAGESIZE = 100;

    /**
     * 总页数
     */
    protected int pageCount;
    
    /**
     * 当前页数
     */
    protected int pageIndex = 1;
    
    /**
     * 当前页面显示记录数
     */
    protected int pageSize = 50;
    
    /**
     * 总记录数
     */
    protected long entryCount;
    
    /**
     * 记录开始索引
     */
    protected int entryFromIndex;
    
    /**
     * 记录结束索引
     */
    protected int entryToIndex;
    
    /**
     * 排序属性参数
     */
    protected String orderBy = null;
    
    /**
     * 顺序 (asc or desc)
     */
    protected String order = null;
    
    /**
     * 自动重新获取长度
     */
    protected boolean autoCount = true;
    
    public PageRequest()
    {
        super();
    }
    
    public PageRequest(int pageSize)
    {
        setPageSize(pageSize);
    }
    
    public PageRequest(int pageIndex, int pageSize)
    {
        setPageIndex(pageIndex);
        setPageSize(pageSize);
    }
    
    /**
     * 获得当前页的页号
     * @return pageIndex 序号从1开始, 默认为1.
     */
    public int getPageIndex()
    {
        return pageIndex;
    }
    
    /**
     * 设置当前页的页号
     * @param pageIndex 页号从1开始, 低于1时自动调整为1.
     */
    public void setPageIndex(final int pageIndex)
    {
        this.pageIndex = pageIndex;
        
        if (pageIndex < 1)
        {
            this.pageIndex = 1;
        }
    }
    
    /**
     * 设置每页的记录数量
     * @param pageSize 超出MIN_PAGESIZE与MAX_PAGESIZE范围时会自动调整.
     */
    public void setPageSize(final int pageSize)
    {
        this.pageSize = pageSize;
        
        if (pageSize < MIN_PAGESIZE) this.pageSize = MIN_PAGESIZE;
        if (pageSize > MAX_PAGESIZE) this.pageSize = MAX_PAGESIZE;
    }
    
    /**
     * 获得排序字段, 无默认值. 多个排序字段时用','分隔.
     * @return orderBy
     */
    public String getOrderBy()
    {
        return orderBy;
    }
    
    /**
     * 设置排序字段, 多个排序字段时用','分隔.
     * @param orderBy 排序字段
     */
    public void setOrderBy(final String orderBy)
    {
        this.orderBy = orderBy;
    }
    
    /**
     * 获得排序方向, 无默认值.
     * @return 排序方向
     */
    public String getOrder()
    {
        return order;
    }
    
    /**
     * 设置排序方式向.
     * @param orderDir 可选值为desc或asc, 多个排序字段时用','分隔.
     */
    public void setOrder(final String orderDir)
    {
        String lowcaseOrderDir = Strings.lowerCase(orderDir);
        
        // 检查order字符串的合法值
        String[] orderDirs = Strings.split(lowcaseOrderDir, ',');
        for (String orderDirStr : orderDirs)
        {
            if (!Strings.equals(Sort.DESC, orderDirStr) && !Strings.equals(Sort.ASC, orderDirStr))
            {
                throw new IllegalArgumentException("Sort order " + orderDirStr + " is invalidated!");
            }
        }
        
        this.order = lowcaseOrderDir;
    }
    
    /**
     * 获得排序参数.
     * @return List<Sort> 排序参数
     */
    public List<Sort> getSort()
    {
        if (orderBy == null && order== null)
        {
            return Collections.emptyList();
        }
        String[] orderBys = Strings.split(orderBy, ',');
        String[] orderDirs = Strings.split(order, ',');
        Asserts.isTrue(orderBys.length == orderDirs.length, "分页多重排序参数中,排序字段与排序方向的个数不相等");
        
        List<Sort> orders = Collections.emptyList();
        for (int i = 0; i < orderBys.length; i++)
        {
            orders.add(new Sort(orderBys[i], orderDirs[i]));
        }
        
        return orders;
    }
    
    /**
     * 是否已设置排序字段, 无默认值.
     * @return 排序字段
     */
    public boolean isOrderBySetted()
    {
        return (Strings.isNotBlank(orderBy) && Strings.isNotBlank(order));
    }
    
    /**
     * 是否默认计算总记录数.
     * @return 默认计算总记录数
     */
    public boolean isAutoCount()
    {
        return autoCount;
    }
    
    /**
     * 设置是否默认计算总记录数.
     * @param autoCount 总记录数
     */
    public void setAutoCount(final boolean autoCount)
    {
        this.autoCount = autoCount;
    }
    
    /**
     * 根据pageIndex和pageSize计算当前页第一条记录在总结果集中的位置.
     * @return 第一条记录在总结果集中的位置, 序号从0开始
     */
    public int getEntryOffset()
    {
        return ((pageIndex - 1) * pageSize);
    }
    
    /**
     * 获得每页的记录数量.
     * @return pageSize 每页的记录数量
     */
    public int getPageSize()
    {
        return pageSize;
    }
    
    /**
     * 获取每页开始条目数 用于 ORACLE 和 web
     * @return 每页开始条目数 用于 ORACLE 方言
     */
    public int getEntryFromIndex()
    {
        entryFromIndex = getEntryOffset() + 1;
        if (entryFromIndex < 1) entryFromIndex = 1;
        return entryFromIndex;
    }
    
    /**
     * 获取每页结束条目数 用于 ORACLE 和 web
     * @return 每页结束条目数 用于 ORACLE 方言
     */
    public int getEntryToIndex()
    {
        entryToIndex = entryFromIndex + pageSize - 1;
        if (entryToIndex > entryCount)
        {
            entryToIndex = (int) entryCount;
        }
        return entryToIndex;
    }
}
