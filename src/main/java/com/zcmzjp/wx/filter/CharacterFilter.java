package com.zcmzjp.wx.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
public class CharacterFilter implements Filter {

    @Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
                HttpServletRequest request1 = (HttpServletRequest) request;
        try{
//            Object object =request1.getSession().getAttribute("_const_cas_assertion_");
//            Assertion assertion =(Assertion)object;
//            String loginName =assertion.getPrincipal().getName();
//            Map data=assertion.getAttributes();
//            System.out.println(loginName+":"+data.toString());
//
//            AttributePrincipal principal = (AttributePrincipal) request1.getUserPrincipal();
//            System.out.println(principal);
//            String username = principal.getName();
//            System.out.println(username+"::");
//            Map<String,Object> attributes = principal.getAttributes();
        }catch (Exception e){}

                String s=request1.getRequestURI();
                //文件流无法过滤
                if(s.contains("/udetor/jsp/controller.jsp")){
                    chain.doFilter(request, response);
                }else{
                    HttpServletRequestWrapper myRequestWrapper = new MyHttpServletRequestWrapper(
		    (HttpServletRequest) request);
                    chain.doFilter(myRequestWrapper, response);
                }
		
	}

    @Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
