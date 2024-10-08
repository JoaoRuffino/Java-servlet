import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import com.google.gson.Gson;
import model.User;
import controller.ControllerUser;


@WebServlet("/users")


public class ServUser extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        ControllerUser controll = new ControllerUser();
        
        try {
        	//out.println("GET request received");
            List<User> users = controll.consumeUsers();
            
            Gson gson = new Gson();
            String jsonResponse = gson.toJson(users);
            
            out.print(jsonResponse);
            
           
            
            
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("Database error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            out.println("Error: " + e.getMessage());
        }
    }

   

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        User user = new User();
        ControllerUser controll = new ControllerUser();
        
        String user_id = request.getParameter("user_id");
        if (user_id == null || user_id.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"message\": \"User ID is required.\"}");
            return;
        }
        user.setUser_id(Integer.parseInt(user_id));
        try {
        	if(controll.userDelete(user)) {
		        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		        //out.print("User successfully deleted");
        	}else {
		        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	            out.print("{\"message\": \"Fail delete user.\"}");

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

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        ControllerUser controll = new ControllerUser();
        
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String cep = request.getParameter("cep");
        String user_id = request.getParameter("user_id");


        if (email == null || email.isEmpty() || username == null || username.isEmpty() || cep == null || cep.isEmpty() 
        		|| user_id == null || user_id.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"message\": \"Missing information.\"}");
            return;
        }
        
        User user = new User();
        user.setCep(cep);
        user.setEmail(email);
        user.setUsername(username);
        user.setUser_id(Integer.parseInt(user_id));
        try {
        	if(controll.userUpdate(user)) {
		        response.setStatus(HttpServletResponse.SC_NO_CONTENT);

        	}else {
		        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	            out.print("{\"message\": \"Fail update user.\"}");

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
