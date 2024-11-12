package DAO;

import java.sql.SQLException;
import java.util.List;

import model.Product;

public interface ProductDao {
	public List<Product> getProduct() throws SQLException, Exception;
	public  boolean setProduct(Product product) throws SQLException, Exception;
	public boolean deleteProduct(Integer idProduct) throws SQLException, Exception;
	public boolean updateProduct(Product product) throws SQLException, Exception;
}
