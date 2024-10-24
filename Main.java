import java.util.Scanner;
import java.sql.SQLException;

import com.online.foodapp.helper.Admin;
import com.online.foodapp.helper.UserHelper;
import com.online.foodapp.helper.Hotel;
import com.online.foodapp.helper.FoodOrder;
import com.online.foodapp.database.DBConnection;
import com.online.foodapp.model.UserData;
import com.online.foodapp.model.HotelData;
import com.online.foodapp.model.FoodData;
import com.online.foodapp.Dao.UserDAO;
import com.online.foodapp.Dao.HotelDAO;
import com.online.foodapp.Dao.FoodOrderDAO;
import com.online.foodapp.Dao.AdminDAO;

  public class Main{
  private static Scanner scanner = new Scanner(System.in); 
  
   static {
         DBConnection.createConnection();
       }
   public static void exit(){
     try{
       DBConnection.closeConnection();
     }
     catch(SQLException e){
       e.getMessage();
     }
     finally{
       scanner.close();
     }
   }
   
    public static void main(String[] args){
       UserData userData = new UserData();
       UserDAO userDao = new UserDAO();
       HotelData hotelData = new HotelData();
       HotelDAO hotelDao = new HotelDAO();
       AdminDAO adminDao = new AdminDAO();
       FoodData foodData = new FoodData();
       FoodOrderDAO foodOrderDao = new FoodOrderDAO();       
       Hotel hotel = new Hotel(scanner,hotelData,hotelDao,foodData,userData);
       Admin admin = new Admin(scanner,adminDao,hotel,userDao);
       FoodOrder foodOrder = new FoodOrder(scanner,userDao,foodOrderDao);
       UserHelper userHelper = new UserHelper(scanner,userData,userDao,hotel,foodOrder,admin);
       boolean quit = false;
       while(!quit){
          System.out.println("=====================================");
          System.out.println("        WELCOME TO FRESH FEAST       ");
          System.out.println("=====================================");
          System.out.println("Enter Your Choice\n1.Sign Up(Create Your Account)\n2.Sign In(Already have an Account)\n3.Exit");
          int choice =scanner.nextInt(); 
          scanner.nextLine();
            switch(choice){
              case 1:
                userHelper.signUp();
                break;
              case 2:
                userHelper.signIn();
                break;
              case 3:
                Main.exit();
                quit = true;
                System.out.println("Quiting........");
                break;
              default:
                System.out.println("Invalid Type");
            }
       }
    }
  }
