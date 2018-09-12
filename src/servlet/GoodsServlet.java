package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javabean.Good;

public class GoodsServlet extends HttpServlet {
	public void init(ServletConfig sc) throws ServletException {
		//初始化商品列表
		super.init(sc);
		Double price;
		List<Good> goods=new ArrayList<Good>();
		
		Good good=new Good();
		good.setName("apple");
		//从web.xml中的<context-param>读取水果的价格
		price=Double.parseDouble(this.getServletContext().getInitParameter("apple"));
		good.setPrice(price);		
		goods.add(good);
		
		good=new Good();
		good.setName("pear");
		price=Double.parseDouble(this.getServletContext().getInitParameter("pear"));
		good.setPrice(price);
		
		goods.add(good);	
		ServletContext servletContex=sc.getServletContext();
		servletContex.setAttribute("goods", goods);//保存为全局数据

	}	


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String type=request.getParameter("type");
		
//		this.getServletContext().getRequestDispatcher("/head.jsp").include (request,response);
		
		if("showGoods".equals(type)){	
			List<Good> goods=(ArrayList<Good>) this.getServletContext().getAttribute("goods");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML><HEAD>");
		
			out.print("<table width='324' height='120' border='1'> ");
			out.print("	<tr bgcolor='#FFACAC'>");
			out.print("		<td>商品</td><td>单价</td><td>添加到购物车</td>");			
			out.print("	</tr>");
			
			//显示商品
			for(int i=0;i<goods.size();i++){
				Good good=goods.get(i);				
				out.print("	<tr bgcolor='#F1FAFA'>");
				out.print("	  	<td>"+good.getName()+"</td>"+
						  "		<td>"+good.getPrice()+"</td>"+				
						  "		<td><a href='/exercise3-3/servlet/CartServlet?type=addCart&"+good.getName()+"="+good.getName()+"'>加入购物车</a></td>");
				out.print("	</tr>");				
			}			
			out.print("</table></BODY></HTML>");

		}
		
//		this.getServletContext().getRequestDispatcher("/foot.jsp").include (request,response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}	


}
