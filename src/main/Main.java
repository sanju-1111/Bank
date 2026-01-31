package main;

import service.BankService;
import dto.Account;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BankService service = new BankService(sc);
        Account acc = null;

        while (true) {
            System.out.println("\n1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choice: ");

            int ch = sc.nextInt();
            sc.nextLine();

            if (ch == 1) {
                service.createAccount();
            }
            else if (ch == 2) {
                acc = service.login();
                if (acc != null) {
                    while (true) {
                        System.out.println("\n1. Deposit");
                        System.out.println("2. Withdraw");
                        System.out.println("3. Check Balance");
                        System.out.println("4. Delete Account");
                        System.out.println("5. Logout");
                        System.out.print("Choice: ");

                        int op = sc.nextInt();
                        sc.nextLine();

                        if (op == 1) service.deposit(acc);
                        else if (op == 2) service.withdraw(acc);
                        else if (op == 3) service.checkBalance(acc);
                        else if (op == 4) {
                            service.deleteAccount(acc);
                            break;
                        }
                        else break;
                    }
                }
            }
            else {
                break;
            }
        }
        sc.close();
    }
}
