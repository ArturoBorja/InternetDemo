package com.example.internetdemo;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ItemHolder extends RecyclerView.ViewHolder {
    public CardView cardView;
    public TextView txt_itemrecycle_title;
    public TextView txt_itemrecycle_subtitle;
    public TextView txt_itemrecycle_pages;
    public TextView txt_itemrecycle_autor;
    public Button btn_itemrecycle_buy;
    AdaptadorLibros adaptador;

    public ItemHolder(@NonNull final View itemView, final AdaptadorLibros adaptador) {
        super(itemView);
        this.adaptador = adaptador;
        cardView=itemView.findViewById(R.id.card_view);
        txt_itemrecycle_title=itemView.findViewById(R.id.txt_itemrecycle_title);
        txt_itemrecycle_subtitle=itemView.findViewById(R.id.txt_itemrecycle_subtitle);
        txt_itemrecycle_autor=itemView.findViewById(R.id.txt_itemrecycle_autor);
        txt_itemrecycle_pages=itemView.findViewById(R.id.txt_itemrecycle_pages);
        btn_itemrecycle_buy=itemView.findViewById(R.id.btn_itemrecycle_buy);

    }
}