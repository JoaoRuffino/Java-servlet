

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;
import java.util.regex.Pattern;


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
		response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        ControllerUser controll = new ControllerUser();
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,8}$";
        Pattern pass = Pattern.compile(regex);
        
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String cep = request.getParameter("cep");
        if (email == null || email.isEmpty() || username == null || username.isEmpty() || password == null || password.isEmpty()
        		|| cep == null || cep.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"message\": \"Missing information.\"}");
            return;
        }
        if(!pass.matcher(password).matches()) {
        	// Senha de 8 a 16 caracteres com pelo menos um dígito, pelo menos um
            // letra minúscula, pelo menos uma letra maiúscula, pelo menos uma
            // caractere especial sem espaços em branco
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"message\": \"Password not valid.\"}");
            return;
        }
        String hashSenha = BCrypt.hashpw(password, BCrypt.gensalt());
        
        User user = new User();
        user.setCep(cep);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(hashSenha);

        
        try {
        	
        	if(controll.userRegister(user)) {
		        response.setStatus(HttpServletResponse.SC_CREATED);
        	}else {
		        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	            out.print("{\"message\": \"Error creating user.\"}");

        	}
        }catch (SQLException e) {
	        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
            out.println("Database error: " + e.getMessage());
        } catch (Exception e) {
	        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
            out.println("Error: " + e.getMessage());
        }
	}
	
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("DELETE request received");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("PUT request received");
    }

}
