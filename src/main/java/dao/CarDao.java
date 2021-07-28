package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import entity.Car;
import entity.User;
import main.DBUtil;

public class CarDao {

	private static final String CREATE_CAR_QUERY = "INSERT INTO cars(brand, model, price, mileage, fueltype) VALUES (?, ?, ?, ?, ?)";
	private static final String READ_CAR_QUERY = "SELECT * FROM cars WHERE id = ?";
	private static final String UPDATE_CAR_QUERY = "UPDATE cars SET brand = ?, model = ?, price = ?, mileage = ?, fueltype = ? WHERE id = ?";
	private static final String DELETE_CAR_QUERY = "DELETE FROM cars WHERE id = ?";
	private static final String FIND_ALL_CARS_QUERY = "SELECT * FROM cars";
	
	

	public Car create(Car car) {
		try(Connection conn = DBUtil.getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement(CREATE_CAR_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, car.getBrand());
			preparedStatement.setString(2, car.getModel());
			preparedStatement.setBigDecimal(3, car.getPrice());
			preparedStatement.setInt(4, car.getMileage());
			preparedStatement.setString(5, car.getFueltype());
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if(rs.next()) {
				car.setId((long) rs.getInt(1));
			}
			return car;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return null;
		}
	}
	
	public Car read(long carId) {
		try(Connection conn = DBUtil.getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement(READ_CAR_QUERY);
			preparedStatement.setLong(1, carId);
			Car car = new Car();
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				
				car.setBrand(rs.getString("brand"));
				car.setModel(rs.getString("model"));
				car.setPrice(rs.getBigDecimal("price"));
				car.setMileage(rs.getInt("mileage"));
				car.setFueltype(rs.getString("fueltype"));
				car.setId(carId);
			}
			
			return car;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return null;
		}
		
	}
	
	public void update(Car car) {
		try(Connection conn = DBUtil.getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_CAR_QUERY);
			preparedStatement.setString(1, car.getBrand());
			preparedStatement.setString(2, car.getModel());
			preparedStatement.setBigDecimal(3, car.getPrice());
			preparedStatement.setInt(4, car.getMileage());
			preparedStatement.setString(5, car.getFueltype());
			preparedStatement.setLong(6,  car.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	public void delete(long carId) {
		try(Connection conn = DBUtil.getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement(DELETE_CAR_QUERY);
			preparedStatement.setLong(1, carId);
			preparedStatement.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	public List<Car> findAll() {
		try(Connection conn = DBUtil.getConnection()) {
			List<Car> cars = new ArrayList<>();
			PreparedStatement preparedStatement = conn.prepareStatement(FIND_ALL_CARS_QUERY);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Car car = new Car();
				car.setId(rs.getLong("id"));
				car.setBrand(rs.getString("brand"));
				car.setModel(rs.getString("model"));
				car.setPrice(rs.getBigDecimal("price"));
				car.setMileage(rs.getInt("mileage"));
				car.setFueltype(rs.getString("fueltype"));
				
				cars.add(car);
			}
			
			return cars;
		} catch(SQLException sqle) {
			sqle.printStackTrace();
			return null;
		}
	}
	
}
