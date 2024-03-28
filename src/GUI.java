//Rochana Godigamuwa Program(20221116)
//Start Date 15.12.2023       //End Date 12.01.2024
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class GUI extends JFrame {

    private WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
    private DefaultTableModel productTableModel;
    private JTextArea productDetailsArea;
    private JComboBox<String> productTypeComboBox;
    private JTable productTable;
    private ShoppingCart shoppingCart;
    private JTable shoppingCartTable;

    public GUI() {
        // Create the frame
        JFrame frame = new JFrame("Rocha Shopping Manager");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create four panels
        JPanel panel1 = new JPanel(new GridLayout(1, 3)); // Subpanels in a row
        frame.add(panel1, BorderLayout.NORTH); // Add panel1 to the north of the frame

        // Subpanel 1 - Label for Shopping Manager
        JPanel subPanel1 = new JPanel(new GridBagLayout());
        panel1.add(subPanel1);

         // Label for Shopping Manager
        JLabel shoppingManagerLabel = new JLabel("Select Product Category");
        shoppingManagerLabel.setPreferredSize(new Dimension(150, 30));

        GridBagConstraints gbcLabel = new GridBagConstraints();
        gbcLabel.gridx = 0;
        gbcLabel.gridy = 0;
        gbcLabel.anchor = GridBagConstraints.NORTHWEST; // Left-align the label
        subPanel1.add(shoppingManagerLabel, gbcLabel);

        // Subpanel 2 - Dropdown list for Product Type
        JPanel subPanel2 = new JPanel();
        panel1.add(subPanel2);

        String[] items = {"All", "Electronics", "Clothing"};
        productTypeComboBox = new JComboBox<>(items);
        productTypeComboBox.setPreferredSize(new Dimension(150, 30));
        subPanel2.add(productTypeComboBox);

        // Subpanel 3 - Button for Shopping Cart
        JPanel subPanel3 = new JPanel();
        panel1.add(subPanel3);

        JButton shoppingCartButton = new JButton("Shopping Cart");
        shoppingCartButton.setPreferredSize(new Dimension(150, 30));
        subPanel3.add(shoppingCartButton);

        // Create table with 5 columns and 6 rows
        String[] columnNames = {"Product ID", "Product Name", "Available Units", "Price"};

        productTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (String columnName : columnNames) {
            productTableModel.addColumn(columnName);
        }

        productTable = new JTable(productTableModel);
        JScrollPane scrollPane = new JScrollPane(productTable);

        // Initializing shoppingCartTable
        shoppingCartTable = new JTable(new DefaultTableModel(columnNames, 0));
        JScrollPane cartScrollPane = new JScrollPane(shoppingCartTable);

        // Add action listener for the "Shopping Cart" button
        shoppingCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open a new frame with two panels
                JFrame detailsFrame = new JFrame("Product Details");
                detailsFrame.setSize(600, 400);
                detailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this frame on exit

                // First Panel with Table
                JPanel panelTable = new JPanel(new BorderLayout());
                detailsFrame.add(panelTable, BorderLayout.WEST);

                // Create table with 3 columns and 3 rows (including titles)
                String[] columnTitles = {"Product ID", "Product Name", "Available Units", "Price"};
                DefaultTableModel tableModel = new DefaultTableModel(columnTitles, 3);
                JTable detailsTable = new JTable(tableModel);
                JScrollPane tableScrollPane = new JScrollPane(detailsTable);
                panelTable.add(cartScrollPane, BorderLayout.CENTER);

                // Second Panel with Text Area
                JPanel panelTextArea = new JPanel(new BorderLayout());
                detailsFrame.add(panelTextArea, BorderLayout.CENTER);

                JTextArea detailsTextArea = new JTextArea("Details");
                detailsTextArea.setEditable(false);
                panelTextArea.add(detailsTextArea, BorderLayout.CENTER);

                // Set up an event listener for the table
                detailsTable.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int selectedRow = detailsTable.getSelectedRow();
                        if (selectedRow >= 0) {
                            displayDetails(selectedRow, detailsTextArea);
                        }
                    }
                });

                // Make the details frame visible
                detailsFrame.setVisible(true);
            }
        });

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel2.add(scrollPane, BorderLayout.CENTER);

        productDetailsArea = new JTextArea("Product Details");
        productDetailsArea.setEditable(false);

        JButton addToCartButton = new JButton("Add to Shopping Cart");
        JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel4.add(addToCartButton);

        // Set the layout manager for the frame
        frame.setLayout(new GridLayout(4, 1));

        // Add panels to the frame
        frame.add(panel2);
        frame.add(productDetailsArea);
        frame.add(panel4);

        // Make the frame visible
        frame.setVisible(true);

        shoppingCart = new ShoppingCart();

        // Add action listeners for the buttons
        productTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateProductTable((String) productTypeComboBox.getSelectedItem());
            }
        });

        shoppingCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayShoppingCart();
            }
        });

        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToShoppingCart(productTable.getSelectedRow());
            }
        });

        // Add a MouseAdapter to the productTable
        productTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = productTable.getSelectedRow();
                if (selectedRow >= 0) {
                    displayProductDetails(selectedRow);
                }
            }
        });
    }

    private void displayDetails(int selectedIndex, JTextArea detailsTextArea) {
        if (selectedIndex >= 0 && selectedIndex < productTableModel.getRowCount()) {
            StringBuilder productDetails = new StringBuilder("Details:\n");
            for (int column = 0; column < productTableModel.getColumnCount(); column++) {
                Object value = productTableModel.getValueAt(selectedIndex, column);
                productDetails.append(productTableModel.getColumnName(column))
                        .append(": ")
                        .append(value)
                        .append("\n");
            }
            detailsTextArea.setText(productDetails.toString());
        }
    }

    private void updateProductTable(String productType) {
        List<Product> filteredProducts = shoppingManager.getProductsByType(productType);
        displayProductsInTable(filteredProducts);
    }

    private void displayShoppingCart() {
        // Display the shopping cart contents
        List<Product> cartContents = shoppingCart.getProductsList();
        DefaultTableModel cartTableModel = (DefaultTableModel) shoppingCartTable.getModel();

        // Clear the shopping cart table
        cartTableModel.setRowCount(0);

        // Add products to the shopping cart table
        for (Product product : cartContents) {
            Object[] rowData = new Object[]{
                    product.getProduct_ID(),
                    product.getProduct_Name(),
                    product.getNo_Available_Items(),
                    product.getPrice(),
            };
            cartTableModel.addRow(rowData);
        }

        double totalCost = shoppingCart.calculateTotalCost();
        // Add a row for total cost
        Object[] totalRow = new Object[]{"Total Cost", "", "", totalCost};
        cartTableModel.addRow(totalRow);
    }

    private void addToShoppingCart(int selectedIndex) {
        if (selectedIndex >= 0) {
            Product selectedProduct = shoppingManager.getProductList().get(selectedIndex);
            shoppingCart.addProduct(selectedProduct);
            updateShoppingCartTable(); // Update the shopping cart table
        } else {
            JOptionPane.showMessageDialog(this, "Please select a product from the table to add to the cart.");
        }
    }

    private void updateShoppingCartTable() {
        List<Product> cartContents = shoppingCart.getProductsList();
        DefaultTableModel cartTableModel = (DefaultTableModel) shoppingCartTable.getModel();

        // Clear the shopping cart table
        cartTableModel.setRowCount(0);

        // Add products to the shopping cart table
        for (Product product : cartContents) {
            Object[] rowData = new Object[]{
                    product.getProduct_ID(),
                    product.getProduct_Name(),
                    product.getNo_Available_Items(),
                    product.getPrice(),
            };
            cartTableModel.addRow(rowData);
        }
    }

    private void displayProductDetails(int selectedIndex) {
        if (selectedIndex >= 0) {
            Product selectedProduct = shoppingManager.getProductList().get(selectedIndex);
            StringBuilder productDetails = new StringBuilder("Product Details:\n");
            productDetails.append("Product ID: ").append(selectedProduct.getProduct_ID()).append("\n");
            productDetails.append("Product Name: ").append(selectedProduct.getProduct_Name()).append("\n");
            productDetails.append("Available Units: ").append(selectedProduct.getNo_Available_Items()).append("\n");
            productDetails.append("Price: ").append(selectedProduct.getPrice()).append("\n");

            productDetailsArea.setText(productDetails.toString());
        }
    }

    private void displayProductsInTable(List<Product> products) {
        // Clear the table
        productTableModel.setRowCount(0);

        // Add products to the table
        for (Product product : products) {
            Object[] rowData = new Object[]{
                    product.getProduct_ID(),
                    product.getProduct_Name(),
                    product.getNo_Available_Items(),
                    product.getPrice(),
            };
            productTableModel.addRow(rowData);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI();
            }
        });
    }
}
