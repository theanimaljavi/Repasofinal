package com.javigu.repasofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //arraylist con las provincias del json
    ArrayList<Provincias> provincias_json;
    Provincias provincias;
    //recyclerview
    RecyclerView rv;
    ImageButton ivProvincia;
    Button btnAtras,btnSiguiente;
    TextView tvNombre;
    String url;
    int posicion;
    //sacar contexto
    private static MainActivity mApp = null;
    //objeto de la clase fragment descipcion
    FragmentDescripcion fgDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //aplicar contexto
        mApp = this;
        //referencias objetos xml
        tvNombre = findViewById(R.id.tvNombre);
        ivProvincia = findViewById(R.id.ivProvincias);
        btnAtras = findViewById(R.id.btnAtras);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        fgDesc = new FragmentDescripcion();

        //referencia al recyclarfview
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        /* ocultar el rycyclerview
        //inciar el recyclerview
        rv.setAdapter(new AdaptadorProvincias(provincias_json));
         */
        //cargar json provincias
        provincias = new Provincias();
        provincias_json = provincias.llenarProvinciasJSON();
        //cargar imagen y titulo
        tvNombre.setText(provincias_json.get(0).getNombre());
        url = provincias_json.get(0).getImagen();
        Picasso.with(this).load(url).into(ivProvincia);
        posicion=0;

        //acciones on los botones
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cargar imagen y titulo anterior, si posicion es 0, se pondrá la última del array (JSON)
                if (posicion == 0){
                    posicion = provincias_json.size()-1;
                }else{
                    posicion--;
                }

                tvNombre.setText(provincias_json.get(posicion).getNombre());
                url = provincias_json.get(posicion).getImagen();
                Picasso.with(v.getContext()).load(url).into(ivProvincia);
            }
        });

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cargar imagen y titulo anterior, si posicion es 0, se pondrá la última del array (JSON)
                if (posicion == provincias_json.size()-1){
                    posicion = 0;
                }else{
                    posicion++;
                }

                tvNombre.setText(provincias_json.get(posicion).getNombre());
                url = provincias_json.get(posicion).getImagen();
                Picasso.with(v.getContext()).load(url).into(ivProvincia);
            }
        });

        //pulsar en la imagen nos lleva al activity de maps
        ivProvincia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String coords = provincias_json.get(posicion).getCoordenadas();
                Intent i = new Intent(v.getContext(), MapsProvincia.class);
                i.putExtra("maps_provincia", coords);
                startActivity(i);
            }
        });

        //TextvIew del titulo tambien se puede pulsar, abrirá un fragmento con la imagen y la descripcion
        tvNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = provincias_json.get(posicion).getId();
                getSupportFragmentManager().beginTransaction().replace(R.id.contendorDescripcion, fgDesc.newInstance(id)).commit();
            }
        });

    }
    //devuelve contexto
    public static Context context()
    {
        return mApp.getApplicationContext();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflador del menú
        MenuInflater infladorMenu = getMenuInflater();
        infladorMenu.inflate(R.menu.menu, menu);
        //Asociar el menu al menu_busqueda.xml
        return super.onCreateOptionsMenu(menu);
    }
/*
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        switch (item.getItemId()){
            //ELEGIRJUEGO
            case R.id.extras:
                Intent intent = new Intent (this, ElegirJuego.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.salir:
                builder = new AlertDialog.Builder(this);
                builder.setTitle("¿Desea salir de la aplicación?");

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                //mostrar alert dialog
                builder.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

 */


}