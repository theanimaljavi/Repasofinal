package com.javigu.repasofinal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentDescripcion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDescripcion extends Fragment {
    TextView tvDesc;
    ImageView ivBandera;
    ImageButton ibSalir;
    ArrayList<Provincias> provincias;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "id";

    // TODO: Rename and change types of parameters
    private String id;

    public FragmentDescripcion() {
        // Required empty public constructor
    }

    /**
     * @return A new instance of fragment FragmentDescripcion.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentDescripcion newInstance(String id) {
        FragmentDescripcion fragment = new FragmentDescripcion();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.fragment_descripcion, container, false);
        //referenciar datos
        tvDesc = view.findViewById(R.id.tvFGDesc);
        ivBandera = view.findViewById(R.id.ivFGBandera);
        ibSalir = view.findViewById(R.id.btnFGSalir);
        //rellenar array con provincias
        Provincias pv = new Provincias();
        provincias = pv.llenarProvinciasJSON();
        //añadir la bandera y la descripcion correspondiente
        for (int i=1; i<=5; i++){
            if (i == Integer.parseInt(id)){
                int posicion = i-1;
                tvDesc.setText(provincias.get(posicion).getDescripcion());
                String url = provincias.get(posicion).getImagen();
                Picasso.with(getContext()).load(url).into(ivBandera);
            }
        }

        //boton salir
        ibSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.contendorDescripcion)).commit();
            }
        });
        return view;
    }
}