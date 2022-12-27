package com.example.javaspring2022.security.filter;

import com.example.javaspring2022.dao.CustomerDAO;
import com.example.javaspring2022.models.Customer;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

public class CustomFilter extends OncePerRequestFilter {

    private CustomerDAO customerDAO;

    public CustomFilter(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        String authorization = request.getHeader("Authorization");
        if(authorization !=  null && authorization.startsWith("Bearer ")){
            String token = authorization.replace("Bearer ","");
            String subject = Jwts.parser()
                    .setSigningKey("okten".getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            System.out.println(subject);

            Customer customer = customerDAO.findCustomerByLogin(subject);
            System.out.println(customer);
            if(customer !=  null){
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken( customer.getLogin(),
                        customer.getPassword(),
                        Collections.singletonList(new SimpleGrantedAuthority(customer.getRole()))));

            }

        }

        filterChain.doFilter(request,response);
    }
    }


