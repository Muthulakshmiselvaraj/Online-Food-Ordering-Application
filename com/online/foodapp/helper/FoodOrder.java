package com.online.foodapp.helper;

import java.util.Scanner;

import com.online.foodapp.Dao.FoodOrderDAO;
import com.online.foodapp.Dao.UserDAO;
import com.online.foodapp.model.UserData;

public class FoodOrder{
    private Scanner scanner;
    private FoodOrderDAO foodOrderDao;
    private UserDAO userDao;
    
    public FoodOrder(Scanner scanner,UserDAO userDao,FoodOrderDAO foodOrderDao){
         this.scanner = scanner;
         this.foodOrderDao = foodOrderDao;
         this.userDao = userDao;
    }
    
    public void displayFood(int userId){
        System.out.println("----Enjoy Your Food With Us----");
        boolean exit = false;
        while(!exit){
           System.out.println("1.MyProfile\n2.Search the Food By Location\n3.Search the Food By HotelName\n4.Exit");
           int choice = scanner.nextInt();
           scanner.nextLine();
           switch(choice){
              case 1:
                 myProfile(userId);
              case 2:
                 getHotelByLocation(userId);
                 break;
              case 3:
                 getFoodByHotelName(userId);
                 break;
              case 4:
                 exit = true;
                 System.out.println("Exiting...");
                 break;
              default:
                 System.out.println("Invalid Type");
           }
        }
    }
    
    public void myProfile(int userId){
        boolean exit = false;
        while(!exit){
           System.out.println("1.MyOrders\n2.UpdateMy Profile\n3.Change Password\n4.Exit");
           int choice = scanner.nextInt();
           scanner.nextLine();
           switch(choice){
              case 1:
                foodOrderDao.myOrders(userId);
                break;
              case 2:
                updateAddress(userId);
                break;
              case 3:
                changePassword(userId);
                break;
              case 4:
                exit = true;
                System.out.println("Exiting....");
                break;
              default:
                System.out.println("Invalid Type");
           }
        }
    }
    
    public void updateAddress(int userId){
        System.out.println("Enter the new Address to delivey your Food");
        String address = scanner.nextLine();
        userDao.updateAddress(userId,address);
    }
    
    public void changePassword(int userId){
        System.out.println("Enter the new password to your Account Safety");
        String password = scanner.nextLine();
        userDao.changePassword(userId,password);
    }
    
    public void getHotelByLocation(int userId){
        System.out.println("Enter The Location:");
        String location = scanner.nextLine();
        foodOrderDao.getFoodByLoc(location);
        System.out.println("Enter The Hotel Name:");
        String hotel = scanner.nextLine();
        foodOrderDao.getFoodCategoryByHotel(hotel);
        System.out.println("Enter the FoodCategory:");
        String category = scanner.nextLine();
        foodOrderDao.getFoodByCategory(category,hotel);
        customerOrders(userId,hotel);
    }
    
    public void getFoodByHotelName(int userId){
        System.out.println("Enter The Hotel Name:");
        String hotelName = scanner.nextLine();
        foodOrderDao.getFoodCategoryByHotel(hotelName);
        System.out.println("Enter the FoodCategory:");
        String category = scanner.nextLine();
        foodOrderDao.getFoodByCategory(category,hotelName);
        customerOrders(userId,hotelName);
    }
    
    public void customerOrders(int userId,String hotel){
         System.out.println("which food would you like?");
         String food = scanner.nextLine();
         System.out.println("How much would you want ?");
         int quantity = scanner.nextInt();
         scanner.nextLine();
         System.out.println("Would you like to give tips for our services?");
         double tips = scanner.nextDouble();
         scanner.nextLine(); 
         int foodlistId = foodOrderDao.getFoodListId(food,hotel);  
         Double price = foodOrderDao.getPrice(foodlistId);
         Double deliveryFee = foodOrderDao.getDeliveryCharge(hotel);
         Double totalAmount = quantity * price;
         payment(tips,totalAmount,deliveryFee);
         foodOrderDao.orderDetails( userId,foodlistId,quantity,totalAmount);
    }
    
    public void payment(double tips,double amount,double deliveryCharge){ 
         System.out.println("Your Total Amount for Your Food is :"+amount);
         System.out.println("Tips Amount is :"+tips);
         System.out.println("Delivery Charge is :"+deliveryCharge);
         double total = amount+tips+deliveryCharge;
         System.out.println("Your Total Amount is :"+total);
             
    }
}
