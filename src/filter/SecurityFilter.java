package filter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//过滤受限网址
public class SecurityFilter implements Filter {
	//从web.xml中读取filter的初始数据
	public void init(FilterConfig filterConfig) throws ServletException {
		//把用户数据保存到map中
		Map<String,String> usersMap= new Hashtable<String,String>();

		usersMap.put("tom",filterConfig.getInitParameter("tom"));
		usersMap.put("jerry",filterConfig.getInitParameter("tom"));

		//保存数据到ServletContext中
		filterConfig.getServletContext().setAttribute("usersMap", usersMap);
	}	
	
	//每次拦截网页，都会执行doFileter方法
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		//强制转换成HttpServletRequest，以便获得session
		HttpServletRequest req = (HttpServletRequest) request;		
		HttpSession session = req.getSession();
		
		if (session.getAttribute("username") != null) {//检查是否登录			
			chain.doFilter(request, response);	//传给下个filter处理		
		} else {//没登录
			String queryString=req.getQueryString();//获取用户请求的查询字符串,比如：type=addCart&apple=apple
			String originalUrl=req.getRequestURI();//获取用户请求的原始网址
			originalUrl+="?"+queryString;//拼成完整网址

			session.setAttribute("originalUrl", originalUrl);//保存原始网址到session			
			//强制转换成HttpServletResponse，以便获得sendRedirect方法
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendRedirect("/exercise3-3/login.jsp");//跳转到登录网页，至此中断了过滤链
		}
	}
	
	public void destroy() {}
}
