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
import com.example.vida_suculenta.model.Pedido;
import com.example.vida_suculenta.subactivity.DetalleProducto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.MyViewHolder> {

    private Context mContext ;
    private ArrayList<Pedido> mData ;
    private AdapterView.OnItemClickListener itemClickListener;

    public PedidoAdapter(Context context, ArrayList<Pedido> lista){
        mContext= context;
        mData= lista;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View item = inflater.inflate(R.layout.pedido_row_item,parent , false);
        //View v = LayoutInflater.from(mcontext).inflate(R.layout.ly_itemsempleo,parent,false);
        return new MyViewHolder(item);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.producto.setText(mData.get(position).getNombre_producto());
        holder.fecha.setText(mData.get(position).getFecha());
        holder.precio.setText(mData.get(position).getPrecio());
        holder.estado.setText(mData.get(position).getEstado());

       /* Glide.with(this.mContext)
                .load(mData.get(position).getUrlimagen())
                //.error(R.drawable.imgnotfound)
                .into(holder.imageView);*/

        Picasso.with(mContext)
                .load(mData.get(position).getUrl())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView producto, fecha, precio, estado;
        ImageView imageView ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            producto =  itemView.findViewById(R.id.nombreP);
            fecha =  itemView.findViewById(R.id.fechaP);
            precio =  itemView.findViewById(R.id.precioP);
            estado =  itemView.findViewById(R.id.estadoP);
            imageView = (ImageView)itemView.findViewById(R.id.imagenp);

        }
    }

}
