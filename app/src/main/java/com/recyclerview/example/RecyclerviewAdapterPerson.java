package com.recyclerview.example;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerviewAdapterPerson extends RecyclerView.Adapter<RecyclerviewAdapterPerson.MyViewHolder> {
    ArrayList<Person>  personArrayList;
    private Context context;
    DbHelper dbHelper;

    public RecyclerviewAdapterPerson(Context context, ArrayList<Person> list ) {
        this.context = context;
        this.personArrayList = list;
        dbHelper = new DbHelper(context);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView   delete;
        private TextView productName, id;

        public MyViewHolder(View view) {

            super(view);

            productName = view.findViewById(R.id.product_name);
            id = view.findViewById(R.id.id);

            delete = view.findViewById(R.id.delete);
        }
    }

    @Override
    public RecyclerviewAdapterPerson.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);

        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final RecyclerviewAdapterPerson.MyViewHolder holder, final int position) {

        holder.productName.setText( personArrayList.get(position).getName());
        holder.id.setText(personArrayList.get(position).getId() + "");



        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deletProduct(personArrayList.get(position).getId());
                personArrayList.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context, "The item deleted", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return personArrayList.size();

    }
}
