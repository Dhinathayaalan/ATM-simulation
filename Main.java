package Bank;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Bank bank = new Bank();
		
		while(true) {
			System.out.println("\nEnter 1 to create account");
			System.out.println("Enter 2 to if already have an account");
			System.out.println("Enter 3 to view all accounts");
			System.out.println("Enter 4 to exit");
			
			String str = sc.nextLine();
			int opt = Integer.parseInt(str);
			if(opt == 1) {
				bank.createAccount();
			}
			else if(opt == 2) {
				bank.login();
			}
			else if(opt == 3) {
				bank.viewAccounts();
			}
			else {
				break;
			}
		}

	}

}
