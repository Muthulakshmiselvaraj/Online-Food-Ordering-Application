package com.online.foodapp.Dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.online.foodapp.database.DBConnection;
import com.online.foodapp.model.UserData;
import com.online.foodapp.helper.FoodOrder;

public class UserDAO{
   Connection con = null;
   PreparedStatement pstmt = null;
     public void addUser(UserData userData){
        String query = "INSERT INTO SIGNUP(NAME,EMAILID,PHONENUMBER,ADDRESS,PASSWORD,ROLE) VALUES (?,?,?,?,?,NULL);" ;
     
         try{
            Connection con = DBConnection.getConnection();
            pstmt = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1,userData.getName());
            pstmt.setString(2,userData.getEmail());
            pstmt.setLong(3,userData.getPhoneNum());                     
            pstmt.setString(4,userData.getAddress());
            pstmt.setString(5,userData.getPassword());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
              if(rs.next()){
                 int userId = rs.getInt(1);
                 userData.setUserId(userId);
                 String query2 = "INSERT INTO SIGNIN(USERID,EMAILID,PASSWORD) VALUES (?,?,?);" ;
                 PreparedStatement pstmt2 = DBConnection.getConnection().prepareStatement(query2);
                 pstmt2.setInt(1,userId);
                 pstmt2.setString(2,userData.getEmail());
                 pstmt2.setString(3,userData.getPassword());
                 pstmt2.executeUpdate();
                 
              }
         }
         catch(SQLException e){
            e.printStackTrace();
         }
     }
     
     public void setRole(String role,int id){
         String query = "UPDATE SIGNUP SET ROLE =? WHERE ID = ? ;" ;
         try{     
            con = DBConnection.getConnection();
            pstmt =con.prepareStatement(query);
            pstmt.setString(1,role);
            pstmt.setInt(2,id);
            int row = pstmt.executeUpdate();
            if(row>0){
               System.out.println("Accepted Successfully");
            }
            else{
               System.out.println("Give user id not found");
            }
         }
         catch(SQLException e){
            e.printStackTrace();
         }
     }
     
     public String getRoleFromEmailAndPassword(String email,String password){
        String query = "SELECT ROLE FROM SIGNUP JOIN SIGNIN ON SIGNUP.ID = SIGNIN.USERID WHERE SIGNIN.EMAILID = ? AND SIGNIN.PASSWORD = ? ;" ;
        String role = null;
          try{
             con = DBConnection.getConnection();
             pstmt =con.prepareStatement(query);
             pstmt.setString(1,email);
             pstmt.setString(2,password);
             ResultSet rs = pstmt.executeQuery();
               if(rs.next()){
                  role = rs.getString("ROLE");
               }
          }
          catch(SQLException e){
             e.printStackTrace();
          }
         return role;
     }
     
     public int getId(String email,String password){
         String query = "SELECT USERID FROM SIGNIN WHERE EMAILID = ? AND PASSWORD = ? ;" ;
         int id = 0;
            try{
             con = DBConnection.getConnection();
             pstmt =con.prepareStatement(query);
             pstmt.setString(1,email);
             pstmt.setString(2,password);
             ResultSet rs = pstmt.executeQuery();
               if(rs.next()){
                  id = rs.getInt("USERID");
               }
          }
          catch(SQLException e){
             e.printStackTrace();
          }
         return id;
     }
     
     public void updateAddress(int userId,String address){
         String query = "UPDATE SIGNUP SET ADDRESS = ? WHERE ID =? ;" ;
            try{
              con = DBConnection.getConnection();
              pstmt =con.prepareStatement(query);
              pstmt.setString(1,address);
              pstmt.setInt(2,userId);
              pstmt.executeUpdate();
              System.out.println("Your Address was updated Successfully!");
            }
            catch(SQLException e){
             e.printStackTrace();
          }
     }
     
     public void changePassword(int userId,String password){
         String query = "UPDATE SIGNIN SET PASSWORD = ? WHERE USERID =? ;" ;
            try{
              con = DBConnection.getConnection();
              pstmt =con.prepareStatement(query);
              pstmt.setString(1,password);
              pstmt.setInt(2,userId);
              pstmt.executeUpdate();
              System.out.println("Your Password was updated Successfully!");
            }
            catch(SQLException e){
             e.printStackTrace();
          }
     }
}
