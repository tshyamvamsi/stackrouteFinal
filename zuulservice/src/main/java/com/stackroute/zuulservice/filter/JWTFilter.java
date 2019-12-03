package com.stackroute.zuulservice.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTFilter extends GenericFilterBean{

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
          throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;

    final String header = httpRequest.getHeader("authorization");

    if("OPTIONS".equalsIgnoreCase(httpRequest.getMethod())) {
      httpResponse.setStatus(HttpServletResponse.SC_OK);
      chain.doFilter(request, response);
    }
    else {
      if(header == null || !header.startsWith("Bearer ")) {
        throw new ServletException("Invalid or empty auth header");
      }
      final String token = header.substring(7);
      final Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
      request.setAttribute("claims", claims);
      chain.doFilter(request, response);
    }
  }

}