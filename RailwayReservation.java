import java.io.*;
import java.util.*;

public class RailwayReservation {
    static List<Train> trains = new ArrayList<>();
    static Map<Integer, Passenger> bookings = new HashMap<>();
    static int pnrCounter = 1001;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        loadTrains();
        loadBookings();

        while (true) {
            System.out.println("\n===== Railway Reservation System =====");
            System.out.println("1. View Available Trains");
            System.out.println("2. Book Ticket");
            System.out.println("3. Cancel Ticket");
            System.out.println("4. Check PNR Status");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewTrains();
                    break;
                case 2:
                    bookTicket(sc);
                    break;
                case 3:
                    cancelTicket(sc);
                    break;
                case 4:
                    checkPNR(sc);
                    break;
                case 5:
                    saveBookings();
                    System.out.println("Exiting system. Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again!");
            }
        }
    }

    // Load predefined train data
    static void loadTrains() {
        trains.add(new Train(101, "Rajdhani Express", "Delhi", "Mumbai", 5));
        trains.add(new Train(102, "Shatabdi Express", "Bangalore", "Chennai", 3));
        trains.add(new Train(103, "Duronto Express", "Kolkata", "Delhi", 4));
    }

    static void viewTrains() {
        System.out.println("\nAvailable Trains:");
        for (Train train : trains) {
            System.out.println(train);
        }
    }

    static void bookTicket(Scanner sc) {
        System.out.print("\nEnter your name: ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.print("Enter your age: ");
        int age = sc.nextInt();
        System.out.print("Enter Train Number: ");
        int trainNum = sc.nextInt();

        Train selectedTrain = null;
        for (Train train : trains) {
            if (train.trainNumber == trainNum) {
                selectedTrain = train;
                break;
            }
        }

        if (selectedTrain == null) {
            System.out.println("Invalid Train Number!");
            return;
        }

        if (selectedTrain.availableSeats == 0) {
            System.out.println("Sorry, no seats available!");
            return;
        }

        selectedTrain.bookSeat();
        int pnr = pnrCounter++;
        Passenger passenger = new Passenger(name, age, selectedTrain.trainName, trainNum, pnr);
        bookings.put(pnr, passenger);
        saveBookings();
        System.out.println("Ticket Booked! Your PNR: " + pnr);
    }

    static void cancelTicket(Scanner sc) {
        System.out.print("\nEnter PNR Number to cancel ticket: ");
        int pnr = sc.nextInt();

        if (!bookings.containsKey(pnr)) {
            System.out.println("Invalid PNR Number!");
            return;
        }

        Passenger passenger = bookings.remove(pnr);
        for (Train train : trains) {
            if (train.trainNumber == passenger.trainNumber) {
                train.cancelSeat();
                break;
            }
        }
        saveBookings();
        System.out.println("Ticket Cancelled Successfully!");
    }

    static void checkPNR(Scanner sc) {
        System.out.print("\nEnter PNR Number: ");
        int pnr = sc.nextInt();

        if (bookings.containsKey(pnr)) {
            System.out.println("PNR Status: " + bookings.get(pnr));
        } else {
            System.out.println("Invalid PNR Number!");
        }
    }

    static void saveBookings() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tickets.txt"))) {
            oos.writeObject(bookings);
        } catch (Exception e) {
            System.out.println("Error saving bookings!");
        }
    }

    static void loadBookings() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("tickets.txt"))) {
            bookings = (HashMap<Integer, Passenger>) ois.readObject();
        } catch (Exception e) {
            System.out.println("No previous bookings found.");
        }
    }
}
