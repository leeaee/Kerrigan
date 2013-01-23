package com.modoop.zerg.taipan.core.entity.page;

/**
 * User: Liyi
 * Date: 1/31/12
 * Time: 11:37 PM
 */
public class Sort
{
    /**
     * 排序参数
     */
    public static final String ASC = "asc";
    public static final String DESC = "desc";

    private final String property;
    private final String dir;

    public Sort(String property, String dir)
    {
        this.property = property;
        this.dir = dir;
    }

    public String getProperty()
    {
        return property;
    }

    public String getDir()
    {
        return dir;
    }
}
