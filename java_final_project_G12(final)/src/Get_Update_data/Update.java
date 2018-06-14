package Get_Update_data;

import java.sql.*;

public class Update {
    int health;
    int maxhealth;
    int money;
    int lv;
    int ATK;
    int exp;

    int sword_equip;
    int bow_equip;
    int spear_equip;

    int medicine_large;
    int medicine_small;

    public Update(){
    }

    public void Health_update(int health) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        try {
            Connection object = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/g12", "root", "0000");
            Statement statement = object.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM g12.player";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                rs.absolute(1);
                rs.updateInt("health",health);
                rs.updateRow();
            }
            object.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Money_update(int money) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        try {
            Connection object = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/g12", "root", "0000");
            Statement statement = object.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM g12.player";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                rs.absolute(1);
                rs.updateInt("Money",money);
                rs.updateRow();
            }
            object.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void Exp_update(int exp) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        try {
            Connection object = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/g12", "root", "0000");
            Statement statement = object.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM g12.player";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                rs.absolute(1);
                rs.updateInt("Exp",exp);
                rs.updateRow();
            }
            object.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void ATK_update(int ATK) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        try {
            Connection object = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/g12", "root", "0000");
            Statement statement = object.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM g12.player";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                rs.absolute(1);
                rs.updateInt("ATK",ATK);
                rs.updateRow();
            }
            object.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void Lv_update(int lv) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        try {
            Connection object = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/g12", "root", "0000");
            Statement statement = object.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM g12.player";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                rs.absolute(1);
                rs.updateInt("Lv",lv);
                rs.updateRow();
            }
            object.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void MaxHealth_update(int maxhealth) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        try {
            Connection object = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/g12", "root", "0000");
            Statement statement = object.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM g12.player";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                rs.absolute(1);
                rs.updateInt("Maxhealth",maxhealth);
                rs.updateRow();
            }
            object.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Sowrd_update(int sword_equip) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        try {
            Connection object = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/g12", "root", "0000");
            Statement statement = object.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM g12.player";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                rs.absolute(1);
                rs.updateInt("Sword_equip",sword_equip);
                rs.updateRow();
            }
            object.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Bow_update(int bow_equip) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        try {
            Connection object = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/g12", "root", "0000");
            Statement statement = object.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM g12.player";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                rs.absolute(1);
                rs.updateInt("Bow_equip",bow_equip);
                rs.updateRow();
            }
            object.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Spear_update(int spear_equip) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        try {
            Connection object = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/g12", "root", "0000");
            Statement statement = object.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM g12.player";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                rs.absolute(1);
                rs.updateInt("Spear_equip",spear_equip);
                rs.updateRow();
            }
            object.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Medicine_large_update(int medicine_large) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        try {
            Connection object = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/g12", "root", "0000");
            Statement statement = object.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM g12.player";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                rs.absolute(1);
                rs.updateInt("Medicine_large",medicine_large);
                rs.updateRow();
            }
            object.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Medicine_small_update(int medicine_small) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        try {
            Connection object = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/g12", "root", "0000");
            Statement statement = object.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM g12.player";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                rs.absolute(1);
                rs.updateInt("Medicine_small",medicine_small);
                rs.updateRow();
            }
            object.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
