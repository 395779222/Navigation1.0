<%@ page pageEncoding="utf-8"%>
<%// 设置 HTTP/1.1 no-cache 头 
response.setHeader("Cache-Control", "no-store,no-cache,must-revalidate");  
// 设置 IE 扩展 HTTP/1.1 no-cache headers， 用户自己添加  
response.addHeader("Cache-Control", "post-check=0, pre-check=0");  
// 设置标准 HTTP/1.0 no-cache header.  
response.setHeader("Pragma", "no-cache");
out.println(request.getAttribute("jsonString"));
%>