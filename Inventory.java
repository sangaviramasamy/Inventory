import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;


public class Inventory {

    ArrayList<Product> product = new ArrayList<Product>();

    Inventory(){
        String filePath = "/Users/sangavi/IdeaProjects/Inventory.java/src/main/java/ProductInfo.txt";

        try {
            Scanner scanner = new Scanner(new File(filePath));

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] parts = line.split(", ");


                if (parts.length == 4) {
                    int productID = Integer.parseInt(parts[0]);
                    String pro_name = parts[1];
                    float pro_price = Float.parseFloat(parts[2]);
                    int pro_quantity = Integer.parseInt(parts[3]);

                    Product p = new Product(productID,pro_name,pro_price,pro_quantity);

                    product.add(p);
                } else {
                    System.out.println("Invalid format in the file: " + line);
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        }
    }

    public void writeToTextFile() {
        String filePath = "/Users/sangavi/IdeaProjects/Inventory.java/src/main/java/ProductInfo.txt";

        try {
            FileWriter writer = new FileWriter(filePath);

            for (Product p : product) {
                // Format the data as a CSV line
                String line = p.pro_id + ", " + p.pro_name + ", " + p.pro_price + ", " + p.pro_quantity;

                // Write the line to the file
                writer.write(line + System.getProperty("line.separator"));
            }

            writer.close();
            System.out.println("Data written to the file successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + filePath);
            e.printStackTrace();
        }
    }


    public void add(){
        System.out.print("Enter Product ID, Price, Quantity, Name : ");
        Scanner sc = new Scanner(System.in);
        int productID = sc.nextInt();

        for(int i = 0 ; i < product.size(); i++) {
            if(product.get(i).pro_id == productID){
                System.out.println("Product already exits");
                return;
            }
        }

        float pro_price = sc.nextFloat();

        int pro_quantity = sc.nextInt();

        String pro_name = sc.next();

        Product p = new Product(productID,pro_name,pro_price,pro_quantity);

        product.add(p);

        System.out.println("Product added");

        writeToTextFile();

        return;
    }

    public void view(){
        System.out.print("Enter Product ID : ");
        Scanner sc = new Scanner(System.in);
        int pro_id = sc.nextInt();
        int count = 0;
        for(int i=0 ; i<product.size();i++){
            if(product.get(i).pro_id == pro_id){
                System.out.println("Name : " + product.get(i).pro_name);
                System.out.println("Price : " + product.get(i).pro_price);
                System.out.println("Quantity : " + product.get(i).pro_quantity);
                count = 1;
            }
        }

        if(count == 0){
            System.out.println("The product is not found");
        }
    }

    public void remove(){
        System.out.print("Enter Product ID : ");
        Scanner sc = new Scanner(System.in);
        int pro_id = sc.nextInt();
        int count = 0;
        for(int i=0 ; i<product.size();i++){
            if(product.get(i).pro_id == pro_id){
                product.remove(product.get(i));
                System.out.println("The product is removed");
                count = 1;
            }
        }

        if(count == 0){
            System.out.println("The product is not found");
        }

        writeToTextFile();

    }

    public void generate(){

        System.out.print("Enter Product ID : ");
        Scanner sc = new Scanner(System.in);
        int pro_id = sc.nextInt();
        float sum = 0;
        for(int i=0 ; i<product.size();i++){
            sum = sum + product.get(i).pro_price * product.get(i).pro_quantity;
        }

        System.out.println("Product Total Amount"+ " " + sum);

    }

    public void purchase(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Product ID : ");
        int pro_id = sc.nextInt();
        System.out.print("Enter Product Quantity : ");
        int pro_quantity = sc.nextInt();

        int count = 0;
        for(int i=0 ; i<product.size();i++){
            if(product.get(i).pro_id == pro_id){
                if(product.get(i).pro_quantity >= pro_quantity) {
                    product.get(i).pro_quantity = product.get(i).pro_quantity - pro_quantity;
                    count = 1;
                    System.out.println("The product is purchased");
                }
                else{
                    System.out.println("The product have limited quantity");
                    return;
                }

            }
        }

        if(count == 0){
            System.out.println("The product is not found");
        }

        writeToTextFile();
    }



    public static void main(String args[]){

    Inventory i = new Inventory();

    Scanner sc = new Scanner(System.in);


    System.out.println("1.ADD  2.VIEW  3.REMOVE  4.GENERATE  5.PURCHASE  6.EXIT ");
    System.out.print("Enter the case : ");

    int c = sc.nextInt();

    while(c >= 0 && c <=5) {

        switch (c) {
            case 1:
                i.add();
                break;
            case 2:
                i.view();
                break;
            case 3:
                i.remove();
                break;
            case 4:
                i.generate();
                break;
            case 5:
                i.purchase();
                break;
            default:
                System.out.println("exit");
                break;

        }
        System.out.println("1.ADD  2.VIEW  3.REMOVE  4.GENERATE  5.PURCHASE  6.EXIT ");

        System.out.print("Enter the case : ");
        c = sc.nextInt();
    }

    }

}

class Product {

    int pro_id;
    String pro_name;
    float pro_price;
    int pro_quantity;

    Product(int pro_id, String pro_name,float pro_price, int pro_quantity){
        this.pro_id = pro_id;
        this.pro_name = pro_name;
        this.pro_price = pro_price;
        this.pro_quantity = pro_quantity;
    }


}