package com.example.vida_suculenta;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vida_suculenta.WebServices.Asynchtask;
import com.example.vida_suculenta.WebServices.WebService;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Profile extends AppCompatActivity /*implements Asynchtask */ {

    TextView name, mail;
    Button logout;
    private ProgressDialog progreso;
    private RequestQueue queue ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        logout = findViewById(R.id.logout);
        name = findViewById(R.id.name);
        mail = findViewById(R.id.mail);
        queue = Volley.newRequestQueue(this);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);

        if(signInAccount != null){
            name.setText(signInAccount.getDisplayName());
            mail.setText(signInAccount.getEmail());
            LgVolley(signInAccount.getEmail());
            //validarUsuario(signInAccount.getEmail());
        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
            }
        });

    }

    /*private void validarUsuario(String correo){
        progreso = new ProgressDialog(this);
        progreso.setMessage("Verificando...");
        progreso.show();
        Map<String, String> datos = new HashMap<String, String>();
        String s=correo;

        datos.put("correo",correo);
        WebService ws = new WebService("http://vida-suculenta.rf.gd/WebServices/consultarUsuario2.php",
                datos, Profile.this, Profile.this);
        ws.execute("POST");
    }*/
    private void LgVolley(String correo){
        String urllg="http://vida-suculenta.rf.gd/WebServices/consultarUsuario2.php?correo="+correo;
        String datos="";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urllg, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject obj = null;
                String resultado=response;
                try {
                    obj = new JSONObject(response);
                    JSONArray arrayObject = (JSONArray) obj.get("Producto");
                    for (int i=0;i<arrayObject.length();i++){
                        JSONObject object2 = (JSONObject) arrayObject.get(i);
                        resultado=object2.getString("ESTADO");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (resultado.equals("1")){
                    //Intent intent = new Intent(getApplicationContext(),Profile.class);
                    //startActivity(intent);
                    Toast.makeText(Profile.this, "Ingreso correcto", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(Profile.this, "Usuario no registrado", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(Profile.this, "Error"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }

/*
    @Override
    public void processFinish(String result) throws JSONException {

        JSONObject obj = new JSONObject(result);

        JSONArray arrayObject = (JSONArray) obj.get("Producto");
        String resultado="";
        for (int i=0;i<arrayObject.length();i++){
            JSONObject object2 = (JSONObject) arrayObject.get(i);
            resultado=object2.getString("ESTADO");
        }
        if (resultado.equals("1")){
            //Intent intent = new Intent(getApplicationContext(),Profile.class);
            //startActivity(intent);
            Toast.makeText(Profile.this, "Ingreso correcto", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(Profile.this, "Usuario no registrado", Toast.LENGTH_SHORT).show();
        }
    }*/
}