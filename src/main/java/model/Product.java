package model;

public class Product {
	public Integer idProduct;
	public String manufacturer;
	public String name;
	public String brand;
	public String model;
	public String idCategory;
	public String description;
	public String unitMeasure;
	public String width;
	public String heigh;
	public String depth;
	public String weight;
	public String color;
	
	public Product() {
		
	}
	
	
	public Product(Integer idProduct, String manufacturer, String name, String brand, String model, String idCategory,
			String description, String unitMeasure, String width, String heigh, String depth, String weight,
			String color) {
		super();
		this.idProduct = idProduct;
		this.manufacturer = manufacturer;
		this.name = name;
		this.brand = brand;
		this.model = model;
		this.idCategory = idCategory;
		this.description = description;
		this.unitMeasure = unitMeasure;
		this.width = width;
		this.heigh = heigh;
		this.depth = depth;
		this.weight = weight;
		this.color = color;
	}
	public Integer getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(String idCategory) {
		this.idCategory = idCategory;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUnitMeasure() {
		return unitMeasure;
	}
	public void setUnitMeasure(String unitMeasure) {
		this.unitMeasure = unitMeasure;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeigh() {
		return heigh;
	}
	public void setHeigh(String heigh) {
		this.heigh = heigh;
	}
	public String getDepth() {
		return depth;
	}
	public void setDepth(String depth) {
		this.depth = depth;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}


}
