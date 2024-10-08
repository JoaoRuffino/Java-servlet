package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import model.Product;

public class ControllerProduct {

	
	
	public List<Product> consumeProduct() throws SQLException, Exception{
		
		DBConnection conn = new DBConnection();
		String query = "select * from products";
		List<Product> products = new ArrayList<>();
		PreparedStatement statement = conn.getConnection().prepareStatement(query);
		ResultSet rs = statement.executeQuery();
		while(rs.next()) {
			Product product = new Product();
			product.setIdProduct(rs.getInt("idProduct"));
			product.setManufacturer(rs.getString("manufacturer"));
			product.setName(rs.getString("name"));
			product.setBrand(rs.getString("brand"));
			product.setModel(rs.getString("model"));
			product.setIdCategory(rs.getString("idCategory"));
			product.setDescription(rs.getString("description"));
			product.setUnitMeasure(rs.getString("unitMeasure"));
			product.setWidth(rs.getString("width"));
			product.setHeigh(rs.getString("heigh"));
			product.setDepth(rs.getString("depth"));
			product.setWeight(rs.getString("weight"));
			product.setColor(rs.getString("color"));
			products.add(product);
		}
		if (rs != null) rs.close();
        if (statement != null) statement.close();
		
		return products;
	}
	
	public boolean productRegister(Product product) throws SQLException, Exception{
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
	
	public boolean productDelete(Integer idProduct) throws SQLException, Exception{
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
	
	public boolean productUpdate(Product product) throws SQLException, Exception{
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


