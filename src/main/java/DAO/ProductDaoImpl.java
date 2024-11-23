package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBQuery;
import model.Product;

public class ProductDaoImpl implements ProductDao {
	private DBQuery dbQuery;
	
	public ProductDaoImpl() {
		this.dbQuery = new DBQuery("products", 
	            "idProduct, manufacturer, name, brand, model, idCategory, description, unitMeasure, width, heigh, depth, weight, color", 
	            "idProduct");
	}
	
	@Override
	public List<Product> getProduct() throws SQLException{
		List<Product> products = new ArrayList<>();
		try (ResultSet rs = dbQuery.select("")) {
            while (rs.next()) {
                Product product = new Product(
                    rs.getInt("idProduct"),
                    rs.getString("manufacturer"),
                    rs.getString("name"),
                    rs.getString("brand"),
                    rs.getString("model"),
                    rs.getString("idCategory"),
                    rs.getString("description"),
                    rs.getString("unitMeasure"),
                    rs.getString("width"),
                    rs.getString("heigh"),
                    rs.getString("depth"),
                    rs.getString("weight"),
                    rs.getString("color")
                );
                products.add(product);
            }
        }
        return products;
	}
	public boolean setProduct(Product product) throws SQLException {
        String[] values = new String[]{
            String.valueOf(product.getIdProduct()),
            product.getManufacturer(),
            product.getName(),
            product.getBrand(),
            product.getModel(),
            product.getIdCategory(),
            product.getDescription(),
            product.getUnitMeasure(),
            product.getWidth(),
            product.getHeigh(),
            product.getDepth(),
            product.getWeight(),
            product.getColor()
        };
        return dbQuery.insert(values) > 0;
    }
	
	public boolean deleteProduct(Integer idProduct) throws SQLException{
		//String[] values = new String[]{String.valueOf(idProduct)};
        return dbQuery.delete(String.valueOf(idProduct)) > 0;
	}
	
	public boolean updateProduct(Product product) throws SQLException{
		String[] values = new String[]{
	            String.valueOf(product.getIdProduct()),
	            product.getManufacturer(),
	            product.getName(),
	            product.getBrand(),
	            product.getModel(),
	            product.getIdCategory(),
	            product.getDescription(),
	            product.getUnitMeasure(),
	            product.getWidth(),
	            product.getHeigh(),
	            product.getDepth(),
	            product.getWeight(),
	            product.getColor()
	        };
        return dbQuery.update(values) > 0;

	}
}
