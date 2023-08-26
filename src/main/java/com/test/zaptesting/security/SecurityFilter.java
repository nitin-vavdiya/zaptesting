package com.test.zaptesting.security;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;

@Component
@Order(1)
@Slf4j
public class SecurityFilter implements Filter {

    private final String apiKey;

    public SecurityFilter(@Value("${security.apiKey}") String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String requestURI = req.getRequestURI();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        if(antPathMatcher.match("/docs/api-docs/**", requestURI)
            || antPathMatcher.match("/ui/swagger-ui/**", requestURI)
        ){
            chain.doFilter(request, response);
        }else{
            if (apiKey.equals(req.getHeader(HttpHeaders.AUTHORIZATION))){
                chain.doFilter(request, response);
            }else{
                throw new SecurityException("Invalid API key");
            }
        }


    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
