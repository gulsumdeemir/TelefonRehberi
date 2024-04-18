package com.gulsum.telefonrehberimvize;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UpdateActivity extends AppCompatActivity {
    EditText name_input, surname_input, number_input;
    Button update_button, delete_button;
    String id, name, surname, number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name_input = findViewById(R.id.name_input2);
        surname_input = findViewById(R.id.surname_input2);
        number_input = findViewById(R.id.number_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }

        update_button.setOnClickListener(v -> {
            MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
            name = name_input.getText().toString().trim();
            surname = surname_input.getText().toString().trim();
            number = number_input.getText().toString().trim();
            myDB.updateData(id, name, surname, number);

        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });

    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
        getIntent().hasExtra("surname") && getIntent().hasExtra("number")){
            //verileri getirdim
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            surname = getIntent().getStringExtra("surname");
            number = getIntent().getStringExtra("number");

            //verileri oluşturdum
            name_input.setText(name);
            surname_input.setText(surname);
            number_input.setText(number);

        }else{
            Toast.makeText(this, "Veri Yok", Toast.LENGTH_SHORT).show();
        }

    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Silmek İstediğinizden Emin misiniz " + name + " ?");
        builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();

            }
        });
        builder.setNegativeButton("Hayir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}