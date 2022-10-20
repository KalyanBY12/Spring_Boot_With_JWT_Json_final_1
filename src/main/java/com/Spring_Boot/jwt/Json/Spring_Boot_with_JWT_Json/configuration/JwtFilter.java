package com.Spring_Boot.jwt.Json.Spring_Boot_with_JWT_Json.configuration;

import com.Spring_Boot.jwt.Json.Spring_Boot_with_JWT_Json.service.TokenService;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@Configuration
public class JwtFilter extends GenericFilterBean {

    private TokenService tokenService;

    public JwtFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest=(HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse=(HttpServletResponse)servletResponse;

        String token=httpServletRequest.getHeader("Authorization");
        if("OPTIONS".equalsIgnoreCase(httpServletRequest.getMethod()))
        {
            httpServletResponse.sendError(HttpServletResponse.SC_OK,"success");
            return;
        }

        if(allowRequestWithoutToken(httpServletRequest))
        {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(servletRequest,servletResponse);
        }
        else{
            ObjectId userid=new ObjectId(tokenService.getUserIdFromToken(token));
            httpServletRequest.setAttribute("userId",userid);
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }

    public boolean allowRequestWithoutToken(HttpServletRequest httpServletRequest)
    {
        System.out.println(httpServletRequest.getRequestURI());
        if(httpServletRequest.getRequestURI().contains("/saveUser")){
            return true;
        }
        return false;

    }
}
