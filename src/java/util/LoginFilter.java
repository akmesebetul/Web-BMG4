/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entity.User;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Casper
 */
@WebFilter("/*") 
public class LoginFilter implements Filter{

 

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) sr; 
        HttpServletResponse res = (HttpServletResponse) sr1;
        
        String url = req.getRequestURI();
        
        User u = (User) req.getSession().getAttribute("valid_user");
        
        if(u == null){ //kullanıcı yoksa
            
            if( url.contains("secret") || url.contains("logout")){    
                res.sendRedirect(req.getContextPath()+"/login.xhtml");
            }else{
              fc.doFilter(sr,sr1);
            }
            
            
        }else{ //varsa
            if( url.contains("register") || url.contains("login")){   
                 res.sendRedirect(req.getContextPath()+"/secret/secret.xhtml");
            }else if( url.contains("logout")){
                req.getSession().invalidate();
                 res.sendRedirect(req.getContextPath()+"/index.xhtml");
            }else{
               fc.doFilter(sr,sr1);
            }
        }
    }
    
}
