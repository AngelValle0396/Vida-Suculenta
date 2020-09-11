package com.example.vida_suculenta.ui.gallery;

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
import androidx.recyclerview.widget.RecyclerView;

import com.example.vida_suculenta.R;
import com.example.vida_suculenta.adapter.ImageAdapter;
import com.example.vida_suculenta.model.Imagen;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    ArrayList<Imagen> lstBook ;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        RecyclerView myrv = (RecyclerView) root.findViewById(R.id.recyclerview);
        lstBook = new ArrayList<>();
        lstBook.add(new Imagen("https://i.ibb.co/FnWR4r0/terrario-colgante3.jpg","Terrario colgante","Description book","4"));
        lstBook.add(new Imagen("https://i.ibb.co/jV345cT/maceta-creativa1.jpg","Maceta creativa","Description book","4"));
        lstBook.add(new Imagen("https://i.ibb.co/9TmGPW5/pecera-media1.jpg","Terrario pecera","Description book","4"));
        lstBook.add(new Imagen("https://i.ibb.co/qyw5Jc0/terrario-bello1.jpg","Terrario bello","Description book","4"));
        ImageAdapter myAdapter = new ImageAdapter(getActivity().getApplicationContext(),lstBook);
        myrv.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(),2));
        myrv.setAdapter(myAdapter);
        //final TextView textView = root.findViewById(R.id.text_gallery);
        /*galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}