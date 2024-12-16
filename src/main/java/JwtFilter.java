

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

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
import io.jsonwebtoken.ExpiredJwtException;
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

        // Considerações para CORS
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        res.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
        res.setHeader("Access-Control-Allow-Credentials", "true");

        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            res.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String requestURI = req.getRequestURI();

        // Lista de URLs que precisam de autenticação
        Collection<String> protectedUris = new ArrayList<>();
        protectedUris.add("/FirstProjeto/users");
        protectedUris.add("/FirstProjeto/products");

        if (!protectedUris.contains(requestURI)) {
            chain.doFilter(req, res);
            return;
        }

        // Verificar token para URLs protegidas
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
                out.print("{\"message\": \"Token expirado.\"}");
                return;
            }
            request.setAttribute("email", claims.getSubject());
            chain.doFilter(req, res);
        } catch (ExpiredJwtException | SignatureException e) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.print("{\"message\": \"Token inválido ou expirado.\"}");
        }
    }
}
