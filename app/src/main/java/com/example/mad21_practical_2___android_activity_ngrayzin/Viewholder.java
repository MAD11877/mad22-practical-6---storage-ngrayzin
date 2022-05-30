package com.example.mad21_practical_2___android_activity_ngrayzin;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
public class Viewholder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/{
    TextView nameText;
    TextView descText;
    ImageView image;
    ImageView bigimage;
    //adapter.OnNoteListener onNoteListener;
    public int getItemViewType(int position) {return position;}
    public Viewholder(View view){
        super(view);
        nameText = view.findViewById(R.id.textView3);
        descText = view.findViewById(R.id.textView4);
        image = (ImageView) view.findViewById(R.id.imageView2);
        bigimage = (ImageView) view.findViewById(R.id.imageView3);
        //image.setOnClickListener(this);
        //this.onNoteListener = onNoteListner;
        //view.setOnClickListener(this);

    }

    /*@Override
    public void onClick(View view) {
        onNoteListener.onNoteClick(getAdapterPosition());
    }*/
}


