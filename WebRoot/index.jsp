<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html><head></head>
  
  <body>
    <a href="/exercise3-3/servlet/GoodsServlet?type=showGoods">显示商品</a><br><br>
    思路：
    1、从Servlet中的返回客户端代码：使用this.getServletContext().getRequestDispatcher("/head.jsp").include (request,response);
    2、对于所有jsp文件，通过过滤器拦截，在过滤器中使用this.getServletContext().getRequestDispatcher("/head.jsp").include (request,response);
  </body>
</html>
