package com.online.foodapp.Dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import com.online.foodapp.database.DBConnection;
import com.online.foodapp.helper.FoodOrder;

public class FoodOrderDAO{
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    public void myOrders(int userId){
        String query ="SELECT H.HOTELNAME,F.FOODNAME,O.QUANTITY,O.TOTALAMOUNT,O.DATE FROM HOTEL H JOIN FOODLIST F ON H.ID = F.HOTELID JOIN ORDERDETAILS O ON O.FOODLISTID = F.ID WHERE O.USERID = ? ;" ;
          try{
             con = DBConnection.getConnection();
             pstmt =con.prepareStatement(query);
             pstmt.setInt(1,userId);
             rs = pstmt.executeQuery();
             while(rs.next()){
               System.out.println("HotelName :"+rs.getString("HOTELNAME")+"\nFoodName :"+rs.getString("FOODNAME")+"\nQuantity :"+rs.getInt("QUANTITY")+"\nTotalAmount :"+rs.getDouble("TOTALAMOUNT")+"\nDate :"+rs.getDate("DATE"));
               System.out.println("-------------------------------------");
             }
          }
          catch(SQLException e){
             e.getMessage();
          }  
    }
    
    public void getFoodByLoc(String location){
        String query = "SELECT HOTELNAME FROM HOTEL WHERE LOCATION = ? ;" ;
          try{
             con = DBConnection.getConnection();
             pstmt =con.prepareStatement(query);
             pstmt.setString(1,location);
             rs = pstmt.executeQuery();
             while(rs.next()){
                System.out.println(" --> "+rs.getString("HOTELNAME"));
             }
          }
          catch(SQLException e){
             e.getMessage();
          }
    }
    
     public void getFoodCategoryByHotel(String hotel){
         String query = "SELECT DISTINCT fc.foodcategory " +
                        "FROM foodlist fl " +
                        "JOIN foodcategorylist fc ON fl.foodcategory = fc.id " +
                        "JOIN hotel h ON fl.hotelid = h.id " +
                        "WHERE h.hotelname = ?";
        try {
            con = DBConnection.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, hotel);
            rs = pstmt.executeQuery();
            System.out.println("Available food categories at " + hotel + ":");
            boolean categoryFound = false;
              while (rs.next()) {
                categoryFound = true;
                 System.out.println("--> " + rs.getString("foodcategory"));
              }
             if (!categoryFound) {
               System.out.println("No categories found for the specified hotel.");
               return;
             }
       }
       catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
}
    public void getFoodByCategory(String hotel,String category){
    try {
        String categoryQuery = "SELECT id FROM FOODCATEGORYLIST WHERE FOODCATEGORY = ?";
        con = DBConnection.getConnection();
        pstmt = con.prepareStatement(categoryQuery);
        pstmt.setString(1, category);
        ResultSet rs = pstmt.executeQuery();
        int categoryId = -1;
        if (rs.next()) {
            categoryId = rs.getInt("id");
        } else {
            System.out.println("Category not found. Please try again.");
            return;
        }
        rs.close();
        pstmt.close();
        String foodQuery = "SELECT foodname, price FROM foodlist WHERE foodcategory = ? AND hotelid = (SELECT id FROM hotel WHERE hotelname = ?)";
        pstmt = con.prepareStatement(foodQuery);
        pstmt.setInt(1, categoryId);
        pstmt.setString(2, hotel);
        rs = pstmt.executeQuery();
        boolean foodAvailable = false;
        System.out.println("Available foods in " + category + " category at " + hotel + ":");
        while (rs.next()) {
            foodAvailable = true;
            System.out.println(rs.getString("foodname") + " - Rs:" + rs.getInt("price"));
        }
        if (!foodAvailable) {
            System.out.println("No foods found in this category for the selected hotel. Try another food category.");
            return;
        }
        rs.close();
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    } 
  }

    public int getFoodListId(String food,String hotel){
        String query = "SELECT ID FROM HOTEL WHERE HOTELNAME = ? ;" ;
        String query2 = "SELECT ID FROM FOODLIST WHERE HOTELID = ? AND FOODNAME = ? ;" ;
        int foodListId = -1;
          try{
            con = DBConnection.getConnection();
            pstmt =con.prepareStatement(query);
            pstmt.setString(1,hotel);
            rs = pstmt.executeQuery();
            if(rs.next()){
                int hotelId = rs.getInt("ID");
                PreparedStatement pstmt2 = con.prepareStatement(query2);
                pstmt2.setInt(1,hotelId);
                pstmt2.setString(2,food);
                ResultSet rs2 = pstmt2.executeQuery();
                if(rs2.next()){
                    foodListId = rs2.getInt("ID");
                }
                rs2.close();
                pstmt2.close();
            }
            rs.close();
            pstmt.close();
         }
         catch(SQLException e){
             e.getMessage();
          }
       return foodListId;
       
    }
    
    public double getPrice(int id){
        String query ="SELECT PRICE FROM FOODLIST WHERE ID = ? ;";
        double price = 0;
        try{
          con = DBConnection.getConnection();
          pstmt =con.prepareStatement(query);
          pstmt.setInt(1,id);
          rs = pstmt.executeQuery();
          while(rs.next()){
              price = rs.getDouble("PRICE");
          }
        }
        catch(SQLException e){
             e.getMessage();
          }
       return price;
    }
    
    public double getDeliveryCharge(String hotel){
        String query = "SELECT DELIVERYCHARGE FROM HOTEL WHERE HOTELNAME = ?;" ;
        double delfee = 0;
        try{
          con = DBConnection.getConnection();
          pstmt =con.prepareStatement(query);
          pstmt.setString(1,hotel);
          rs = pstmt.executeQuery();
          while(rs.next()){
              delfee = rs.getDouble("DELIVERYCHARGE");
          }
        }
        catch(SQLException e){
             e.getMessage();
          }
       return delfee;
    }
    
    public void orderDetails(int userId,int foodlistId,int quantity,double totalAmount){
        String query = "INSERT INTO ORDERDETAILS (USERID,FOODLISTID,QUANTITY,TOTALAMOUNT,DATE) VALUES (?,?,?,?,?) ;" ;
          try{
            con = DBConnection.getConnection();
            pstmt =con.prepareStatement(query);
            pstmt.setInt(1,userId);
            pstmt.setInt(2,foodlistId);
            pstmt.setInt(3,quantity);
            pstmt.setDouble(4,totalAmount);
            Date currentDate = new Date(System.currentTimeMillis());
            pstmt.setDate(5, currentDate);
            int row = pstmt.executeUpdate();
            if(row>0){
               System.out.println("Successful");
               System.out.println("Your Order will be Delivered with in 30 Minutes");
            }
          }
          catch(SQLException e){
             e.printStackTrace();
          }    
    }
}
