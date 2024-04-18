package com.gulsum.telefonrehberimvize;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    Activity activity;
    private ArrayList person_id, name, surname, number;

    CustomAdapter(Activity activity, Context context, ArrayList person_id, ArrayList name, ArrayList surname, ArrayList number){
        this.activity = activity;
        this.context = context;
        this.person_id = person_id;
        this.name = name;
        this.surname = surname;
        this.number = number;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,final int position) {

        holder.person_id_txt.setText(String.valueOf(person_id.get(position)));
        holder.name_txt.setText(String.valueOf(name.get(position)));
        holder.surname_txt.setText(String.valueOf(surname.get(position)));
        holder.number_txt.setText(String.valueOf(number.get(position)));
        holder.mainLayout.setOnClickListener(v -> {
            Intent intent = new Intent(context, UpdateActivity.class);
            intent.putExtra("id", String.valueOf(person_id.get(position)));
            intent.putExtra("name", String.valueOf(name.get(position)));
            intent.putExtra("surname", String.valueOf(surname.get(position)));
            intent.putExtra("number", String.valueOf(number.get(position)));
            activity.startActivityForResult(intent,1);

        });
    }

    @Override
    public int getItemCount() {
        return person_id.size();
    }
     class MyViewHolder extends RecyclerView.ViewHolder {
        TextView person_id_txt, name_txt, surname_txt, number_txt;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            person_id_txt = itemView.findViewById(R.id.person_id_txt);
            name_txt = itemView.findViewById(R.id.name_txt);
            surname_txt = itemView.findViewById(R.id.surname_txt);
            number_txt = itemView.findViewById(R.id.number_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
