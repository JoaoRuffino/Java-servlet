

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Authenticator.Authenticator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;


@WebFilter("/*")
public class JwtFilter extends HttpFilter implements Filter {
       
	private static final long serialVersionUID = 1L;

	public JwtFilter() {
        super();
    }

	public void destroy() {
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	    HttpServletRequest req = (HttpServletRequest) request;
	    HttpServletResponse res = (HttpServletResponse) response;

	    String requestURI = req.getRequestURI();
	    //System.out.println(requestURI);
	    if (requestURI.equals("/FirstProjeto/user/login") || requestURI.equals("/FirstProjeto/user/register") 
	    		|| requestURI.equals("/FirstProjeto/products")) {
	        chain.doFilter(req, res);
	        return;
	    }

	    String authHeader = req.getHeader("Authorization");
	    if (authHeader == null || !authHeader.matches("Bearer .+")) {
	        res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	        return;
	    }
	    
	    String token = authHeader.substring(7);
	    try {
	        Claims claims = Authenticator.validateToken(token);
	        if (Authenticator.isTokenExpired(claims)) {
	            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	            response.getWriter().write("Token expirado.");
	            return;
	        }

	        request.setAttribute("email", claims.getSubject());
	        chain.doFilter(req, res);
	    } catch (SignatureException e) {
	        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        response.getWriter().write("Token inválido.");
	    }
	}


	

}
