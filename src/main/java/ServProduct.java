
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import DAO.ProductDao;
import DAO.ProductDaoImpl;

@WebServlet("/all/products")
public class ServProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServProduct() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		ProductDao productdao = new ProductDaoImpl();
		String idProduct = request.getParameter("idProduct");
		String manufacturer = request.getParameter("manufacturer");
		String name = request.getParameter("name");
		String brand = request.getParameter("brand");
		String model = request.getParameter("model");
		String idCategory = request.getParameter("idCategory");
		String description = request.getParameter("description");
		String unitMeasure = request.getParameter("unitMeasure");
		String width = request.getParameter("width");
		String heigh = request.getParameter("heigh");
		String depth = request.getParameter("depth");
		String weight = request.getParameter("weight");
		String color = request.getParameter("color");
		String[] parameters = { idProduct, manufacturer, name, brand, model, idCategory, description, unitMeasure,
				width, heigh, depth, weight, color };
		for (String param : parameters) {
			if (param == null || param.isEmpty()) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				out.print("{\"message\": \"Missing information.\"}");
				return;
			}
		}
		Product product = new Product(Integer.parseInt(idProduct), manufacturer, name, brand, model, idCategory, description, unitMeasure, width, heigh,
				depth, weight, color);
		try {
			if (productdao.setProduct(product)) {
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			} else {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				out.print("{\"message\": \"Fail register Product.\"}");
			}
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
		

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		ProductDao productdao = new ProductDaoImpl();
		String idProduct = request.getParameter("idProduct");
		if (idProduct == null || idProduct.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			out.print("{\"message\": \"Product ID is required.\"}");
			return;
		}

		try {
			if (productdao.deleteProduct(Integer.parseInt(idProduct))) {
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);

			} else {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				out.print("{\"message\": \"Fail delete Product.\"}");

			}
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

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		ProductDao productdao = new ProductDaoImpl();

		String idProduct = request.getParameter("idProduct");
		String manufacturer = request.getParameter("manufacturer");
		String name = request.getParameter("name");
		String brand = request.getParameter("brand");
		String model = request.getParameter("model");
		String idCategory = request.getParameter("idCategory");
		String description = request.getParameter("description");
		String unitMeasure = request.getParameter("unitMeasure");
		String width = request.getParameter("width");
		String heigh = request.getParameter("heigh");
		String depth = request.getParameter("depth");
		String weight = request.getParameter("weight");
		String color = request.getParameter("color");

		String[] parameters = { idProduct, manufacturer, name, brand, model, idCategory, description, unitMeasure,
				width, heigh, depth, weight, color };

		for (String param : parameters) {
			if (param == null || param.isEmpty()) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				out.print("{\"message\": \"Missing information.\"}");
				return;
			}
		}
		Product product = new Product(Integer.parseInt(idProduct), manufacturer, name, brand, model, idCategory, description, unitMeasure, width, heigh,
				depth, weight, color);

		try {
			if (productdao.updateProduct(product)) {
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);

			} else {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				out.print("{\"message\": \"Fail update Product.\"}");

			}
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
