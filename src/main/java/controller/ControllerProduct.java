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
}
