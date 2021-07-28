package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entity.Transaction;
import main.DBUtil;

public class TransactionDao {

	
	private static final String CREATE_TRANSACTION_QUERY = "INSERT INTO transactions(length_days, DATE_START, DATE_END, PRICE_AMOUNT, users_id, cars_id) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String READ_TRANSACTION_QUERY = "SELECT * FROM transactions WHERE id = ?";
	private static final String UPDATE_TRANSACTION_QUERY = "UPDATE transactions SET length_days = ?, DATE_START = ?, DATE_END = ?, PRICE_AMOUNT = ?, users_id = ?, cars_id = ? WHERE id = ?";
	private static final String DELETE_TRANSACTION_QUERY = "DELETE FROM transactions WHERE id = ?";
	private static final String FIND_ALL_TRANSACTIONS_QUERY = "SELECT * FROM transactions";

	
	public Transaction create(Transaction transaction) {
		try(Connection conn = DBUtil.getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement(CREATE_TRANSACTION_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, transaction.getLength_days());
			preparedStatement.setString(2, transaction.getDate_start().toString());
			preparedStatement.setString(3, transaction.getDate_end().toString());
			preparedStatement.setBigDecimal(4, transaction.getPrice_amount());
			preparedStatement.setLong(5, transaction.getUser_id());
			preparedStatement.setLong(6, transaction.getCar_id());
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if(rs.next()) {
				transaction.setId((long) rs.getInt(1));
			}
			return transaction;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return null;
		}
	}

	public Transaction read(long id) {
		try(Connection conn = DBUtil.getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement(READ_TRANSACTION_QUERY);
			preparedStatement.setLong(1, id);
			Transaction transaction = new Transaction();
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				
				int length_days = rs.getInt("length_days");
				LocalDate date_start = LocalDate.parse(rs.getString("DATE_START"));
				LocalDate date_end = LocalDate.parse(rs.getString("DATE_END"));
				BigDecimal price_amount = rs.getBigDecimal("PRICE_AMOUNT");
				long user_id = rs.getInt("users_id");
				long car_id = rs.getInt("cars_id");
				
				transaction.setLength_days(length_days);
				transaction.setDate_start(date_start);
				transaction.setDate_end(date_end);
				transaction.setPrice_amount(price_amount);
				transaction.setUser_id(user_id);
				transaction.setCar_id(car_id);
				transaction.setId(id);
			}
			
			return transaction;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return null;
		}
		
	}
	
	public void update(Transaction transaction) {
		try(Connection conn = DBUtil.getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_TRANSACTION_QUERY);
			preparedStatement.setInt(1, transaction.getLength_days());
			preparedStatement.setString(2, transaction.getDate_start().toString());
			preparedStatement.setString(3, transaction.getDate_end().toString());
			preparedStatement.setBigDecimal(4, transaction.getPrice_amount());
			preparedStatement.setLong(5, (long) transaction.getUser_id());
			preparedStatement.setLong(6, (long) transaction.getCar_id());
			preparedStatement.setLong(7, transaction.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	public void delete(long transactionId) {
		try(Connection conn = DBUtil.getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement(DELETE_TRANSACTION_QUERY);
			preparedStatement.setLong(1, transactionId);
			preparedStatement.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	public List<Transaction> findAll() {
		try(Connection conn = DBUtil.getConnection()) {
			List<Transaction> transactions = new ArrayList<>();
			PreparedStatement preparedStatement = conn.prepareStatement(FIND_ALL_TRANSACTIONS_QUERY);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Transaction transaction = new Transaction();
				transaction.setId(rs.getLong("id"));
				transaction.setLength_days(rs.getInt("length_days"));
				transaction.setDate_start(LocalDate.parse(rs.getString("DATE_START")));
				transaction.setDate_end(LocalDate.parse(rs.getString("DATE_END")));
				transaction.setPrice_amount(new BigDecimal(rs.getString("PRICE_AMOUNT")));
				transaction.setUser_id(rs.getLong("users_id"));
				transaction.setCar_id(rs.getLong("cars_id"));
				
				transactions.add(transaction);
			}
			
			return transactions;
		} catch(SQLException sqle) {
			sqle.printStackTrace();
			return null;
		}
	}
}
