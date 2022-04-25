package main.store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GuiComponent implements ActionListener {

    private DBConnection db;
    Account currentAccount;

    int primaryColorHex = 0x1e2534;
    int secondaryColorHex = 0xC4C4C4;
    int secondaryColorHex2 = 0xDBDBDB;

    JFrame frame;

    Color primaryColor;
    Color secondaryColor;
    Color secondaryColor2;
    ImageIcon logo, logoWithBg, back;
    File file1, file2;
    Font primaryFont;
    Font secondaryFont;
    Font secondaryFontButton;
    Font secondaryFontLabel;
    Font tableFont;
    Font fieldFont, fieldFontSmall, fieldFontItalic;

    JPanel panel;
    JPanel panel2;
    JLabel logoLabel, logoLabelDesc1, logoLabelDesc2;
    JLabel userNameLabel;
    JLabel passwordLabel;
    JLabel confirmPasswordLabel;
    JLabel roleLabel;
    JLabel errorLabel;

    Font montserrat;
    Font sora;
    JTextField userField;
    JPasswordField passwordField;
    JPasswordField confirmPasswordField;
    JButton signInButton;
    JButton signUpButton;
    JButton createAccount;
    JButton backButton;
    JRadioButton rb1, rb2;
    JToggleButton toggleButton;
    JCheckBox cb;

    ButtonGroup bg;

    GuiComponent(DBConnection db) throws IOException, FontFormatException {

        this.db = db;

        logoWithBg = new ImageIcon("main\\Images\\logoWithBg.png");
        back = new ImageIcon("main\\Images\\back.png");

        file1 = new File("main\\fonts\\Montserrat.ttf");
        montserrat = Font.createFont(Font.TRUETYPE_FONT, file1);

        file2 = new File("main\\fonts\\Sora-SemiBold.ttf");
        sora = Font.createFont(Font.TRUETYPE_FONT, file2);

        primaryColor = new Color(primaryColorHex);
        secondaryColor = new Color(secondaryColorHex);
        secondaryColor2 = new Color(secondaryColorHex2);

        primaryFont = sora.deriveFont(Font.BOLD, 40f);
        secondaryFont = montserrat.deriveFont(Font.PLAIN, 35f);
        secondaryFontLabel = montserrat.deriveFont(Font.PLAIN, 24f);
        secondaryFontButton = montserrat.deriveFont(Font.PLAIN, 20f);
        tableFont = montserrat.deriveFont(Font.PLAIN, 14f);
        fieldFont = montserrat.deriveFont(Font.PLAIN, 16f);
        fieldFontSmall = montserrat.deriveFont(Font.PLAIN, 12f);
        fieldFontItalic = montserrat.deriveFont(Font.ITALIC, 12f);

        userNameLabel = new JLabel("Username:");
        userNameLabel.setFont(secondaryFontLabel);

        userField = new JTextField();
        userField.setFont(fieldFont);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(secondaryFontLabel);

        passwordField = new JPasswordField();
        passwordField.setFont(fieldFont);

        errorLabel = new JLabel();
        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(fieldFontItalic);

        panel = new JPanel();
        welcome();

        panel2 = new JPanel();
        signIn();

        frame = new JFrame();
        frame.setTitle("HIKMM");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(logoWithBg.getImage());
        frame.setSize(917, 639); // to make the frame without the title bar a 900 x 600 exactly
        frame.setLayout(null);
        frame.setResizable(false);
        frame.add(panel);
        frame.add(panel2);
        frame.setVisible(true);

    }

    public void welcome() {
        panel.setBounds(0, 0, 450, 600);
        logo = new ImageIcon("main\\Images\\logo.png");
        panel.setBackground(primaryColor);
        panel.setLayout(null);

        logoLabel = new JLabel("HIKMM");
        logoLabel.setForeground(Color.WHITE);
        logoLabel.setFont(primaryFont);
        logoLabel.setIcon(logo);
        logoLabel.setBounds(150, 147, 157, 185);
        logoLabel.setHorizontalTextPosition(JLabel.CENTER);
        logoLabel.setVerticalTextPosition(JLabel.BOTTOM);
        logoLabel.setIconTextGap(25);
        panel.add(logoLabel);

        logoLabelDesc1 = new JLabel("Store Management");
        logoLabelDesc1.setForeground(Color.WHITE);
        logoLabelDesc1.setFont(secondaryFont);
        logoLabelDesc1.setBounds(55, 351, 500, 40);
        panel.add(logoLabelDesc1);
        logoLabelDesc2 = new JLabel("            System"); // ?????????
        logoLabelDesc2.setForeground(Color.WHITE);
        logoLabelDesc2.setFont(secondaryFont);
        logoLabelDesc2.setBounds(55, 390, 500, 55);
        panel.add(logoLabelDesc2);

    }

    public void signIn() {
        panel2.setBounds(450, 0, 450, 600);
        panel2.setBackground(secondaryColor);
        panel2.setLayout(null);

        userNameLabel.setBounds(50, 200, 170, 35);
        userField.setBounds(220, 200, 170, 30);
        panel2.add(userNameLabel);
        panel2.add(userField);

        passwordLabel.setBounds(50, 250, 170, 35);
        passwordField.setBounds(220, 253, 170, 30);
        panel2.add(passwordLabel);
        panel2.add(passwordField);

        panel2.add(errorLabel);

        signInButton = new JButton("Sign in");
        signInButton.setBounds(147, 340, 156, 50);
        signInButton.setBackground(primaryColor);
        signInButton.setForeground(secondaryColor);
        signInButton.setFont(secondaryFontButton);
        signInButton.setSize(150, 50);
        signInButton.setFocusable(false);
        signInButton.addActionListener(this);
        panel2.add(signInButton);

        signUpButton = new JButton("Sign up");
        signUpButton.setBounds(147, 400, 156, 50);
        signUpButton.setBackground(primaryColor);
        signUpButton.setForeground(secondaryColor);
        signUpButton.setFont(secondaryFontButton);
        signUpButton.setSize(150, 50);
        signUpButton.setFocusable(false);
        signUpButton.addActionListener(this);
        panel2.add(signUpButton);

    }

    public void signInVisibility(boolean setVisibility) {
        signInButton.setVisible(setVisibility);
        signUpButton.setVisible(setVisibility);
        userField.setVisible(setVisibility);
        userNameLabel.setVisible(setVisibility);
        passwordField.setVisible(setVisibility);
        passwordLabel.setVisible(setVisibility);
    }

    public void signUp() {

        backButton = new JButton(back);
        backButton.setBackground(secondaryColor);
        backButton.setFocusable(false);
        backButton.setBorder(null);
        backButton.addActionListener(this);

        JPanel p = new JPanel();
        p.setBounds(30, 50, 20, 40);
        p.setBackground(secondaryColor);
        p.setLayout(new FlowLayout());
        p.add(backButton);
        panel2.add(p);

        userNameLabel.setBounds(30, 180, 166, 35);
        userNameLabel.setFont(fieldFont);
        userField.setBounds(220, 180, 196, 30);
        panel2.add(userNameLabel);
        panel2.add(userField);

        passwordLabel.setBounds(30, 230, 166, 35);
        passwordLabel.setFont(fieldFont);
        passwordField.setBounds(220, 230, 196, 30);
        panel2.add(passwordLabel);
        panel2.add(passwordField);

        confirmPasswordLabel = new JLabel("Confirm Password: ");
        confirmPasswordLabel.setFont(fieldFont);
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setFont(fieldFont);

        confirmPasswordLabel.setBounds(30, 280, 166, 35);
        confirmPasswordField.setBounds(220, 280, 196, 30);
        panel2.add(confirmPasswordLabel);
        panel2.add(confirmPasswordField);

        panel2.add(errorLabel);

        roleLabel = new JLabel("Role:");
        roleLabel.setFont(fieldFont);
        roleLabel.setBounds(30, 330, 100, 20);
        panel2.add(roleLabel);

        rb1 = new JRadioButton("Admin");
        rb1.setBounds(245, 330, 80, 20);
        rb1.setFont(fieldFontSmall);
        rb1.setBackground(secondaryColor);
        rb1.setForeground(primaryColor);
        rb1.setEnabled(false);

        rb2 = new JRadioButton("Store");
        rb2.setBounds(330, 330, 80, 20);
        rb2.setFont(fieldFontSmall);
        rb2.setBackground(secondaryColor);
        rb2.setForeground(primaryColor);

        bg = new ButtonGroup();
        bg.add(rb1);
        bg.add(rb2);

        panel2.add(rb1);
        panel2.add(rb2);

        cb = new JCheckBox("Agree to Privacy terms and conditions");
        cb.setBackground(secondaryColor);
        cb.setForeground(primaryColor);
        cb.setFont(fieldFontItalic);
        cb.setBounds(28, 370, 300, 20);
        cb.setSelected(true);
        panel2.add(cb);

        createAccount = new JButton("Create Account");
        createAccount.setBounds(30, 410, 120, 40);
        createAccount.setBackground(primaryColor);
        createAccount.setForeground(secondaryColor);
        createAccount.setFont(fieldFont);
        createAccount.setSize(160, 40);
        createAccount.setFocusable(false);
        createAccount.addActionListener(this);
        panel2.add(createAccount);

    }

    public JPanel getOptionPane(String message) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0x1E2534));
        JLabel label = new JLabel(message);
        label.setForeground(new Color(0xC4C4C4));

        UIManager.put("OptionPane.background", new Color(0x1E2534));
        UIManager.put("Panel.background", new Color(0x1E2534));
        UIManager.put("Button.background", new Color(0xC4C4C4));
        UIManager.put("Button.foreground", new Color(0x1E2534));

        panel.add(label);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signInButton) {
            String name = userField.getText();
            String password = String.valueOf(passwordField.getPassword());
            currentAccount = db.fetchAccount(name);
            if (currentAccount != null) {
                if (currentAccount.authentication(password)) {
                    try {
                        new StoreDashboard(currentAccount, db);
                    } catch (IOException | FontFormatException e2) {
                        e2.printStackTrace();
                    }
                    frame.dispose();
                } else {
                    errorLabel.setText("Incorrect password");
                    errorLabel.setBounds(220, 230, 300, 20);
                    passwordField.setText("");
                }
            } else {
                errorLabel.setText("Username not found try sign up");
                errorLabel.setBounds(220, 180, 300, 20);
                userField.setText("");
                passwordField.setText("");
            }

            System.out.println(name);
            System.out.println(password);

        } else if (e.getSource() == signUpButton) {
            signInButton.setVisible(false);
            signUpButton.setVisible(false);
            signUp();
        } else if (e.getSource() == backButton) {
            try {
                frame.dispose();
                new GuiComponent(db);
            } catch (IOException | FontFormatException e1) {
                e1.printStackTrace();
            }
        } else if (e.getSource() == createAccount) {
            String defaultRole = "store";
            String defaultLocation = "Addis Ababa";
            String name = userField.getText();
            // userField.setText("");
            String password = String.valueOf(passwordField.getPassword());
            String confirmedPassword = String.valueOf(confirmPasswordField.getPassword());
            if (db.nameIsFree(name)) {
                if (password.equals(confirmedPassword)) {
                    db.addAccount(name, defaultRole, password);
                    Account newUser = db.fetchAccount(name);
                    if ("store".equals(newUser.getRole())) {
                        db.addStore(newUser.getAccId(), defaultLocation);
                    }
                    JOptionPane.showConfirmDialog(frame, getOptionPane("Account created successfully."),
                            "HIKMM - Sign Up", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
                    try {
                        new StoreDashboard(newUser, db);
                    } catch (IOException | FontFormatException er) {
                        er.printStackTrace();
                    }
                    frame.dispose();
                } else {
                    errorLabel.setText("Password doesn't match");
                    errorLabel.setBounds(220, 255, 166, 30);
                    passwordField.setText("");
                    confirmPasswordField.setText("");
                }
            } else {
                errorLabel.setText("Username already exists");
                errorLabel.setBounds(220, 155, 166, 30);
                userField.setText("");
                passwordField.setText("");
                confirmPasswordField.setText("");
            }

            System.out.println(name);
            System.out.println(password);
            System.out.println(confirmedPassword);
        }
    }

    // for test purpose only
    // public static void main(String[] args) {
    // try {
    // new GuiComponent(db);
    // } catch (IOException | FontFormatException er) {
    // er.printStackTrace();
    // }
    // }
}
