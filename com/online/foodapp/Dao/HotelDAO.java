package com.online.foodapp.Dao;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.online.foodapp.database.DBConnection;
import com.online.foodapp.model.HotelData;
import com.online.foodapp.model.FoodData;

public class HotelDAO{
   PreparedStatement pstmt = null;
     public void addHotel(int userId,HotelData hotelData){
         String query = "INSERT INTO HOTEL(OWNERID,HOTELNAME,OWNERNAME,CONTACT,LOCATION,ADDRESS,DELIVERYCHARGE) VALUES (?,?,?,?,?,?,?) ;" ;
          try{
            pstmt = DBConnection.getConnection().prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
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
                System.out.println("Next.........."); 
              }
          }
          catch(SQLException e){
              e.getMessage();
          }
     }
     
     public void addMenu(HotelData hotelData,FoodData foodData){
         String query = "INSERT INTO FOODLIST(HOTELID,FOODCATEGORY,FOODNAME,PRICE) VALUES (?,?,?,?);" ;
          try{
            pstmt = DBConnection.getConnection().prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1,hotelData.getHotelId());
            pstmt.setString(2,foodData.getFoodCategory());
            pstmt.setString(3,foodData.getFoodName());
            pstmt.setDouble(4,foodData.getPrice());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
              if(rs.next()){
                int FoodlistId = rs.getInt("id");
                foodData.setFoodListId(FoodlistId);
                //System.out.println("Your FoodList Id is :" +FoodlistId);
                System.out.println("Next.........."); 
              }
          }
          catch(SQLException e){
              e.getMessage();
          }
     }
     
     public void addDocuments(HotelData hotelData){
         String query = "INSERT INTO HOTELDOCUMENTS(HOTELID,FSSAINO,GSTNO,BANKNAME,ACCOUNTNO,IFSCCODE) VALUES (?,?,?,?,?,?);" ;
          try{
             pstmt = DBConnection.getConnection().prepareStatement(query);
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
     
     public void deleteHotel(int id){
          String query1 = "DELETE FROM HOTEL WHERE ID = ?;";
          String query2 = "DELETE FROM FOODLIST WHERE HOTELID = ?;";
          String query3 = "DELETE FROM HOTELDOCUMENTS WHERE HOTELID = ?;";
            try{
               PreparedStatement pstmt1 = DBConnection.getConnection().prepareStatement(query1);
               pstmt1.setInt(1,id);
               pstmt1.executeUpdate();
               
               PreparedStatement pstmt2 = DBConnection.getConnection().prepareStatement(query2);
               pstmt2.setInt(1,id);
               pstmt2.executeUpdate();
               
               PreparedStatement pstmt3 = DBConnection.getConnection().prepareStatement(query3);
               pstmt3.setInt(1,id);
               pstmt3.executeUpdate();
               
            }
            catch(SQLException e){
              e.getMessage();
            }
     }
     
     public void updateMenu(int resId,FoodData foodData){
         String query = "INSERT INTO FOODLIST(HOTELID,FOODCATEGORY,FOODNAME,PRICE) VALUES (?,?,?,?);" ;
           try{
            pstmt = DBConnection.getConnection().prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1,resId);
            pstmt.setString(2,foodData.getFoodCategory());
            pstmt.setString(3,foodData.getFoodName());
            pstmt.setDouble(4,foodData.getPrice());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
              if(rs.next()){
                int FoodlistId = rs.getInt("id");
                foodData.setFoodListId(FoodlistId);
                //System.out.println("Your FoodList Id is :" +FoodlistId); 
              }
          }
          catch(SQLException e){
              e.getMessage();
          }
     }
     
     public void deleteFood(int id, String food){
          String query = "DELETE FROM FOODLIST WHERE HOTELID = ? AND FOODNAME = ? ;" ;
            try{ 
              pstmt = DBConnection.getConnection().prepareStatement(query);
              pstmt.setInt(1,id);
              pstmt.setString(2,food);
              pstmt.executeUpdate();
              System.out.println("Food details will be deleted successfully");
            }
            catch(SQLException e){
              e.getMessage();
            }   
     }
     
     public void updateFoodPrice(int id,String food,double price){
          String query = "UPDATE FOODLIST SET PRICE =? WHERE HOTELID = ? AND FOODNAME = ?;" ; 
            try{ 
              pstmt = DBConnection.getConnection().prepareStatement(query);
              pstmt.setDouble(1,price);
              pstmt.setInt(2,id);
              pstmt.setString(3,food);
              pstmt.executeUpdate();
              System.out.println("Food details updated successfully");
            }
            catch(SQLException e){
              e.getMessage();
            }
     }
}
