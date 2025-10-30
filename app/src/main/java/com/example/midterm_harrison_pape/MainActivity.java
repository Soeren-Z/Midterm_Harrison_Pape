package com.example.midterm_harrison_pape;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText inputNum;
    private Button submit;
    private ListView multiplicationList;
    private Button history;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        inputNum = findViewById(R.id.inputNum);
        submit = findViewById(R.id.submit);
        multiplicationList = findViewById(R.id.multiplicationList);
        history = findViewById(R.id.history);
        list = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        multiplicationList.setAdapter(adapter);

        multiplicationList.setOnItemClickListener((parent, view, position, id) -> {
            String item = list.get(position);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder
                    .setTitle("Delete Row")
                    .setMessage("Do you want to delete this row?" + item)
                    .setNegativeButton("Cancel", null)
                    .setPositiveButton("Okay", (dialog, w) -> {
                        list.remove(position);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(this, "Deleted row: " + item, Toast.LENGTH_SHORT).show();
                    })
                    .show();
        });
    }
    public void GenerateList(View view) {
        String input = inputNum.getText().toString().trim();
        if(input.isEmpty()) {
            Toast.makeText(this, "Enter a number", Toast.LENGTH_SHORT).show();
            return;
        }
        int num = Integer.parseInt(input);
        list.clear();
        for(int i = 1; i < 11; i++) {
            list.add(num + " x " + i + " = " + (num * i));
        }
        adapter.notifyDataSetChanged();
    }
}