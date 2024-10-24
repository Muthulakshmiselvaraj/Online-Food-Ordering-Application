package com.online.foodapp.Dao;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import com.online.foodapp.database.DBConnection;
//import com.online.foodapp.model.Admin;
//import com.online.foodapp.model.FoodData;


public class AdminDAO{
      PreparedStatement pstmt = null;
      Statement stmt = null;
      ResultSet rs = null;
     public int hotelDetails(){
         String query = "SELECT ID,OWNERID,HOTELNAME,OWNERNAME,CONTACT,LOCATION,ADDRESS FROM HOTEL ORDER BY ID DESC LIMIT 1; ";
         int id = 0;
           try{
             stmt = DBConnection.getConnection().createStatement();
             rs = stmt.executeQuery(query);
             while(rs.next()){
                 id = rs.getInt("OWNERID");
                 System.out.println("HotelId :"+rs.getInt("Id")+"HotelName :"+rs.getString("HOTELNAME")+"  OwnerName :"+rs.getString("OWNERNAME")+"  Contact :"+rs.getLong("CONTACT")+"  Location :"+rs.getString("LOCATION")+"  Address :"+rs.getString("ADDRESS"));
             }
           }
           catch(SQLException e){
              e.getMessage();
          }
          return id; 
     }
     
     public void hotelDocuments(){
         String query = "SELECT FSSAINO,GSTNO FROM HOTELDOCUMENTS ORDER BY ID DESC LIMIT 1; ";
           try{
             stmt = DBConnection.getConnection().createStatement();
             rs = stmt.executeQuery(query);
             while(rs.next()){
                 System.out.println("FSSAI Number :"+rs.getString("FSSAINO")+"  GST Number :"+rs.getString("GSTNO"));
             }
           }
           catch(SQLException e){
              e.getMessage();
          } 
     }
     
     public void ordersPerDay(){
         String countQuery = "SELECT COUNT(*) AS order_count FROM ORDERDETAILS WHERE DATE = ?;";
           try{
             pstmt = DBConnection.getConnection().prepareStatement(countQuery);
             Date currentDate = new Date(System.currentTimeMillis());
             pstmt.setDate(1,currentDate);
             rs = pstmt.executeQuery();
             if(rs.next()){
                int count = rs.getInt("order_count");
                System.out.println("Total Number of Orders :"+count); 
             }
           }
           catch(SQLException e){
              e.getMessage();
          }
     }
     
     public void hotels(){
         String hotels = "SELECT HOTELNAME,OWNERNAME,LOCATION FROM HOTEL ;" ;
           try{
             stmt = DBConnection.getConnection().createStatement();
             rs = stmt.executeQuery(hotels);
             while(rs.next()){
                System.out.println("HotelName :"+rs.getString("HOTELNAME")+"Ownername :"+rs.getString("OWNERNAME")+"Location :"+rs.getString("LOCATION"));
             }
           }
           catch(SQLException e){
              e.getMessage();
          }
     }
     
     public void totalOrders(){
         String orders = "SELECT ID,TOTALAMOUNT,DATE FROM ORDERDETAILS;" ;
         String orderscount = "SELECT COUNT(*) AS order_count FROM ORDERDETAILS";
           try{
             stmt = DBConnection.getConnection().createStatement();
             Statement countStmt = DBConnection.getConnection().createStatement();
             
             rs = stmt.executeQuery(orders);
             ResultSet countRs = countStmt.executeQuery(orderscount);
             
             while(rs.next()){
                System.out.println("Order Id:"+rs.getInt("ID")+"Total Amount :"+rs.getDouble("TOTALAMOUNT")+"Date :"+rs.getDate("DATE"));
             }
             
             if(countRs.next()){
               int count = rs.getInt("order_count");
               System.out.println("Total Number Of Orders in this Application is :"+count);
             }
           }
           catch(SQLException e){
              e.getMessage();
          }
     }
}
