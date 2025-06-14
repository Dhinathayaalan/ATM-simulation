package Bank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Account {

	static int count = 1000;
	private int accountNumber;
	private String name;
	private String createTime;
	private String password;
	private double balance;
	private List<String> transactions = new ArrayList<>();
	
	public Account(String name, String password){
		this.accountNumber = ++count;
		this.name = name;
		this.password = password;
		this.balance = 2000;
		this.createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss"));
	}

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		Account.count = count;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public List<String> getTransactions() {
		return transactions;
	}

	public void addTransactions(String transaction) {
		this.transactions.add(transaction);
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", name=" + name + ", createTime=" + createTime
				+ ", balance=" + balance + "]";
	}
	
}
