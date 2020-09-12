package com.example.vida_suculenta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;


import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vida_suculenta.Profile;
import com.example.vida_suculenta.WebServices.WebService;
import com.example.vida_suculenta.WebServices.Asynchtask;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity implements Asynchtask {
    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN = 123;
    private FirebaseAuth mAuth;
    private ProgressDialog progreso;
    GoogleSignInAccount account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        createRequest();

        findViewById(R.id.google_signIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }
    private void createRequest() {


        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("812450020583-gr3oig4ss6hon3j5kai92nck1jnu8p9c.apps.googleusercontent.com")
                .requestEmail()
                .build();


        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


    }

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
    private void signIn() {

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                  account = task.getResult(ApiException.class);
                  firebaseAuthWithGoogle(account);
                  //validarUsuario();

            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                // ...
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void validarUsuario(){
        /*progreso = new ProgressDialog(this);
        progreso.setMessage("Verificando...");
        progreso.show();*/
        Map<String, String> datos = new HashMap<String, String>();
        String s="";
        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        s=account.getEmail();
        datos.put("correo",account.getEmail());
        WebService ws = new WebService("http://vida-suculenta.rf.gd/WebServices/consultarUsuario2.php",
                datos, Login.this, Login.this);
        ws.execute("POST");
    }
    Boolean bol=false;

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            TextView head= findViewById(R.id.nombreUs);
                            head.setText(account.getDisplayName());
                            TextView subhead=findViewById(R.id.correoUS);
                            subhead.setText(account.getEmail());
                            Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                            startActivity(intent);
                            bol=true;
                        } else {
                            Toast.makeText(Login.this, "Sorryauth failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user!=null){
            Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
            startActivity(intent);
        }
    }

    @Override
    public void processFinish(String result) throws JSONException {
        JSONObject obj = new JSONObject(result);
        List<String> lista= new ArrayList<>();
        JSONArray arrayObject = (JSONArray) obj.get("Producto");
        String resultado="";
        for (int i=0;i<arrayObject.length();i++){
            JSONObject object2 = (JSONObject) arrayObject.get(i);
            resultado=object2.getString("ESTADO");
        }
        if (resultado.equals("1")){
            Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
            startActivity(intent);
        }else
        {
            Toast.makeText(Login.this, "Usuario no registrado", Toast.LENGTH_SHORT).show();
        }

    }
}