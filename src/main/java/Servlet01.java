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

@WebServlet("/users")
public class Servlet01 extends HttpServlet {
    private static final long serialVersionUID = 1L;


    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        
        Consume consume = new Consume();
        try {
        	//out.println("GET request received");
            List<User> users = consume.consumeUsers();
            
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //out.println("POST request received");
        Consume consume = new Consume();

        User user = new User();
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        user.setEmail(email);
        user.setPassword(password);
        Gson gson = new Gson();
        
        try {
			boolean verify = consume.userLoginDB(user);
			if (verify) {
		        response.setStatus(HttpServletResponse.SC_OK);
		        //String jsonResponse = gson.toJson("Login successful");
	            
	            out.print("Login successful");		    
	       } 
			else {
		        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		        out.println("Invalid credentials");
		    }
	
			
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
