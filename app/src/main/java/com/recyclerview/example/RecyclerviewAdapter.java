package com.recyclerview.example;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.MyViewHolder> {
    ArrayList<Product> productList;
    private Context context;
    DbHelper dbHelper;

    public RecyclerviewAdapter(Context context, ArrayList<Product> product) {
        this.context = context;
        this.productList = product;
        dbHelper = new DbHelper(context);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView isFavorite, delete;
        private TextView productName, id;

        public MyViewHolder(View view) {

            super(view);

            productName = view.findViewById(R.id.product_name);
            id = view.findViewById(R.id.id);
            isFavorite = view.findViewById(R.id.is_favorite);
            delete = view.findViewById(R.id.delete);
        }
    }

    @Override
    public RecyclerviewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);

        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final RecyclerviewAdapter.MyViewHolder holder, final int position) {

        holder.productName.setText(productList.get(position).getName());
        holder.id.setText(productList.get(position).getId() + "");
        if (productList.get(position).isFavorite()) {
            holder.isFavorite.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_favorite));
            holder.isFavorite.setColorFilter(ContextCompat.getColor(context, R.color.heart), android.graphics.PorterDuff.Mode.SRC_IN);
        } else {
            holder.isFavorite.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_empty_heart));
            holder.isFavorite.setColorFilter(ContextCompat.getColor(context, R.color.heart), android.graphics.PorterDuff.Mode.SRC_IN);
        }

        holder.isFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (productList.get(position).isFavorite()) {
                    holder.isFavorite.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_favorite));
                    holder.isFavorite.setColorFilter(ContextCompat.getColor(context, R.color.heart), android.graphics.PorterDuff.Mode.SRC_IN);
                    productList.get(position).setFavorite(false);
                    dbHelper.updateFavorite(productList.get(position).getId(), 0);
                } else {
                    holder.isFavorite.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_empty_heart));
                    holder.isFavorite.setColorFilter(ContextCompat.getColor(context, R.color.primary_text), android.graphics.PorterDuff.Mode.SRC_IN);
                    productList.get(position).setFavorite(true);
                    dbHelper.updateFavorite(productList.get(position).getId(), 1);
                }
                notifyDataSetChanged();
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deletProduct(productList.get(position).getId());
                productList.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context, "The item deleted", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();

    }
}
