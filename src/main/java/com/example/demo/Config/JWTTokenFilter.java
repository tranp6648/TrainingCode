package com.example.demo.Config;

import com.example.demo.DTO.Response.ApiResponse;
import com.example.demo.DTO.Response.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String requestURI = httpServletRequest.getRequestURI();
            if(requestURI.equals("/api/auth/login")){
                filterChain.doFilter(request, response);
                return;
            }
            String token = httpServletRequest.getHeader("Authorization");
            if (token == null || token.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                ApiResponse apiResponse = ResponseUtil.ErrorStatus(401, "Unauthorized - Token is missing or empty");
                response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
                return;
            }
            if (token != null && token.startsWith("Bearer ") ) {
                token = token.substring(7);
                if (jwtService.isValidToken(token)) {
                    String username = jwtService.extractUsername(token);
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            username, null, jwtService.getAuthorities(token)
                    );
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                }else{
                    throw new Exception("Invalid Token");
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            ApiResponse apiResponse = ResponseUtil.ErrorStatus(401, "Unauthorized - Invalid or expired token");
            response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
        }

    }
}
