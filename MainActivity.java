package com.example.fitnessisolate;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    private EditText edtEquipName, edtEquipQuantity, edtEquipBrand, edtEquipSupplier, edtEquipPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnReturn = findViewById(R.id.btnReturn);
        Button btnView = findViewById(R.id.btnView);
        Button btnUpdate = findViewById(R.id.btnUpdate);
        Button btnDelete = findViewById(R.id.btnDelete);
        Button btnSearch = findViewById(R.id.btnSearch);
        myDb = new DatabaseHelper(this);
        edtEquipName = (EditText) findViewById(R.id.inputEquipName);
        edtEquipQuantity = (EditText) findViewById(R.id.inputEquipQuantity);
        edtEquipBrand = (EditText) findViewById(R.id.inputEquipBrand);
        edtEquipSupplier = (EditText) findViewById(R.id.inputEquipSupplier);
        edtEquipPrice = (EditText) findViewById(R.id.inputEquipPrice);


        Button btnInsert = findViewById(R.id.btnInsert);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtEquipName.getText().toString().equals("") ||
                        edtEquipQuantity.getText().toString().equals("") ||
                        edtEquipPrice.getText().toString().equals("") ||
                        edtEquipBrand.getText().toString().equals("") ||
                        edtEquipSupplier.getText().toString().equals("")
                ) {
                    Toast.makeText(MainActivity.this, "Cannot update, there are empty fields!", Toast.LENGTH_LONG).show();

                } else
                checkRegister();

            }
        });
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewData();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtEquipName.getText().toString().equals("") ||
                        edtEquipQuantity.getText().toString().equals("") ||
                        edtEquipPrice.getText().toString().equals("") ||
                        edtEquipBrand.getText().toString().equals("") ||
                        edtEquipSupplier.getText().toString().equals("")
                ) {
                    Toast.makeText(MainActivity.this, "Cannot update, there are empty fields!", Toast.LENGTH_LONG).show();

                } else
                updateData();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtEquipName.getText().toString().equals("") ||
                        edtEquipQuantity.getText().toString().equals("") ||
                        edtEquipPrice.getText().toString().equals("") ||
                        edtEquipBrand.getText().toString().equals("") ||
                        edtEquipSupplier.getText().toString().equals("")
                ) {
                    Toast.makeText(MainActivity.this, "Cannot Delete, there are empty fields!", Toast.LENGTH_LONG).show();

                } else
                deleteData();
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,SearchInventory.class);
                startActivity(intent);
            }
        });
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,Login.class);
                startActivity(intent);
            }
        });
    }

    public void checkRegister() {
        if (edtEquipName.getText().toString().equals("") ||
                edtEquipQuantity.getText().toString().equals("") ||
                edtEquipPrice.getText().toString().equals("") ||
                edtEquipBrand.getText().toString().equals("") ||
                edtEquipSupplier.getText().toString().equals("")) {
            Toast.makeText(this, "Cannot insert, there are empty fields!", Toast.LENGTH_LONG).show();
        } else {
            int quantity = Integer.parseInt(edtEquipQuantity.getText().toString());
            int price = Integer.parseInt(edtEquipPrice.getText().toString());
            boolean isInserted = myDb.insertData(
                    edtEquipName.getText().toString(),
                    quantity,
                    price,
                    edtEquipBrand.getText().toString(),
                    edtEquipSupplier.getText().toString());
            if (isInserted == true) {
                Toast.makeText(this, "Registration Successful!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            } else {

                Toast.makeText(this, "Insert Unsuccessful", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void viewData() {

        Cursor res;
        res = myDb.getAllData();

        if (res.getCount() == 0) {
            displayData("Error", "There is no records of equipment!");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {

            buffer.append("Equipment Id:" + res.getString(0) + "\n");
            buffer.append("Equipment Name :" + res.getString(1) + "\n");
            buffer.append("Equipment Quantity :" + res.getString(2) + "\n");
            buffer.append("Equipment Price :" + res.getString(3) + "\n");
            buffer.append("Equipment Brand :" + res.getString(4) + "\n");
            buffer.append("Equipment Supplier :" + res.getString(5) + "\n");


        }

        displayData("Equipment Details", buffer.toString());
    }

    public void displayData(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void updateData() {
        int quantity = Integer.parseInt(edtEquipQuantity.getText().toString());
        int price = Integer.parseInt(edtEquipPrice.getText().toString());
        boolean isUpdated = myDb.updateAccounts(
                edtEquipName.getText().toString(),
                quantity,
                price, edtEquipBrand.getText().toString(), edtEquipSupplier.getText().toString());
        if (isUpdated == true) {
            Toast.makeText(MainActivity.this, "Account Updated!", Toast.LENGTH_LONG).show();

        } else
            Toast.makeText(MainActivity.this, "Account could not be updated!", Toast.LENGTH_LONG).show();


    }
    public void deleteData(){
        Integer deletedRows= myDb.deleteData(edtEquipName.getText().toString());
        if(deletedRows>0){
            Toast.makeText(MainActivity.this, "Equipment details deleted!", Toast.LENGTH_LONG).show();
        }else{
            displayData("Error", "The equipment does not exist or has already been deleted!");
        }

    }
}



