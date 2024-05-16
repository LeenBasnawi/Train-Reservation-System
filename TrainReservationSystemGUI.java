/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication177;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public class TrainReservationSystemGUI extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);
    private JPanel welcomePanel = new JPanel();
    private JPanel signUpPanel = new JPanel();
    private JPanel loginPanel = new JPanel();
    private JPanel systemPanel = new JPanel();
    private JPanel addTrainPanel = new JPanel();
    private JPanel bookSeatPanel = new JPanel();
    private Map<String, String> userDatabase = new HashMap<>();
    private int trainCount = 0;
    private int seatCount = 0;

    public TrainReservationSystemGUI() {
        setTitle("Train Reservation System");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().add(mainPanel);

        initWelcomePanel();
        initSignUpPanel();
        initLoginPanel();
        initSystemPanel();
        initAddTrainPanel();
        initBookSeatPanel();

        mainPanel.add(welcomePanel, "Welcome");
        mainPanel.add(signUpPanel, "SignUp");
        mainPanel.add(loginPanel, "Login");
        mainPanel.add(systemPanel, "System");
        mainPanel.add(addTrainPanel, "AddTrain");
        mainPanel.add(bookSeatPanel, "BookSeat");

        cardLayout.show(mainPanel, "Welcome");
    }

    private void initWelcomePanel() {
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        JLabel welcomeTextLabel = new JLabel("Welcome to the Train Reservation System", JLabel.CENTER);
        welcomeTextLabel.setForeground(Color.BLUE);
        welcomeTextLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JButton signUpButton = new JButton("Sign Up");
        JButton loginButton = new JButton("Login");

        signUpButton.addActionListener(e -> cardLayout.show(mainPanel, "SignUp"));
        loginButton.addActionListener(e -> cardLayout.show(mainPanel, "Login"));

        welcomePanel.add(Box.createVerticalGlue());
        welcomePanel.add(welcomeTextLabel);
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        welcomePanel.add(signUpButton);
        welcomePanel.add(loginButton);
        welcomePanel.add(Box.createVerticalGlue());
    }

    private void initSignUpPanel() {
        signUpPanel.setLayout(new GridLayout(6, 2, 10, 10));
        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton submitButton = new JButton("Submit");

        signUpPanel.add(new JLabel("First Name:"));
        signUpPanel.add(firstNameField);
        signUpPanel.add(new JLabel("Last Name:"));
        signUpPanel.add(lastNameField);
        signUpPanel.add(new JLabel("Email:"));
        signUpPanel.add(emailField);
        signUpPanel.add(new JLabel("Password:"));
        signUpPanel.add(passwordField);
        signUpPanel.add(new JLabel()); // Spacer
        signUpPanel.add(submitButton);

        submitButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            if (!email.isEmpty() && !password.isEmpty()) {
                userDatabase.put(email, password);
                JOptionPane.showMessageDialog(this, "Registration Successful. Please log in.");
                cardLayout.show(mainPanel, "Login");
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            }
        });
    }

    private void initLoginPanel() {
        loginPanel.setLayout(new GridLayout(3, 2, 10, 10));
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JLabel errorLabel = new JLabel("");

        loginPanel.add(new JLabel("Email:"));
        loginPanel.add(emailField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(errorLabel);

        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            if (userDatabase.containsKey(email) && userDatabase.get(email).equals(password)) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                cardLayout.show(mainPanel, "System");
            } else {
                errorLabel.setText("Incorrect email or password, try again.");
            }
        });
    }

    private void initSystemPanel() {
        systemPanel.setLayout(new BorderLayout());
        JLabel headerLabel = new JLabel("Train Reservation System", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        systemPanel.add(headerLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        JButton addTrainButton = new JButton("Add Train");
        JButton bookSeatButton = new JButton("Book Seat");
        JButton displayTrainsButton = new JButton("Display Trains");

        addTrainButton.addActionListener(e -> cardLayout.show(mainPanel, "AddTrain"));
        bookSeatButton.addActionListener(e -> cardLayout.show(mainPanel, "BookSeat"));
        displayTrainsButton.addActionListener(this::handleDisplayTrains);

        buttonPanel.add(addTrainButton);
        buttonPanel.add(bookSeatButton);
        buttonPanel.add(displayTrainsButton);

        systemPanel.add(buttonPanel, BorderLayout.CENTER);
    }

    private void initAddTrainPanel() {
        addTrainPanel.setLayout(new GridLayout(6, 2, 10, 10));
        JTextField trainNumberField = new JTextField();
        JTextField destinationField = new JTextField();
        JTextField departureTimeField = new JTextField();
        JTextField seatsField = new JTextField();
        JButton submitButton = new JButton("Submit");

        addTrainPanel.add(new JLabel("Train Number:"));
        addTrainPanel.add(trainNumberField);
        addTrainPanel.add(new JLabel("Destination:"));
        addTrainPanel.add(destinationField);
        addTrainPanel.add(new JLabel("Departure Time:"));
        addTrainPanel.add(departureTimeField);
        addTrainPanel.add(new JLabel("Available Seats:"));
        addTrainPanel.add(seatsField);
        addTrainPanel.add(new JLabel()); // Spacer
        addTrainPanel.add(submitButton);

        submitButton.addActionListener(e -> {
            trainCount++;
            JOptionPane.showMessageDialog(this, "Train " + trainCount + " added successfully");
            cardLayout.show(mainPanel, "System");
        });
    }

    private void initBookSeatPanel() {
        bookSeatPanel.setLayout(new GridLayout(4, 2, 10, 10));
        JTextField trainNumberField = new JTextField();
        JTextField passengerNameField = new JTextField();
        JButton submitButton = new JButton("Submit");

        bookSeatPanel.add(new JLabel("Train Number:"));
        bookSeatPanel.add(trainNumberField);
        bookSeatPanel.add(new JLabel("Passenger Name:"));
        bookSeatPanel.add(passengerNameField);
        bookSeatPanel.add(new JLabel()); // Spacer
        bookSeatPanel.add(new JLabel()); // Spacer
        bookSeatPanel.add(new JLabel()); // Spacer
        bookSeatPanel.add(submitButton);

        submitButton.addActionListener(e -> {
            seatCount++;  // Increment seat count as a simple simulation of booking
            JOptionPane.showMessageDialog(this, "Seat " + seatCount + " successfully booked for " + passengerNameField.getText());
            cardLayout.show(mainPanel, "System");
        });
    }

    private void handleBookSeat(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "Functionality to book a seat.");
    }

    private void handleDisplayTrains(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "Functionality to display trains.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TrainReservationSystemGUI frame = new TrainReservationSystemGUI();
            frame.setVisible(true);
        });
    }
}