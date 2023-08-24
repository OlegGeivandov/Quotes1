package com.example.quotes1;

import com.example.quotes1.model.Model;
import com.example.quotes1.model.TickersQuotesSimple;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }




    Model model= new Model();
    TickersQuotesSimple tickersSimple;
    String urlFirst = "https://iss.moex.com/iss/engines/stock/markets/shares/securities/";
    String url_Day_Interval_2009= "/candles.csv?from=2009-01-01&till=2010-12-31&interval=24";
    String url_Day_Interval_2011= "/candles.json?from=2011-01-01&till=2012-12-31&interval=24";



    public void initialize() throws IOException {
        model.readFromWebListOfString(urlFirst+TickersQuotesSimple.SBER.toString()+url_Day_Interval_2009);
    }


}