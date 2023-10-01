//I declare that my work contains no examples of misconduct, such as plagiarism or collusion
//Any code taken from other sources is referenced within my code solution

//Author's Name - Thihansa Akmeemana
//Student ID - w1952801


public class Ticket {
    int row_no;
    int seat_no;
    double price;
    Person person;

    public Ticket(int row, int seat, double price, Person person){
        this.row_no = row;
        this.seat_no = seat;
        this.price = price;
        this.person = person;
    }

    public void print(){
        System.out.println("Name : "+ this.person.name);
        System.out.println("Surname : "+ this.person.surname);
        System.out.println("Email : "+ this.person.email);
        System.out.println("Row : "+ this.row_no);
        System.out.println("Seat : "+ this.seat_no);
        System.out.println("Ticket price : "+ this.price + "\n");
    }
}

