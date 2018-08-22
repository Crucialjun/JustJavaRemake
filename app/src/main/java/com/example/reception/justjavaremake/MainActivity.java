package com.example.reception.justjavaremake;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String orderSummary = createOrderSummary(price, hasWhippedCream, hasChocolate);
        Intent mailOrder = new Intent(Intent.ACTION_SENDTO);
        mailOrder.setData(Uri.parse("mailto:"));
        mailOrder.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order for " + getUserName());
        mailOrder.putExtra(Intent.EXTRA_TEXT, orderSummary);


        if (mailOrder.resolveActivity(getPackageManager()) != null) {
            startActivity(mailOrder);
        }


    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
            display(quantity);
        } else {
            Toast overOrder = Toast.makeText(getApplicationContext(), "You Cannot Order more than 100 " +
                    "cups of coffee", Toast.LENGTH_SHORT);
            overOrder.show();
        }

    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            display(quantity);
        } else {
            Toast overOrder = Toast.makeText(getApplicationContext(), "You Cannot Order less than 1 " +
                    "cup of coffee", Toast.LENGTH_SHORT);
            overOrder.show();
        }

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    private int calculatePrice(boolean whippedCream, boolean chocolate) {
        int price = 5;

        if (whippedCream) {
            price = price + 1;
        }
        if (chocolate) {
            price = price + 2;
        }
        return quantity * price;
    }


    private String createOrderSummary(int price, boolean addWhippCream, boolean addChocolate) {
        String summary = "Name: " + getUserName();
        summary += "\nAdd whipped cream? " + addWhippCream;
        summary += "\nAdd chocolate? " + addChocolate;
        summary += "\nTotal: Ksh" + price;
        summary += "\nThank you!";
        return summary;
    }

    public String getUserName() {
        EditText userName = findViewById(R.id.userName);
        return userName.getText().toString();
    }
}
