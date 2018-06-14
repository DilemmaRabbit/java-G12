package Get_Update_data;

import java.sql.*;

public class Get {
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

    public Get() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        try{
            Connection object = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/g12","root","0000");
            Statement statement = object.createStatement();
            String sql = "SELECT * FROM g12.player";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                health = rs.getInt("Health");
                maxhealth = rs.getInt("MaxHealth");
                money = rs.getInt("Money");
                lv = rs.getInt("Lv");
                ATK = rs.getInt("ATK");
                exp = rs.getInt("Exp");
                sword_equip = rs.getInt("Sword_equip");
                bow_equip =  rs.getInt("Bow_equip");
                spear_equip = rs.getInt("Spear_equip");
                medicine_large = rs.getInt("Medicine_large");
                medicine_small = rs.getInt("Medicine_small");
            }
            object.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public int getATK(){
        return ATK;
    }
    public int getHealth(){ return health; }
    public int getMaxhealth(){ return maxhealth; }
    public int getLv(){ return lv; }
    public int getMoney(){
        return money;
    }
    public int getExp(){ return exp; }
    public int getSword_equip(){return  sword_equip;}
    public int getBow_equip(){return bow_equip;}
    public int getSpear_equip(){return  spear_equip;}
    public int getMedicine_large(){return  medicine_large;}
    public int getMedicine_small(){return  medicine_small;}
}
