package main;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;

import dao.CarDao;
import dao.TransactionDao;
import dao.UserDao;
import entity.Transaction;
import entity.User;
import entity.Car;

public class MainClass {

	public static void main(String[] args) {

		UserDao userDao = new UserDao();
		User user = new User();
		user.setEmail("email");
		user.setPassword("password");
		user.setUsername("username");
		userDao.create(user);
		
	}

}