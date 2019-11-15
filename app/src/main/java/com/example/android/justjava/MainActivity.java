package com.example.android.justjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int Quantity = 2;
    boolean hasWhippedCream = false;
    boolean hasChoco = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {


        CheckBox checkBoxWC = findViewById(R.id.Check_Box_WC);
        if (checkBoxWC.isChecked()) {
            hasWhippedCream = true;
        } else hasWhippedCream = false;

        CheckBox checkboxChoco = findViewById(R.id.Check_Box_Choco);
        if (checkboxChoco.isChecked()) {
            hasChoco = true;
        }


        int price = calculatePrice(Quantity, 5);

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(price));
        sendIntent.setType("text/plain");
        sendIntent.setPackage("com.whatsapp");
        startActivity(sendIntent);


    }


    /**
     * Calculates the price of the order.
     *
     * @param Quantity    is the number of cups of coffee ordered
     * @param pricePerCup is the price per cup
     */
    private int calculatePrice(int Quantity, int pricePerCup) {
        if (hasWhippedCream) {
            pricePerCup = pricePerCup + 1;
        }
        if (hasChoco) {
            pricePerCup = pricePerCup + 2;
        }
        return Quantity * pricePerCup;

    }

    /* Creates an order summary
       @param price = price of the order

     */

    private String createOrderSummary(int price) {

        return getString(R.string.nameofperson) + nameField() + "\n" + getString(R.string.addWC) + hasWhippedCream + "\n" + getString(R.string.addCH) + hasChoco + "\n" + getString(R.string.quant2)+ Quantity + "\n" + getString(R.string.total) + price + "\n" + getString(R.string.thanks);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int quant) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + quant);
    }


    /**
     * This method displays the given text on the screen.
     */


    public void increment(View view) {
        if (Quantity == 30) {
            Toast.makeText(this, getString(R.string.retard), Toast.LENGTH_SHORT).show();
        }
        if (Quantity < 30) {
            Quantity = Quantity + 1;
            displayQuantity(Quantity);
        }

    }


    public void decrement(View view) {
        if (Quantity == 1) {
            Toast.makeText(this, getString(R.string.giveus), Toast.LENGTH_SHORT).show();
        }

        if (Quantity > 1) {
            Quantity = Quantity - 1;
            displayQuantity(Quantity);
        }

    }

    public String nameField() {
        EditText nameFieldText = findViewById(R.id.name_edit_view);
        String name = nameFieldText.getText().toString();
        return name;
    }


}