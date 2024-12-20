

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import DAO.UserDao;
import DAO.UserDaoImpl;
import model.User;
import utilities.Utilities;


@WebServlet("/user/register")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public RegisterUser() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
        PrintWriter out = response.getWriter();
    	Utilities util = new Utilities();
    	UserDao userdao = new UserDaoImpl();
        String regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
        Pattern pass = Pattern.compile(regex);
        
        String email = util.clearSqlInjection(request.getParameter("email"));
        String username = util.clearSqlInjection(request.getParameter("username"));
        String password = util.clearSqlInjection(request.getParameter("password"));
        String cep = util.clearSqlInjection(request.getParameter("cep"));
        if (email == null || email.isEmpty() || username == null || username.isEmpty() || password == null || password.isEmpty()
        		|| cep == null || cep.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"message\": \"Missing information.\"}");
            return;
        }
        if(!util.checkEmail(email)) {
        	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"message\": \"Invalid email.\"}");
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
        	
        	if(userdao.setUser(user)) {
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

}
