package com.example.quotes1.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Arrays;
//import com.microsoft.sqlserver.jdbc.SQLServerDriver;

public class Model {
    public ArrayList<String> listStringFromStockExchange = new ArrayList<>();
    ArrayList<Candle> listCandleFromStockExchange = new ArrayList<>();

    ArrayList<String> yearsBeginList = new ArrayList<>(Arrays.asList("2010", "2012", "2014", "2016", "2018", "2020", "2021"));
    ArrayList<String> yearsEndList = new ArrayList<>(Arrays.asList("2011", "2013", "2015", "2017", "2019", "2021", "2023"));
    ArrayList<String> insertTickersList = new ArrayList<>(Arrays.asList("GAZP", "MGNT", "SBER", "SNGS"));

    public ArrayList<String> urlStringList = new ArrayList<>();

    public Model() throws IOException, SQLException {
//        createUrlStringListAndReadFromWebListOfString();

        String s = "https://iss.moex.com/iss/engines/stock/markets/shares/securities/SNGS/candles.json?from=2010-01-01&till=2010-01-13&interval=24";
        readFromWebListOfString(s, "SNGS");

//        for (String str: listStringFromStockExchange) {
//            insertCandleToDB(str);
//        }
    }

    public void createUrlStringListAndReadFromWebListOfString() throws IOException {
        String urlFirst = "https://iss.moex.com/iss/engines/stock/markets/shares/securities/";
        //GAZP
        String urlSecond= "/candles.csv?from=";
        //2010;
        String urlThird="-01-01&till=";
        //2011
        String urlEnd= "-12-31&interval=24";

        for (int i = 0; i < insertTickersList.size(); i++) {
            for (int j = 0; j < yearsBeginList.size(); j++) {
                String s = urlFirst+insertTickersList.get(i)+urlSecond+yearsBeginList.get(j)+urlThird+yearsEndList.get(j)+urlEnd;
                urlStringList.add(s);
                readFromWebListOfString(s, insertTickersList.get(i));
            }
        }
    }




    public void readFromWebListOfString(String ur, String ticker) throws IOException {
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
//            list.add(line.replace("\"", "'").replace(",", "").replace("[","").replace("]",""));
            list.add(ticker + ", 24," + line.replace("[","").replace("]","").replace("\"", "\'"));
//            System.out.println(line);
        }
//        listStringFromStockExchange = new ArrayList<>(list.subList(3,list.lastIndexOf(list)));

        for (int i = 0; i < 14; i++) list.remove(0);
        for (int i = 0; i < 2; i++) list.remove(list.size()-1);
        listStringFromStockExchange.addAll(list);
        System.out.println(listStringFromStockExchange.get(2));

            reader.close();
//        System.out.println(listStringFromStockExchange.get(0));
//        System.out.println(listStringFromStockExchange.get(listStringFromStockExchange.size()-1));

    }

    public Connection connectToDB() {
//        String url = "jdbc:sqlserver://192.168.56.1;databaseName=QuotesBD_23082023;integratedSecurity=true;";
//        String url = "jdbc:sqlserver://localhost;databaseName=QuotesBD_23082023;integratedSecurity=true;";
//        String url = "jdbc:sqlserver://192.168.1.1;databaseName=QuotesBD_23082023;integratedSecurity=true;";
//        String url = "jdbc:sqlserver://localhost\\sqlexpress;databaseName=QuotesBD_23082023;integratedSecurity=true;";
//        String url = "jdbc:sqlserver://localhost\\\\sqlexpress;databaseName=QuotesBD_23082023;integratedSecurity=true;";
//        String url = "jdbc:sqlserver://192.168.56.1\\sqlexpress;databaseName=QuotesBD_23082023;integratedSecurity=true;";
//        String url = "jdbc:sqlserver://DESKTOP-2RQ0P4C\\SQLEXPRESS;databaseName=QuotesBD_23082023;integratedSecurity=true;";
//        String url = "jdbc:sqlserver://DESKTOP-2RQ0P4C\\SQLEXPRESS;databaseName=QuotesBD_23082023;";
//        String url = "jdbc:sqlserver://DESKTOP-2RQ0P4C\\SQLEXPRESS;databaseName=QuotesBD_23082023;";

        String url = "jdbc:sqlserver://10.10.104.163\\SQLEXPRESS;databaseName=StockExchange;trustServerCertificate=true; user=Student; password=123;";

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

//    public void connectToDB_Test() {
//        Connection conn = null;
//
//        try {
//
////            String url = "jdbc:sqlserver://localhost\\localhost;integratedSecurity=true;"; //Соединение с сервером localhost, именованным экземпляром localhost, завершилось ошибкой. Ошибка: "java.net.SocketTimeoutException: Receive timed out".
////            String url = "jdbc:sqlserver://192.168.56.1;databaseName=QuotesBD_23082023;integratedSecurity=true;"; //Не удалось установить соединение TCP/IP к серверу 192.168.56.1 по порту 1433. Ошибка: "Connect timed out. Проверьте свойства соединения. Убедитесь, что на сервере запущен экземпляр SQL Server и он принимает TCP/IP-соединения по порту.
////          String url = "jdbc:sqlserver://localhost;databaseName=QuotesBD_23082023;integratedSecurity=true;"; //Не удалось установить соединение TCP/IP к серверу localhost по порту 1433. Ошибка: "Connection refused: no further information. Проверьте свойства соединения. Убедитесь, что на сервере запущен экземпляр SQL Server и он принимает TCP/IP-соединения по порту.
////          String url = "jdbc:sqlserver://192.168.1.1;databaseName=QuotesBD_23082023;integratedSecurity=true;"; //Не удалось установить соединение TCP/IP к серверу 192.168.1.1 по порту 1433. Ошибка: "Connect timed out.
////             String url = "jdbc:sqlserver://localhost\\sqlexpress;databaseName=QuotesBD_23082023;integratedSecurity=true;"; //Соединение с сервером localhost, именованным экземпляром sqlexpress, завершилось ошибкой. Ошибка: "java.net.SocketTimeoutException: Receive timed out".
////        String url = "jdbc:sqlserver://localhost\\\\sqlexpress;databaseName=QuotesBD_23082023;integratedSecurity=true;"; //Соединение с сервером localhost, именованным экземпляром \sqlexpress, завершилось ошибкой. Ошибка: "java.net.SocketTimeoutException: Receive timed out".
////        String url = "jdbc:sqlserver://192.168.56.1\\sqlexpress;databaseName=QuotesBD_23082023;integratedSecurity=true;"; // Соединение с сервером 192.168.56.1, именованным экземпляром sqlexpress, завершилось ошибкой. Ошибка: "java.net.SocketTimeoutException: Receive timed out".
////        String url = "jdbc:sqlserver://DESKTOP-2RQ0P4C\\SQLEXPRESS;databaseName=QuotesBD_23082023;integratedSecurity=true;"; //Соединение с сервером DESKTOP-2RQ0P4C, именованным экземпляром sqlexpress, завершилось ошибкой. Ошибка: "java.net.SocketTimeoutException: Receive timed out".
////        String url = "jdbc:sqlserver://DESKTOP-2RQ0P4C\\SQLEXPRESS;databaseName=QuotesBD_23082023;"; //Соединение с сервером DESKTOP-2RQ0P4C, именованным экземпляром sqlexpress, завершилось ошибкой. Ошибка: "java.net.SocketTimeoutException: Receive timed out".
////            String url = "jdbc:sqlserver://DESKTOP-2RQ0P4C\\SQLEXPRESS;databaseName=QuotesBD_23082023;"; //Соединение с сервером DESKTOP-2RQ0P4C, именованным экземпляром sqlexpress, завершилось ошибкой. Ошибка: "java.net.SocketTimeoutException: Receive timed out".
////            String url = "jdbc:sqlserver://DESKTOP-2RQ0P4C\\SQLEXPRESS;initial catalog=QuotesBD_23082023;integratedSecurity=true;"; //Соединение с сервером DESKTOP-2RQ0P4C, именованным экземпляром sqlexpress, завершилось ошибкой. Ошибка: "java.net.SocketTimeoutException: Receive timed out".
////            String url = "jdbc:sqlserver://192.168.1.1\\SQLEXPRESS;initial catalog=QuotesBD_23082023;integratedSecurity=true;"; //Соединение с сервером 192.168.1.1, именованным экземпляром sqlexpress, завершилось ошибкой. Ошибка: "java.net.SocketTimeoutException: Receive timed out".
//
//
////            String url = "jdbc:sqlserver://10.10.104.163\\SQLEXPRESS;databaseName=StockExchange;integratedSecurity=true;trustServerCertificate=true; user=Student&password=abc123";
//            String url = "jdbc:sqlserver://10.10.104.163\\SQLEXPRESS;databaseName=StockExchange;trustServerCertificate=true; user=Student; password=123;";
//
//            conn = DriverManager.getConnection(url);
//            if (conn != null) {
//                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
//                System.out.println("Driver name: " + dm.getDriverName());
//                System.out.println("Driver version: " + dm.getDriverVersion());
//                System.out.println("Product name: " + dm.getDatabaseProductName());
//                System.out.println("Product version: " + dm.getDatabaseProductVersion());
//            }
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//            try {
//                if (conn != null && !conn.isClosed()) {
//                    conn.close();
//                }
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        }
//    }


    public void insertCandleToDB(String dataString) throws SQLException {
        String[] arr = dataString.split("\s");
//        String insertSql = "INSERT INTO QUOTESDAY (ticker, timing, [open], [close], [high], [low], [value], [volume], [begin], [end]) VALUES ('SBER', '24', 86.56, 86.69, 88.17, 85.51, 13024966177.74, 149200144, '2010-01-11 00:00:00', '2010-01-11 23:59:59');";
//        String insertSql = "INSERT INTO QUOTESDAY (ticker, timing, [open], [close], [high], [low], [value], [volume], [begin], [end]) VALUES ('" + SBER', '24', 86.56, 86.69, 88.17, 85.51, 13024966177.74, 149200144, '2010-01-11 00:00:00', '2010-01-11 23:59:59 +"');";
        Connection connection = connectToDB();
             Statement statement = connection.createStatement();
//        try{
////            statement.executeQuery(insertSql);
//        } catch(SQLException e){
//
//        }

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