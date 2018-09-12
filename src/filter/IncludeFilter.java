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

public class IncludeFilter implements Filter {	
	//每次拦截网页，都会执行doFileter方法
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		//强制转换成HttpServletRequest，以便获得session
		HttpServletRequest req = (HttpServletRequest) request;		
		String originalUrl=req.getRequestURI();//获取用户请求的原始网址
		if(originalUrl.endsWith("jsp") || originalUrl.endsWith("/exercise3-3/")
				|| originalUrl.endsWith("/exercise3-3")){
			req.getServletContext().getRequestDispatcher("/foot.jsp").include (request,response);
			
			chain.doFilter(request, response);	//传给下个filter处理	
			req.getServletContext().getRequestDispatcher("/head.jsp").include (request,response);				
		}else
			chain.doFilter(request, response);	//传给下个filter处理
	}
	
	public void destroy() {}
	public void init(FilterConfig filterConfig) throws ServletException {	}	
}
