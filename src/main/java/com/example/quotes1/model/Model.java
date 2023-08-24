package com.example.quotes1.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

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