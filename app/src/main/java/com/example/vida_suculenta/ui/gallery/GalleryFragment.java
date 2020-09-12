package com.example.vida_suculenta.ui.gallery;

import android.app.ProgressDialog;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
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
        String json="{\n" +
                "Producto: [\n" +
                "{\n" +
                "ID_CATALO_PRO: \"1\",\n" +
                "ID_PRODUCTO: \"11\",\n" +
                "ID_CATALOGO: \"1\",\n" +
                "NOMBRE_PRODUCTO: \"Maceta creativa\",\n" +
                "PRECIO: \"8\",\n" +
                "DESCRIPCION: \"Maceta creativa mediana\",\n" +
                "IMAGEN_REF: \"https://vidasuculenta.000webhostapp.com/images/maceta_creativa1.jpg\",\n" +
                "DISPONIBLE: \"1\"\n" +
                "},\n" +
                "{\n" +
                "ID_CATALO_PRO: \"2\",\n" +
                "ID_PRODUCTO: \"12\",\n" +
                "ID_CATALOGO: \"1\",\n" +
                "NOMBRE_PRODUCTO: \"Terrario pecera\",\n" +
                "PRECIO: \"25\",\n" +
                "DESCRIPCION: \"Terrario Pecera tipo copa mediana, colores, plantas, mensajes totalmente. personalizables\",\n" +
                "IMAGEN_REF: \"https://vidasuculenta.000webhostapp.com/images/pecera_copa1.jpg\",\n" +
                "DISPONIBLE: \"1\"\n" +
                "},\n" +
                "{\n" +
                "ID_CATALO_PRO: \"3\",\n" +
                "ID_PRODUCTO: \"13\",\n" +
                "ID_CATALOGO: \"1\",\n" +
                "NOMBRE_PRODUCTO: \"Terrario pecera\",\n" +
                "PRECIO: \"20\",\n" +
                "DESCRIPCION: \"Peceras mediana con accesorios miniaturas, colores, plantas, mensajes, accesorios, personajes totalmente personalizables.\",\n" +
                "IMAGEN_REF: \"https://vidasuculenta.000webhostapp.com/images/pecera_media1.jpg\",\n" +
                "DISPONIBLE: \"1\"\n" +
                "},\n" +
                "{\n" +
                "ID_CATALO_PRO: \"4\",\n" +
                "ID_PRODUCTO: \"14\",\n" +
                "ID_CATALOGO: \"1\",\n" +
                "NOMBRE_PRODUCTO: \"Terrario pecera\",\n" +
                "PRECIO: \"8\",\n" +
                "DESCRIPCION: \"Peceras pequeñas, colores, plantas, mensajes totalmente personalizables.\",\n" +
                "IMAGEN_REF: \"https://vidasuculenta.000webhostapp.com/images/pecera_pequeña1.jpg\",\n" +
                "DISPONIBLE: \"1\"\n" +
                "},\n" +
                "{\n" +
                "ID_CATALO_PRO: \"5\",\n" +
                "ID_PRODUCTO: \"15\",\n" +
                "ID_CATALOGO: \"1\",\n" +
                "NOMBRE_PRODUCTO: \"Terrario bello\",\n" +
                "PRECIO: \"30\",\n" +
                "DESCRIPCION: \"Terrarios con accesorios, colores, plantas, mensajes totalmente personalizables.\",\n" +
                "IMAGEN_REF: \"https://vidasuculenta.000webhostapp.com/images/terrario_bello1.jpg\",\n" +
                "DISPONIBLE: \"1\"\n" +
                "},\n" +
                "{\n" +
                "ID_CATALO_PRO: \"6\",\n" +
                "ID_PRODUCTO: \"16\",\n" +
                "ID_CATALOGO: \"1\",\n" +
                "NOMBRE_PRODUCTO: \"Terrario bello\",\n" +
                "PRECIO: \"30\",\n" +
                "DESCRIPCION: \"Terrarios con accesorios, colores, plantas, mensajes totalmente personalizables.\",\n" +
                "IMAGEN_REF: \"https://vidasuculenta.000webhostapp.com/images/terrario_bello2.jpg\",\n" +
                "DISPONIBLE: \"1\"\n" +
                "},\n" +
                "{\n" +
                "ID_CATALO_PRO: \"7\",\n" +
                "ID_PRODUCTO: \"17\",\n" +
                "ID_CATALOGO: \"1\",\n" +
                "NOMBRE_PRODUCTO: \"Terrario colgante\",\n" +
                "PRECIO: \"30\",\n" +
                "DESCRIPCION: \"Terrario colgante, colores, plantas, mensajes, accesorios, personaje, base y colores totalmente personalizables.\",\n" +
                "IMAGEN_REF: \"https://vidasuculenta.000webhostapp.com/images/terrario_colgante1.jpg\",\n" +
                "DISPONIBLE: \"1\"\n" +
                "},\n" +
                "{\n" +
                "ID_CATALO_PRO: \"8\",\n" +
                "ID_PRODUCTO: \"18\",\n" +
                "ID_CATALOGO: \"1\",\n" +
                "NOMBRE_PRODUCTO: \"Terrario pecera\",\n" +
                "PRECIO: \"35\",\n" +
                "DESCRIPCION: \"Terrario pecera grande, colores, plantas, mensajes, accesorios, personaje, piedras arenas totalmente personalizables.\",\n" +
                "IMAGEN_REF: \"https://vidasuculenta.000webhostapp.com/images/terrario_pecera_grande1.jpg\",\n" +
                "DISPONIBLE: \"1\"\n" +
                "}\n" +
                "]\n" +
                "}";
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray arrayObject = (JSONArray) obj.get("Producto");

            String v="";
            for (int i=0;i<arrayObject.length();i++){
                JSONObject object2 = (JSONObject) arrayObject.get(i);
                Imagen img=new Imagen();
                img.setNombre(object2.getString("NOMBRE_PRODUCTO"));
                img.setDescripcion(object2.getString("PRECIO"));
                img.setPrecio(object2.getString("DESCRIPCION"));
                img.setUrlimagen(object2.getString("IMAGEN_REF"));
                //img.setUrlimagen("https://i.ibb.co/FnWR4r0/terrario-colgante3.jpg");
                lstimagenes.add(img);
            }
            ImageAdapter imageAdapter = new ImageAdapter(getActivity().getApplicationContext(), lstimagenes);
            myrv.setAdapter(imageAdapter);

        }catch (JSONException e)
        {
            Toast.makeText(getActivity().getApplicationContext() ,e.getMessage(),Toast.LENGTH_LONG);
        }
        }


    @Override
    public void processFinish(String result) throws JSONException {

    }
}