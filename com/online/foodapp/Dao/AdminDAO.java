package com.online.foodapp.Dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import com.online.foodapp.database.DBConnection;
//import com.online.foodapp.model.Admin;
//import com.online.foodapp.model.FoodData;


public class AdminDAO{
      Connection con = null;
      PreparedStatement pstmt = null;
      Statement stmt = null;
      ResultSet rs = null;
     public int hotelDetails(){
         String query = "SELECT ID,OWNERID,HOTELNAME,OWNERNAME,CONTACT,LOCATION,ADDRESS FROM HOTEL ORDER BY ID DESC LIMIT 1; ";
         int id = 0;
           try{
             con = DBConnection.getConnection();
             stmt = con.createStatement();
             rs = stmt.executeQuery(query);
             while(rs.next()){
                 id = rs.getInt("OWNERID");
                 System.out.println("\nHotelId :"+rs.getInt("Id")+"\nHotelName :"+rs.getString("HOTELNAME")+"\nOwnerName :"+rs.getString("OWNERNAME")+"\nContact :"+rs.getLong("CONTACT")+"\nLocation :"+rs.getString("LOCATION")+"\nAddress :"+rs.getString("ADDRESS"));
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
             con = DBConnection.getConnection();
             stmt = con.createStatement();
             rs = stmt.executeQuery(query);
             while(rs.next()){
                 System.out.println("\nFSSAI Number :"+rs.getString("FSSAINO")+"\nGST Number :"+rs.getString("GSTNO"));
             }
           }
           catch(SQLException e){
              e.getMessage();
          } 
     }
     
     public void ordersPerDay(){
         String countQuery = "SELECT COUNT(*) AS order_count FROM ORDERDETAILS WHERE DATE = ?;";
           try{
             con = DBConnection.getConnection();
             pstmt = con.prepareStatement(countQuery);
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
             con = DBConnection.getConnection();
             stmt = con.createStatement();
             rs = stmt.executeQuery(hotels);
             while(rs.next()){
                System.out.println("\nHotelName :"+rs.getString("HOTELNAME")+"\nOwnername :"+rs.getString("OWNERNAME")+"\nLocation :"+rs.getString("LOCATION"));
                System.out.println("------------------------------------");
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
             con = DBConnection.getConnection();
             stmt = con.createStatement();
             Statement countStmt = con.createStatement();
             
             rs = stmt.executeQuery(orders);
             ResultSet countRs = countStmt.executeQuery(orderscount);
             
             while(rs.next()){
                System.out.println("Order Id:"+rs.getInt("ID")+"\nTotal Amount :"+rs.getDouble("TOTALAMOUNT")+"\nDate :"+rs.getDate("DATE"));
                System.out.println("-------------------------------------");
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
