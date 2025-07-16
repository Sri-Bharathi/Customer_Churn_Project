import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connection conn = DBConnection.connect();

        if (conn == null) {
            System.out.println("Unable to connect to database.");
            return;
        }

        while (true) {
            System.out.println("\n=== Customer Risk Checker ===");
            System.out.println("1. View All Customer Summary");
            System.out.println("2. Get Customer by ID");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewAll(conn);
                    break;
                case 2:
                    System.out.print("Enter Customer ID: ");
                    int id = sc.nextInt();
                    getCustomerById(conn, id);
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void viewAll(Connection conn) {
        try {
            String query = "SELECT * FROM customer_summary";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("\nID | Name | Total Spent | Last Txn | Risk");
            while (rs.next()) {
                System.out.printf("%d | %s | %.2f | %s | %s%n",
                        rs.getInt("customer_id"),
                        rs.getString("name"),
                        rs.getDouble("total_spent"),
                        rs.getString("last_transaction"),
                        rs.getString("churn_risk"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void getCustomerById(Connection conn, int id) {
        try {
            String query = "SELECT * FROM customer_summary WHERE customer_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("\nCustomer Details:");
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Total Spent: " + rs.getDouble("total_spent"));
                System.out.println("Last Transaction: " + rs.getString("last_transaction"));
                System.out.println("Churn Risk: " + rs.getString("churn_risk"));
            } else {
                System.out.println("No customer found with ID: " + id);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
