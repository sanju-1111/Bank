package com.simplebank.main;

import com.simplebank.service.Bank;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);   // ✅ create ONE scanner
        Bank bank = new Bank(sc);               // ✅ pass scanner to Bank

        while (true) {
            System.out.println("\n--- SIMPLE BANK ---");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            if (choice == 1) {
                bank.createAccount();
            } 
            else if (choice == 2) {
                int id = bank.login();
                if (id != -1) {
                    while (true) {
                        System.out.println("\n1. Deposit");
                        System.out.println("2. Withdraw");
                        System.out.println("3. Check Balance");
                        System.out.println("4. Logout");
                        System.out.print("Choice: ");

                        int ch = sc.nextInt();
                        sc.nextLine();

                        if (ch == 1) bank.deposit(id);
                        else if (ch == 2) bank.withdraw(id);
                        else if (ch == 3) bank.checkBalance(id);
                        else break;
                    }
                }
            } 
            else {
                System.out.println("Thank You!");
                break;
            }
        }

        sc.close(); // ✅ close scanner ONCE
    }
}
