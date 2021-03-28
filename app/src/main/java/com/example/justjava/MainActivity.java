package com.example.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 2;
    int pricePerCup = 5;
    int priceTopping1 = 1;
    int priceTopping2 = 2;
    boolean hasTopping1;
    boolean hasTopping2;
    TextView quantityTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quantityTextView = findViewById(R.id.quantity_text_view);

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        hasTopping1=((CheckBox) findViewById(R.id.topping1_checkbox)).isChecked();
        hasTopping2=((CheckBox) findViewById(R.id.topping2_checkbox)).isChecked();
        String message=createOrderSummary(calculatePrice(hasTopping1, hasTopping2), hasTopping1, hasTopping2);
        Intent sendEmail = new Intent(Intent.ACTION_SENDTO);
        sendEmail.setData(Uri.parse("mailto:"));
        sendEmail.putExtra(Intent.EXTRA_TEXT, message);
        EditText nameTextView = findViewById(R.id.name_field);
        String name = nameTextView.getText().toString();
        sendEmail.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order_summary_email_subject,name));
        if (sendEmail.resolveActivity(getPackageManager()) != null){
            startActivity(sendEmail);
        }
    }

    public int calculatePrice(boolean hasTopping1, boolean hasTopping2){
        int pricePerCup = this.pricePerCup;
        if (hasTopping1) {
            pricePerCup += this.priceTopping1;
        }
        if (hasTopping2){
            pricePerCup += this.priceTopping2;
        }
        return quantity * pricePerCup;
    }
    public String createOrderSummary(int price, boolean hasTopping1, boolean hasTopping2){
        String priceMessage = getString(R.string.order_summary_name, ((EditText) findViewById(R.id.name_field)).getText());
        Log.d("message1",priceMessage);
        priceMessage += "\n" + getString(R.string.order_summary_topping1, getString(R.string.topping1),hasTopping1);
        Log.d("message2",priceMessage);
        priceMessage += "\n" + getString(R.string.order_summary_topping2, getString(R.string.topping2),hasTopping2);
        Log.d("message3",priceMessage);
        priceMessage += "\n" + getString(R.string.order_summary_quantity,getString(R.string.quantity),quantity);
        Log.d("message4",priceMessage);
        priceMessage += "\n" + getString(R.string.order_summary_price,NumberFormat.getCurrencyInstance().format(quantity*price));
        Log.d("message5",priceMessage);
        priceMessage += "\n" + getString(R.string.thank_you);
        Log.d("message6",priceMessage);
        return priceMessage;

    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        quantityTextView.setText(String.format(Locale.US,"%1$d",number));
    }

    public void increment(View view){
        if (quantity == 100){
            Toast.makeText(this,"You cannot order more than 100 coffee cups", Toast.LENGTH_LONG).show();
            return;
        }
        quantity += 1;
        displayQuantity(quantity);
    }
    public void decrement(View view){
        if (quantity == 1){
            Toast.makeText(this,"You cannot order less than 1 coffee cup", Toast.LENGTH_LONG).show();
            return;
        }
        quantity -= 1;
        displayQuantity(quantity);
    }
}