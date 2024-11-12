

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import DAO.ProductDao;
import DAO.ProductDaoImpl;
import model.Product;


@WebServlet("/products")
public class GetProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public GetProduct() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        try {
        	//out.println("GET request received");
        	List<Product> products = new ArrayList<>();
        	ProductDao productdao = new ProductDaoImpl();
    		products = productdao.getProduct();
    		
            Gson gson = new Gson();
            String jsonResponse = gson.toJson(products);
            response.setStatus(HttpServletResponse.SC_OK);
            out.print(jsonResponse);
            
            
        } catch (SQLException e) {
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
