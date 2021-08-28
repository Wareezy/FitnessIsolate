package com.example.fitnessisolate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchInventory extends AppCompatActivity {

    DatabaseHelper myDb;
    SQLiteDatabase sqLiteDatabase;
    private TextView txtEquipmentName, txtEquipmentBrand, txtEquipmentSupplier, txtEquipmentQuantity, txtEquipmentPrice,txtEquipEntered;
    EditText edtEquipmentName;
    String search_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_inventory);

        Button btnMain1 = findViewById(R.id.btnMainActivity2);
        Button btnSearch = findViewById(R.id.btnSearch);
        edtEquipmentName = (EditText) findViewById(R.id.txtEquipId);
        txtEquipmentName = findViewById(R.id.txtName);
        txtEquipmentQuantity = findViewById(R.id.txtQuantity);
        txtEquipmentPrice = findViewById(R.id.txtPrice);
        txtEquipmentBrand = findViewById(R.id.txtBrand);
        txtEquipmentSupplier = findViewById(R.id.txtSupplier);
        btnMain1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchInventory.this, MainActivity.class);
                startActivity(intent);


                myDb = new DatabaseHelper(SearchInventory.this);




            }
            });




        }
    public void Search_Id(View view) {
        search_id = edtEquipmentName.getText().toString();
        myDb = new DatabaseHelper(getApplicationContext());
        sqLiteDatabase = myDb.getReadableDatabase();
        Cursor cursor = myDb.searchData(search_id, sqLiteDatabase);
        if (cursor.moveToFirst()) {

            String EquipName = cursor.getString(1);
            String EquipQuan = cursor.getString(2);
            String EquipPrice = cursor.getString(3);
            String EquipBrand = cursor.getString(4);
            String EquipSupplier = cursor.getString(5);



            Toast.makeText(SearchInventory.this, "Equipment Found!", Toast.LENGTH_SHORT).show();
            txtEquipmentName.setText("Equipment Name: "+EquipName);
            txtEquipmentQuantity.setText("Equipment Quantity: "+EquipQuan);
            txtEquipmentPrice.setText("Equipment Price: "+EquipPrice);
            txtEquipmentBrand.setText("Equipment rand: "+EquipBrand);
            txtEquipmentSupplier.setText("Equipment Supplier: "+EquipSupplier);




        } else {
            Toast.makeText(SearchInventory.this, "Could not find Equipment", Toast.LENGTH_SHORT).show();
            txtEquipmentName.setText("Equipment Name: ");
            txtEquipmentQuantity.setText("Equipment Quantity: ");
            txtEquipmentPrice.setText("Equipment Price: ");
            txtEquipmentBrand.setText("Equipment rand: ");
            txtEquipmentSupplier.setText("Equipment Supplier: ");


        }
    }}

