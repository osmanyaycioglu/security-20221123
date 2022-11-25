package com.training.security.security.filter;

import com.training.security.security.MyUserDetailService;
import com.training.security.security.jwt.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import io.jsonwebtoken.*;

@Component
public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    private JWTService jwtService;
    @Autowired
    private MyUserDetailService myUserDetailService;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (authorization != null) {
            if (authorization.startsWith("Bearer")) {
                String token = authorization.substring(7);
                Jws<Claims> claims = jwtService.check(token);
                if (claims == null) {
                    throw new ServletException("JWT Error");
                }
                Claims body = claims.getBody();
                String ip = body.get("ip",
                                     String.class);
                String subject = body.getSubject();
                UserDetails userDetails = myUserDetailService.loadUserByUsername(subject);
                if (userDetails == null) {
                    throw new ServletException("Error user");
                }

                final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                                                                                                                                        null,
                                                                                                                                        userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext()
                                     .setAuthentication(usernamePasswordAuthenticationToken);

            }
        }
        filterChain.doFilter(request,
                             response);

    }
}
