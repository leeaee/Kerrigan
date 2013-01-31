package com.modoop.zerg.taipan.core.mybatis;

import com.modoop.zerg.taipan.core.entity.page.Page;
import com.modoop.zerg.taipan.core.entity.page.PageRequest;
import org.apache.commons.beanutils.PropertyUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;
import java.util.Map;

/**
 * Mybatis分页查询工具类, 为分页查询增加传递.
 * <p/>
 * startrow, endrow：用于oracle分页使用,从1开始
 * offset, limit：用于mysql 分页使用,从0开始
 * <p/>
 * @author: Genkyo Lee
 * @date: 8/22/12
 */
public class MyBatisDao extends SqlSessionDaoSupport
{
    public <T> Page<T> selectPage(String statementName, final Map<String, Object> parameters)
    {
        PageRequest pageRequest = this.setPageParameter(parameters);
        String countStatementName = statementName.concat("Count");
        return selectPage(pageRequest, statementName, countStatementName, parameters);
    }

    public <T> Page<T> selectPage(final PageRequest pageRequest, String statementName, String countStatementName, final Object parameter)
    {
        Page<T> page = new Page<T>(pageRequest);
        Number totalItems = (Number) getSqlSession().selectOne(countStatementName, parameter);

        if (totalItems != null && totalItems.longValue() > 0)
        {
            List<T> list = getSqlSession().selectList(statementName, toParameterMap(parameter, page));
            page.setResult(list);
            page.setEntryCount(totalItems.longValue());
        }

        return page;
    }

    @SuppressWarnings("unchecked")
    public static Map toParameterMap(Object parameter, Page page)
    {
        Map map = toParameterMap(parameter);
        map.put("startrow", page.getEntryFromIndex());
        map.put("endrow", page.getEntryToIndex());
        map.put("offset", page.getEntryOffset());
        map.put("limit", page.getPageSize());
        return map;
    }

    @SuppressWarnings("rawtypes")
    public static Map toParameterMap(Object parameter)
    {
        if (parameter instanceof Map)
        {
            return (Map) parameter;
        }
        else
        {
            try
            {
                return PropertyUtils.describe(parameter);
            }
            catch (Exception e)
            {
                return null;
            }
        }
    }

    private PageRequest setPageParameter(final Map<String, Object> parameters)
    {
        int pageIndex = parameters.get("page") != null ? Integer.parseInt(parameters.get("page").toString()) : 1;
        int pageSize = parameters.get("size") != null ? Integer.parseInt(parameters.get("size").toString()) : 20;
        return new PageRequest(pageIndex, pageSize);
    }
}
