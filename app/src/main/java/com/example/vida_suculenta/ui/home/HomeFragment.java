package com.example.vida_suculenta.ui.home;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.vida_suculenta.R;
import com.example.vida_suculenta.adapter.PedidoAdapter;
import com.example.vida_suculenta.model.Imagen;
import com.example.vida_suculenta.model.Pedido;
import com.example.vida_suculenta.ui.gallery.GalleryViewModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private GalleryViewModel galleryViewModel;
    ArrayList<Pedido> lstpedidos;

    RecyclerView myrv;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
       // final TextView textView = root.findViewById(R.id.text_home);
        myrv = (RecyclerView) root.findViewById(R.id.recyclerviewp);

        myrv.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(),2));
        lstpedidos = new ArrayList<>();
        /*homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        lstpedidos.add(new Pedido("Terrario bello","10/25/2020","Recibido","25","https://i.ibb.co/qyw5Jc0/terrario-bello1.jpg"));
        lstpedidos.add(new Pedido("Terrario pecera","09/14/2020","Recibido","30","https://i.ibb.co/9TmGPW5/pecera-media1.jpg"));
        lstpedidos.add(new Pedido("Terrario colgante","09/09/2020","Recibido","25","https://i.ibb.co/FnWR4r0/terrario-colgante3.jpg"));
        lstpedidos.add(new Pedido("Maceta creativa","09/01/2020","Recibido","8","https://i.ibb.co/jV345cT/maceta-creativa1.jpg"));
        PedidoAdapter myAdapter = new PedidoAdapter(getActivity().getApplicationContext(), lstpedidos);
        myrv.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        myrv.setAdapter(myAdapter);
        return root;
    }
}