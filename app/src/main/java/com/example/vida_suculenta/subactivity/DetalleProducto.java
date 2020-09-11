package com.example.vida_suculenta.subactivity;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.vida_suculenta.R;
import com.squareup.picasso.Picasso;

public class DetalleProducto extends Fragment {

    private DetalleProductoViewModel mViewModel;
    private TextView nombre,description,url;
    private ImageView img;

    public static DetalleProducto newInstance() {
        return new DetalleProducto();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.detalle_producto_fragment, container, false);
        nombre = (TextView) root.findViewById(R.id.titulo);
        description = (TextView) root.findViewById(R.id.descrip);

        img = (ImageView) root.findViewById(R.id.imagede);

        Intent intent = getActivity().getIntent();
        String Title = intent.getExtras().getString("Nombre");
        String Description = intent.getExtras().getString("Descripcion");
        String image = intent.getExtras().getString("Url") ;

        // Setting values

        nombre.setText(Title);
        description.setText(Description);
        /*Glide.with(this)
                .load(image)
                //.error(R.drawable.imgnotfound)
                .into(img);*/
        Picasso.with(getActivity().getApplicationContext())
                .load(image)
                .into(img);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DetalleProductoViewModel.class);
        // TODO: Use the ViewModel
    }

}