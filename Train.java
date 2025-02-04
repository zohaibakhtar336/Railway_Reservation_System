import java.util.*;

class Train {
    int trainNumber;
    String trainName;
    String source;
    String destination;
    int totalSeats;
    int availableSeats;

    public Train(int trainNumber, String trainName, String source, String destination, int totalSeats) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    public void bookSeat() {
        if (availableSeats > 0) {
            availableSeats--;
        }
    }

    public void cancelSeat() {
        if (availableSeats < totalSeats) {
            availableSeats++;
        }
    }

    @Override
    public String toString() {
        return "Train No: " + trainNumber + ", Name: " + trainName +
               ", From: " + source + " To: " + destination +
               ", Available Seats: " + availableSeats;
    }
}
