package com.example.mad21_practical_2___android_activity_ngrayzin;

import static android.util.Log.d;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.errorprone.annotations.Var;

import java.lang.reflect.TypeVariable;
import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<Viewholder>{
    ArrayList<User> data;
    DBhandler db;
    //private OnNoteListener monNoteListener;
    public adapter(ArrayList<User> data, DBhandler dBhandler){
        this.data = data;
        db = dBhandler;
    }
    @Override
    public int getItemViewType(int position) {
        //int pos = g
        return position;
    }
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View c = LayoutInflater.from(parent.getContext()).inflate(R.layout.profilelayers,null,false);
        return new Viewholder(c);

    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        User name = data.get(position);
        String n = name.name;
        holder.nameText.setText(n);
        User des = data.get(position);
        String d = des.description;
        holder.descText.setText(d);
        User follow = data.get(position);
        Boolean f = follow.followed;
        User id = data.get(position);
        Integer ID = id.id;
        if(!n.substring(n.length() - 1).equals("7")){
            d("name", n);
            holder.bigimage.setVisibility(View.GONE);
        }
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Profile");
                builder.setMessage(n);
                builder.setCancelable(true);
                builder.setPositiveButton(
                        "VIEW", new
                                DialogInterface.OnClickListener(){
                                    public void onClick(DialogInterface dialog, int id){
                                        Intent activityName = new Intent(view.getContext(),MainActivity.class);
                                        User john = db.findUser(n);
                                        activityName.putExtra("name", n);
                                        activityName.putExtra("des", john.description);
                                        activityName.putExtra("follow", john.followed);
                                        activityName.putExtra("id", john.id);
                                        view.getContext().startActivity(activityName);

                                    }
                                });
                builder.setNegativeButton("CLOSE", new
                        DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                dialog.cancel();

                            }
                        });
                AlertDialog alert11 = builder.create();
                alert11.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}




