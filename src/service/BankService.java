package service;

import dao.AccountDAO;
import dto.Account;
import java.util.Scanner;

public class BankService {

    private AccountDAO dao = new AccountDAO();
    private Scanner sc;

    public BankService(Scanner sc) {
        this.sc = sc;
    }

    public void createAccount() {
        Account acc = new Account();

        System.out.print("Name: ");
        acc.setName(sc.nextLine());

        System.out.print("Email: ");
        acc.setEmail(sc.nextLine());

        System.out.print("Password: ");
        acc.setPassword(sc.nextLine());

        acc.setBalance(0);
        dao.createAccount(acc);

        System.out.println("✅ Account Created");
    }

    public Account login() {
        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String pass = sc.nextLine();

        Account acc = dao.getAccount(email, pass);
        if (acc != null) {
            System.out.println("✅ Login Successful");
        } else {
            System.out.println("❌ Invalid Login");
        }
        return acc;
    }

    public void deposit(Account acc) {
        System.out.print("Amount: ");
        double amt = sc.nextDouble();
        sc.nextLine();

        dao.updateBalance(acc.getAccountId(), amt);
        System.out.println("💰 Deposit Successful");
    }

    public void withdraw(Account acc) {
        System.out.print("Amount: ");
        double amt = sc.nextDouble();
        sc.nextLine();

        double bal = dao.getBalance(acc.getAccountId());
        if (bal < amt) {
            System.out.println("❌ Insufficient Balance");
            return;
        }

        dao.updateBalance(acc.getAccountId(), -amt);
        System.out.println("💸 Withdrawal Successful");
    }

    public void checkBalance(Account acc) {
        System.out.println(
            "💼 Balance: " +
            dao.getBalance(acc.getAccountId()));
    }

    public void deleteAccount(Account acc) {
        dao.deleteAccount(acc.getAccountId());
        System.out.println("🗑️ Account Deleted");
    }
}
