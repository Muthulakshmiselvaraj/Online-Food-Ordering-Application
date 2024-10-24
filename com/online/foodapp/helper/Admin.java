package com.online.foodapp.helper;

import java.util.Scanner;

import com.online.foodapp.Dao.AdminDAO;
import com.online.foodapp.Dao.UserDAO;

public class Admin{
   private Scanner scanner;
   private AdminDAO adminDao;
   private Hotel hotel;
   private UserDAO userDao;
   
   public Admin(Scanner scanner,AdminDAO adminDao,Hotel hotel,UserDAO userDao){
       this.scanner = scanner;
       this.adminDao = adminDao;
       this.hotel = hotel;
       this.userDao = userDao;
   }
   
    public void viewApplication(){
         System.out.println("======WELCOME ADMIN=====");
         boolean exit = false;
         while(!exit){
            System.out.println("1.Hotels Request\n2.Today Orders\n3.View Hotels\n4.Total Orders\n5.exit\nEnter Your Choice..");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch(choice){
              case 1:
                hotelsRequest();
                break;
              case 2:
                todayOrders();
                break;
              case 3:
                viewHotels();
                break;
              case 4:
                totalOrders();
                break;
              case 5:
                exit = true;
                System.out.println("Exiting...");
              default:
                System.out.println("Invalid Type");
            }
         }
    }
    
    public void hotelsRequest(){
        System.out.println("Available Hotels Requests are:\n---Check Hotel Details---");
        int id = adminDao.hotelDetails();
        System.out.println("----Document Details of The Hotel----");
        adminDao.hotelDocuments();
        System.out.println(" What is the Status Of the Hotel ?\n1.Accepted\n2.Rejected");
        int status = scanner.nextInt();
        switch(status){
           case 1:
             String query = "UPDATE SIGNUP SET ROLE = 'HOTELOWNER' WHERE USERID = ? ;" ;
             userDao.setRole(query,id);
             break;
           case 2:
             hotel.deleteRestaurant();
             break;
           default:
             System.out.println("Invalid Type");            
        }
   }
   
   public void todayOrders(){
       System.out.println("-----Today Orders Are-----");
       adminDao.ordersPerDay();
       
   }
   
   public void viewHotels(){
       System.out.println("---Available Hotels in Your Applications Are---");
       adminDao.hotels();
   } 
   
   public void totalOrders(){
       System.out.println("----Total Orders in Your Application----"); 
       adminDao.totalOrders();
   }       
}
