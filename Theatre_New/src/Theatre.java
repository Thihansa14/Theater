//I declare that my work contains no examples of misconduct, such as plagiarism or collusion
//Any code taken from other sources is referenced within my code solution

//Author's Name - Thihansa Akmeemana
//Student ID - w1952801
//Date - 25/02/2023

import java.util.*;
import  java.io.*;
import java.util.ArrayList;
public class Theatre {

    static int[] row1={0,0,0,0,0,0,0,0,0,0,0,0};
    static int[] row2={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    static int[] row3={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    static int[][] seats={row1,row2,row3};                          //creating a 2D array which includes all three rows
    static ArrayList<Ticket> tickets = new ArrayList<Ticket>();    //creating an array list of tickets to save all the Tickets


    public static void print_menu(){
        System.out.println("-".repeat(75));
        System.out.println("Please select an option: ");           // Customer Options
        System.out.println("1) Buy a ticket");
        System.out.println("2) Print seating area");
        System.out.println("3) Cancel ticket ");
        System.out.println("4) List available seats");
        System.out.println("5) Save to file");
        System.out.println("6) Load from file");
        System.out.println("7) Print ticket information and total price");
        System.out.println("8) Sort tickets by price");
        System.out.println("\t 0) Quit ");
        System.out.println("-".repeat(75));
    }
    public static void main(String[] args) {
        System.out.println( "Welcome to the New Theatre");
        Scanner sc=new Scanner(System.in);
        boolean quit = false;
        while(!quit){
            print_menu();
            System.out.println("Enter option:");       // Calling up on each method according to the user input
            int option = sc.nextInt();
            switch (option){
                case 1:
                    buy_ticket();
                    break;
                case 2:
                    print_seating_area();
                    break;
                case 3:
                    cancelTicket();
                    break;
                case 4:
                    show_available();
                    break;
                case 5:
                    save();
                    break;
                case 6:
                    load();
                    break;
                case 7:
                    show_tickets_info();
                    break;
                case 8:
                    sort_tickets(tickets);
                    break;
                case 0:
                    System.out.println("Exiting program...");
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid option!! Please re-enter your option.");
            }
        }
    }

    //Creating a method for buy tickets
    public static void buy_ticket(){
        int row = inputValidator(1, 3, "Enter the row number:");
        int seat;
        if (row == 1) {
            seat = inputValidator(1, 12, "Enter the seat number:");
        } else if (row == 2) {
            seat = inputValidator(1, 16, "Enter the seat number:");
        } else {
            seat = inputValidator(1, 20, "Enter the seat number:");
        }
        if (seatCheckerAndBooker(row, seat, true)) {
            String name = userDetailsInput("Enter your name:");
            String surname = userDetailsInput("Enter your surname:");

            Scanner input = new Scanner(System.in);
            String email;
            while(true) {
                System.out.println(" ");
                System.out.print("Enter your email:");
                email = input.next();
                if (email.contains("@") && email.contains(".")) {
                    break;
                } else {
                    System.out.println("Invalid Email Address.Try Again!!");
                    continue;
                }
            }
            double price = inputValidator(1, 5000, "Enter seat price: ");


            Person person = new Person(name, surname, email);
            Ticket ticket = new Ticket(row, seat, price, person);
            tickets.add(ticket);
            System.out.println("Ticket purchased successfully");        // Displaying user that the ticket was purchased successfully
            System.out.println("You have booked the seat " + seat + " of row " + row);
        }else {
            System.out.println("Sorry! Seat is already booked");
        }
    }

    public static void print_seating_area(){
        System.out.println("    ***********    ");
        System.out.println("    *  STAGE  *    ");
        System.out.println("    ***********    ");
        for (int[] i:seats){
            for(int j:i){
                if(j==1){
                    System.out.print("X");
                }else{
                    System.out.print("O");
                }
            }
            System.out.println();
        }
    }
    // Creating a method to Cancel tickets
    public static void cancelTicket(){
        int row = inputValidator(1, 3, "Enter the row number:");
        int seat;
        if (row == 1) {
            seat = inputValidator(1, 12, "Enter the seat number:");
        } else if (row == 2) {
            seat = inputValidator(1, 16, "Enter the seat number:");
        } else {
            seat = inputValidator(1, 20, "Enter the seat number:");
        }
        if (seatCheckerAndBooker(row, seat, false)) {
            int count=0;
            for (Ticket ticket:tickets){
                if(ticket.row_no==row && ticket.seat_no==seat){
                    tickets.remove(count);
                    break;
                }
                count++;
            }
            System.out.println("Ticket cancelled successfully!");
            System.out.println("You have cancelled the seat "+seat+" of row "+row);

        }else {
            System.out.println("Seat isn't booked");
        }
    }

    public static boolean seatCheckerAndBooker(int row_num,int seat_num,boolean book){
        // to book the seat
        if(book) {
            if (row_num == 1) {
                if (row1[seat_num - 1] == 0) {
                    row1[seat_num - 1] = 1;
                    return true;
                }
            }
            if (row_num == 2) {
                if (row2[seat_num - 1] == 0) {
                    row2[seat_num - 1] = 1;
                    return true;
                }
            }
            if (row_num == 3) {
                if (row3[seat_num - 1] == 0) {
                    row3[seat_num - 1] = 1;
                    return true;
                }
            }
        }
        // to cancel book the seat
        else {
            if (row_num == 1) {
                if (row1[seat_num - 1] == 1) {
                    row1[seat_num - 1] = 0;
                    return true;
                }
            }
            if (row_num == 2) {
                if (row2[seat_num - 1] == 1) {
                    row2[seat_num - 1] = 0;
                    return true;
                }
            }
            if (row_num == 3) {
                if (row3[seat_num - 1] == 1) {
                    row3[seat_num - 1] = 0;
                    return true;
                }
            }
        }
        return false;
    }

    //Method to validate the inputs
    public static int inputValidator(int min,int max,String question){
        while (true){
            Scanner input = new Scanner(System.in);
            try {
                System.out.print(question);
                int data = input.nextInt();
                if (min <= data && data <= max) {
                    return data;
                } else {
                    System.out.println("Please enter a number between :" + min + " and " + max);
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer");
            }
        }
    }

    // Method  to input user details
    public static String userDetailsInput(String question){
        Scanner input=new Scanner(System.in);

        System.out.print(question);
        return input.next();
    }

    public static void show_available(){
        System.out.print("Seats available in row 1:");
        for(int i=0;i<12;i++){
            if (row1[i]==0){
                System.out.print((i+1)+",");
            }
        }
        System.out.println("\b");
        System.out.print("Seats available in row 2:");
        for(int i=0;i<16;i++){
            if (row2[i]==0){
                System.out.print((i+1)+",");
            }
        }
        System.out.println("\b");
        System.out.print("Seats available in row 3:");
        for(int i=0;i<20;i++){
            if (row3[i]==0){
                System.out.print((i+1)+",");
            }
        }
        System.out.println("\b");
    }

    //Writing programing data to a specific file
    public static void save(){

        try{
            FileWriter myWriter = new FileWriter("seats.txt");
            for (int[] row:seats){
                for (int seats : row){
                    if(seats == 1){
                        myWriter.write("1");

                    }else{
                        myWriter.write("0");
                    }
                }
                myWriter.write("\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        }
        catch (IOException e) {
            System.out.println("An error occurred");
        }
    }

    // Loading Program Data From File
    public  static void load() {
        try{
            File file = new File("seats.txt");        // Getting the file to be read
            Scanner file_reader = new Scanner(file);
            while (file_reader.hasNextLine()){
                String text = file_reader.nextLine();         //Reading the content in the file line by line
                System.out.println(text);                     //Displaying the content that was read from the file
            }
            file_reader.close();
        } catch (IOException e) {
            System.out.println("An Error occurred while reading the File."); // If the file cannot be read showing an error
            e.printStackTrace();
        }
    }

    public static void show_tickets_info(){
        double total_price = 0;
        for(Ticket ticket:tickets){
            total_price+=ticket.price;
            ticket.print();
        }
        System.out.println("Total ticket price is "+ total_price);//printing the total price of tickets
    }

    public static void sort_tickets(ArrayList<Ticket>  array){
        //sorting function
        int n = array.size();

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (array.get(j).price < array.get(min_idx).price)
                    min_idx = j;

            // Swap the found minimum element with the first element
            Ticket temp = array.get(min_idx);
            array.set(min_idx,array.get(i)) ;
            array.set(i,temp) ;
        }
        for (Ticket ticket:array ){
            ticket.print();
        }

    }

}




