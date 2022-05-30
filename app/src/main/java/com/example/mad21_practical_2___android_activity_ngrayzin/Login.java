package com.example.mad21_practical_2___android_activity_ngrayzin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://mad-prac6-rayzin-default-rtdb.asia-southeast1.firebasedatabase.app");
        mAuth = FirebaseAuth.getInstance();
        Button login = findViewById(R.id.button5);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edittxtusername = findViewById(R.id.editTextTextPersonName);
                EditText edittxtpw = findViewById(R.id.editTextTextPassword);

                String username = edittxtusername.getText().toString();
                String password = edittxtpw.getText().toString();
                DatabaseReference myRef = database.getReference("Users/" + username);

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Map<String, Object> map = (HashMap<String, Object>) snapshot.getValue();

                        String correctPassword = "";
                        try {
                            correctPassword = map.get("password").toString();
                        } catch (Exception e) {
                            Toast.makeText(Login.this, "USERNAME NOT FOUND", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (correctPassword.equals(password)) {
                            Intent intent = new Intent(Login.this, ListActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(Login.this, "INCORRECT PASSWORD", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.w("TAG", "Failed to read value.", error.toException());
                    }
                });

            }
        });

    }

}
