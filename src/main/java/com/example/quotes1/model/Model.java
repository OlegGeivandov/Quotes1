package com.example.quotes1.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
//import com.microsoft.sqlserver.jdbc.SQLServerDriver;

public class Model {
//    ArrayList<String> listStringFromStockExchange = new ArrayList<>();
    ArrayList<String> listStringFromStockExchange;
    ArrayList<Candle> listCandleFromStockExchange = new ArrayList<>();


    public void readFromWebListOfString(String ur) throws IOException {
//        listStringFromStockExchange.clear();
        ArrayList<String> list = new ArrayList<>();
        // Адрес веб-страницы, которую мы хотим получить
        URL url = new URL(ur);

        // Создаем объект BufferedReader для чтения данных с сайта
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream(), "UTF-8"));
        while (true) {
            String line = reader.readLine();
            if (line == null)
                break;
            list.add(line);
//            System.out.println(line);

        }
//        listStringFromStockExchange = new ArrayList<>(list.subList(3,list.lastIndexOf(list)));
        listStringFromStockExchange = new ArrayList<>(list);
        for (int i = 0; i < 3; i++) listStringFromStockExchange.remove(0);
        for (int i = 0; i < 2; i++) listStringFromStockExchange.remove(listStringFromStockExchange.size()-1);

            reader.close();
        System.out.println(listStringFromStockExchange.get(0));
        System.out.println(listStringFromStockExchange.get(listStringFromStockExchange.size()-1));

    }

    private Connection connectToDB() {
//        String url = "jdbc:sqlserver://192.168.56.1;databaseName=QuotesBD_23082023;integratedSecurity=true;";
//        String url = "jdbc:sqlserver://localhost;databaseName=QuotesBD_23082023;integratedSecurity=true;";
//        String url = "jdbc:sqlserver://192.168.1.1;databaseName=QuotesBD_23082023;integratedSecurity=true;";
//        String url = "jdbc:sqlserver://localhost\\sqlexpress;databaseName=QuotesBD_23082023;integratedSecurity=true;";
//        String url = "jdbc:sqlserver://localhost\\\\sqlexpress;databaseName=QuotesBD_23082023;integratedSecurity=true;";
//        String url = "jdbc:sqlserver://192.168.56.1\\sqlexpress;databaseName=QuotesBD_23082023;integratedSecurity=true;";
//        String url = "jdbc:sqlserver://DESKTOP-2RQ0P4C\\SQLEXPRESS;databaseName=QuotesBD_23082023;integratedSecurity=true;";
//        String url = "jdbc:sqlserver://DESKTOP-2RQ0P4C\\SQLEXPRESS;databaseName=QuotesBD_23082023;";
        String url = "jdbc:sqlserver://DESKTOP-2RQ0P4C\\SQLEXPRESS;databaseName=QuotesBD_23082023;";

        //"jdbc:sqlserver://localhost\\sqlexpress";


        try {
            Connection conn = DriverManager.getConnection(url);
            System.out.println("подключено");
            return conn;
        } catch (Exception e) {
            System.out.println("не удалось подключиться к базе. " + e.getMessage());
            return null;
        }
    }

    public void connectToDB_Test() {
        Connection conn = null;

        try {

//            String url = "jdbc:sqlserver://localhost\\localhost;integratedSecurity=true;"; //Соединение с сервером localhost, именованным экземпляром localhost, завершилось ошибкой. Ошибка: "java.net.SocketTimeoutException: Receive timed out".
//            String url = "jdbc:sqlserver://192.168.56.1;databaseName=QuotesBD_23082023;integratedSecurity=true;"; //Не удалось установить соединение TCP/IP к серверу 192.168.56.1 по порту 1433. Ошибка: "Connect timed out. Проверьте свойства соединения. Убедитесь, что на сервере запущен экземпляр SQL Server и он принимает TCP/IP-соединения по порту.
//          String url = "jdbc:sqlserver://localhost;databaseName=QuotesBD_23082023;integratedSecurity=true;"; //Не удалось установить соединение TCP/IP к серверу localhost по порту 1433. Ошибка: "Connection refused: no further information. Проверьте свойства соединения. Убедитесь, что на сервере запущен экземпляр SQL Server и он принимает TCP/IP-соединения по порту.
//          String url = "jdbc:sqlserver://192.168.1.1;databaseName=QuotesBD_23082023;integratedSecurity=true;"; //Не удалось установить соединение TCP/IP к серверу 192.168.1.1 по порту 1433. Ошибка: "Connect timed out.
//             String url = "jdbc:sqlserver://localhost\\sqlexpress;databaseName=QuotesBD_23082023;integratedSecurity=true;"; //Соединение с сервером localhost, именованным экземпляром sqlexpress, завершилось ошибкой. Ошибка: "java.net.SocketTimeoutException: Receive timed out".
//        String url = "jdbc:sqlserver://localhost\\\\sqlexpress;databaseName=QuotesBD_23082023;integratedSecurity=true;"; //Соединение с сервером localhost, именованным экземпляром \sqlexpress, завершилось ошибкой. Ошибка: "java.net.SocketTimeoutException: Receive timed out".
//        String url = "jdbc:sqlserver://192.168.56.1\\sqlexpress;databaseName=QuotesBD_23082023;integratedSecurity=true;"; // Соединение с сервером 192.168.56.1, именованным экземпляром sqlexpress, завершилось ошибкой. Ошибка: "java.net.SocketTimeoutException: Receive timed out".
//        String url = "jdbc:sqlserver://DESKTOP-2RQ0P4C\\SQLEXPRESS;databaseName=QuotesBD_23082023;integratedSecurity=true;"; //Соединение с сервером DESKTOP-2RQ0P4C, именованным экземпляром sqlexpress, завершилось ошибкой. Ошибка: "java.net.SocketTimeoutException: Receive timed out".
//        String url = "jdbc:sqlserver://DESKTOP-2RQ0P4C\\SQLEXPRESS;databaseName=QuotesBD_23082023;"; //Соединение с сервером DESKTOP-2RQ0P4C, именованным экземпляром sqlexpress, завершилось ошибкой. Ошибка: "java.net.SocketTimeoutException: Receive timed out".
//            String url = "jdbc:sqlserver://DESKTOP-2RQ0P4C\\SQLEXPRESS;databaseName=QuotesBD_23082023;"; //Соединение с сервером DESKTOP-2RQ0P4C, именованным экземпляром sqlexpress, завершилось ошибкой. Ошибка: "java.net.SocketTimeoutException: Receive timed out".
//            String url = "jdbc:sqlserver://DESKTOP-2RQ0P4C\\SQLEXPRESS;initial catalog=QuotesBD_23082023;integratedSecurity=true;"; //Соединение с сервером DESKTOP-2RQ0P4C, именованным экземпляром sqlexpress, завершилось ошибкой. Ошибка: "java.net.SocketTimeoutException: Receive timed out".
            String url = "jdbc:sqlserver://192.168.1.1\\SQLEXPRESS;initial catalog=QuotesBD_23082023;integratedSecurity=true;"; //Соединение с сервером 192.168.1.1, именованным экземпляром sqlexpress, завершилось ошибкой. Ошибка: "java.net.SocketTimeoutException: Receive timed out".

            //192.168.1.1
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


    public void insertCandleToDB() throws SQLException {
        String insertSql = "INSERT INTO QuotesBD_23082023.QUOTESDAY (ticker, timing, open, close, high, low, value, volume, begin, end) VALUES "
                + "('SBER', '24', 86.56, 86.69, 88.17, 85.51, 13024966177.74, 149200144, 2010-01-11 00:00:00, 2010-01-11 23:59:59);";

        ResultSet resultSet = null;

        try (Connection connection = connectToDB();
             PreparedStatement prepsInsertProduct = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);) {

            prepsInsertProduct.execute();
            // Retrieve the generated key from the insert.
            resultSet = prepsInsertProduct.getGeneratedKeys();

            // Print the ID of the inserted row.
            while (resultSet.next()) {
                System.out.println("Generated: " + resultSet.getString(1));
            }
        }
        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
    }






}

//    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream(), "UTF-8"));
//        while (true) {
//                String line = reader.readLine();
//                if (line == null)
//                break;
//                System.out.println(line);
//                }


//                String line;
//                while ((line = reader.readLine()) != null) {
//                listStringFromStockExchange.add(line);
//                System.out.println(line);
//                }
//
//                } finally {
//                reader.close();
//                }