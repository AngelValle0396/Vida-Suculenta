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
<<<<<<< Updated upstream
import com.example.vida_suculenta.model.Pedido;
import com.example.vida_suculenta.subactivity.DetalleProducto;
=======
import com.example.vida_suculenta.subactivity.DetalleProducto;
import com.example.vida_suculenta.ui.home.HomeFragment;
>>>>>>> Stashed changes
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.MyViewHolder> {

    private Context mContext ;
<<<<<<< Updated upstream
    private ArrayList<Pedido> mData ;
    private AdapterView.OnItemClickListener itemClickListener;

    public PedidoAdapter(Context context, ArrayList<Pedido> lista){
=======
    private ArrayList<Imagen> mData ;
    private AdapterView.OnItemClickListener itemClickListener;

    public PedidoAdapter(Context context, ArrayList<Imagen> lista){
>>>>>>> Stashed changes
        mContext= context;
        mData= lista;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
<<<<<<< Updated upstream
        View item = inflater.inflate(R.layout.pedido_row_item,parent , false);
=======
        View item = inflater.inflate(R.layout.pedidos_row_item,parent , false);
>>>>>>> Stashed changes
        //View v = LayoutInflater.from(mcontext).inflate(R.layout.ly_itemsempleo,parent,false);
        return new MyViewHolder(item);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
<<<<<<< Updated upstream
        holder.producto.setText(mData.get(position).getNombre_producto());
        holder.fecha.setText(mData.get(position).getFecha());
        holder.precio.setText(mData.get(position).getPrecio());
        holder.estado.setText(mData.get(position).getEstado());

=======
        holder.titulo.setText(mData.get(position).getNombre());
        String s="";
        s=mData.get(position).getNombre();
>>>>>>> Stashed changes
       /* Glide.with(this.mContext)
                .load(mData.get(position).getUrlimagen())
                //.error(R.drawable.imgnotfound)
                .into(holder.imageView);*/
<<<<<<< Updated upstream

        Picasso.with(mContext)
                .load(mData.get(position).getUrl())
                .into(holder.imageView);

=======
       s=mData.get(position).getUrlimagen();
        Picasso.with(mContext)
                .load(mData.get(position).getUrlimagen())
                .into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, HomeFragment.class);
                // passing data to the book activity
                intent.putExtra("Nombre",mData.get(position).getNombre());
                intent.putExtra("Descripcion",mData.get(position).getDescripcion());
                intent.putExtra("Url",mData.get(position).getUrlimagen());
                // start the activity
                mContext.startActivity(intent);
            }
        });
>>>>>>> Stashed changes
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
<<<<<<< Updated upstream
        TextView producto, fecha, precio, estado;
        ImageView imageView ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            producto =  itemView.findViewById(R.id.nombreP);
            fecha =  itemView.findViewById(R.id.fechaP);
            precio =  itemView.findViewById(R.id.precioP);
            estado =  itemView.findViewById(R.id.estadoP);
            imageView = (ImageView)itemView.findViewById(R.id.imagenp);
=======
        TextView titulo, descripcion, precio;
        ImageView imageView ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo =  itemView.findViewById(R.id.title);
            imageView = (ImageView)itemView.findViewById(R.id.image);
>>>>>>> Stashed changes

        }
    }

<<<<<<< Updated upstream
=======
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
>>>>>>> Stashed changes
}
