

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;


import controller.ControllerUser;
import model.User;


@WebServlet("/user/register")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public RegisterUser() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        ControllerUser controll = new ControllerUser();
        
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String hashSenha = BCrypt.hashpw(password, BCrypt.gensalt());		
        String cep = request.getParameter("cep");
        
        User user = new User();
        user.setCep(cep);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(hashSenha);
        
	}

}
