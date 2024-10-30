package com.online.foodapp.helper;

import java.util.Scanner;

import com.online.foodapp.model.HotelData;
import com.online.foodapp.model.FoodData;
import com.online.foodapp.model.UserData;
import com.online.foodapp.Dao.HotelDAO;
import com.online.foodapp.model.FoodCategory;

public class Hotel{
    private Scanner scanner;
    private HotelData hotelData;
    private HotelDAO hotelDao;
    private FoodData foodData;
    private UserData userData;
     
     public Hotel(Scanner scanner,HotelData hotelData,HotelDAO hotelDao,FoodData foodData,UserData userData){
         this .scanner = scanner;
         this.hotelData = hotelData;
         this.hotelDao = hotelDao;
         this.foodData = foodData;
         this.userData = userData;
     }
     
     public void addRestaurant(int userId){
         System.out.println("================================");
         System.out.println("    Partner With Fresh Feast    ");
         System.out.println("       Grow Your Busines!       ");
         System.out.println("================================");
         System.out.println("----------Register Your Restaurant-------------"); 
         System.out.println("   Step 1: Restaurant Details\n   Step 2: Menu and Operrations\n   Step 3: Documents details\n   Step 4: Partner Contract");
         restaurantDetails(userId);
         menuAndOperations();
         documentsDetails();
         partnerContract();
     }
     
     public void restaurantDetails(int userId){
          System.out.println("\nStep 1:");
          System.out.println("\n---- Restaurant Details ----");
          System.out.println("Enter Your Restaurant Name:");
          hotelData.setResName(scanner.nextLine());
          System.out.println("Enter Restaurant Owner Name:");
          hotelData.setOwnerName(scanner.nextLine());
          System.out.println("Enter Restaurant Contact Number: ");
          hotelData.setResNum(scanner.nextLong());
          scanner.nextLine();
            if(hotelData.getResNum() == null){
                return;
            }
          System.out.println("Enter Your Restaurant Location:");
          hotelData.setLocation(scanner.nextLine());
          System.out.println("Enter Your Restaurant Address:");
          hotelData.setAddress(scanner.nextLine());
          System.out.println("Enter Your Delivery Charge for Food Delivery:");
          hotelData.setDelFee(scanner.nextDouble());
          scanner.nextLine();
          hotelDao.addHotel(userId,hotelData);
     }
     
     public void menuAndOperations(){
       System.out.println("\nStep 2:"); 
       System.out.println("\n---- Menu and Operations ----");
       for(FoodCategory foods : FoodCategory.values()){
          System.out.println("How many "+foods.name()+" Dishes You want to insert :");
          foodData.setFoodCategory(foods.name());
          int choice = scanner.nextInt();
          scanner.nextLine();
            for(int i = 1; i<=choice;i++){
               System.out.println("Enter Your "+i+" food Name :");
               foodData.setFoodName(scanner.nextLine());
               System.out.println("Price of the food: ");
               foodData.setPrice(scanner.nextDouble());
               scanner.nextLine();
               hotelDao.addMenu(hotelData,foodData);
            }
        }
     }
     
     public void documentsDetails(){
          System.out.println("\nStep 3:"); 
          System.out.println("\n---- Documents Details ----");
          System.out.println("Please Enter Your FSSAI License Number:");
          hotelData.setFssai(scanner.nextLine());
          System.out.println("Please Enter your GST Registration Certificate Number:");
          hotelData.setGst(scanner.nextLine());
          System.out.println("---- Enter Your BankAccount Details ----");
          System.out.println("Enter your Bank Name:");
          hotelData.setBank(scanner.nextLine());
          System.out.println("Enter Your Account Number:");
          hotelData.setAccnum(scanner.nextLong());
          scanner.nextLine();
          System.out.println("Enter Your IFSC Code:");
          hotelData.setIfsc(scanner.nextLine());
          hotelDao.addDocuments(hotelData);
          System.out.println("Document details saved successfully!");
     }
     
     public void partnerContract(){
          System.out.println("\nStep 4:");
          System.out.println("\n---- Partner Contract ----");
          System.out.println("As the owner of My Hotel, hereby declare that all information provided about my hotel,including" +
                             "licenses, menu, and pricing, is accurate. I agree to comply with all legal and quality standards in providing food services" +
                             "to customers via Fresh Feast. I accept the terms and conditions of the platform, including payment processing  and " +
                             "commissions. I also authorize Fresh Feast to promote my hotel and handle customer payments on my behalf.") ; 
         System.out.println("Proceed to Store the information on Your Hotel.....\n Agreed or Not ?(y/n) ");
         String input = scanner.next().trim().toLowerCase(); 
         char c = input.charAt(0);
           if(c == 'y'){
                System.out.println("Your Hotel Request has been sent to the Admin......\nYour Hotel will be Added Within 24 hours ......");
                System.out.println("---------Go TO Home Page ---------");
                System.out.println();
           }
           else if(c == 'n'){
                deleteRestaurant();
           }
     }
     
     public void deleteRestaurant(){
         System.out.println("Enter Your Restaurant Id :");
         int id = scanner.nextInt();
         scanner.nextLine();
         hotelDao.deleteHotel(id);
         System.out.println("Hotel Details has been Deleted Successfully"); 
     } 
     
     public void viewRestaurant(int userId){
         System.out.println("\nWelcome to Your Hotel Management Page!");
         boolean exit = false;
         while(!exit){
         System.out.println("\n----- Menu -----\n1. Add Hotel\n2. Delete Hotel\n3. Add Food\n4. Remove Food\n5. Update Price\n6. Exit\nEnter Your Choice :");
         int choice = scanner.nextInt();
         scanner.nextLine(); 
         switch(choice){
            case 1:
               addRestaurant(userId);
               break;
            case 2:
               deleteRestaurant();
               break;
            case 3:
               updateFood();
               break;
            case 4:
               removeFood();
               break;
            case 5:
               updatePrice();
               break;
            case 6:
               exit = true;
               System.out.println("Exiting...");
               break;
            default:
               System.out.println("Invalid Choice! Please Try Again..");
         }
       }
     }
     
     public void updateFood(){
          System.out.println("Enter Your Restaurant Id:");
          int id = scanner.nextInt();
          scanner.nextLine();
          System.out.println("How Many foods You Want to add? ");
          int foodCount = scanner.nextInt();
          scanner.nextLine();
            for(int count = 1;count <= foodCount ;count++){
               System.out.println("Enter The FoodCategory:");
               foodData.setFoodCategory(scanner.nextLine());
               System.out.println("Enter the Food Name :");
               foodData.setFoodName(scanner.nextLine());
               System.out.println("Price of the food:");
               foodData.setPrice(scanner.nextDouble());
               scanner.nextLine();
               hotelDao.updateMenu(id,foodData);
               System.out.println("Food item updated successfully!");
            }
     }
     
     public void removeFood(){
          System.out.println("Enter Your Restaurant id:");
          int id = scanner.nextInt();
          scanner.nextLine();
          System.out.println("Which food you want to Remove :");
          String food = scanner.nextLine();
          hotelDao.deleteFood(id,food);
          System.out.println("Food item removed successfully!");
          
     }
     
     public void updatePrice(){
         System.out.println("Enter Your Restaurant Id:");
         int id = scanner.nextInt();
         scanner.nextLine();
         System.out.println("which Food Price, you want to update:");
         String food = scanner.nextLine();
         System.out.println("Updated price is :");
         double price = scanner.nextDouble();
         scanner.nextLine();
         hotelDao.updateFoodPrice(id,food,price);
         System.out.println("Food price updated successfully!");
     }     
}

