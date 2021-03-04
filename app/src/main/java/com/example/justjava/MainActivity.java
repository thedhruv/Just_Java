package com.example.justjava;



import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity=0;
    int price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        String priceMessage ="Total: " + NumberFormat.getCurrencyInstance(Locale.US).format(quantity*price) + "\nThank you!";
        displayMessage(priceMessage);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    private void displayPrice(int number){
        TextView priceTextView=findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance(Locale.US).format(number));

    }
    public void increment(View view){
        quantity +=1;
        display(quantity);
        displayPrice(quantity*price);
    }
    public void decrement(View view){
        if (quantity>=1){
            quantity-=1;
        }
        display(quantity);
        displayPrice(quantity*price);
    }
    public void displayMessage(String message){
        TextView priceTextView = findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
}