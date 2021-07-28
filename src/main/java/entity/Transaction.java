package entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {

	private long id;
	private int length_days;
	private LocalDate date_start;
	private LocalDate date_end;
	private BigDecimal price_amount;
	private long user_id;
	private long car_id;
	
	
	public Transaction(int length_days, LocalDate date_start, LocalDate date_end, BigDecimal price_amount, long user_id,
			long car_id) {
		this.length_days = length_days;
		this.date_start = date_start;
		this.date_end = date_end;
		this.price_amount = price_amount;
		this.user_id = user_id;
		this.car_id = car_id;
	}
	
	public Transaction() {
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getLength_days() {
		return length_days;
	}
	public void setLength_days(int length_months) {
		this.length_days = length_months;
	}
	public LocalDate getDate_start() {
		return date_start;
	}
	public void setDate_start(LocalDate date_start) {
		this.date_start = date_start;
	}
	public LocalDate getDate_end() {
		return date_end;
	}
	public void setDate_end(LocalDate date_end) {
		this.date_end = date_end;
	}
	public BigDecimal getPrice_amount() {
		return price_amount;
	}
	public void setPrice_amount(BigDecimal price_amount) {
		this.price_amount = price_amount;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public long getCar_id() {
		return car_id;
	}
	public void setCar_id(long car_id) {
		this.car_id = car_id;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", length_days=" + length_days + ", date_start=" + date_start + ", date_end="
				+ date_end + ", price_amount=" + price_amount + ", user_id=" + user_id + ", car_id=" + car_id + "]";
	}
	
	
	
}
