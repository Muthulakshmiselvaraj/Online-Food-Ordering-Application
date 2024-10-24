package com.online.foodapp.helper;

import java.util.Scanner;

import com.online.foodapp.model.UserData;
import com.online.foodapp.Dao.UserDAO;

public class UserHelper{
    private Scanner scanner;
    private UserData userData;
    private UserDAO userDao;
    private Hotel hotel;
    private FoodOrder foodOrder;
    private Admin admin;
    public UserHelper(Scanner scanner,UserData userData,UserDAO userDao,Hotel hotel,FoodOrder foodOrder,Admin admin){
       this.scanner = scanner;
       this.userData = userData;
       this.userDao = userDao;
       this.hotel = hotel;
       this.foodOrder = foodOrder;
       this.admin = admin;
    }
    
      public void signUp(){
           System.out.println("Enter Your Name:");
           userData.setName(scanner.nextLine());
           System.out.println("Enter Your EmailID :");
           userData.setEmail(scanner.nextLine());
           System.out.println("Enter Your PhoneNumber :");
           userData.setPhoneNum(scanner.nextLong());
           scanner.nextLine();
             if(userData.getPhoneNum() == null){
                return;
             }
           System.out.println("Enter Your Address :");
           userData.setAddress(scanner.nextLine());
           System.out.println("Enter Your Password :");
           userData.setPassword(scanner.nextLine());
           
          userDao.addUser(userData);
          int id = userDao.getId(userData.getEmail(),userData.getPassword());
          loginpage(id);
      }
      public void loginpage(int userId){
           System.out.println("Enter Your Choice :");
           System.out.println("1.Add Resutaurant\n2.Ordering Food ");
           int choice = scanner.nextInt();
           scanner.nextLine();
           String query ="";
           switch(choice){
           case 1:
             hotel.addRestaurant(userId);
             break;
           case 2:
             foodOrder.displayFood(userId);
             query = "UPDATE SIGNUP SET ROLE = 'CUSTOMER' WHERE USERID = ? ;" ;
             userDao.setRole(query,userData.getUserId());
             break;
           default:
             System.out.println("Invalid Type");
          }
      }
      
      public void signIn(){
           System.out.println("Enter Your EmailID:");
           String email = scanner.nextLine();
           System.out.println("Enter Your Password:");
           String password = scanner.nextLine();
           String role = userDao.getRoleFromEmailAndPassword(email,password);
           int id = userDao.getId(email,password);
           switch(role){
           case "customer":
              foodOrder.displayFood(id);
              break;
           case "hotelowner":
              hotel.viewRestaurant(id);
              break;
           case "admin" :
              admin.viewApplication();
              break;
           default :
              System.out.println("Invalid EmailID or Password");
           }
      }
      
}
