import java.util.Scanner;

public class TrainReservationSystem {
    static Scanner scanner = new Scanner(System.in);

    static class PassengerNode {
        String name;
        int seatNumber;
        PassengerNode next;

        PassengerNode(String name, int seatNumber) {
            this.name = name;
            this.seatNumber = seatNumber;
            this.next = null;
        }
    }

    static class TrainNode {
        String trainNumber;
        String destination;
        String departureTime;
        int availableSeats;
        TrainNode next;
        PassengerNode passengerHead;

        TrainNode(String trainNumber, String destination, String departureTime, int availableSeats) {
            this.trainNumber = trainNumber;
            this.destination = destination;
            this.departureTime = departureTime;
            this.availableSeats = availableSeats;
            this.next = null;
            this.passengerHead = null;
        }

        void bookSeat(String passengerName) {
            reserveSpecificSeat(passengerName, availableSeats);
        }

        void reserveSpecificSeat(String passengerName, int seatNumber) {
            PassengerNode current = passengerHead;
            while (current != null) {
                if (current.seatNumber == seatNumber) {
                    System.out.println("Seat number " + seatNumber + " is already booked.");
                    return;
                }
                current = current.next;
            }

            PassengerNode newPassenger = new PassengerNode(passengerName, seatNumber);
            if (passengerHead == null) {
                passengerHead = newPassenger;
            } else {
                current = passengerHead;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newPassenger;
            }
            availableSeats--;
            System.out.println("Seat " + seatNumber + " successfully booked for " + passengerName);
        }

        void cancelSeat(int seatNumber) {
            if (passengerHead == null) {
                System.out.println("No bookings found to cancel.");
                return;
            }

            if (passengerHead.seatNumber == seatNumber) {
                passengerHead = passengerHead.next;
                availableSeats++;
                System.out.println("Seat number " + seatNumber + " booking cancelled successfully.");
                return;
            }

            PassengerNode current = passengerHead;
            while (current.next != null) {
                if (current.next.seatNumber == seatNumber) {
                    current.next = current.next.next;
                    availableSeats++;
                    System.out.println("Seat number " + seatNumber + " booking cancelled successfully.");
                    return;
                }
                current = current.next;
            }

            System.out.println("Seat number " + seatNumber + " not found.");
        }

        void updateSeat(String passengerName, int newSeatNumber) {
            PassengerNode current = passengerHead;
            while (current != null) {
                if (current.seatNumber == newSeatNumber) {
                    System.out.println("Seat number " + newSeatNumber + " is already booked.");
                    return;
                }
                current = current.next;
            }

            current = passengerHead;
            while (current != null) {
                if (current.name.equals(passengerName)) {
                    System.out.println("Updating seat number for " + passengerName + " from " + current.seatNumber + " to " + newSeatNumber);
                    current.seatNumber = newSeatNumber;
                    return;
                }
                current = current.next;
            }

            System.out.println("Passenger " + passengerName + " not found.");
        }

        void displayPassengers() {
            if (passengerHead == null) {
                System.out.println("No passengers on this train.");
                return;
            }

            System.out.println("Passengers on Train " + trainNumber + ":");
            PassengerNode current = passengerHead;
            while (current != null) {
                System.out.println("Passenger Name: " + current.name + ", Seat Number: " + current.seatNumber);
                current = current.next;
            }
        }
    }

    static class TrainLinkedList {
        TrainNode head;

        void addTrain(String trainNumber, String destination, String departureTime, int availableSeats) {
            TrainNode newTrain = new TrainNode(trainNumber, destination, departureTime, availableSeats);
            if (head == null) {
                head = newTrain;
            } else {
                TrainNode current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newTrain;
            }
            System.out.println("Train " + trainNumber + " added successfully.");
        }

        TrainNode findTrain(String trainNumber) {
            TrainNode current = head;
            while (current != null) {
                if (current.trainNumber.equals(trainNumber)) {
                    return current;
                }
                current = current.next;
            }
            System.out.println("Train " + trainNumber + " not found.");
            return null;
        }

        void deleteTrain(String trainNumber) {
            if (head == null) {
                System.out.println("Train list is empty.");
                return;
            }

            if (head.trainNumber.equals(trainNumber)) {
                head = head.next;
                System.out.println("Train " + trainNumber + " deleted successfully.");
                return;
            }

            TrainNode current = head;
            while (current.next != null) {
                if (current.next.trainNumber.equals(trainNumber)) {
                    current.next = current.next.next;
                    System.out.println("Train " + trainNumber + " deleted successfully.");
                    return;
                }
                current = current.next;
            }

            System.out.println("Train " + trainNumber + " not found.");
        }

        void displayTrains() {
            if (head == null) {
                System.out.println("No trains available.");
                return;
            }

            System.out.println("Available Trains:");
            TrainNode current = head;
            while (current != null) {
                System.out.println("Train Number: " + current.trainNumber + ", Destination: " + current.destination + ", Departure Time: " + current.departureTime + ", Available Seats: " + current.availableSeats);
                current = current.next;
            }
        }
    }

    public static void main(String[] args) {
        TrainLinkedList trainList = new TrainLinkedList();

        while (true) {
            System.out.println("\n--- Train Reservation System ---");
            System.out.println("1. Add Train");
            System.out.println("2. Book Seat");
            System.out.println("3. Reserve Specific Seat");
            System.out.println("4. Cancel Seat");
            System.out.println("5. Display Trains");
            System.out.println("6. Display Train Passengers");
            System.out.println("7. Delete Train");
            System.out.println("8. Update Passenger Seat");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    System.out.print("Enter Train Number: ");
                    String trainNumber = scanner.nextLine();
                    System.out.print("Enter Destination: ");
                    String destination = scanner.nextLine();
                    System.out.print("Enter Departure Time: ");
                    String departureTime = scanner.nextLine();
                    System.out.print("Enter Number of Available Seats: ");
                    int availableSeats = scanner.nextInt();
                    trainList.addTrain(trainNumber, destination, departureTime, availableSeats);
                    break;
                case 2:
                    System.out.print("Enter Train Number to book a seat: ");
                    trainNumber = scanner.nextLine();
                    TrainNode train = trainList.findTrain(trainNumber);
                    if (train != null) {
                        System.out.print("Enter Passenger Name: ");
                        String passengerName = scanner.nextLine();
                        train.bookSeat(passengerName);
                    }
                    break;
                case 3:
                    System.out.print("Enter Train Number to reserve a specific seat: ");
                    trainNumber = scanner.nextLine();
                    train = trainList.findTrain(trainNumber);
                    if (train != null) {
                        System.out.print("Enter Passenger Name: ");
                        String passengerName = scanner.nextLine();
                        System.out.print("Enter Seat Number: ");
                        int seatNumber = scanner.nextInt();
                        train.reserveSpecificSeat(passengerName, seatNumber);
                    }
                    break;
                case 4:
                    System.out.print("Enter Train Number to cancel a seat: ");
                    trainNumber = scanner.nextLine();
                    train = trainList.findTrain(trainNumber);
                    if (train != null) {
                        System.out.print("Enter Seat Number to Cancel: ");
                        int seatNumber = scanner.nextInt();
                        train.cancelSeat(seatNumber);
                    }
                    break;
                case 5:
                    trainList.displayTrains();
                    break;
                case 6:
                    System.out.print("Enter Train Number to view passengers: ");
                    trainNumber = scanner.nextLine();
                    train = trainList.findTrain(trainNumber);
                    if (train != null) {
                        train.displayPassengers();
                    }
                    break;
                case 7:
                    System.out.print("Enter Train Number to delete: ");
                    trainNumber = scanner.nextLine();
                    trainList.deleteTrain(trainNumber);
                    break;
                case 8:
                    System.out.print("Enter Train Number for seat update: ");
                    trainNumber = scanner.nextLine();
                    train = trainList.findTrain(trainNumber);
                    if (train != null) {
                        System.out.print("Enter Passenger Name for seat update: ");
                        String passengerName = scanner.nextLine();
                        System.out.print("Enter New Seat Number: ");
                        int newSeatNumber = scanner.nextInt();
                        train.updateSeat(passengerName, newSeatNumber);
                    }
                    break;
                case 9:
                    System.out.println("Exiting the system.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}