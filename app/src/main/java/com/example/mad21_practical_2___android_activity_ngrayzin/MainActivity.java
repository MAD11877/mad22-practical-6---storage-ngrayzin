package com.example.mad21_practical_2___android_activity_ngrayzin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DBhandler db = new DBhandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent mIntent = getIntent();
        String Value = mIntent.getStringExtra("name");
        String Value2 = mIntent.getStringExtra("des");
        Boolean follow = getIntent().getExtras().getBoolean("follow");
        Integer id = getIntent().getIntExtra("ID",0);
        TextView tv1 = (TextView)findViewById(R.id.textView);
        TextView tv2 = (TextView)findViewById(R.id.textView2);
        tv1.setText(Value);
        tv2.setText(Value2);
        User user = new User(Value,Value2,id,follow);
        /*ArrayList<User> list = db.getUsers();
        for(int i = 0; i < list.size(); i++){
            if(user.name == list[i].name)
        }*/
        Button button = (Button)findViewById(R.id.button2);
        button.setText(user.followed ? "UNFOLLOW": "FOLLOW");
        followfunction(user);

    }

    public void followfunction(User user)
    {
        Button button = (Button)findViewById(R.id.button2);
        OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View view) {
                user.followed = !user.followed;
                db.updateUser(user);
                Log.d("TAG", user.name);
                Log.d("testing", user.followed.toString());
                button.setText(user.followed ? "UNFOLLOW" : "FOLLOW");
                Toast.makeText(MainActivity.this, user.followed ? "Followed" : "Unfollowed", Toast.LENGTH_SHORT).show();

            }
        };
        button.setOnClickListener(listener);
    }

    public void messagefunction(View view)
    {
        Intent activityName = new Intent(MainActivity.this,MessageGroup.class);
        startActivity(activityName);
    }


}