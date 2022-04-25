package main.store;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;
import java.io.IOException;

public class StoreDashboard extends GuiComponent {

    Account acc;
    DBConnection db;

    int secondaryColorHex3 = 0x1B1B1B;

    Color secondaryColor3;

    JFrame frame;
    JPanel panel;
    JPanel mainPanel;
    JPanel leftPanel;
    JPanel[] p = new JPanel[6];
    JPanel accNamePanel;
    JPanel tablePanel;
    JTable transactionTable;
    JLabel accName;
    JLabel itemNameLabel;
    JLabel quanitityLabel;
    JLabel categoryLabel;
    JLabel storeNameLabel;
    JLabel titleLabel;
    JTextField itemNameField;
    JTextField quantityField;
    JComboBox<String> category;
    JComboBox<Store> storeName;
    JComboBox<Product> itemName;
    JButton button;
    JButton button2;
    JButton homeButton;
    JButton signOutButton;
    JButton[] b = new JButton[6];
    ImageIcon home, night, light;
    ImageIcon[] menuIcons = new ImageIcon[6];
    ImageIcon[] menuIcons_w = new ImageIcon[6];

    WindowListener listener;
    DefaultTableCellRenderer centerRenderer;

    String[] menu = { "Insert Item", "Update Item", "Inventory", "Request Item", "Pending Request", "Transactions" };
    Product[] storeProduct;
    Store[] storeList;
    Request[] requestsList;

    public StoreDashboard(Account currentAcc, DBConnection db) throws IOException, FontFormatException {
        super(db);
        super.frame.dispose();
        this.db = db;
        acc = currentAcc;

        String[] url = { "main\\Images\\insert.png", "main\\Images\\update.png", "main\\Images\\inventory.png",
                "main\\Images\\request.png", "main\\Images\\pending.png", "main\\Images\\transactions.png" };
        String[] url_w = { "main\\Images\\insert_w.png", "main\\Images\\update_w.png", "main\\Images\\inventory_w.png",
                "main\\Images\\request_w.png", "main\\Images\\pending_w.png", "main\\Images\\transactions_w.png" };

        String[] categoryList = { "TV", "Smart phone", "Fridge", "Washing machine" };

        for (int i = 0; i < 6; i++) {
            menuIcons[i] = new ImageIcon(url[i]);
            menuIcons_w[i] = new ImageIcon(url_w[i]);
        }

        home = new ImageIcon("main\\Images\\home.png");
        night = new ImageIcon("main\\Images\\night.png");
        light = new ImageIcon("main\\Images\\light.png");

        secondaryColor3 = new Color(secondaryColorHex3);

        panel = new JPanel();
        panel.setBounds(0, 0, 900, 600);
        panel.setLayout(null);
        panel.setBackground(secondaryColor);

        leftPanel = new JPanel();
        leftPanel.setBounds(0, 0, 150, 900);
        leftPanel.setLayout(null);
        leftPanel.setBackground(primaryColor);

        titleLabel = new JLabel();
        titleLabel.setForeground(primaryColor);
        titleLabel.setFont(secondaryFontButton);

        itemNameLabel = new JLabel();
        itemNameLabel.setText("Item name:");
        itemNameLabel.setForeground(primaryColor);
        itemNameLabel.setFont(fieldFont);

        itemNameField = new JTextField();
        itemNameField.setBackground(secondaryColor2);
        itemNameField.setFont(fieldFont);

        itemName = new JComboBox<>();
        itemName.setBackground(secondaryColor2);
        itemName.setFont(fieldFont);

        quanitityLabel = new JLabel("Quantity:");
        quanitityLabel.setForeground(primaryColor);
        quanitityLabel.setFont(fieldFont);

        quantityField = new JTextField();
        quantityField.setBackground(secondaryColor2);
        quantityField.setFont(fieldFont);

        categoryLabel = new JLabel("Category:");
        categoryLabel.setForeground(primaryColor);
        categoryLabel.setFont(fieldFont);

        category = new JComboBox<>(categoryList);
        category.setBackground(secondaryColor2);
        category.setFont(fieldFont);

        storeNameLabel = new JLabel("Store name:");
        storeNameLabel.setForeground(primaryColor);
        storeNameLabel.setFont(fieldFont);

        storeList = db.fetchStoreList(acc);
        storeName = new JComboBox<>(storeList);
        storeName.setBackground(secondaryColor2);
        storeName.setFont(fieldFont);

        button = new JButton();

        mainPanel = new JPanel();
        panel.add(mainPanel);
        dashboard();

        tablePanel = new JPanel();
        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        homeButton = new JButton(home);
        homeButton.setBounds(53, 102, 45, 45);
        homeButton.setBackground(primaryColor);
        homeButton.setFocusable(false);
        homeButton.setBorder(null);
        homeButton.addActionListener(this);
        leftPanel.add(homeButton);
        panel.add(leftPanel);

        accName = new JLabel("Store: " + currentAcc.getUserName());// getUserName();
        accName.setForeground(Color.white);
        accName.setFont(secondaryFontButton);
        accName.setBounds(30, 0, 320, 75);

        accNamePanel = new JPanel();
        accNamePanel.setBounds(150, 85, 320, 75);
        accNamePanel.setLayout(null);
        accNamePanel.setBackground(primaryColor);
        accNamePanel.add(accName);
        panel.add(accNamePanel);

        signOutButton = new JButton("Sign Out");
        signOutButton.setBounds(765, 35, 110, 30);
        signOutButton.setBackground(secondaryColor2);
        signOutButton.setForeground(primaryColor);
        signOutButton.setFont(fieldFont);
        signOutButton.setFocusable(false);
        // signOutButton.setBorder(null);
        signOutButton.addActionListener(this);
        panel.add(signOutButton);

        toggleButton = new JToggleButton(night);
        toggleButton.setBounds(725, 35, 30, 30);
        toggleButton.setBackground(secondaryColor2);
        toggleButton.setFocusable(false);
        toggleButton.addActionListener(this);
        panel.add(toggleButton);

        frame = new JFrame();
        frame.setTitle("HIKMM");
        frame.setIconImage(logoWithBg.getImage());
        frame.setSize(917, 639);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.add(panel);
        listener = new WindowAdapter() {

            public void windowClosing(WindowEvent w) {
                int response = JOptionPane.showConfirmDialog(frame, getOptionPane(" Are you sure you want exit?"),
                        "HIKMM - Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION)
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                else
                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        };

        frame.addWindowListener(listener);
        frame.setVisible(true);

    }

    void dashboard() {

        mainPanel.setBounds(150, 160, 750, 440);
        mainPanel.setLayout(null);
        mainPanel.setBackground(secondaryColor);

        for (int i = 0, x = 0, y = 0; i < menuIcons.length; i++, x += 225) {
            int px = 75 + x;
            p[i] = new JPanel();
            p[i].setLayout(null);
            b[i] = new JButton(menu[i], menuIcons[i]);
            if (i < 3) {
                p[i].setBounds(px, 50, 150, 150);
            } else if (i == 3) {
                x = 0;
                y += 160;
                p[i].setBounds(75, 240, 150, 150);
            } else {
                p[i].setBounds(px, 240, 150, 150);
            }
            b[i].setVerticalTextPosition(JButton.BOTTOM);
            b[i].setHorizontalTextPosition(JButton.CENTER);
            b[i].setIconTextGap(12);
            b[i].setBounds(0, 0, 150, 150);
            b[i].setBackground(secondaryColor2);
            b[i].setFocusable(false);
            b[i].addActionListener(this);
            p[i].add(b[i]);
            mainPanel.add(p[i]);
        }

    }

    void disableVisibility() {
        for (int i = 0; i < p.length; i++) {
            p[i].setVisible(false);
        }
    }

    void insertPage() {

        titleLabel.setBounds(100, 50, 200, 30);
        titleLabel.setText("Insert Item");
        mainPanel.add(titleLabel);

        itemNameLabel.setBounds(135, 100, 200, 30);
        mainPanel.add(itemNameLabel);

        itemNameField.setBounds(300, 100, 200, 30);
        mainPanel.add(itemNameField);

        quanitityLabel.setBounds(135, 140, 200, 30);
        mainPanel.add(quanitityLabel);

        quantityField.setBounds(300, 140, 200, 30);
        mainPanel.add(quantityField);

        categoryLabel.setBounds(135, 180, 200, 30);
        mainPanel.add(categoryLabel);

        category.setBounds(300, 180, 200, 30);
        mainPanel.add(category);

        button.setText("Insert");
        button.setBounds(270, 250, 100, 30);
        button.setBackground(primaryColor);
        button.setForeground(secondaryColor);
        button.setFocusable(false);
        button.addActionListener(this);
        mainPanel.add(button);

    }

    void updatePage() {

        titleLabel.setBounds(100, 50, 200, 30);
        titleLabel.setText("Update Item");
        mainPanel.add(titleLabel);

        itemNameLabel.setBounds(135, 100, 200, 30);
        mainPanel.add(itemNameLabel);

        itemName.setBounds(300, 100, 200, 30);
        mainPanel.add(itemName);

        quanitityLabel.setBounds(135, 140, 200, 30);
        mainPanel.add(quanitityLabel);

        quantityField.setBounds(300, 140, 200, 30);
        mainPanel.add(quantityField);

        button.setText("Update");
        button.setBounds(270, 200, 100, 30);
        button.setBackground(primaryColor);
        button.setForeground(secondaryColor);
        button.setFocusable(false);
        button.addActionListener(this);
        mainPanel.add(button);

    }

    void inventoryPage() {

        String[][] data = db.fetchAllInventory(acc.getAccId());
        String[] column = { "No.", "Item", "Quantity" };

        titleLabel.setBounds(100, 50, 200, 30);
        titleLabel.setText("Inventory");
        mainPanel.add(titleLabel);

        JTable tbl = new JTable(data, column);
        tbl.setFont(tableFont);
        tbl.setRowHeight(25);
        tbl.setBackground(primaryColor);
        tbl.setForeground(secondaryColor);
        tbl.setEnabled(false);

        JScrollPane sp = new JScrollPane(tbl);

        if (data.length > 16) {
            tablePanel.setBounds(135, 100, 468, 300);
        } else {
            tablePanel.setBounds(135, 100, 468, data.length * 25 + 26);
        }

        tbl.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbl.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbl.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        tablePanel.setLayout(new FlowLayout());
        tablePanel.setBackground(secondaryColor);
        tablePanel.add(sp);
        mainPanel.add(tablePanel);

    }

    void requestPage() {

        titleLabel.setBounds(100, 50, 200, 30);
        titleLabel.setText("Request");
        mainPanel.add(titleLabel);

        storeNameLabel.setBounds(135, 100, 200, 30);
        mainPanel.add(storeNameLabel);

        storeName.setBounds(300, 100, 200, 30);
        mainPanel.add(storeName);

        itemNameLabel.setBounds(135, 140, 200, 30);
        mainPanel.add(itemNameLabel);

        storeProduct = db.fetchProductList();
        itemName = new JComboBox<>(storeProduct);
        itemName.setBackground(secondaryColor2);
        itemName.setFont(fieldFont);
        itemName.setBounds(300, 140, 200, 30);
        mainPanel.add(itemName);

        quanitityLabel.setBounds(135, 180, 200, 30);
        mainPanel.add(quanitityLabel);

        quantityField.setBounds(300, 180, 200, 30);
        mainPanel.add(quantityField);

        button.setText("Request");
        button.setBounds(270, 250, 100, 30);
        button.setBackground(primaryColor);
        button.setForeground(secondaryColor);
        button.setFocusable(false);
        button.addActionListener(this);
        mainPanel.add(button);

    }

    void pRequestPage() {
        requestsList = db.fetchRequestList(acc);

        String[][] data = Request.toTable(requestsList);

        String[] column = { "No.", "Item", "Quantity", "Sender" };

        titleLabel.setBounds(100, 50, 200, 30);
        titleLabel.setText("Pending Requests");
        mainPanel.add(titleLabel);

        JTable tbl = new JTable(data, column);
        tbl.setFont(tableFont);
        tbl.setRowHeight(25);
        tbl.setBackground(primaryColor);
        tbl.setForeground(secondaryColor);
        tbl.setEnabled(false);

        JScrollPane sp = new JScrollPane(tbl);

        if (data.length > 16) {
            tablePanel.setBounds(135, 100, 468, 300);
        } else {
            tablePanel.setBounds(135, 100, 468, data.length * 25 + 26);
        }

        tbl.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbl.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbl.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbl.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        tablePanel.setLayout(new FlowLayout());
        tablePanel.setBackground(secondaryColor);
        tablePanel.add(sp);
        mainPanel.add(tablePanel);

        button.setText("Accept");
        button.setBounds(265, 360, 100, 30);
        button.setBackground(new Color(0x62CD66));
        button.setForeground(primaryColor);
        button.setFocusable(false);
        button.addActionListener(this);
        mainPanel.add(button);

        button2 = new JButton("Decline");
        button2.setBounds(380, 360, 100, 30);
        button2.setBackground(new Color(0xE26262));
        button2.setForeground(primaryColor);
        button2.setFocusable(false);
        button2.addActionListener(this);
        mainPanel.add(button2);

    }

    void transactionsPage() {

        String[] tableBtnLabels = { "GRN", "Sent", "Received" };
        JButton[] tableBtns = new JButton[3];

        for (int i = 0, x = 0; i < tableBtns.length; i++, x += 172) {
            int px = 145 + x;

            p[i] = new JPanel();
            p[i].setLayout(null);
            p[i].setBounds(px, 100, 100, 30);

            tableBtns[i] = new JButton(tableBtnLabels[i]);
            tableBtns[i].setBounds(0, 0, 100, 30);
            tableBtns[i].setBackground(primaryColor);
            tableBtns[i].setForeground(secondaryColor);
            tableBtns[i].setFocusable(false);
            tableBtns[i].addActionListener(this);
            p[i].add(tableBtns[i]);
            mainPanel.add(p[i]);
        }

        String[][] data = db.fetchGRN(acc.getAccId());

        String[] column = { "No.", "Date", "Item", "Supplier", "Quantity" };

        titleLabel.setBounds(100, 50, 200, 30);
        titleLabel.setText("Transactions");
        mainPanel.add(titleLabel);

        transactionTable = new JTable(data, column);
        transactionTable.setFont(tableFont);
        transactionTable.setRowHeight(25);
        transactionTable.setBackground(primaryColor);
        transactionTable.setForeground(secondaryColor);
        transactionTable.setEnabled(false);

        JScrollPane sp = new JScrollPane(transactionTable);

        if (data.length > 16) {
            tablePanel.setBounds(135, 150, 468, 300);
        } else {
            tablePanel.setBounds(135, 150, 468, data.length * 25 + 26);
        }

        transactionTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        transactionTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        transactionTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        transactionTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        transactionTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        tablePanel.setLayout(new FlowLayout());
        tablePanel.setBackground(secondaryColor);
        tablePanel.add(sp);
        mainPanel.add(tablePanel);

    }

    public void lightMode() {
        toggleButton.setIcon(night);

        panel.setBackground(secondaryColor);
        mainPanel.setBackground(secondaryColor);
        tablePanel.setBackground(secondaryColor);

        titleLabel.setForeground(primaryColor);
        itemNameLabel.setForeground(primaryColor);
        quanitityLabel.setForeground(primaryColor);
        categoryLabel.setForeground(primaryColor);
        storeNameLabel.setForeground(primaryColor);
        itemNameField.setForeground(primaryColor);
        quantityField.setForeground(primaryColor);
        category.setForeground(primaryColor);
        itemName.setForeground(primaryColor);
        storeName.setForeground(primaryColor);
        itemNameField.setCaretColor(primaryColor);
        quantityField.setCaretColor(primaryColor);

        itemNameField.setBackground(secondaryColor2);
        quantityField.setBackground(secondaryColor2);
        category.setBackground(secondaryColor2);
        itemName.setBackground(secondaryColor2);
        storeName.setBackground(secondaryColor2);

        toggleButton.setBackground(secondaryColor2);
        for (int i = 0; i < b.length; i++) {
            b[i].setBackground(secondaryColor2);
            b[i].setForeground(primaryColor);
            b[i].setIcon(menuIcons[i]);
            b[i].setBorder(BorderFactory.createLineBorder(new Color(0x7A8A99), 1));
        }
        signOutButton.setBackground(secondaryColor2);
        signOutButton.setForeground(primaryColor);
        signOutButton.setBorder(BorderFactory.createLineBorder(new Color(0x7A8A99), 1));
    }

    public void darkMode() {
        toggleButton.setIcon(light);

        panel.setBackground(Color.black);
        mainPanel.setBackground(Color.black);
        tablePanel.setBackground(Color.black);

        titleLabel.setForeground(secondaryColor);
        itemNameLabel.setForeground(secondaryColor);
        quanitityLabel.setForeground(secondaryColor);
        categoryLabel.setForeground(secondaryColor);
        storeNameLabel.setForeground(secondaryColor);
        itemNameField.setForeground(secondaryColor);
        quantityField.setForeground(secondaryColor);
        category.setForeground(secondaryColor);
        itemName.setForeground(secondaryColor);
        storeName.setForeground(secondaryColor);
        itemNameField.setCaretColor(secondaryColor);
        quantityField.setCaretColor(secondaryColor);

        itemNameField.setBackground(secondaryColor3);
        quantityField.setBackground(secondaryColor3);
        category.setBackground(secondaryColor3);
        itemName.setBackground(secondaryColor3);
        storeName.setBackground(secondaryColor3);

        for (int i = 0; i < b.length; i++) {
            b[i].setBackground(secondaryColor3);
            b[i].setForeground(secondaryColor2);
            b[i].setIcon(menuIcons_w[i]);
            b[i].setBorder(BorderFactory.createLineBorder(new Color(0x37434E), 1));
        }
        signOutButton.setBackground(secondaryColor3);
        signOutButton.setForeground(secondaryColor2);
        signOutButton.setBorder(BorderFactory.createLineBorder(new Color(0x37434E), 1));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == toggleButton) {
            if (toggleButton.isSelected()) {
                darkMode();
            } else {
                lightMode();
            }
        }
        if (e.getSource() == signOutButton) {
            try {
                new GuiComponent(db);
            } catch (IOException | FontFormatException error) {
                error.printStackTrace();
            }
            frame.dispose();
        }
        if (e.getSource() == homeButton) {
            try {
                new StoreDashboard(acc, db);
                frame.dispose();
            } catch (IOException | FontFormatException e1) {
                e1.printStackTrace();
            }
        }
        if (e.getActionCommand() == "Insert Item") {

            System.out.println("Insert Item");
            disableVisibility();
            insertPage();

        } else if (e.getActionCommand() == "Update Item") {
            storeProduct = db.fetchStoreProduct(acc.getAccId());
            itemName = new JComboBox<>(storeProduct);
            itemName.setBackground(secondaryColor2);
            itemName.setFont(fieldFont);

            disableVisibility();
            updatePage();

        } else if (e.getActionCommand() == "Inventory") {

            System.out.println("Inventory");
            disableVisibility();
            inventoryPage();

        } else if (e.getActionCommand() == "Request Item") {

            System.out.println("Request Item");
            disableVisibility();
            requestPage();

        } else if (e.getActionCommand() == "Pending Request") {

            System.out.println("Pending Request");
            disableVisibility();
            pRequestPage();

        }
        if (e.getActionCommand() == "Transactions") {

            System.out.println("Transactions");
            disableVisibility();
            transactionsPage();
        }
        panel.add(mainPanel);

        if (e.getActionCommand() == "Insert") {

            String productName = itemNameField.getText();
            String selectedCategory = category.getItemAt(category.getSelectedIndex());
            int quantity = Integer.parseInt(quantityField.getText());

            db.addProduct(acc, productName, selectedCategory, quantity);
            JOptionPane.showConfirmDialog(frame, getOptionPane("Item has been inserted."), "HIKMM - Insert Item",
                    JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);

            System.out.println("Inserted");

        } else if (e.getActionCommand() == "Update") {

            Product product = itemName.getItemAt(itemName.getSelectedIndex());
            int quantity = Integer.parseInt(quantityField.getText());
            int response = JOptionPane.showConfirmDialog(frame,
                    getOptionPane("Are you sure you want to update this item?"), "HIKMM - Update Item",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {

                db.addProduct(acc, product.getName(), product.getCategory(), quantity);
                JOptionPane.showConfirmDialog(frame, getOptionPane("Item has been updated."), "HIKMM - Update Item",
                        JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
                System.out.println("Updated");
            } else if (response == JOptionPane.NO_OPTION) {
                JOptionPane.showConfirmDialog(frame, getOptionPane("Item NOT updated."), "HIKMM - Update Item",
                        JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
            }
        } else if (e.getActionCommand() == "Request") {
            Store store = storeName.getItemAt(storeName.getSelectedIndex());
            Product product = itemName.getItemAt(itemName.getSelectedIndex());
            int quantity = Integer.parseInt(quantityField.getText());

            db.newRequest(acc, product, store, quantity);

            JOptionPane.showConfirmDialog(frame, getOptionPane("Your request has been sent."), "HIKMM - Request Sent",
                    JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);

            System.out.println("Request Sent");
        } else if (e.getActionCommand() == "Accept") {
            for (Request request : requestsList) {
                db.acceptRequest(acc, request);
            }
            System.out.println("Accepted");
        } else if (e.getActionCommand() == "Decline") {
            for (Request request : requestsList) {
                db.changeStatus(request.getRequestId(), Status.declined);
            }
            System.out.println("Declined");
        } else if (e.getActionCommand() == "GRN") {
            String[][] data = db.fetchGRN(acc.getAccId());
            String[] column = { "No.", "Date", "Item", "Supplier", "Quantity" };

            DefaultTableModel model = new DefaultTableModel(data, column);
            transactionTable.setModel(model);
            System.out.println("GRN");
        } else if (e.getActionCommand() == "Sent") {

            String[][] data = db.fetchSentItem(acc.getAccId());

            String[] column = { "No.", "Date", "Item", "Sent to", "Quantity" };

            titleLabel.setBounds(100, 50, 200, 30);
            titleLabel.setText("Transactions");
            mainPanel.add(titleLabel);

            if (data.length > 16) {
                tablePanel.setBounds(135, 150, 468, 300);
            } else {
                tablePanel.setBounds(135, 150, 468, data.length * 25 + 26);
            }

            DefaultTableModel model = new DefaultTableModel(data, column);
            transactionTable.setModel(model);

            System.out.println("Sent");
        } else if (e.getActionCommand() == "Received") {
            String[][] data = db.fetchReceivedItem(acc.getAccId());

            String[] column = { "No.", "Date", "Item", "Received from", "Quantity" };

            titleLabel.setBounds(100, 50, 200, 30);
            titleLabel.setText("Transactions");
            mainPanel.add(titleLabel);

            if (data.length > 16) {
                tablePanel.setBounds(135, 150, 468, 300);
            } else {
                tablePanel.setBounds(135, 150, 468, data.length * 25 + 26);
            }
            DefaultTableModel model = new DefaultTableModel(data, column);
            transactionTable.setModel(model);

            System.out.println("Recieved");

        }
        // for test purpose only
        // public static void main(String[] args) {
        // try {
        // Account a = new Account(6, "Akso", "Store", "1234");
        // new StoreDashboard(a);
        // } catch (IOException | FontFormatException e) {
        // e.printStackTrace();
        // }
    }

}
