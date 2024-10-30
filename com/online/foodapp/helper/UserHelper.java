package com.online.foodapp.helper;

import java.util.Scanner;

import com.online.foodapp.model.UserData;
import com.online.foodapp.Dao.UserDAO;
import com.online.foodapp.Validator_User.UserValidator;
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
        
      public void signUp() {
    
    String name;
    boolean isValid;
    do {
        System.out.println("Enter Your Name:");
        name = scanner.nextLine();
        isValid = UserValidator.validateInput("name", name);
        if (!isValid) {
            System.out.println("Invalid Name. Please try again.");
        }
    } while (!isValid); 
    userData.setName(name);

    String email;
    do {
        System.out.println("Enter Your EmailID:");
        email = scanner.nextLine();
        isValid = UserValidator.validateInput("email", email);
        if (!isValid) {
            System.out.println("Invalid email. Please try again.");
        }
    } while (!isValid);
    userData.setEmail(email);
    
    String phone;
    do {
        System.out.println("Enter Your PhoneNumber:");
        phone = scanner.nextLine();
        isValid = UserValidator.validateInput("phoneNumber", phone); 
        if (!isValid) {
            System.out.println("Invalid phone number. Please try again.");
        }
    } while (!isValid); 
    userData.setPhoneNum(Long.parseLong(phone));

    
    String address;
    do {
        System.out.println("Enter Your Address:");
        address = scanner.nextLine().trim();
        isValid = UserValidator.validateInput("address", address); 
        if (!isValid) {
            System.out.println("Invalid address format. Please try again.");
        }
    } while (!isValid); 
    userData.setAddress(address);

    String password;
    do {
        System.out.println("Enter Your Password (password must be 6-8 characters with at least one special character):");
        password = scanner.nextLine();
        isValid = UserValidator.validateInput("password", password);
        if (!isValid) {
            System.out.println("Invalid password format. Please try again.");
        }
    } while (!isValid); 
    userData.setPassword(password);

    
    userDao.addUser(userData);
    System.out.println("Hi " + userData.getName() + ", Welcome to Our Fresh Feast");
    System.out.println("---------------------------------------------------");

    
    int id = userDao.getId(userData.getEmail(), userData.getPassword());
    loginpage(id);
}

      public void loginpage(int userId){
           System.out.println("Enter Your Choice :");
           System.out.println("1. Add Resutaurant\n2. Ordering Food ");
           int choice = scanner.nextInt();
           scanner.nextLine();
           switch(choice){
           case 1:
             hotel.addRestaurant(userId);
             break;
           case 2:
             userDao.setRole("customer",userData.getUserId());
             foodOrder.displayFood(userId);
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
           System.out.println(role);
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
