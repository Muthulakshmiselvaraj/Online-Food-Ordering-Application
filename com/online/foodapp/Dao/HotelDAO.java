package com.online.foodapp.Dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.online.foodapp.database.DBConnection;
import com.online.foodapp.model.HotelData;
import com.online.foodapp.model.FoodData;

public class HotelDAO{
   Connection con = null;
   PreparedStatement pstmt = null;
     public void addHotel(int userId,HotelData hotelData){
         String query = "INSERT INTO HOTEL(OWNERID,HOTELNAME,OWNERNAME,CONTACT,LOCATION,ADDRESS,DELIVERYCHARGE) VALUES (?,?,?,?,?,?,?) ;" ;
          try{
            con = DBConnection.getConnection();
            pstmt = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1,userId);
            pstmt.setString(2,hotelData.getResName());
            pstmt.setString(3,hotelData.getOwnerName());
            pstmt.setLong(4,hotelData.getResNum());
            pstmt.setString(5,hotelData.getLocation());
            pstmt.setString(6,hotelData.getAddress());
            pstmt.setDouble(7,hotelData.getDelFee());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
              if(rs.next()){
                int ResId = rs.getInt("id");
                hotelData.setHotelId(ResId);
                System.out.println("Your Restaurant Id is :" +ResId);
                
              }
          }
          catch(SQLException e){
              e.getMessage();
          }
     }
     
   public void addMenu(HotelData hotelData, FoodData foodData) {
    String getCategoryIdQuery = "SELECT id FROM foodcategorylist WHERE foodcategory = ?;";
    String insertFoodListQuery = "INSERT INTO foodlist(hotelId, foodcategory, foodname, price) VALUES (?, ?, ?, ?);";
    
    try {
        con = DBConnection.getConnection();
        pstmt = con.prepareStatement(getCategoryIdQuery);
        pstmt.setString(1, foodData.getFoodCategory());
        ResultSet categoryRs = pstmt.executeQuery();
        
        if (categoryRs.next()) {
            int foodCategoryId = categoryRs.getInt("id");
            pstmt = con.prepareStatement(insertFoodListQuery, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, hotelData.getHotelId());
            pstmt.setInt(2, foodCategoryId);  
            pstmt.setString(3, foodData.getFoodName());
            pstmt.setDouble(4, foodData.getPrice());
            pstmt.executeUpdate();
            ResultSet foodListRs = pstmt.getGeneratedKeys();
            if (foodListRs.next()) {
                int foodListId = foodListRs.getInt(1);
                foodData.setFoodListId(foodListId);
                //System.out.println("Food item added with ID: " + foodListId);
            }
        } else {
            System.out.println("Food category not found!");
        }
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
   } 
     
     public void addDocuments(HotelData hotelData){
         String query = "INSERT INTO HOTELDOCUMENTS(HOTELID,FSSAINO,GSTNO,BANKNAME,ACCOUNTNO,IFSCCODE) VALUES (?,?,?,?,?,?);" ;
          try{
             con = DBConnection.getConnection();
             pstmt =con.prepareStatement(query);
             pstmt.setInt(1,hotelData.getHotelId());
             pstmt.setString(2,hotelData.getFssai());
             pstmt.setString(3,hotelData.getGst());
             pstmt.setString(4,hotelData.getBank());
             pstmt.setLong(5,hotelData.getAccnum());
             pstmt.setString(6,hotelData.getIfsc());
             pstmt.executeUpdate();
             System.out.println("Next...........");
          }
         catch(SQLException e){
              e.getMessage();
          } 
     }
     
  public void deleteHotel(int id) {
    String query1 = "DELETE FROM FOODLIST WHERE HOTELID = ?;";
    String query2 = "DELETE FROM HOTELDOCUMENTS WHERE HOTELID = ?;";
    String query3 = "DELETE FROM HOTEL WHERE ID = ?;";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement pstmt1 = con.prepareStatement(query1);
         PreparedStatement pstmt2 = con.prepareStatement(query2);
         PreparedStatement pstmt3 = con.prepareStatement(query3)) {

        // Delete from FOODLIST
        pstmt1.setInt(1, id);
        pstmt1.executeUpdate();

        // Delete from HOTELDOCUMENTS
        pstmt2.setInt(1, id);
        pstmt2.executeUpdate();

        // Finally, delete from HOTEL
        pstmt3.setInt(1, id);
        pstmt3.executeUpdate();

        
    } catch (SQLException e) {
        System.out.println("Error while deleting hotel: " + e.getMessage());
    }
  }
 
       
     public void updateMenu(int resId,FoodData foodData){
         String getCategoryIdQuery = "SELECT id FROM foodcategorylist WHERE foodcategory = ?;";
         String insertFoodListQuery = "INSERT INTO foodlist(hotelid, foodcategory, foodname, price) VALUES (?, ?, ?, ?);";
           try{
            con = DBConnection.getConnection();
            pstmt = con.prepareStatement(getCategoryIdQuery);
            pstmt.setString(1, foodData.getFoodCategory());
            ResultSet categoryRs = pstmt.executeQuery();
            
            if (categoryRs.next()) {
                int foodCategoryId = categoryRs.getInt("id");            
                pstmt = con.prepareStatement(insertFoodListQuery,Statement.RETURN_GENERATED_KEYS);
                pstmt.setInt(1,resId);
                pstmt.setInt(2,foodCategoryId);
                pstmt.setString(3,foodData.getFoodName());
                pstmt.setDouble(4,foodData.getPrice());
                pstmt.executeUpdate();
                ResultSet foodListRs = pstmt.getGeneratedKeys();
                  if (foodListRs.next()) {
                     int foodListId = foodListRs.getInt(1);
                     foodData.setFoodListId(foodListId);
                    // System.out.println("Food item added with ID: " + foodListId);
                  }
          } else {
            System.out.println("Food category not found!");
           }
        } catch (SQLException e) {
            // System.out.println("Error: " + e.printStackTrace());
            e.printStackTrace();
         }
      }
     
     public void deleteFood(int id, String food){
          String query = "DELETE FROM FOODLIST WHERE HOTELID = ? AND FOODNAME = ? ;" ;
            try{ 
              con = DBConnection.getConnection();
              pstmt =con.prepareStatement(query);
              pstmt.setInt(1,id);
              pstmt.setString(2,food);
              pstmt.executeUpdate();
            }
            catch(SQLException e){
              e.getMessage();
            }   
     }
     
     public void updateFoodPrice(int id,String food,double price){
          String query = "UPDATE FOODLIST SET PRICE =? WHERE HOTELID = ? AND FOODNAME = ?;" ; 
            try{ 
              con = DBConnection.getConnection();
              pstmt =con.prepareStatement(query);
              pstmt.setDouble(1,price);
              pstmt.setInt(2,id);
              pstmt.setString(3,food);
              pstmt.executeUpdate();
            }
            catch(SQLException e){
              e.getMessage();
            }
     }
}
