import javax.swing.*;                      //Rochana Program (20221116)
import java.io.File;                       //Start Date 15.12.2023       //End Date 12.01.2024
import java.io.FileWriter;
import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Define a class implementing the ShoppingManager interface
public class WestminsterShoppingManager implements ShoppingManager {

    public static ArrayList<String> productIds = new ArrayList<>();     //ArrayList to Store product IDs
    private static List<Product> productList = new ArrayList<>();      //List to store products
    public List<Product> getProductList() {           //Getter for productList
        return productList;
    }
    public List<Product> getProductsByType(String productType) {     //Method to get products by Type
        List<Product> filteredList = new ArrayList<>();
        for (Product product : productList) {                //Checks if product in productList
            if (product instanceof Electronics && productType.equalsIgnoreCase("Electronics")) {    //Sees if user has entered electronics and if he has entered simple or capital letter
                filteredList.add(product);
            } else if (product instanceof Clothing && productType.equalsIgnoreCase("Clothing")) {    //Sees if user has entered Clothing and if he has entered simple or capital letter
                filteredList.add(product);
            } else if (productType.equalsIgnoreCase("All")) {
                filteredList.add(product);
            }
        }
        return filteredList;
    }


    public static boolean menu(Scanner scan, boolean Boolean, String user){
        switch (user){                                                                   //See if he is manager or customer
            case "1":
                System.out.println();

                System.out.println("--------Westminster Shopping Manager--------");       //The Console Menu for Manager
                System.out.println();
                System.out.println("1.Add a Product");
                System.out.println("2.Delete a Product");
                System.out.println("3.Print the Products Added");
                System.out.println("4.Save the Products Added");
                System.out.println("5.Load the Products");
                System.out.println("6.Exit");
                System.out.println();
                System.out.println("--------------------------------------");
                try {
                    //Takes manager's input
                    System.out.print("Select your Option Please (1-6): ");
                    int option = scan.nextInt();

                    //Takes the input as option
                    switch (option) {
                        case (1):
                            //When adding a product can't add more than 50 product to the system
                            if(productIds.size() >= 51){
                                System.out.println("You can only add up to 50 products to your System.");
                                break;
                            }
                            addProduct(scan);
                            break;
                        case (2):
                            deleteProduct(scan);
                            break;
                        case (3):
                            printProducts();
                            break;
                        case (4):
                            saveFile();
                            break;
                        case (5):
                            loadFile();
                            break;
                        case (6):
                            Boolean = false;
                            System.out.println("You have exited Westminster Shopping Manager");
                            break;
                        default:
                            System.out.println("Invalid Input.Please Re-enter.");
                            break;
                    }
                }catch (Exception e){
                    System.out.println("Invalid input.Please Re-enter.");
                }
                return Boolean;
            case "2":
                loadFile();
                SwingUtilities.invokeLater(() -> new GUI());                //Starts the GUI loading the previous saved file
                return false;
            default:
                System.out.println("Invalid Input");
                return false;
        }
    }


    //
    //                              ADDING PRODUCT FOR THE SYSTEM (1).
    public static void addProduct(Scanner scan){
        String productType;
        String productId  = null;
        String productName;
        int avaliableUnits = 0;
        double price = 0;
        String brandName;
        int warranty = 0;
        double size = 0;
        String color;
        String Electronic = "e";
        String Clothing = "c";
        do {
            //Ask the user to enter the type of product
            System.out.println("Please give the Type of your Product");
            System.out.print("Enter 'E' for Electronics or Enter 'C' for Clothing: ");
            productType = scan.next();

        //Takes input as turns it into lower case and checks
        }while(!(productType.equalsIgnoreCase(Electronic) || productType.equalsIgnoreCase(Clothing)));
        do{
            try {
                //Takes product ID for the specific category
                System.out.print("Enter Product Id: ");
                productId = scan.next();
            }catch (Exception e){
                System.out.println("Invalid input.Please Re-enter.");     //If input is other than integer
            }
            if(productIds.contains(productId)){                           //See it is already in the array
                System.out.println("Product ID already in use");
            }
        }while(productIds.contains(productId));                            //Loops out if not in array
        productIds.add(productId);

        System.out.print("Enter Product Name: ");                          //Takes product name
        productName = scan.next();

        //Calling InputValidationChecker method to check if correct input is given by manager FOR available unit which is always a integer
        avaliableUnits = InputValidationChecker(avaliableUnits, "Available units in your Stock: ");


        //Calling InputValidationChecker method to check if correct input is given by manager FOR PRICE
        price = InputValidationChecker(price, "Price of Product: ");


        if(productType.equalsIgnoreCase(Electronic)){

            //If mamnger chooses Clothing then only they should ask for this
            System.out.print("Brand Name: ");
            brandName = scan.next();


            //Calling InputValidationChecker method to check if correct input is given by manager FOR WARRANTY
            warranty = InputValidationChecker(warranty, "Warranty in Product: ");

            Electronics electronic = new Electronics(productId,productName,avaliableUnits,price,brandName,warranty);
            productList.add(electronic);
            System.out.println("Product added to the System.");
            System.out.println();
        }else{
            size = InputValidationChecker(size, "Size: ");
            System.out.print("Enter Colour: ");
            color = scan.next();

            Clothing clothing = new Clothing(productId, productName, avaliableUnits, price, size, color);
            productList.add(clothing);
            System.out.println("Product added to the System.");
            System.out.println();
        }


    }
    //Validation checker for input Whole Numbers and String     // UNIT TESTING //
    public static int InputValidationChecker(int unit, String UnitString){
        do{
            Scanner scanner = new Scanner(System.in);              //Takes input if it's a integer and String
            try{
                System.out.print(UnitString);                     //If it's a integer checks if it's negative number
                unit = scanner.nextInt();
                if(unit <= 0){
                    System.out.println("Please enter a Positive Number.");
                }
            }catch (Exception e){                                 //Else if he has entered an string it would say to re-enter.
                System.out.println("Invalid input. Please Re-enter.");
            }
        }while(unit <= 0);                                        //If he still enters incorrect values

        return unit;
    }

    //Validation checker for input Double and String     // UNIT TESTING //
    public static double InputValidationChecker(double unit, String UnitString){
        do{
            Scanner scanner = new Scanner(System.in);                         //Takes the input if it's a unit and unitString
            try{
                System.out.print(UnitString);                                 //Gives the input
                unit = scanner.nextDouble();
                if(unit <= 0){                                                //Checks the input to see if it's less than 0
                    System.out.println("Please enter a Positive Number.");
                }
            }catch (Exception e){
                System.out.println("Invalid input.Please Re-enter.");          //If its any other input tells to re-enter
            }
        }while(unit <= 0);                                                     //If he see enters invalid input again loops

        return unit;
    }

    //                      DELETE PRODUCT METHOD FOR SYSTEM (2)
    public static void deleteProduct(Scanner scan){
        String productID;
        System.out.print("Enter ID of the Product you want to Delete from system: ");
        productID = scan.next();                                                 //Takes the input of user
        if(productIds.contains(productID)){                                      //See if it is they in the productIds ArrayList
            for(Product prod : productList){                                     //See if is in productlist
                if(prod.getProduct_ID().equalsIgnoreCase(productID)){
                    if(prod instanceof Electronics){
                        System.out.println("Product Type: Electronic");
                    }else{
                        System.out.println("Product Type: Clothing");
                    }
                    System.out.println(prod.toString());
                    System.out.println("Are you sure you want to remove the above item?(Enter Y or N): ");
                    String decision = scan.next();
                    if(decision.equalsIgnoreCase("y")) {
                        System.out.println("Above item has been removed from System");
                        productList.remove(prod);
                        productIds.remove(productID);
                    }else{
                        System.out.println("Above item did not get removed from the System.");
                    }
                    System.out.println("There are " + productIds.size() + " products left.");
                    System.out.println();
                    break;
                }
            }
        }else{
            System.out.println("No such item found in System:");
        }
    }

    //                                PRINTING ADDED PRODUCTS (3)
    public static void printProducts(){
        Collections.sort(productList, Comparator.comparing(Product::getProduct_ID));
        for(Product prod : productList){
            if(prod instanceof Electronics){
                System.out.println("Product Type: Electronic");
                ((Electronics) prod).displayDetails();
            }else{
                System.out.println("Product Type: Clothing");
                ((Clothing) prod).displayDetails();
            }
            System.out.println();
        }
    }

    //                              SAVE FILE METHOD FOR INPUTS GIVEN (4)
    public static void saveFile(){
        try(FileWriter dataFile = new FileWriter("RochaFile.txt")){
            for(Product prod : productList){
                if(prod instanceof Electronics){
                    dataFile.write(((Electronics) prod).saveElectronic() + "\n");
                }else{
                    dataFile.write(((Clothing) prod).saveClothing() + "\n");
                }
            }
            System.out.println("File has been saved successfully.");
            System.out.println();

        }catch(Exception e){
            System.out.println("An Error Occurred while trying to Save File");//
        }
    }

    //                                 LOAD FILE METHOD FOR PREVIOUS INPUTS GIVEN (4)
    public static void loadFile(){
        try {
            productList.clear();
            File dataFile = new File("RochaFile.txt");
            Scanner fileScanner = new Scanner(dataFile);
            while(fileScanner.hasNextLine()){
                String product = fileScanner.nextLine();
                String[] itemDetails = product.split("-", 7);
                if(itemDetails[0].equals("Electronic")){
                    Electronics electronic = new Electronics(itemDetails[1],itemDetails[2],Integer.parseInt(itemDetails[3]),Double.parseDouble(itemDetails[4]),itemDetails[5],Integer.parseInt(itemDetails[6]));
                    productList.add(electronic);
                    productIds.add(itemDetails[1]);
                }else{
                    Clothing clothing = new Clothing(itemDetails[1],itemDetails[2],Integer.parseInt(itemDetails[3]),Double.parseDouble(itemDetails[4]),Double.parseDouble(itemDetails[5]),itemDetails[6]);
                    productList.add(clothing);
                    productIds.add(itemDetails[1]);
                }
            }
            System.out.println("File has been loaded successfully.");
            System.out.println();

        }catch (Exception e){
            System.out.println("Invalid Input.");
        }
    }

    public void addProduct(Product product) {

    }

    @Override
    public void deleteProduct(String productID) {

    }

    @Override
    public void saveProductListToFile() {

    }

    @Override
    public void loadProductListFromFile() {

    }

}
