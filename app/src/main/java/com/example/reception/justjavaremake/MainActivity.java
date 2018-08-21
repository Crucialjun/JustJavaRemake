package com.example.reception.justjavaremake;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCream = findViewById(R.id.whippedCream);
        CheckBox chocolateCheckBox = findViewById(R.id.chocolate);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        boolean hasWhippedCream = whippedCream.isChecked();
        int price = calculatePrice();
        String orderSummary = createOrderSummary(price,hasWhippedCream,hasChocolate);
        displayMessage(orderSummary);

    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        quantity = quantity + 1;
        display(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        quantity = quantity - 1;
        display(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    private int calculatePrice() {
        return quantity * 5;
    }


    private String createOrderSummary(int price, boolean addWhippCream,boolean addChocolate) {
        String summary = "Name: " + getUserName();
        summary += "\nAdd whipped cream? " + addWhippCream;
        summary += "\nAdd chocolate? " + addChocolate;
        summary += "\nTotal: Ksh" + price;
        summary += "\nThank you!";
        return summary;
    }

    private String getUserName() {
        EditText userName = findViewById(R.id.userName);
        return userName.getText().toString();
    }
}
