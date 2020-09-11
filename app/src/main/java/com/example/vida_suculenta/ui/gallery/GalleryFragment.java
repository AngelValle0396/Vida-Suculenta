package com.example.vida_suculenta.ui.gallery;

import android.app.ProgressDialog;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vida_suculenta.R;
import com.example.vida_suculenta.WebServices.Asynchtask;
import com.example.vida_suculenta.adapter.ImageAdapter;
import com.example.vida_suculenta.model.Imagen;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GalleryFragment extends Fragment implements Asynchtask  {

    private GalleryViewModel galleryViewModel;
    ArrayList<Imagen> lstimagenes;
    private ProgressDialog progreso;
    private RequestQueue queue ;
    RecyclerView myrv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        myrv = (RecyclerView) root.findViewById(R.id.recyclerview);
        queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        myrv.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(),2));
        lstimagenes = new ArrayList<>();
        cargarImagenes();
        /*lstimagenes.add(new Imagen("https://i.ibb.co/FnWR4r0/terrario-colgante3.jpg","Terrario colgante","Description book","4"));
        lstimagenes.add(new Imagen("https://i.ibb.co/jV345cT/maceta-creativa1.jpg","Maceta creativa","Description book","4"));
        lstimagenes.add(new Imagen("https://i.ibb.co/9TmGPW5/pecera-media1.jpg","Terrario pecera","Description book","4"));
        lstimagenes.add(new Imagen("https://i.ibb.co/qyw5Jc0/terrario-bello1.jpg","Terrario bello","Description book","4"));
        ImageAdapter myAdapter = new ImageAdapter(getActivity().getApplicationContext(), lstimagenes);
        myrv.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(),2));
        myrv.setAdapter(myAdapter);*/
        //final TextView textView = root.findViewById(R.id.text_gallery);
        /*galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

   /* @Override
    public void processFinish(String result) throws JSONException {
        try {
            JSONArray jsonlista= new JSONArray(result);
            lstimagenes = Imagen.JsonObjectsBuild(jsonlista);
            ImageAdapter adapator = new ImageAdapter(getActivity().getApplicationContext(), lstimagenes);
            myrv.setAdapter(adapator);

        }catch (JSONException e)
        {
            Toast.makeText(getActivity().getApplicationContext() ,e.getMessage(),Toast.LENGTH_LONG);
        }
    }*/
    private void cargarImagenes(){
        String urllg="https://vidasuculenta.000webhostapp.com/ws/consulta.php";

        JSONObject obj = new JSONObject();
        //obj.put("id", "1");
        //obj.put("name", "myname");
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST,urllg,obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        queue.add(jsObjRequest);


    }

    @Override
    public void processFinish(String result) throws JSONException {

    }
}