package com.lsc.gateway.configuration;

import com.lsc.gateway.services.TokenService;
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
public class JWTFilter extends GenericFilterBean {
    private TokenService tokenService;

    JWTFilter() {
        this.tokenService = new TokenService();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String token = httpServletRequest.getHeader("Authorization");
        if ("OPTIONS".equalsIgnoreCase(httpServletRequest.getMethod())) {
            httpServletResponse.sendError(HttpServletResponse.SC_OK, "success");
            return;
        }
        if (allowRequestWithoutToken(httpServletRequest)) {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            if (token == null || !tokenService.isTokenValid(token)) {
                httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                ObjectId userId = new ObjectId(tokenService.getUserIdFromToken(token));
                httpServletRequest.setAttribute("userId", userId);
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    public boolean allowRequestWithoutToken(HttpServletRequest request) {
        if (request.getRequestURI().contains("/user") || request.getRequestURI().contains("/login") || request.getRequestURI().contains("/token")) {
            return true;
        }
        return false;
    }
}