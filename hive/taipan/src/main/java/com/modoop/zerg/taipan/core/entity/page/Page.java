package com.modoop.zerg.taipan.core.entity.page;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 与具体ORM实现无关的分页参数及查询结果封装.
 * 
 * @param <T> Page中记录的类型.
 * @author Liyi
 */
public class Page<T> extends PageRequest implements Iterable<T>
{
    /**
     * 对象结果
     */
    protected List<T> result = Collections.emptyList();
    
    // 构造函数
    public Page(PageRequest request)
    {
        this.pageIndex = request.pageIndex;
        this.pageSize = request.pageSize;
        this.autoCount = request.autoCount;
        this.orderBy = request.orderBy;
        this.order = request.order;
    }
    
    // 查询结果函数
    /**
     * 取得页内的记录列表.
     */
    public List<T> getResult()
    {
        return result;
    }
    
    public void setResult(final List<T> result)
    {
        this.result = result;
    }
    
    // 查询参数函数
    /**
     * 取得总记录数.
     */
    public long getEntryCount()
    {
        return entryCount;
    }
    
    public void setEntryCount(long entryCount)
    {
        this.entryCount = entryCount;
    }
    
    /**
     * 根据pageSize与entryCount计算总页数
     */
    public int getPageCount()
    {
        pageCount = ((int) entryCount - 1) / pageSize + 1;
        
        if (pageIndex < 1 || pageIndex > pageCount)
        {
            throw new IndexOutOfBoundsException("pageIndex is invalid!");
        }
        return pageCount;
    }
    
    /**
     * 是否还有下一页.
     */
    public boolean isHasNext()
    {
        return (pageIndex + 1 <= getPageCount());
    }
    
    /**
     * 取得下页的页号,序号从1开始.
     */
    public int getNextPage()
    {
        if (isHasNext())
            return pageIndex + 1;
        else
            return pageIndex;
    }
    
    /**
     * 是否还有上一页.
     */
    public boolean isHasPre()
    {
        return (pageIndex - 1 >= 1);
    }
    
    /**
     * 取得上页的页号,序号从1开始.
     */
    public int getPrePage()
    {
        if (isHasPre())
            return pageIndex - 1;
        else
            return pageIndex;
    }
    
    @Override
    public Iterator<T> iterator()
    {
        return result.iterator();
    }
}
