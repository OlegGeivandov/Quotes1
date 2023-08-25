package com.example.quotes1;

import com.example.quotes1.model.Model;
import com.example.quotes1.model.TickersQuotesSimple;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.SQLException;

public class HelloController {
    @FXML
    private Label welcomeText;

    public HelloController() throws IOException, SQLException {
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }




    Model model= new Model();






    public void initialize() throws IOException, SQLException {




        }


//        model.readFromWebListOfString("https://iss.moex.com/iss/engines/stock/markets/shares/securities/SNGS/candles.json?from=2010-01-01&till=2010-01-13&interval=24");

//        for (String url : model.urlStringList) {
//            model.insertCandleToDB(url);
    //}





}