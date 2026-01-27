package com.simplebank.service;

import com.simplebank.util.DBConnection;
import java.sql.*;
import java.util.Scanner;

public class Bank {

    private Scanner sc;

    // Constructor injection
    public Bank(Scanner sc) {
        this.sc = sc;
    }

    // Create Account
    public void createAccount() {
        try {
            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Email: ");
            String email = sc.nextLine();

            System.out.print("Password: ");
            String pass = sc.nextLine();

            String sql = "INSERT INTO accounts(name,email,password,balance) VALUES(?,?,?,?)";
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, pass);
            ps.setDouble(4, 0);

            ps.executeUpdate();
            System.out.println("✅ Account Created");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Login
    public int login() {
        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Email: ");
            String email = sc.nextLine();

            System.out.print("Password: ");
            String pass = sc.nextLine();

            String sql = "SELECT account_id FROM accounts WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("✅ Login Successful");
                return rs.getInt("account_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("❌ Invalid Login");
        return -1;
    }

    public void deposit(int id) {
        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Amount: ");
            double amt = sc.nextDouble();
            sc.nextLine(); // consume newline

            String sql = "UPDATE accounts SET balance = balance + ? WHERE account_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, amt);
            ps.setInt(2, id);

            ps.executeUpdate();
            System.out.println("💰 Deposited Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void withdraw(int id) {
        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Amount: ");
            double amt = sc.nextDouble();
            sc.nextLine();

            PreparedStatement ps1 =
                con.prepareStatement("SELECT balance FROM accounts WHERE account_id=?");
            ps1.setInt(1, id);

            ResultSet rs = ps1.executeQuery();
            rs.next();

            if (rs.getDouble(1) < amt) {
                System.out.println("❌ Insufficient Balance");
                return;
            }

            PreparedStatement ps2 =
                con.prepareStatement("UPDATE accounts SET balance = balance - ? WHERE account_id=?");
            ps2.setDouble(1, amt);
            ps2.setInt(2, id);

            ps2.executeUpdate();
            System.out.println("💸 Withdrawal Successful");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkBalance(int id) {
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps =
                con.prepareStatement("SELECT balance FROM accounts WHERE account_id=?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            rs.next();

            System.out.println("💼 Balance: " + rs.getDouble(1));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
