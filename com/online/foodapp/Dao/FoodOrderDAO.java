package com.online.foodapp.Dao;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import com.online.foodapp.database.DBConnection;
import com.online.foodapp.helper.FoodOrder;

public class FoodOrderDAO{
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    public void myOrders(int userId){
        String query ="SELECT H.HOTELNAME,F.FOODNAME,O.QUANTITY,O.TOTALAMOUNT,O.DATE FROM HOTEL H JOIN FOODLIST F ON H.ID = F.HOTELID JOIN ORDERDETAILS O ON O.FOODLISTID = F.ID WHERE O.USERID = ? ;" ;
          try{
             pstmt = DBConnection.getConnection().prepareStatement(query);
             pstmt.setInt(1,userId);
             rs = pstmt.executeQuery();
             while(rs.next()){
               System.out.println("HotelName :"+rs.getString("HOTELNAME")+"\nFoodName :"+rs.getString("FOODNAME")+"\nQuantity :"+rs.getInt("QUANTITY")+"\nTotalAmount :"+rs.getDouble("TOTALAMOUNT")+"\nDate :"+rs.getDate("DATE"));
             }
          }
          catch(SQLException e){
             e.getMessage();
          }  
    }
    
    public void getFoodByLoc(String location){
        String query = "SELECT HOTELNAME FROM HOTEL JOIN FOODLIST ON HOTEL.ID = FOODLIST.HOTELID WHERE LOCATION = ? ;" ;
          try{
             pstmt = DBConnection.getConnection().prepareStatement(query);
             pstmt.setString(1,location);
             rs = pstmt.executeQuery();
             while(rs.next()){
                System.out.println(rs.getString("HOTELNAME"));
             }
          }
          catch(SQLException e){
             e.getMessage();
          }
    }
    
     public void getFoodCategoryByHotel(String hotel){
        String query = "SELECT FOODCATEGORY FROM FOODLIST WHERE HOTELNAME = ? ;" ;
          try{
             pstmt = DBConnection.getConnection().prepareStatement(query);
             pstmt.setString(1,hotel);
             rs = pstmt.executeQuery();
             while(rs.next()){
                System.out.println(rs.getString("FOODCATEGORY"));
             }
          }
          catch(SQLException e){
             e.getMessage();
          }
    }
    
    public void getFoodByCategory(String hotel,String category){
        String query = "SELECT FOODNAME,PRICE FROM FOODLIST WHERE HOTELNAME = ? AND FOODCATEGORY = ? ;" ;
          try{
             pstmt = DBConnection.getConnection().prepareStatement(query);
             pstmt.setString(1,hotel);
             pstmt.setString(2,category);
             rs = pstmt.executeQuery();
             while(rs.next()){
                System.out.println(rs.getString("FOODNAME")+" - "+rs.getDouble("PRICE"));
             }
          }
          catch(SQLException e){
             e.getMessage();
          }
    }
    
    public int getFoodListId(String food,String hotel){
        String query = "SELECT ID FROM HOTEL WHERE HOTELNAME = ? ;" ;
        String query2 = "SELECT ID FROM FOODLIST WHERE HOTELID = ? AND FOODNAME = ? ;" ;
        int foodListId = 0;
          try{
            pstmt = DBConnection.getConnection().prepareStatement(query);
            pstmt.setString(1,hotel);
            rs = pstmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("ID");
                PreparedStatement pstmt2 = DBConnection.getConnection().prepareStatement(query2);
                pstmt2.setInt(1,id);
                pstmt2.setString(2,food);
                ResultSet rs2 = pstmt.executeQuery();
                while(rs2.next()){
                    foodListId = rs.getInt("ID");
                }
            }
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
          pstmt = DBConnection.getConnection().prepareStatement(query);
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
          pstmt = DBConnection.getConnection().prepareStatement(query);
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
            pstmt = DBConnection.getConnection().prepareStatement(query);
            pstmt.setInt(1,userId);
            pstmt.setInt(2,foodlistId);
            pstmt.setInt(3,quantity);
            pstmt.setDouble(4,totalAmount);
            Date currentDate = new Date(System.currentTimeMillis());
            pstmt.setDate(5, currentDate);
            pstmt.executeUpdate();
          }
          catch(SQLException e){
             e.getMessage();
          }    
    }
}
