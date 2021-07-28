package entity;

import java.math.BigDecimal;

public class Car {

	private long id;
	private String brand;
	private String model;
	private BigDecimal price;
	private int mileage;
	private String fueltype;
	
	public Car() {
	}

	public Car(String brand, String model, BigDecimal price, int mileage, String fueltype) {
		this.brand = brand;
		this.model = model;
		this.price = price;
		this.mileage = mileage;
		this.fueltype = fueltype;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public String getFueltype() {
		return fueltype;
	}

	public void setFueltype(String fueltype) {
		this.fueltype = fueltype;
	}
	
	@Override
	public String toString() {
		return "Car [id=" + id + ", brand=" + brand + ", model=" + model + ", price=" + price + ", mileage=" + mileage
				+ ", fueltype=" + fueltype + "]";
	}
	
}
