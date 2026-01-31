package dao;

import dto.Account;
import util.DBConnection;
import java.sql.*;

public class AccountDAO {

    // CREATE
    public void createAccount(Account acc) {
        try (Connection con = DBConnection.getConnection()) {

            String sql =
                "INSERT INTO accounts(name,email,password,balance) VALUES(?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, acc.getName());
            ps.setString(2, acc.getEmail());
            ps.setString(3, acc.getPassword());
            ps.setDouble(4, acc.getBalance());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // READ (LOGIN)
    public Account getAccount(String email, String password) {
        Account acc = null;

        try (Connection con = DBConnection.getConnection()) {

            String sql =
                "SELECT * FROM accounts WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                acc = new Account();
                acc.setAccountId(rs.getInt("account_id"));
                acc.setName(rs.getString("name"));
                acc.setEmail(rs.getString("email"));
                acc.setBalance(rs.getDouble("balance"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return acc;
    }

    // UPDATE (DEPOSIT / WITHDRAW)
    public void updateBalance(int id, double amount) {
        try (Connection con = DBConnection.getConnection()) {

            String sql =
                "UPDATE accounts SET balance = balance + ? WHERE account_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, amount);
            ps.setInt(2, id);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // READ (CHECK BALANCE)
    public double getBalance(int id) {
        try (Connection con = DBConnection.getConnection()) {

            PreparedStatement ps =
                con.prepareStatement(
                    "SELECT balance FROM accounts WHERE account_id=?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // DELETE
    public void deleteAccount(int id) {
        try (Connection con = DBConnection.getConnection()) {

            PreparedStatement ps =
                con.prepareStatement(
                    "DELETE FROM accounts WHERE account_id=?");
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
