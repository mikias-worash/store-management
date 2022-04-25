package main.store;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

class DashboardFuncs extends GuiComponent {
    Account acc;
    DBConnection db;
    int labelColorHex = 0x3E4C6A;
    Color labelColor;

    JPanel dPanel;
    JLabel itemNameLabel;
    JLabel quanitityLabel;
    JLabel categoryLabel;
    JLabel storeNameLabel;
    JLabel titleLabel;
    JTextField itemNameField;
    JTextField quantityField;
    JComboBox<String> category;
    JComboBox storeName;
    JComboBox itemName;
    JButton button;
    JButton button2;

    DashboardFuncs(Account acc, DBConnection db) throws IOException, FontFormatException {
        super(db);
//        db = new DBConnection("main\\db\\store.db");
        this.db = db;
        super.frame.dispose();
        this.acc = acc;

        labelColor = new Color(labelColorHex);

        dPanel = new JPanel();
        dPanel.setBounds(150, 160, 750, 440);
        dPanel.setLayout(null);
        dPanel.setBackground(secondaryColor);

        titleLabel = new JLabel();
        titleLabel.setForeground(labelColor);
        titleLabel.setFont(secondaryFontButton);
        titleLabel.setBounds(100, 40, 200, 30);
        dPanel.add(titleLabel);

        itemNameLabel = new JLabel();
        itemNameLabel.setText("Item name:");
        itemNameLabel.setForeground(labelColor);
        itemNameLabel.setFont(fieldFont);

        itemNameField = new JTextField();
        itemNameField.setBackground(secondaryColor2);
        itemNameField.setFont(fieldFont);

        quanitityLabel = new JLabel("Quantity:");
        quanitityLabel.setForeground(labelColor);
        quanitityLabel.setFont(fieldFont);

        quantityField = new JTextField();
        quantityField.setBackground(secondaryColor2);
        quantityField.setFont(fieldFont);

        categoryLabel = new JLabel("Category:");
        categoryLabel.setForeground(labelColor);
        categoryLabel.setFont(fieldFont);

        storeNameLabel = new JLabel("Store name:");
        storeNameLabel.setForeground(labelColor);
        storeNameLabel.setFont(fieldFont);

        button = new JButton();

    }

    JPanel insertPage() {
        String[] categoryList = {"Tv", "Smart phone", "Fridge", "Washing machine"};

        titleLabel.setText("Insert Item");

        itemNameLabel.setBounds(135, 100, 200, 30);
        dPanel.add(itemNameLabel);

        itemNameField.setBounds(300, 100, 200, 30);
        dPanel.add(itemNameField);

        quanitityLabel.setBounds(135, 140, 200, 30);
        dPanel.add(quanitityLabel);

        quantityField.setBounds(300, 140, 200, 30);
        dPanel.add(quantityField);

        categoryLabel.setBounds(135, 180, 200, 30);
        dPanel.add(categoryLabel);

        category = new JComboBox<>(categoryList);
        category.setBackground(secondaryColor2);
        category.setBounds(300, 180, 200, 30);
        dPanel.add(category);

        button.setText("Insert");
        button.setBounds(270, 250, 100, 30);
        button.setBackground(primaryColor);
        button.setForeground(secondaryColor);
        button.setFocusable(false);
        button.addActionListener(this);
        dPanel.add(button);

        return dPanel;
    }

    JPanel updatePage() {

        titleLabel.setText("Update Item");

        itemNameLabel.setBounds(135, 100, 200, 30);
        dPanel.add(itemNameLabel);

        itemNameField.setBounds(300, 100, 200, 30);
        dPanel.add(itemNameField);

        quanitityLabel.setBounds(135, 140, 200, 30);
        dPanel.add(quanitityLabel);

        quantityField.setBounds(300, 140, 200, 30);
        dPanel.add(quantityField);

        button.setText("Update");
        button.setBounds(270, 200, 100, 30);
        button.setBackground(primaryColor);
        button.setForeground(secondaryColor);
        button.setFocusable(false);
        button.addActionListener(this);
        dPanel.add(button);

        return dPanel;
    }

    JPanel inventoryPage() {

        titleLabel.setText("Inventory");

        itemNameLabel.setBounds(135, 100, 200, 30);
        dPanel.add(itemNameLabel);

        itemNameField.setBounds(300, 100, 200, 30);
        dPanel.add(itemNameField);

        return dPanel;
    }

    JPanel requestPage() {

        titleLabel.setText("Request");

        storeNameLabel.setBounds(135, 100, 200, 30);
        dPanel.add(storeNameLabel);

        storeName = new JComboBox();
        storeName.setBackground(secondaryColor2);
        storeName.setFont(fieldFont);
        storeName.setBounds(300, 100, 200, 30);
        dPanel.add(storeName);

        itemNameLabel.setBounds(135, 140, 200, 30);
        dPanel.add(itemNameLabel);

        itemName = new JComboBox();
        itemName.setBackground(secondaryColor2);
        itemName.setFont(fieldFont);
        itemName.setBounds(300, 140, 200, 30);
        dPanel.add(itemName);

        quanitityLabel.setBounds(135, 180, 200, 30);
        dPanel.add(quanitityLabel);

        quantityField.setBounds(300, 180, 200, 30);
        dPanel.add(quantityField);

        button.setText("Request");
        button.setBounds(270, 250, 100, 30);
        button.setBackground(primaryColor);
        button.setForeground(secondaryColor);
        button.setFocusable(false);
        button.addActionListener(this);
        dPanel.add(button);

        return dPanel;
    }

    JPanel pRequestPage() {

        titleLabel.setText("Pending Reqests");

        itemNameLabel.setBounds(135, 100, 200, 30);
        dPanel.add(itemNameLabel);

        itemNameField.setBounds(300, 100, 200, 30);
        dPanel.add(itemNameField);

        button.setText("Accept");
        button.setBounds(520, 100, 100, 30);
        button.setBackground(new Color(0x62CD66));
        button.setForeground(primaryColor);
        button.setFocusable(false);
        button.addActionListener(this);
        dPanel.add(button);

        button2 = new JButton("Decline");
        button2.setBounds(630, 100, 100, 30);
        button2.setBackground(new Color(0xE26262));
        button2.setForeground(primaryColor);
        button2.setFocusable(false);
        button2.addActionListener(this);
        dPanel.add(button2);

        return dPanel;
    }

    JPanel transactionsPage() {

        titleLabel.setText("Transactions");

        itemNameLabel.setBounds(135, 100, 200, 30);
        dPanel.add(itemNameLabel);

        itemNameField.setBounds(300, 100, 200, 30);
        dPanel.add(itemNameField);

        return dPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Insert") {

            String productName = itemNameField.getText();
            String selectedCategory = category.getItemAt(category.getSelectedIndex());
            int quantity = Integer.parseInt(quantityField.getText());

            db.addProduct(acc, productName, selectedCategory, quantity);
//            db.close();

        } else if (e.getActionCommand() == "Update") {
            int id = acc.getAccId();
//            db.updateInventory(, );
            System.out.println("Updated");
        } else if (e.getActionCommand() == "Request") {
            System.out.println("Request Sent");
        } else if (e.getActionCommand() == "Accept") {
            System.out.println("Accepted");
        } else if (e.getActionCommand() == "Decline") {
            System.out.println("Declined");
        }
    }

}