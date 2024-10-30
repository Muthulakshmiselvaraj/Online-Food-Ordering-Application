package com.online.foodapp.helper;

import java.util.Scanner;

import com.online.foodapp.Dao.AdminDAO;
import com.online.foodapp.Dao.UserDAO;

public class Admin {
    private Scanner scanner;
    private AdminDAO adminDao;
    private Hotel hotel;
    private UserDAO userDao;

    public Admin(Scanner scanner, AdminDAO adminDao, Hotel hotel, UserDAO userDao) {
        this.scanner = scanner;
        this.adminDao = adminDao;
        this.hotel = hotel;
        this.userDao = userDao;
    }

    public void viewApplication() {
        System.out.println("============= WELCOME ADMIN =============");
        boolean exit = false;
        
        while (!exit) {
            System.out.println("\n--- Main Menu ---");
            System.out.printf("%-20s : %s%n", "1. Hotels Request", "View and process hotel requests");
            System.out.printf("%-20s : %s%n", "2. Today's Orders", "View today's orders");
            System.out.printf("%-20s : %s%n", "3. View Hotels", "View all available hotels");
            System.out.printf("%-20s : %s%n", "4. Total Orders", "View total orders");
            System.out.printf("%-20s : %s%n", "5. Exit", "Exit the admin panel");
            System.out.print("\nEnter Your Choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
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
                    break;
                default:
                    System.out.println("Invalid Choice, Please Try Again.");
            }
        }
    }

    public void hotelsRequest() {
        System.out.println("\n----- Hotel Requests -----");
        System.out.println("Available Hotel Requests:\n----- Check Hotel Details -----");
        int id = adminDao.hotelDetails();
        System.out.println("\n--- Document Details of the Hotel ---");
        adminDao.hotelDocuments();
        System.out.print("\nAdmin Action Required: Please review the status of the hotel application.\n"
               + "Choose the appropriate status:\n"
               + "1. Accept the application\n"
               + "2. Reject the application\n"
               + "Enter your choice (1 or 2): ");
        int status = scanner.nextInt();
        switch (status) {
            case 1:
                System.out.printf("Assigning role 'hotelowner' to User ID: %d%n", id);
                userDao.setRole("hotelowner", id);
                break;
            case 2:
                hotel.deleteRestaurant();
                break;
            default:
                System.out.println("Invalid Choice.");
        }
    }
    public void todayOrders() {
        System.out.println("\n------- Today's Orders -------");
        adminDao.ordersPerDay();
    }

    public void viewHotels() {
        System.out.println("\n--- Available Hotels ---");
        adminDao.hotels();
    }

    public void totalOrders() {
        System.out.println("\n--- Total Orders in Application ---");
        adminDao.totalOrders();
    }
}

