import java.sql.*;
import java.util.Scanner;

class Bank {
    private static final String url = "jdbc:mysql://localhost:3306/database?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
    private static final String user = "root";
    private static final String password = "Vladik3333";
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    private String num, login;
    private long balance;
    private Scanner input =new Scanner(System.in);

    //acc registration
    void openAccount() {
        System.out.print("Enter user ID : ");
        num=input.next();
        System.out.print("Enter login : ");
        login = input.next();
        System.out.print("Enter account balance : ");
        balance = input.nextLong();

            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                String sql = "INSERT INTO users (login, balance) Values (?, ?)";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, login);
                preparedStatement.setLong(2, balance);
                int newAcc = preparedStatement.executeUpdate();
                System.out.println(newAcc + " new account added. User ID is :" + num);
                } catch (SQLException e) {
                System.out.println("Connection error ...");
                e.printStackTrace();
         }
    }

    //show acc
    void showAccount() {
        System.out.println(" Account balance is " + balance + " UAH");

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM users WHERE (login =?)");
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
            } } catch (Exception e) {
            System.err.println("Connection error ...");
            System.err.println(e.getMessage()); }
    }

    //deposit money
    void deposit() {
        long deposit;
        System.out.println("Enter amount to deposit : ");
        deposit=input.nextLong();
        balance=balance+deposit;

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "UPDATE users SET balance =? WHERE login =?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, balance);
            preparedStatement.setString(2, login);
            int newBal = preparedStatement.executeUpdate();
            System.out.println("Account balance is " + balance + " UAH");
        } catch (SQLException e) {
            System.out.println("Connection error ...");
            e.printStackTrace();
        }
    }

    //withdrawal money
    void withdrawal()
    {
        long amt;
        System.out.println("Enter amount you want to withdraw : ");
        amt=input.nextLong();
        if(balance>=amt) {
            balance=balance-amt; }
        else {
            System.out.println("Less balance.Transaction failed."); }

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "UPDATE users SET balance =? WHERE login =?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, balance);
            preparedStatement.setString(2, login);
            int newBal = preparedStatement.executeUpdate();
            System.out.println("Account balance is " + balance + " UAH");
        } catch (SQLException e) {
            System.out.println("Connection error ...");
            e.printStackTrace(); }
    }

    //search acc
    boolean search(String acn)
    {
        if(login.equals(acn)) {
            showAccount();
            return(true); }
        return(false);
    }
}