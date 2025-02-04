class Passenger {
    String name;
    int age;
    String trainName;
    int trainNumber;
    int pnr;

    public Passenger(String name, int age, String trainName, int trainNumber, int pnr) {
        this.name = name;
        this.age = age;
        this.trainName = trainName;
        this.trainNumber = trainNumber;
        this.pnr = pnr;
    }

    @Override
    public String toString() {
        return "PNR: " + pnr + ", Name: " + name + ", Age: " + age +
               ", Train: " + trainName + " (" + trainNumber + ")";
    }
}
