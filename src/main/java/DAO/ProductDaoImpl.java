package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import model.Product;

public class ProductDaoImpl implements ProductDao {
	
	
	public List<Product> getProduct() throws SQLException, Exception{
		
		DBConnection conn = new DBConnection();
		String query = "select * from products";
		List<Product> products = new ArrayList<>();
		PreparedStatement statement = conn.getConnection().prepareStatement(query);
		ResultSet rs = statement.executeQuery();
		while(rs.next()) {
			Product product = new Product(rs.getInt("idProduct"), rs.getString("manufacturer"), rs.getString("name"),
					rs.getString("brand"), rs.getString("model"), rs.getString("idCategory"), rs.getString("description"), 
					rs.getString("unitMeasure"), rs.getString("width"), rs.getString("heigh"), 
					rs.getString("depth"), rs.getString("weight"), rs.getString("color"));
			products.add(product);
		}
		if (rs != null) rs.close();
        if (statement != null) statement.close();
		
		return products;
	}
	
	public  boolean setProduct(Product product) throws SQLException, Exception {
		DBConnection conn = new DBConnection();
		String query = "insert into products(idProduct, manufacturer, name, brand, model, idCategory, description, "
				+ "unitMeasure, width, heigh, depth, weight, color) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = conn.getConnection().prepareStatement(query);
		statement.setInt(1, product.getIdProduct());
		statement.setString(2, product.getManufacturer());
		statement.setString(3, product.getName());
		statement.setString(4, product.getBrand());
		statement.setString(5, product.getModel());
		statement.setString(6, product.getIdCategory());
		statement.setString(7, product.getDescription());
		statement.setString(8, product.getUnitMeasure());
		statement.setString(9, product.getWidth());
		statement.setString(10, product.getHeigh());
		statement.setString(11, product.getDepth());
		statement.setString(12, product.getWeight());
		statement.setString(13, product.getColor());
		int row = statement.executeUpdate();
		if(row > 0) {
	        if (statement != null) statement.close();
			return true;
		}
		return false;
	}
	
	public boolean deleteProduct(Integer idProduct) throws SQLException, Exception{
		DBConnection conn = new DBConnection();
		String query = "delete from products where idProduct = ?";
		PreparedStatement statement = conn.getConnection().prepareStatement(query);
		statement.setInt(1, idProduct);
		int row = statement.executeUpdate();
		if(row > 0) {
	        if (statement != null) statement.close();
			return true;
		}
		return false;
	}
	
	public boolean updateProduct(Product product) throws SQLException, Exception{
		DBConnection conn = new DBConnection();
		String query = "UPDATE products SET manufacturer = ?, name = ?, brand = ?, model = ?, idCategory = ?, description = ?, unitMeasure = ?, "
				+ "width = ?, heigh = ?, depth = ?, weight = ?, color = ? WHERE idProduct = ?";
		PreparedStatement statement = conn.getConnection().prepareStatement(query);
		statement.setString(1, product.getManufacturer());
		statement.setString(2, product.getName());
		statement.setString(3, product.getBrand());
		statement.setString(4, product.getModel());
		statement.setString(5, product.getIdCategory());
		statement.setString(6, product.getDescription());
		statement.setString(7, product.getUnitMeasure());
		statement.setString(8, product.getWidth());
		statement.setString(9, product.getHeigh());
		statement.setString(10, product.getDepth());
		statement.setString(11, product.getWeight());
		statement.setString(12, product.getColor());
		statement.setInt(13, product.getIdProduct());

		int row = statement.executeUpdate();
		if(row > 0) {
	        if (statement != null) statement.close();
			return true;
		}
		return false;
	}
}
