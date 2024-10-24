package com.online.foodapp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection{ 
   private static Connection connection = null;
   private static final String url = "jdbc:postgresql://localhost:5433/myjdbcdb";
   private static final String userName = "myjdbcuser";
   private static final String password = "password";
   
   public static void createConnection(){
      try{
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(url,userName,password);
      }
      catch(SQLException e){
        e.printStackTrace();
      }
      catch(ClassNotFoundException e){
        e.getMessage();
      }
   }
   
   public static Connection getConnection(){
      return connection;
   }
   
   public static void closeConnection() throws SQLException{
       if(connection != null){
         connection.close();
       }
   }
}

