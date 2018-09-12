package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javabean.Cart;
import javabean.Good;
import javabean.GoodItem;

public class CartServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String type=request.getParameter("type");
	
		this.getServletContext().getRequestDispatcher("/head.jsp").include (request,response);
		
		if("addCart".equals(type)){//从购买商品来
			List<Good> goods=(ArrayList<Good>) this.getServletContext().getAttribute("goods");
			HttpSession session=request.getSession();
			Cart cart=(Cart) session.getAttribute("cart");
			if(cart==null){//第一次加入购物车
				cart=new Cart();
			}			
			
			for(int i=0;i<goods.size();i++){
				String goodName=goods.get(i).getName();
				String goodNameString=request.getParameter(goodName);
				
				if(goodNameString!=null){//添加的是该商品
					int index=-1;
					//检查购物车中是否有该商品	
					for(int j=0;j<cart.getGoods().size();j++){
						if(goodName.equals(cart.getGoods().get(j).getName())){
							index=j;
							break;
						}						
					}
					
					if(index > -1){//购物车中有该商品						
						//更新购买数量，把数量加1
						cart.getGoods().get(index).setNumber(cart.getGoods().get(index).getNumber()+1);						
					}else{//购物车中没有该商品
						GoodItem goodItem=new GoodItem();
						goodItem.setName(goodName);
						goodItem.setNumber(1);
						goodItem.setPrice(goods.get(i).getPrice());
						cart.getGoods().add(goodItem);
					}
				}				
			}
			session.setAttribute("cart", cart);//保存 更新后的购物车	
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
			out.println("  <BODY>");
			out.print("<table width='400' height='120' border='1'> ");
			out.print("	<tr bgcolor='#F1FAFA'>");
			out.print("		<td>成功添加到购物车!</td>");
			
			out.print("		<td><a href='/exercise3-3/servlet/CartServlet?type=showCart'>查看购物车</a>");
			out.print("		<td><a href='/exercise3-3/servlet/GoodsServlet?type=showGoods'>返回商品列表</a></td>");
			out.print("		<td><a href='/exercise3-3/servlet/SecurityServlet?type=exit'>注销</a></td>");
			
			out.print("	</tr>");
			out.print("</table>");
		}else if("showCart".equals(type)){//展示购物车
			
			Cart cart=(Cart) request.getSession().getAttribute("cart");
			if(cart==null){//第一次加入购物车
				cart=new Cart();
			}
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
			out.println("  <BODY>");
			out.print("<table width='324' height='120' border='1'> ");
			out.print("	<tr bgcolor='#FFACAC'>");
			out.print("		<td>商品</td><td>单价</td><td>已购买数量</td><td>小计</td>");			
			out.print("	</tr>");
	
			//显示商品
			for(int i=0;i<cart.getGoods().size();i++){
				
				out.print("	<tr bgcolor='#F1FAFA'>");
				out.print("	<td>"	+cart.getGoods().get(i).getName()+"</td><td>"
									+cart.getGoods().get(i).getPrice()+"</td><td>"
									+cart.getGoods().get(i).getNumber()+" </td><td>"
									+cart.getGoods().get(i).getTotal()+" </td>");
				out.print("	</tr>");				
			}			
			out.print("<tr bgcolor='#FFACAC'><td colspan='4' align='right'>总计："
						+cart.getTotalMoney()+"</td></tr></table>");
		}	
		
		this.getServletContext().getRequestDispatcher("/foot.jsp").include (request,response);
	}		
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
}
