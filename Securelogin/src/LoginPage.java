import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class LoginPage {
    private static final byte[] HASHED_PASSWORD = hashPassword("password");

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createLoginForm());
    }

    private static void createLoginForm() {
        // Create a frame for the login page
        JFrame frame = new JFrame("Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 250);
        frame.setLayout(new GridLayout(4, 2));

        // Set background color and font
        frame.getContentPane().setBackground(Color.decode("#F0F8FF"));
        Font font = new Font("Segoe UI", Font.PLAIN, 14);

        // Username Label and Text Field
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(font);
        JTextField userText = new JTextField();
        userText.setFont(font);

        // Password Label and Password Field
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(font);
        JPasswordField passText = new JPasswordField();
        passText.setFont(font);

        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(font);

        // Sign Up Button
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setFont(font);

        // Action listener for the login button
        loginButton.addActionListener(e -> {
            String username = userText.getText();
            char[] password = passText.getPassword();

            // Check if any field is empty
            if (username == null || username.isEmpty() || password == null || password.length == 0) {
                JOptionPane.showMessageDialog(frame, "Please enter both username and password.");
                return;
            }

            // Basic login check (this should be replaced with real authentication)
            if ("admin".equals(username) && Arrays.equals(hashPassword(new String(password)), HASHED_PASSWORD)) {
                JOptionPane.showMessageDialog(frame, "Login Successful!");
                frame.dispose();
                showMenuForm();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid Username or Password");
            }

            // Clear password array after use for security
            Arrays.fill(password, '\0');
        });

        // Action listener for the sign-up button
        signUpButton.addActionListener(e -> {
            frame.dispose();
            showSignUpForm();
        });

        // Add components to the frame
        frame.add(userLabel);
        frame.add(userText);
        frame.add(passLabel);
        frame.add(passText);
        frame.add(new JLabel());
        frame.add(loginButton);
        frame.add(new JLabel());
        frame.add(signUpButton);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Set the frame visibility to true
        frame.setVisible(true);
    }

    // Method to display the menu form
    private static void showMenuForm() {
        JFrame menuFrame = new JFrame("Menu");
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setSize(300, 200);
        menuFrame.setLayout(new GridLayout(3, 1));

        // Set background color and font
        menuFrame.getContentPane().setBackground(Color.decode("#F0F8FF"));
        Font font = new Font("Segoe UI", Font.PLAIN, 14);

        // Updating buttons to the new labels
        JButton option1 = new JButton("Timothy Mutsikwi");
        option1.setFont(font);
        JButton option2 = new JButton("H230298E");
        option2.setFont(font);
        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(font);

        // Add action listener for the logout button
        logoutButton.addActionListener(e -> {
            menuFrame.dispose();
            SwingUtilities.invokeLater(() -> createLoginForm()); // Return to login form after logout
        });

        // Add buttons to the menu frame
        menuFrame.add(option1);
        menuFrame.add(option2);
        menuFrame.add(logoutButton);

        // Center the menu frame on the screen
        menuFrame.setLocationRelativeTo(null);

        // Set the menu frame visibility to true
        menuFrame.setVisible(true);
    }

    // Method to display the sign-up form
    private static void showSignUpForm() {
        JFrame signUpFrame = new JFrame("Sign Up");
        signUpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        signUpFrame.setSize(350, 250);
        signUpFrame.setLayout(new GridLayout(4, 2));

        // Set background color and font
        signUpFrame.getContentPane().setBackground(Color.decode("#F0F8FF"));
        Font font = new Font("Segoe UI", Font.PLAIN, 14);

        // Username Label and Text Field for Sign Up
        JLabel newUserLabel = new JLabel("New Username:");
        newUserLabel.setFont(font);
        JTextField newUserText = new JTextField();
        newUserText.setFont(font);

        // Password Label and Password Field for Sign Up
        JLabel newPassLabel = new JLabel("New Password:");
        newPassLabel.setFont(font);
        JPasswordField newPassText = new JPasswordField();
        newPassText.setFont(font);

        // Confirm Password Label and Password Field for Sign Up
        JLabel confirmPassLabel = new JLabel("Confirm Password:");
        confirmPassLabel.setFont(font);
        JPasswordField confirmPassText = new JPasswordField();
        confirmPassText.setFont(font);

        // Sign Up Button on the Sign-Up Form
        JButton registerButton = new JButton("Register");
        registerButton.setFont(font);

        // Action listener for the register button
        registerButton.addActionListener(e -> {
            String newUsername = newUserText.getText();
            char[] newPassword = newPassText.getPassword();
            char[] confirmPassword = confirmPassText.getPassword();

            // Check if any field is empty
            if (newUsername == null || newUsername.isEmpty() || newPassword == null || confirmPassword == null) {
                JOptionPane.showMessageDialog(signUpFrame, "Please fill in all fields.");
                return;
            }

            // Check if passwords match
            if (!Arrays.equals(newPassword, confirmPassword)) {
                JOptionPane.showMessageDialog(signUpFrame, "Passwords do not match.");
            } else {
                JOptionPane.showMessageDialog(signUpFrame, "Registration Successful!");
                signUpFrame.dispose();
                SwingUtilities.invokeLater(() -> createLoginForm());
            }

            // Clear password arrays for security
            Arrays.fill(newPassword, '\0');
            Arrays.fill(confirmPassword, '\0');
        });

        // Add components to the sign-up frame
        signUpFrame.add(newUserLabel);
        signUpFrame.add(newUserText);
        signUpFrame.add(newPassLabel);
        signUpFrame.add(newPassText);
        signUpFrame.add(confirmPassLabel);
        signUpFrame.add(confirmPassText);
        signUpFrame.add(new JLabel());
        signUpFrame.add(registerButton);

        // Center the sign-up frame on the screen
        signUpFrame.setLocationRelativeTo(null);

        // Set the sign-up frame visibility to true
        signUpFrame.setVisible(true);
    }

    // Hashing function for passwords
    private static byte[] hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(password.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password.", e);
        }
    }
}
