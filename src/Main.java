import java.util.Scanner;         //Rochana Godigamuwa Program(20221116)
                                  //Start Date 15.12.2023       //End Date 12.01.2024

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean run = true;
        System.out.print("If Manager choose - 1 or If Customer choose - 2: ");           //Ask if the user is the manager or customer
        String user = scan.next();
        while(run){
            run = WestminsterShoppingManager.menu(scan, run, user);
        }
    }
}
