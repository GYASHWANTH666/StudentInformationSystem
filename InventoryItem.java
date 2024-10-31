import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class InventoryItem {
    String name;
    int quantity;
    double price;

    InventoryItem(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}

public class InventoryManagementSystem extends JFrame {
    private JTextField nameField;
    private JTextField quantityField;
    private JTextField priceField;
    private JTable inventoryTable;
    private DefaultTableModel tableModel;
    private ArrayList<InventoryItem> inventory;

    public InventoryManagementSystem() {
        inventory = new ArrayList<>();

        // Setup JFrame
        setTitle("Inventory Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));

        inputPanel.add(new JLabel("Item Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        inputPanel.add(quantityField);

        inputPanel.add(new JLabel("Price:"));
        priceField = new JTextField();
        inputPanel.add(priceField);

        JButton addButton = new JButton("Add Item");
        addButton.addActionListener(new AddItemListener());
        inputPanel.add(addButton);

        JButton updateButton = new JButton("Update Item");
        updateButton.addActionListener(new UpdateItemListener());
        inputPanel.add(updateButton);

        JButton deleteButton = new JButton("Delete Item");
        deleteButton.addActionListener(new DeleteItemListener());
        inputPanel.add(deleteButton);

        // Create table
        String[] columnNames = {"Item Name", "Quantity", "Price"};
        tableModel = new DefaultTableModel(columnNames, 0);
        inventoryTable = new JTable(tableModel);

        // Add components to JFrame
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(inventoryTable), BorderLayout.CENTER);
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        for (InventoryItem item : inventory) {
            tableModel.addRow(new Object[]{item.name, item.quantity, item.price});
        }
    }

    private class AddItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            double price = Double.parseDouble(priceField.getText());
            inventory.add(new InventoryItem(name, quantity, price));
            updateTable();
            clearFields();
        }
    }

    private class UpdateItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = inventoryTable.getSelectedRow();
            if (selectedRow != -1) {
                String name = nameField.getText();
                int quantity = Integer.parseInt(quantityField.getText());
                double price = Double.parseDouble(priceField.getText());
                InventoryItem item = inventory.get(selectedRow);
                item.name = name;
                item.quantity = quantity;
                item.price = price;
                updateTable();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(null, "Select an item to update.");
            }
        }
    }

    private class DeleteItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = inventoryTable.getSelectedRow();
            if (selectedRow != -1) {
                inventory.remove(selectedRow);
                updateTable();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(null, "Select an item to delete.");
            }
        }
    }

    private void clearFields() {
        nameField.setText("");
        quantityField.setText("");
        priceField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InventoryManagementSystem ims = new InventoryManagementSystem();
            ims.setVisible(true);
        });
    }
}
