/*
 * 文 件 名:  BaseServlet.java
 * 版    权:  Copyright (c) qixin Coperation.
 * 描    述:  <描述>
 * 修 改 人:  Administrator
 * 修改时间:  2014-4-5
 * 修改内容:  <修改内容>
 */
package cn.eainfo.common;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
/**
 * <功能详细描述>
 * @author  Administrator
 * @version  [版本号, 2014-4-5]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BaseServlet extends HttpServlet{
    protected static final String CONTENT_TYPE = "text/html; charset=gbk";
    protected ServletConfig config;
    protected PrintWriter out = null;//
    
    
    public void init(ServletConfig config) throws ServletException 
    {
    	this.config = config;
    }
    
    protected void commonDispatcher(String path,HttpServletRequest request,
            HttpServletResponse response)//throws ServletException, IOException
    {
        try
        {     
       
        this.getServletContext().getRequestDispatcher(path)
            .forward(request, response);
            return;
        }
        catch(Exception ex) 
        {
            ex.printStackTrace();
        }
    }
}
