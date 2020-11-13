package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        boolean hasChocolate= chocolateCheckBox.isChecked();

        EditText text = (EditText) findViewById(R.id.name_field);
        String name = text.getText().toString();

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(name, price, hasWhippedCream, hasChocolate);
        displayMessage(priceMessage);
    }

    public void increment(View view) {
        if(quantity==100)
            return;
        quantity++;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if(quantity==1)
            return;
        quantity--;
        displayQuantity(quantity);
    }

    @SuppressLint("StringFormatMatches")
    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = getString(R.string.order_summary_name, name);
        priceMessage+= "\n" + getString(R.string.order_summary_whipped_cream, addWhippedCream);
        priceMessage+= "\n" + getString(R.string.order_summary_chocolate, addChocolate);
        priceMessage+= "\n" + getString(R.string.order_summary_quantity, quantity);
        priceMessage+= "\n" + getString(R.string.order_summary_price, NumberFormat.getCurrencyInstance().format(price));
        priceMessage+= "\n" + getString(R.string.thank_you);
        return priceMessage;
    }

    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int basePrice = 5;
        if(addWhippedCream)
            basePrice = basePrice+1;
        if(addChocolate)
            basePrice = basePrice+2;
        return basePrice*quantity;
    }

    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}