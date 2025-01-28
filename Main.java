import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

class User {
    String name;
    String password;
    String profilePicturePath;
    String email;
    String gender;
    LinkedList<String> friends = new LinkedList<>();
    LinkedList<String> posts = new LinkedList<>();

    User(String name, String password, String profilePicturePath, String email , String gender) {
        this.name = name;
        this.password = password;
        this.profilePicturePath = profilePicturePath;
        this.email = email;
        this.gender = gender;
    }
}

class SocialMediaApp {
    LinkedList<User> Users ;

    public SocialMediaApp() {
        Users = new LinkedList<>();
    }
}
public class Main {

    private static SocialMediaApp app = new SocialMediaApp();
    private static User loggedInUser = null;

    public static void main(String[] args) {
        createDirectories();
        loadUsers();
       
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createDirectories() {
        File profilePicturesDir = new File("profile_pictures");
        if (!profilePicturesDir.exists()) {
            profilePicturesDir.mkdirs();
        }
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Social Media App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setLayout(null);

        // Add background image
        try {
            BufferedImage backgroundImage = ImageIO.read(new File("/Users/macbook/SocialMediaApp/bkg_images/welcome page.jpeg"));
            JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
            backgroundLabel.setBounds(0, 0, screenSize.width, screenSize.height);


            frame.add(backgroundLabel);

            // Create a panel for buttons
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
            buttonPanel.setOpaque(false);
            buttonPanel.setBounds(50, screenSize.height / 2 - 50, 200, 200);

            JButton loginButton = new JButton("Login");
            JButton signupButton = new JButton("Signup");

            
            loginButton.setPreferredSize(new Dimension(600, 150));
            signupButton.setPreferredSize(new Dimension(600, 120));
            loginButton.setFont(new Font("Arial", Font.BOLD, 36));
            signupButton.setFont(new Font("Arial", Font.BOLD, 36));

            loginButton.setBackground(new Color(233, 243, 255));
            signupButton.setBackground(new Color(233, 243, 255));
            loginButton.setForeground(new Color(206, 123, 2));
            signupButton.setForeground(new Color(206, 124, 2));

            loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            signupButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            loginButton.setBorderPainted(false);
            signupButton.setBorderPainted(false);

            loginButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    
                    showLoginForm(frame);
                }
            });

            signupButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Open signup form
                    showSignupForm(frame);
                }
            });

            buttonPanel.add(Box.createVerticalGlue());
            buttonPanel.add(loginButton);
            buttonPanel.add(Box.createVerticalStrut(20));
            buttonPanel.add(signupButton);
            buttonPanel.add(Box.createVerticalGlue());

            backgroundLabel.setLayout(null);
            backgroundLabel.add(buttonPanel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading background image.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        }

        frame.setVisible(true);
    }
    
    private static void showLoginForm(JFrame parentFrame) {
    JFrame loginFrame = new JFrame("Login");
    loginFrame.setSize(1024, 640);
    loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JLabel background = new JLabel(new ImageIcon("/Users/macbook/SocialMediaApp/bkg_images/login.jpeg"));
    loginFrame.add(background);
    background.setLayout(null);

    JPanel loginPanel = new JPanel(new GridBagLayout());
    loginPanel.setOpaque(false);
    loginPanel.setLayout(null);
    loginPanel.setBounds(550, 70, 700, 700);


    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.fill = GridBagConstraints.HORIZONTAL;

    JLabel loginLabel = new JLabel("User Login");
    loginLabel.setFont(new Font("Arial", Font.BOLD, 40));
    loginLabel.setForeground(new Color(255, 255, 255));
    loginLabel.setBounds(250, 140, 220, 50);
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.CENTER;
    loginPanel.add(loginLabel, gbc);
    

    gbc.insets = new Insets(10, 10, 10, 10);

    JLabel usernameLabel = new JLabel("Username:");
    usernameLabel.setFont(new Font("Arial", Font.PLAIN, 22));
    usernameLabel.setForeground(new Color(255, 255, 255));
    usernameLabel.setBounds(200, 215, 180, 30);
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    gbc.anchor = GridBagConstraints.WEST;
    loginPanel.add(usernameLabel, gbc);

    JTextField usernameField = new JTextField(10);
    usernameField.setFont(new Font("Arial", Font.PLAIN, 18));
    usernameField.setBounds(350, 215, 180, 30);
    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.anchor = GridBagConstraints.CENTER;
    loginPanel.add(usernameField, gbc);

    JLabel passwordLabel = new JLabel("Password:");
    passwordLabel.setFont(new Font("Arial", Font.PLAIN, 22));
    passwordLabel.setForeground(new Color(255, 255, 255));
    passwordLabel.setBounds(200, 275, 180, 30);
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.anchor = GridBagConstraints.WEST;
    loginPanel.add(passwordLabel, gbc);

    JPasswordField passwordField = new JPasswordField(15);
    passwordField.setFont(new Font("Arial", Font.PLAIN, 18));
    passwordField.setBounds(350, 275, 180, 30);
    gbc.gridx = 1;
    gbc.gridy = 2;
    gbc.anchor = GridBagConstraints.CENTER;
    loginPanel.add(passwordField, gbc);

    gbc.insets = new Insets(10, 10, 10, 10);

    try {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    } catch (Exception e) {
        e.printStackTrace();
    }
    JButton loginButton = new JButton("Login");
    loginButton.setFont(new Font("Arial", Font.PLAIN, 20));
    loginButton.setBackground(new Color(158, 0, 93));
    loginButton.setForeground(new Color(255, 255, 255));
    loginButton.setBorderPainted(false);
    loginButton.setBounds(200, 345, 335, 30);
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 2;
    loginPanel.add(loginButton, gbc);

    JButton forgotPasswordButton = new JButton("Forgot Password");
    forgotPasswordButton.setFont(new Font("Arial", Font.PLAIN, 20));
    forgotPasswordButton.setBackground(new Color(96, 45, 145));
    forgotPasswordButton.setForeground(new Color(255, 255, 255));
    forgotPasswordButton.setBorderPainted(false);
    forgotPasswordButton.setBounds(200, 400, 335, 30);
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.gridwidth = 2;
    loginPanel.add(forgotPasswordButton, gbc);

    JButton signupButton = new JButton("Sign Up");
    signupButton.setFont(new Font("Arial", Font.PLAIN, 20));
    signupButton.setBackground(new Color(96, 45, 145));
    signupButton.setForeground(new Color(255, 255, 255));
    signupButton.setBorderPainted(false);
    signupButton.setBounds(200, 465, 335, 30);
    gbc.gridx = 0;
    gbc.gridy = 5;
    gbc.gridwidth = 2;
    loginPanel.add(signupButton, gbc);

    loginButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (login(username, password)) {
                JOptionPane.showMessageDialog(loginFrame, "Login successful");
                loginFrame.dispose();
                loginFrame.dispose();
                showHomePage(parentFrame);
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Login failed");
            }
        }
    });

    signupButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            showSignupForm(parentFrame);
        }
    });
    
   forgotPasswordButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        // Create the forgot password frame
        JFrame forgotPasswordFrame = new JFrame("Forgot Password");
        forgotPasswordFrame.setSize(1024, 640);
        forgotPasswordFrame.setLayout(new BorderLayout());
        forgotPasswordFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Load the background image
        ImageIcon backgroundIcon = new ImageIcon("/Users/macbook/SocialMediaApp/bkg_images/forgot.jpeg");
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setLayout(new BorderLayout());

        // Panel to hold form components (will be slightly moved down)
        JPanel forgotPasswordPanel = new JPanel();
        forgotPasswordPanel.setLayout(new GridBagLayout());
        forgotPasswordPanel.setOpaque(false); // Make the panel transparent to show the background

        // Set GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 10, 10, 10); 
        gbc.anchor = GridBagConstraints.CENTER; 
        gbc.fill = GridBagConstraints.HORIZONTAL; 

        // Creating and adding the Forgot Password label
        JLabel headingLabel = new JLabel("Forgot Password");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 40)); 
        headingLabel.setForeground(new Color(26, 95, 115)); 
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        forgotPasswordPanel.add(headingLabel, gbc);

        // Creating and adding the email label
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 22)); // Smaller font size for labels
        emailLabel.setForeground(new Color(26, 95, 115)); // Black color for text
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        forgotPasswordPanel.add(emailLabel, gbc);

        // Creating and adding the email text field
        JTextField emailField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        forgotPasswordPanel.add(emailField, gbc);

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Creating and adding the password label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 22)); // Smaller font size for labels
        passwordLabel.setForeground(new Color(26, 95, 115)); // Black color for text
        gbc.gridx = 0;
        gbc.gridy = 2;
        forgotPasswordPanel.add(passwordLabel, gbc);

        // Creating and adding the password text field
        JTextField passwordField = new JTextField(20);
        passwordField.setEditable(false); // Make password field uneditable
        gbc.gridx = 1;
        gbc.gridy = 2;
        forgotPasswordPanel.add(passwordField, gbc);

        // Creating and adding the retrieve button
        JButton retrieveButton = new JButton("Retrieve Password");
        retrieveButton.setFont(new Font("Arial", Font.PLAIN, 15)); // Button font size
    
        retrieveButton.setBackground(new Color(26, 95, 115)); // Button background color

        retrieveButton.setForeground(Color.WHITE); // White text color
        retrieveButton.setBorderPainted(false);
        retrieveButton.setPreferredSize(new Dimension(150, 40)); // Reduce width
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        forgotPasswordPanel.add(retrieveButton, gbc);

        retrieveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String enteredEmail = emailField.getText();
                boolean userFound = false;

                for (User user : app.Users) {
                    if (user.email.equals(enteredEmail)) {
                        passwordField.setText(user.password); // Set the password
                        userFound = true;
                        break;
                    }
                }

                if (!userFound) {
                    JOptionPane.showMessageDialog(forgotPasswordFrame, "Invalid Email");
                }
            }
        });
       
        // Creating and adding the login button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.PLAIN, 20)); // Button font size
        loginButton.setBackground(new Color(255, 255, 255)); // Button background color
        loginButton.setForeground(new Color(26, 95, 115)); // Button text color
        loginButton.setBorderPainted(false); // Remove border
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        forgotPasswordPanel.add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                forgotPasswordFrame.dispose(); 
                showLoginForm(parentFrame); 
            }
        });

        forgotPasswordFrame.getContentPane().add(backgroundLabel, BorderLayout.CENTER);
        backgroundLabel.add(forgotPasswordPanel, BorderLayout.CENTER);
        forgotPasswordFrame.setLocationRelativeTo(null);

        // Show the frame
        forgotPasswordFrame.setVisible(true);
    }
});

    loginPanel.setBounds(loginFrame.getWidth() - 640, 0, 600, loginFrame.getHeight());

    background.add(loginPanel);
    loginFrame.setLocationRelativeTo(parentFrame);
    loginFrame.setVisible(true);
}
    
    
private static void showSignupForm(JFrame parentFrame) {
    JFrame signupFrame = new JFrame("Signup");
    signupFrame.setSize(1024,640);
    signupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    ImageIcon backgroundIcon = new ImageIcon("/Users/macbook/SocialMediaApp/bkg_images/signup.jpeg");
    JLabel backgroundLabel = new JLabel(backgroundIcon);
    backgroundLabel.setLayout(null);

    JPanel signupPanel = new JPanel(new GridBagLayout());
    signupPanel.setOpaque(false);
    signupPanel.setBounds(475, 70, 550, 500);

    GridBagConstraints gbc = new GridBagConstraints();

    JLabel signupLabel = new JLabel("SIGNUP");
    signupLabel.setFont(new Font("Arial", Font.BOLD, 36));
    signupLabel.setForeground(new Color(81, 49, 115));
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    gbc.insets = new Insets(10, 10, 10, 10);
    signupPanel.add(signupLabel, gbc);

    JLabel usernameLabel = new JLabel("Username:");
    usernameLabel.setFont(new Font("Arial", Font.PLAIN, 24));
    usernameLabel.setForeground(new Color(81, 49, 115));
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    gbc.anchor = GridBagConstraints.WEST;
    signupPanel.add(usernameLabel, gbc);

    try {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    } catch (Exception e) {
        e.printStackTrace();
    }

    JTextField usernameField = new JTextField(15);
    usernameField.setFont(new Font("Segeo UI" , Font.PLAIN , 15));
    usernameField.setPreferredSize(new Dimension(250, 30));  // Increased field size
    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.anchor = GridBagConstraints.CENTER;
    signupPanel.add(usernameField, gbc);

    JLabel passwordLabel = new JLabel("Password:");
    passwordLabel.setFont(new Font("Arial", Font.PLAIN, 24));
    passwordLabel.setForeground(new Color(81, 49, 115));
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.anchor = GridBagConstraints.WEST;
    signupPanel.add(passwordLabel, gbc);

    JTextField passwordField = new JTextField(15);
    passwordField.setFont(new Font("Segeo UI" , Font.PLAIN , 15));
    passwordField.setPreferredSize(new Dimension(250, 30));  
    passwordField.addFocusListener(new FocusListener() {
        public void focusGained(FocusEvent e) {
            passwordField.setText("");  // Clear the placeholder when focused
        }

        public void focusLost(FocusEvent e) {
            if (passwordField.getText().isEmpty()) {
                passwordField.setText("eg: Xyx#4321");  // Add placeholder when focus lost
            }
        }
    });
    passwordField.setText("eg: Xyx#4321");
    passwordField.setForeground(new Color(128, 128, 128));  // Light grey for placeholder text
    gbc.gridx = 1;
    gbc.gridy = 2;
    gbc.anchor = GridBagConstraints.CENTER;
    signupPanel.add(passwordField, gbc);

    JLabel emailLabel = new JLabel("Email:");
    emailLabel.setFont(new Font("Arial", Font.PLAIN, 24));
    emailLabel.setForeground(new Color(81, 49, 115));
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.anchor = GridBagConstraints.WEST;
    signupPanel.add(emailLabel, gbc);

    JTextField emailField = new JTextField(15);
    emailField.setFont(new Font("Segeo UI" , Font.PLAIN , 15));
    emailField.setPreferredSize(new Dimension(250, 30));  
    emailField.addFocusListener(new FocusListener() {
        public void focusGained(FocusEvent e) {
            emailField.setText("");  // Clear the placeholder when focused
        }

        public void focusLost(FocusEvent e) {
            if (emailField.getText().isEmpty()) {
                emailField.setText("example@gmail.com");  // Add placeholder when focus lost
            }
        }
    });
    emailField.setText("example@gmail.com");
    emailField.setForeground(new Color(128, 128, 128));  // Light grey for placeholder text
    gbc.gridx = 1;
    gbc.gridy = 3;
    gbc.anchor = GridBagConstraints.CENTER;
    signupPanel.add(emailField, gbc);

    JLabel genderLabel = new JLabel("Gender:");
    genderLabel.setFont(new Font("Arial", Font.PLAIN, 24));
    genderLabel.setForeground(new Color(81, 49, 115));
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.anchor = GridBagConstraints.WEST;
    signupPanel.add(genderLabel, gbc);

    JTextField genderField = new JTextField(15);
    genderField.setFont(new Font("Segeo UI" , Font.PLAIN , 15));
    genderField.setPreferredSize(new Dimension(250, 30));  // Increased field size
    gbc.gridx = 1;
    gbc.gridy = 4;
    gbc.anchor = GridBagConstraints.CENTER;
    signupPanel.add(genderField, gbc);

    JButton uploadButton = new JButton("Click to add profile picture");
    uploadButton.setFont(new Font("Arial", Font.PLAIN, 22));
    uploadButton.setForeground(new Color(81, 49, 115));
    uploadButton.setBackground(Color.WHITE);
    uploadButton.setBorderPainted(false);
    gbc.gridx = 0;
    gbc.gridy = 5;
    gbc.gridwidth = 2;
    signupPanel.add(uploadButton, gbc);

    JButton signupButton = new JButton("Signup");
    signupButton.setFont(new Font("Arial", Font.PLAIN, 22));
    signupButton.setForeground(Color.WHITE);
    signupButton.setBackground(new Color(253, 166, 255));
    gbc.gridx = 0;
    gbc.gridy = 6;
    gbc.gridwidth = 2;
    signupPanel.add(signupButton, gbc);

    File[] selectedProfilePicture = {null};

    uploadButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose a profile picture");
            int result = fileChooser.showOpenDialog(signupFrame);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedProfilePicture[0] = fileChooser.getSelectedFile();
                uploadButton.setText(selectedProfilePicture[0].getName());
            }
        }
    });

    signupButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String email = emailField.getText();
            String gender = genderField.getText();

            if (selectedProfilePicture[0] == null) {
                JOptionPane.showMessageDialog(signupFrame, "Please select a profile picture.");
                return;
            }
            if (signup(username, password, email, gender, selectedProfilePicture[0])) {
                JOptionPane.showMessageDialog(signupFrame, "Signup successful");
                signupFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(signupFrame, "Signup failed");
            }
        }
    });

    signupFrame.setContentPane(backgroundLabel);
    backgroundLabel.add(signupPanel);
    signupFrame.setLocationRelativeTo(parentFrame);
    signupFrame.setVisible(true);
}


    private static boolean login(String username, String password) {
        for (User user : app.Users) {
            if (user.name.equals(username) && user.password.equals(password)) {
                loggedInUser = user;
                return true;
            }
        }
        return false;
    }

    private static boolean signup(String username, String password, String email, String gender, File profilePicture) {
    System.out.println("Attempting signup for user: " + username);

    for (User user : app.Users) {
        if (user.name.equals(username)) {
            System.out.println("Username already exists.");
            JOptionPane.showMessageDialog(null, "Username already exists!");
            return false;
        }
    }

    if (!isValidPassword(password)) {
        System.out.println("Invalid password. Password must be at least 8 characters long, alphanumeric, and contain at least one special character.");
        JOptionPane.showMessageDialog(null,"Invalid password. Password must be at least 8 characters long, alphanumeric, and contain at least one special character.");
        return false;
    }

    if (!isValidEmail(email)) {
        System.out.println("Invalid email. Email must end with '@gmail.com'.");
        JOptionPane.showMessageDialog(null,"Invalid email. Email must end with '@gmail.com'.");
        return false;
    }

    try {
        System.out.println("Reading profile picture: " + profilePicture.getAbsolutePath());
        BufferedImage image = ImageIO.read(profilePicture);

        File userDir = new File("profile_pictures/" + username);
        if (!userDir.exists()) {
            userDir.mkdirs();
        }
        File userDataFile = new File(userDir, "user.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userDataFile))) {
            writer.write(username);
            writer.newLine();
            writer.write(password);
            writer.newLine();
            writer.write(profilePicture.getPath());
            writer.newLine();
            writer.write(email);
            writer.newLine();
            writer.write(gender);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    
        File outputFile = new File(userDir, "profile.jpeg");
        ImageIO.write(image, "jpeg", outputFile);

        User newUser = new User(username, password, outputFile.getAbsolutePath(), email, gender);
        app.Users.add(newUser);

        System.out.println("User successfully signed up: " + username);
        return true;
    } catch (IOException e) {
        e.printStackTrace();
        System.out.println("Error during signup process.");
        return false;
    }
}
    
public static boolean isValidPassword(String password) {
    if (password.length() < 8 || password.length() > 16) {
        return false;
    }

    boolean hasLowerCase = false;
    boolean hasUpperCase = false;
    boolean hasDigit = false;
    boolean hasSpecialChar = false;

    for (int i = 0; i < password.length(); i++) {
        char c = password.charAt(i);

        if (Character.isLowerCase(c)) {
            hasLowerCase = true;
        } else if (Character.isUpperCase(c)) {
            hasUpperCase = true;
        } else if (Character.isDigit(c)) {
            hasDigit = true;
        } else if ("@#$%^&*".indexOf(c) >= 0) {
            hasSpecialChar = true;
        } else if (Character.isWhitespace(c)) {
            return false;  // Reject password with whitespace
        }
    }

    return hasLowerCase && hasUpperCase && hasDigit && hasSpecialChar;
}


private static boolean isValidEmail(String email) {
    String emailPattern = "^[A-Za-z0-9._%+-]+@gmail\\.com$";
    return email != null && email.matches(emailPattern);
}

    private static void showFeed(JFrame frame) {
    frame.getContentPane().removeAll();
    frame.setLayout(new BorderLayout());

    JLabel backgroundLabel = new JLabel();
    backgroundLabel.setIcon(new ImageIcon("/Users/macbook/SocialMediaApp/bkg_images/mypost.jpeg"));
    backgroundLabel.setLayout(new BorderLayout());
    frame.setContentPane(backgroundLabel);

    JPanel feedPanel = new JPanel();
    feedPanel.setLayout(new BoxLayout(feedPanel, BoxLayout.Y_AXIS));
    feedPanel.setBackground(Color.WHITE);
    feedPanel.setOpaque(false);

    for (String post : loggedInUser.posts) {
        addPostToPanel(feedPanel, post, loggedInUser.name);
    }

    for (User friend : app.Users) {
        if (loggedInUser.friends.contains(friend.name)) {
            for (String post : friend.posts) {
                addPostToPanel(feedPanel, post, friend.name);
            }
        }
    }

    JScrollPane scrollPane = new JScrollPane(feedPanel);
    scrollPane.setOpaque(false);
    scrollPane.getViewport().setOpaque(false);
    scrollPane.setPreferredSize(new Dimension(frame.getWidth() / 2, frame.getHeight()));
    scrollPane.setBorder(null);

    JPanel feedContainer = new JPanel(new BorderLayout());
    feedContainer.setOpaque(false);
    feedContainer.add(scrollPane, BorderLayout.EAST);

    JButton backButton = new JButton("Back");
    backButton.setBorderPainted(false);
    backButton.addActionListener(e -> showHomePage(frame));

    JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    topPanel.add(backButton);
    topPanel.setOpaque(false);

    backgroundLabel.add(topPanel, BorderLayout.NORTH);
    backgroundLabel.add(feedContainer, BorderLayout.CENTER);

    frame.revalidate();
    frame.repaint();
}

    
private static void showHomePage(JFrame frame) {
    frame.getContentPane().removeAll();
    frame.setLayout(new BorderLayout());

    // Set background image
    JLabel background = new JLabel(new ImageIcon("/Users/macbook/SocialMediaApp/bkg_images/homepage.jpeg"));
    background.setLayout(new BorderLayout());
    frame.setContentPane(background);

    // Add top label for username
    JLabel userLabel = new JLabel("HI! " + loggedInUser.name, SwingConstants.CENTER);
    userLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
    userLabel.setForeground(new Color(89, 1, 117));
    frame.add(userLabel, BorderLayout.NORTH);

    // Add buttons for functionalities
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
    buttonPanel.setOpaque(false); // Make transparent

    int buttonWidth = 400; // Button width
    int buttonHeight = 300; // Button height

    // Create buttons
    JButton feedButton = createStyledButton("Feed", buttonWidth, buttonHeight);
    feedButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            showFeed(frame);
        }
    });

    JButton profileButton = createStyledButton("My Profile", buttonWidth, buttonHeight);
    profileButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            showProfile(frame);
        }
    });

    JButton friendsButton = createStyledButton("My Friends", buttonWidth, buttonHeight);
    friendsButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            showFriends(frame);
        }
    });

    JButton suggestionsButton = createStyledButton("Friend Suggestions", buttonWidth, buttonHeight);
    suggestionsButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            showUserSuggestions(frame);
        }
    });

    JButton postButton = createStyledButton("Post", buttonWidth, buttonHeight);
    postButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            createPost(frame);
        }
    });

    JButton logoutButton = createStyledButton("Logout", buttonWidth, buttonHeight);
    logoutButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            frame.getContentPane().removeAll();
            createAndShowGUI();
        }
    });

    // Add buttons to the panel with increased spacing
    buttonPanel.add(feedButton);
    buttonPanel.add(Box.createRigidArea(new Dimension(0, 60))); // Increased spacing
    buttonPanel.add(profileButton);
    buttonPanel.add(Box.createRigidArea(new Dimension(0, 60))); // Increased spacing
    buttonPanel.add(friendsButton);
    buttonPanel.add(Box.createRigidArea(new Dimension(0, 60))); // Increased spacing
    buttonPanel.add(suggestionsButton);
    buttonPanel.add(Box.createRigidArea(new Dimension(0, 60))); // Increased spacing
    buttonPanel.add(postButton);
    buttonPanel.add(Box.createRigidArea(new Dimension(0, 60))); // Increased spacing
    buttonPanel.add(logoutButton);

    // Align buttons to the left
    JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    leftPanel.setOpaque(false); // Transparent background
    leftPanel.add(buttonPanel);
    frame.add(leftPanel, BorderLayout.WEST);

    frame.revalidate();
    frame.repaint();
}

// Helper method to create styled buttons
private static JButton createStyledButton(String text, int width, int height) {
    JButton button = new JButton(text);
    button.setPreferredSize(new Dimension(width, height));
    button.setBackground(new Color(210, 246, 255)); // White background
    button.setForeground(new Color(0, 64, 110)); // Text color
    button.setBorderPainted(false); // Remove border
    button.setFocusPainted(false);
    button.setFont(new Font("Segoe UI", Font.PLAIN, 20)); // Set font
    return button;
}



    private static void addPostToPanel(JPanel panel, String post, String username) {
    JPanel postPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;

    postPanel.setBackground(Color.WHITE);
    postPanel.setPreferredSize(new Dimension(200, 250));

    if (post.startsWith("Image: ")) {
        String imagePath = post.substring(7);
        try {
            BufferedImage img = ImageIO.read(new File(imagePath));
            if (img != null) {
                ImageIcon icon = new ImageIcon(img.getScaledInstance(200, 200, Image.SCALE_SMOOTH));
                JLabel picLabel = new JLabel(icon);
                postPanel.add(picLabel, gbc);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    } else {
        String[] words = post.split("\\s+");
        StringBuilder truncatedPost = new StringBuilder();
        for (int i = 0; i < Math.min(words.length, 20); i++) {
            truncatedPost.append(words[i]).append(" ");
        }
        JLabel postLabel = new JLabel("<html>" + truncatedPost.toString().trim() + "</html>");
        postLabel.setForeground(Color.BLACK);
        postLabel.setFont(new Font("Segoe UI", Font.PLAIN , 30));
        postPanel.add(postLabel, gbc);
    }

    gbc.gridy++;
    JLabel userLabel = new JLabel("Posted by: " + username);
    userLabel.setFont(new Font("Times New Roman", Font.ITALIC, 22));
    postPanel.add(userLabel, gbc);
    
    panel.add(postPanel);
    panel.add(Box.createVerticalStrut(10));
}

    private static void showProfile(JFrame frame) {
    frame.getContentPane().removeAll();
    frame.setLayout(new BorderLayout());

    // Main panel to hold content
    JPanel profilePanel = new JPanel();
    profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
    profilePanel.setBackground(new Color(160, 223, 246));
    profilePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding

    // Back Button
    JButton backButton = new JButton("Back");
    backButton.setPreferredSize(new Dimension(100, 40));
    backButton.setBackground(new Color(255, 255, 255)); // White background
    backButton.setForeground(new Color(0, 64, 110)); // Text color
    backButton.setFocusPainted(false);
    backButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
    backButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            showHomePage(frame);
        }
    });

    // Add back button at the top
    JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    topPanel.setOpaque(false); // Transparent background
    topPanel.add(backButton);
    frame.add(topPanel, BorderLayout.NORTH);

    // User details panel
    JPanel userDetailsPanel = new JPanel();
    userDetailsPanel.setLayout(new GridLayout(3, 2, 5, 5)); // 3 rows, 2 columns, 10px gap
    userDetailsPanel.setOpaque(false);

    JLabel nameLabel = new JLabel("User Name:");
    nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
    JLabel nameValueLabel = new JLabel(loggedInUser.name);
    nameValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));

    JLabel emailLabel = new JLabel("Email:");
    emailLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
    JLabel emailValueLabel = new JLabel(loggedInUser.email); // Assuming email field exists
    emailValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));

    JLabel genderLabel = new JLabel("Gender:");
    genderLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
    JLabel genderValueLabel = new JLabel(loggedInUser.gender); // Assuming gender field exists
    genderValueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
    userDetailsPanel.add(nameLabel);
    userDetailsPanel.add(nameValueLabel);
    userDetailsPanel.add(emailLabel);
    userDetailsPanel.add(emailValueLabel);
    userDetailsPanel.add(genderLabel);
    userDetailsPanel.add(genderValueLabel);

    profilePanel.add(userDetailsPanel);
    profilePanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing after details

    // User's posts section
    JLabel postsLabel = new JLabel("Your Posts:");
    postsLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
    postsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    profilePanel.add(postsLabel);
    profilePanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacing

    for (String post : loggedInUser.posts) {
        addPostToPanel(profilePanel, post, loggedInUser.name);
    }

    frame.add(new JScrollPane(profilePanel), BorderLayout.CENTER);

    frame.revalidate();
    frame.repaint();
}

private static void showFriends(JFrame frame) {
    frame.getContentPane().removeAll();
    frame.setLayout(new BorderLayout());

    JLabel backgroundLabel = new JLabel();
    backgroundLabel.setIcon(new ImageIcon("/Users/macbook/SocialMediaApp/bkg_images/friend.jpeg"));
    backgroundLabel.setLayout(new BorderLayout());
    frame.setContentPane(backgroundLabel);

    JButton backButton = new JButton("Back");
    backButton.setPreferredSize(new Dimension(100, 40));
    backButton.setBackground(new Color(255, 255, 255));
    backButton.setForeground(new Color(0, 64, 110));
    backButton.setFocusPainted(false);
    backButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
    backButton.addActionListener(e -> showHomePage(frame));

    JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    topPanel.setOpaque(false);
    topPanel.add(backButton);

    JPanel friendsPanel = new JPanel();
    friendsPanel.setLayout(new BoxLayout(friendsPanel, BoxLayout.Y_AXIS));
    friendsPanel.setBackground(Color.WHITE);
    friendsPanel.setOpaque(false);

    for (String friendName : loggedInUser.friends) {
        for (User user : app.Users) {
            if (user.name.equals(friendName)) {
                JPanel friendPanel = new JPanel();
                friendPanel.setLayout(new FlowLayout());
                friendPanel.setBackground(Color.WHITE);
                JLabel friendLabel = new JLabel("  "+friendName);
                friendLabel.setFont(new Font("Segoe UI" , Font.BOLD , 34));
                friendLabel.setForeground(new Color(79,35,103));
                try {
                    BufferedImage img = ImageIO.read(new File(user.profilePicturePath));
                    if (img != null) {
                        ImageIcon icon = new ImageIcon(img.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
                        JLabel picLabel = new JLabel(icon);
                        friendPanel.add(picLabel);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                friendPanel.add(friendLabel);
                friendsPanel.add(friendPanel);
            }
        }
    }

    JScrollPane scrollPane = new JScrollPane(friendsPanel);
    scrollPane.setOpaque(false);
    scrollPane.getViewport().setOpaque(false);
    scrollPane.setPreferredSize(new Dimension(frame.getWidth() / 2, frame.getHeight()));
    scrollPane.setBorder(null);

    JPanel friendsContainer = new JPanel(new BorderLayout());
    friendsContainer.setOpaque(false);
    friendsContainer.add(scrollPane, BorderLayout.WEST);

    backgroundLabel.add(topPanel, BorderLayout.NORTH);
    backgroundLabel.add(friendsContainer, BorderLayout.CENTER);

    frame.revalidate();
    frame.repaint();
}
private static void showUserSuggestions(JFrame frame) {
    frame.getContentPane().removeAll();
    frame.setLayout(new BorderLayout());

    JLabel backgroundLabel = new JLabel();
    backgroundLabel.setIcon(new ImageIcon("/Users/macbook/SocialMediaApp/bkg_images/suggest.jpeg"));
    backgroundLabel.setLayout(new BorderLayout());
    frame.setContentPane(backgroundLabel);

    JButton backButton = new JButton("Back");
    backButton.setPreferredSize(new Dimension(100, 40));
    backButton.setBackground(new Color(255, 255, 255));
    backButton.setForeground(new Color(0, 64, 110));
    backButton.setFocusPainted(false);
    backButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
    backButton.addActionListener(e -> showHomePage(frame));

    JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    topPanel.setOpaque(false);
    topPanel.add(backButton);

    JPanel suggestionsPanel = new JPanel();
    suggestionsPanel.setLayout(new GridLayout(0, 1, 10, 10));
    suggestionsPanel.setBackground(Color.WHITE);
    suggestionsPanel.setOpaque(false);

    for (User user : app.Users) {
        if (!user.equals(loggedInUser) && !loggedInUser.friends.contains(user.name)) {
            JPanel userPanel = new JPanel();
            userPanel.setLayout(new BorderLayout());
            userPanel.setBackground(Color.WHITE);

            JPanel imageAndNamePanel = new JPanel(new BorderLayout());
            imageAndNamePanel.setOpaque(false);

            try {
                BufferedImage img = ImageIO.read(new File(user.profilePicturePath));
                if (img != null) {
                    ImageIcon icon = new ImageIcon(img.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
                    JLabel picLabel = new JLabel(icon);
                    imageAndNamePanel.add(picLabel, BorderLayout.CENTER);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            JLabel userLabel = new JLabel(user.name, SwingConstants.CENTER);
            userLabel.setFont(new Font("Segoe UI" , Font.BOLD , 30));
            userLabel.setForeground(new Color(0,93,6));
            imageAndNamePanel.add(userLabel, BorderLayout.SOUTH);

            JButton addFriendButton = new JButton("Add Friend");
            addFriendButton.setBackground(new Color(255, 255, 255));
            addFriendButton.setFont(new Font("Open Sans", Font.PLAIN, 14));
            addFriendButton.addActionListener(e -> {
                loggedInUser.friends.add(user.name);
                user.friends.add(loggedInUser.name);
                saveUserFriends(loggedInUser);
                saveUserFriends(user);
                JOptionPane.showMessageDialog(frame, "Friend added: " + user.name);
                showUserSuggestions(frame);
            });

            userPanel.add(imageAndNamePanel, BorderLayout.CENTER);
            userPanel.add(addFriendButton, BorderLayout.SOUTH);

            suggestionsPanel.add(userPanel);
        }
    }

    JScrollPane scrollPane = new JScrollPane(suggestionsPanel);
    scrollPane.setOpaque(false);
    scrollPane.getViewport().setOpaque(false);
    scrollPane.setPreferredSize(new Dimension(frame.getWidth() / 2, frame.getHeight()));
    scrollPane.setBorder(null);

    JPanel suggestionsContainer = new JPanel(new BorderLayout());
    suggestionsContainer.setOpaque(false);
    suggestionsContainer.add(scrollPane, BorderLayout.WEST);

    backgroundLabel.add(topPanel, BorderLayout.NORTH);
    backgroundLabel.add(suggestionsContainer, BorderLayout.CENTER);

    frame.revalidate();
    frame.repaint();
}

    private static void createPost(JFrame frame) {
        String[] options = {"Text Post", "Image Post"};
        int choice = JOptionPane.showOptionDialog(frame, "What type of post would you like to create?", "Create Post",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            // Text Post
            String textPost = JOptionPane.showInputDialog("Enter your post:");
            if (textPost != null && !textPost.trim().isEmpty()) {
                loggedInUser.posts.add(textPost);
                saveUserPosts(loggedInUser);
                JOptionPane.showMessageDialog(frame, "Post created successfully!");
            }
        } else if (choice == 1) {
            // Image Post
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose an image to post");
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    BufferedImage image = ImageIO.read(selectedFile);
                    if (image != null) {
                        File userDir = new File("profile_pictures/" + loggedInUser.name);
                        if (!userDir.exists()) {
                            userDir.mkdirs();
                        }
                        File outputfile = new File(userDir, "post_" + System.currentTimeMillis() + ".jpeg");
                        ImageIO.write(image, "jpeg", outputfile);
                        loggedInUser.posts.add("Image: " + outputfile.getAbsolutePath());
                        saveUserPosts(loggedInUser);
                        JOptionPane.showMessageDialog(frame, "Image post created successfully!");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Failed to read image from file.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Failed to create image post.");
                }
            }
        }
    }

    private static void saveUserPosts(User user) {
        File userDir = new File("profile_pictures/" + user.name);
        if (!userDir.exists()) {
            userDir.mkdirs();
        }
        File postsFile = new File(userDir, "posts.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(postsFile))) {
            for (String post : user.posts) {
                writer.write(post + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveUserFriends(User user) {
        File userDir = new File("profile_pictures/" + user.name);
        if (!userDir.exists()) {
            userDir.mkdirs();
        }
        File friendsFile = new File(userDir, "friends.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(friendsFile))) {
            for (String friend : user.friends) {
                writer.write(friend + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
private static void loadUsers() {
    File profilePicturesDir = new File("profile_pictures");
    if (profilePicturesDir.exists() && profilePicturesDir.isDirectory()) {
        File[] userDirs = profilePicturesDir.listFiles(File::isDirectory);
        if (userDirs != null) {
            for (File userDir : userDirs) {
                File userDataFile = new File(userDir, "user.txt");
                if (userDataFile.exists()) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(userDataFile))) {
                        String username = reader.readLine();
                        String password = reader.readLine();
                        String profilePicturePath = reader.readLine();
                        String email = reader.readLine();
                        String gender= reader.readLine();
                        User user = new User(username, password, profilePicturePath,email,gender);
                        app.Users.add(user);

                        // Load user posts
                        File postsFile = new File(userDir, "posts.txt");
                        if (postsFile.exists()) {
                            try (BufferedReader postsReader = new BufferedReader(new FileReader(postsFile))) {
                                String post;
                                while ((post = postsReader.readLine()) != null) {
                                    user.posts.add(post);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        // Load user friends
                        File friendsFile = new File(userDir, "friends.txt");
                        if (friendsFile.exists()) {
                            try (BufferedReader friendsReader = new BufferedReader(new FileReader(friendsFile))) {
                                String friend;
                                while ((friend = friendsReader.readLine()) != null) {
                                    user.friends.add(friend);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
}
