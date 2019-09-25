package com.example.internetdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AdaptadorLibros extends RecyclerView.Adapter<ItemHolder> {
    Context context;
    List<Item> datos;
    LayoutInflater inflater;

    public AdaptadorLibros(Context context, List<Item> datos) {
        this.context = context;
        this.datos = datos;
        inflater=LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_recycler,parent,false);

        return new ItemHolder(v,this);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemHolder holder, final int position) {
        holder.txt_itemrecycle_title.setText(datos.get(position).title+" - "+datos.get(position).publishedDate);
        holder.txt_itemrecycle_pages.setText("Pag: "+datos.get(position).pageCount);
        holder.txt_itemrecycle_subtitle.setText(datos.get(position).subtitle);
        holder.txt_itemrecycle_autor.setText("Autor: ");
        for (String autor : datos.get(position).Autor) {
            holder.txt_itemrecycle_autor.setText(holder.txt_itemrecycle_autor.getText().toString()+", "+autor);
        }
        //
        if(datos.get(position).saleability){
            holder.btn_itemrecycle_buy.setVisibility(View.VISIBLE);
            holder.btn_itemrecycle_buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,datos.get(position).buyLink,Toast.LENGTH_LONG).show();
                }
            });
        }
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                datos.remove(position);
                holder.adaptador.notifyDataSetChanged();
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return datos.size();
    }
}
