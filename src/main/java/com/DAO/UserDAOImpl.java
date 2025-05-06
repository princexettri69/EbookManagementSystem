package com.DAO;

import com.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAOImpl implements UserDAO {

    public Connection conn;

    public UserDAOImpl(Connection conn) {
        this.conn = conn;
    }

    public boolean userRegister(User us) {
        boolean f = false;
        try {
            String sql = "insert into useraccount(name,email,password,active) values(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, us.getName());
            ps.setString(2, us.getEmail());
            ps.setString(3, us.getPassword());
            ps.setInt(4, 1); // Set active to 1 (active) by default, or you can use 0 if you want to implement email verification

            int i = ps.executeUpdate();
            if (i == 1) {
                f = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    @Override
    public User login(String email, String password) {
        User us = null;

        try {
            String sql = "select * from useraccount where email=? and password=? and active=1";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                us = new User();
                us.setId(rs.getInt("id"));
                us.setName(rs.getString("name"));
                us.setEmail(rs.getString("email"));
                us.setPassword(rs.getString("password"));
                // We don't set other fields like phno, address, etc. since they're not in the useraccount table
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return us;
    }

    public boolean checkPassword(int id, String ps) {
        boolean f = false;
        try {
            String sql = "select * from useraccount where id=? and password=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.setString(2, ps);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                f = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    public boolean updateProfile(User us) {
        boolean f = false;
        try {
            String sql = "update useraccount set name=?, email=? where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, us.getName());
            ps.setString(2, us.getEmail());
            ps.setInt(3, us.getId());
            int i = ps.executeUpdate();
            if (i == 1) {
                f = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    public boolean checkUser(String em) {
        boolean f = true;

        try {
            String sql = "select * from useraccount where email=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, em);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                f = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    // Add a method to save shipping information
    public boolean saveShippingInfo(int userId, String name, String phone, String address1,
                                    String address2, String landmark, String city, String pincode) {
        boolean f = false;
        try {
            // First check if shipping info already exists for this user
            String checkSql = "select * from shipping where userId=?";
            PreparedStatement checkPs = conn.prepareStatement(checkSql);
            checkPs.setInt(1, userId);
            ResultSet rs = checkPs.executeQuery();

            if (rs.next()) {
                // Update existing shipping info
                String sql = "update shipping set name=?, phone=?, address1=?, address2=?, landmark=?, city=?, pincode=? where userId=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, name);
                ps.setString(2, phone);
                ps.setString(3, address1);
                ps.setString(4, address2);
                ps.setString(5, landmark);
                ps.setString(6, city);
                ps.setString(7, pincode);
                ps.setInt(8, userId);

                int i = ps.executeUpdate();
                if (i == 1) {
                    f = true;
                }
            } else {
                // Insert new shipping info
                String sql = "insert into shipping(userId, name, phone, address1, address2, landmark, city, pincode) values(?,?,?,?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, userId);
                ps.setString(2, name);
                ps.setString(3, phone);
                ps.setString(4, address1);
                ps.setString(5, address2);
                ps.setString(6, landmark);
                ps.setString(7, city);
                ps.setString(8, pincode);

                int i = ps.executeUpdate();
                if (i == 1) {
                    f = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    // Add a method to get shipping information
    public ResultSet getShippingInfo(int userId) {
        ResultSet rs = null;
        try {
            String sql = "select * from shipping where userId=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}