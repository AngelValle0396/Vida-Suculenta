package com.example.vida_suculenta.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.vida_suculenta.R;
import com.example.vida_suculenta.model.Imagen;
import com.example.vida_suculenta.subactivity.DetalleProducto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {

    private Context mContext ;
    private ArrayList<Imagen> mData ;
    private AdapterView.OnItemClickListener itemClickListener;

    public ImageAdapter (Context context, ArrayList<Imagen> lista){
        mContext= context;
        mData= lista;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View item = inflater.inflate(R.layout.image_row_item,parent , false);
        //View v = LayoutInflater.from(mcontext).inflate(R.layout.ly_itemsempleo,parent,false);
        return new MyViewHolder(item);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.titulo.setText(mData.get(position).getNombre());
        String s="";
        s=mData.get(position).getNombre();
       /* Glide.with(this.mContext)
                .load(mData.get(position).getUrlimagen())
                //.error(R.drawable.imgnotfound)
                .into(holder.imageView);*/
       s=mData.get(position).getUrlimagen();
        Picasso.with(mContext)
                .load(mData.get(position).getUrlimagen())
                .into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, DetalleProducto.class);

                // passing data to the book activity
                intent.putExtra("Nombre",mData.get(position).getNombre());
                intent.putExtra("Descripcion",mData.get(position).getDescripcion());
                intent.putExtra("Url",mData.get(position).getUrlimagen());
                // start the activity
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titulo, descripcion, precio;
        ImageView imageView ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo =  itemView.findViewById(R.id.title);
            imageView = (ImageView)itemView.findViewById(R.id.image);

        }
    }

/*
    public ImageAdapter(Context mContext, List<Imagen> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.image_row_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.titulo.setText(mData.get(position).getNombre());
        holder.descripcion.setText(mData.get(position).getDescripcion());
        holder.descripcion.setText(mData.get(position).getPrecio());
        Glide.with(this.mContext)
                .load(mData.get(position).getUrlimagen())
                //.error(R.drawable.imgnotfound)
                .into(imageView);



        imageView.setTag(getItem(position).getUrl());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,Book_Activity.class);

                // passing data to the book activity
                intent.putExtra("Title",mData.get(position).getTitle());
                intent.putExtra("Description",mData.get(position).getDescription());
                intent.putExtra("Thumbnail",mData.get(position).getThumbnail());
                // start the activity
                mContext.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titulo, descripcion, precio;
        ImageView imageView ;


        public MyViewHolder(View itemView) {
            super(itemView);

            titulo = (TextView) view.findViewById(R.i) ;

            imageView = (ImageView)itemView.findViewById(R.id.);
        }
    }
*/
}
