package Bank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bank {

	public List<Account> accounts = new ArrayList<>();
	
	static Scanner sc = new Scanner(System.in);
	
	public void createAccount() {
		
		System.out.println("Enter your name");
		String name = sc.nextLine();
		System.out.println("Enter your password");
		String password = sc.nextLine();
		System.out.println("Re-enter your password");
		String parrword2 = sc.nextLine();
		if(password.equals(parrword2)) {
			Account newAccount = new Account(name, password);
			accounts.add(newAccount);
			System.out.println("Account Created Successfully\nNote down your account number => "+ newAccount.getAccountNumber());
		}else {
			System.out.println("Your both password are not same");
		}
	}

	public void viewAccounts() {
		
		for(Account acc : accounts) {
			System.out.println(acc);
		}
	}

	private void checkBalance(Account curr) {
		
		System.out.println("Account No " + curr.getAccountNumber());
		System.out.println("Balance "+ curr.getBalance());
	}

	private Account findAccount(int accNo) {
		for(Account acc : accounts) {
			if(acc.getAccountNumber() == accNo) {
				return acc;
			}
		}
		return null;
	}

	private boolean checkAccountNo(int accNo) {
		for(Account acc : accounts) {
			if(acc.getAccountNumber() == accNo) {
				return true;
			}
		}
		return false;
	}

	public void changePassword(Account curr) {
			
		System.out.println("Enter your old password");
		String oldPass = sc.nextLine();
		if(checkPassword(curr, oldPass)) {
			System.out.println("Enter your new password");
			String password = sc.nextLine();
			System.out.println("Re-enter your new password");
			String parrword2 = sc.nextLine();
			if(password.equals(parrword2)) {
				curr.setPassword(password);
				System.out.println("Password changed Successfully\n");
			}else {
				System.out.println("Your both password are not same");
			}
		}else {
			System.out.println("Entered password is wrong");
		}
	}

	private boolean checkPassword(Account curr, String oldPass) {
		if(curr.getPassword().equals(oldPass)) {
			return true;
		}
		return false;
	}

	public void moneyTransfer(Account sender) {
		
		System.out.println("Enter account number to whom you want to send money");
		String str = sc.nextLine();
		int recieverAccNo = Integer.parseInt(str);
		if(checkAccountNo(recieverAccNo)) {
			System.out.println("Enter amount");
			String str2 = sc.nextLine();
			Account reciever = findAccount(recieverAccNo);
			int amount = Integer.parseInt(str2);
			if(amount <= sender.getBalance()) {
				System.out.println("Enter your password");
				String Pass = sc.nextLine();
				if(checkPassword(sender, Pass)) {
					makeTransaction(sender, reciever, amount);
				}
			}else {
				System.out.println("Insufficient Balance");
			}
		}
	}

	private void makeTransaction(Account sender, Account reciever, int amount) {
		
		sender.setBalance(sender.getBalance()-amount);
		reciever.setBalance(reciever.getBalance()+amount);
		
		String transactionSender = generateTransactionSender(sender, reciever, amount);
		String transactionReciever = generateTransactionReciever(sender, reciever, amount);
		
		sender.addTransactions(transactionSender);
		reciever.addTransactions(transactionReciever);
		
		System.out.println("Amount Successfully sent from "+sender.getName()+" to "+reciever.getName());
		
	}

	private String generateTransactionReciever(Account sender, Account reciever, int amount) {

		String result = "Rs."+amount+" Credited to your AccNo: "+reciever.getAccountNumber()+" to AccNo: "+sender.getAccountNumber()+" at "+LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss"));
		return result;
	}

	private String generateTransactionSender(Account sender, Account reciever, int amount) {
		
		String result = "Rs."+amount+" Debited from your AccNo: "+sender.getAccountNumber()+" to AccNo: "+reciever.getAccountNumber()+" at "+LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss"));
		return result;
	}

	public void login() {
		
		System.out.println("Enter your account number");
		String str = sc.nextLine();
		int accNo = Integer.parseInt(str);
		if(checkAccountNo(accNo)) {
			System.out.println("Enter your password");
			String Pass = sc.nextLine();
			Account curr = findAccount(accNo);
			if(checkPassword(curr, Pass)) {
				
				while(true) {
					System.out.println("Enter 1 to check balance");
					System.out.println("Enter 2 to money transfer");
					System.out.println("Enter 3 to view transactions");
					System.out.println("Enter 4 to change password");
					System.out.println("Enter 5 to download transaction");
					System.out.println("Enter 6 to exit\n");
					
					System.out.println("Welcome "+curr.getName()+"!");
					String str2 = sc.nextLine();
					int opt = Integer.parseInt(str2);
					if(opt == 1) {
						checkBalance(curr);
					}
					else if(opt == 2) {
						moneyTransfer(curr);
					}
					else if(opt == 3) {
						viewTransaction(curr);
					}
					else if(opt == 4) {
						changePassword(curr);
					}
					else if(opt == 5) {
						download(curr);
					}
					else {
						break;
					}
				}
				
			}else {
				System.out.println("Entered password is wrong");
			}
		}else {
			System.out.println("Enter a valid Account Number");
		}	
	}

	private void download(Account curr) {
		
		String fileName = curr.getName()+"Transactions.txt";
		try {
			File file = new File("Transactions/");
			file.mkdir();
			BufferedWriter writer = new BufferedWriter(new FileWriter("Transactions/"+fileName,true));
			List<String> list = curr.getTransactions();
			writer.write(curr.getName());
			writer.newLine();
			writer.newLine();
			for(String transaction : list) {
				writer.write(transaction);
				writer.newLine();
			}
			writer.close();
		
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void viewTransaction(Account curr) {
		
		List<String> transactions = curr.getTransactions();
		for(String transaction : transactions) {
			System.out.println(transaction);
		}
		
	}

}
