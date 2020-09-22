package com.man.common.config;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SimpleCorsFilter implements Filter {
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// 所有请求和返回都会被过滤器所拦截
		HttpServletResponse response = (HttpServletResponse) res;
		// 允许跨域访问的域名
		response.addHeader("Access-Control-Allow-Origin", "*");
		// 允许执行的方法
		response.addHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE");
		// 请求缓存的时间
		response.addHeader("Access-Control-Max-Age", "3600");
		// 实际请求中允许携带的首部字段
		response.addHeader("Access-Control-Allow-Headers", "*");
		// 放行，递交给下一个过滤器
		chain.doFilter(req, res);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
