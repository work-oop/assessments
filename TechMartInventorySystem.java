package techmartinventorysystem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

abstract class Item {
    private String productName;
    private double price;

    public Item(String productName, double price) {
        this.productName = productName;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public abstract void displayDetails();
}

class Product extends Item {
    private int id;
    private int quantityInStock;

    public Product(String productName, double price, int id, int quantityInStock) {
        super(productName, price);
        this.id = id;
        this.quantityInStock = quantityInStock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public void updateStock(int quantity) {
        this.quantityInStock += quantity;
    }

    public double calculateInventoryValue() {
        return getPrice() * quantityInStock;
    }

    @Override
    public void displayDetails() {
        System.out.println("Product Name: " + getProductName());
        System.out.println("Product ID: " + getId());
        System.out.println("Price: " + getPrice()+ "RWF");
        System.out.println("Quantity in Stock: " + getQuantityInStock());
        System.out.println("Total Inventory Value: " + calculateInventoryValue()+"RWF");
        System.out.println("-------------------------");
    }
}

class Inventory {
    private List<Product> productList;

    public Inventory() {
        productList = new ArrayList<>();
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void displayInventory() {
        for (Product product : productList) {
            product.displayDetails();
        }
    }

    public void readInventoryFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String productName = data[0];
                double price = Double.parseDouble(data[1]);
                int id = Integer.parseInt(data[2]);
                int quantity = Integer.parseInt(data[3]);
                Product product = new Product(productName, price, id, quantity);
                addProduct(product);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void writeInventoryToFile(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Product product : productList) {
                bw.write(product.getProductName() + "," + product.getPrice() + ","
                        + product.getId() + "," + product.getQuantityInStock() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class TechMartInventorySystem {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        inventory.readInventoryFromFile("initial_inventory.csv");

        System.out.println("Initial Inventory:");
        inventory.displayInventory();

        Product newProduct1 = new Product("Laptop", 500000.00, 1, 15);
        Product newProduct2 = new Product("Smartphone", 100000.00, 2, 20);
        inventory.addProduct(newProduct1);
        inventory.addProduct(newProduct2);

        newProduct1.updateStock(5);
        newProduct2.updateStock(10);

        System.out.println("Updated Inventory:");
        inventory.displayInventory();

        inventory.writeInventoryToFile("updated_inventory.csv");
    }
}
