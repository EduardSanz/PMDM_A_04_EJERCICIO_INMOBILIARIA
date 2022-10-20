package com.cieep.a04_ejercicioinmobiliaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cieep.a04_ejercicioinmobiliaria.configs.Constantes;
import com.cieep.a04_ejercicioinmobiliaria.databinding.ActivityEditInmuebleBinding;
import com.cieep.a04_ejercicioinmobiliaria.modelos.Inmueble;

public class EditInmuebleActivity extends AppCompatActivity {

    private ActivityEditInmuebleBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditInmuebleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Todas las activities tienen un Intent que las crea
        Intent intentDelMain = getIntent();
        Bundle bundle = intentDelMain.getExtras();
        Inmueble inmueble = (Inmueble) bundle.getSerializable(Constantes.INMUEBLE);

        binding.txtCiudadEditInmueble.setText(inmueble.getCiudad());
        binding.txtNumeroEditInmueble.setText(String.valueOf(inmueble.getNumero()));
        binding.txtCPEditInmueble.setText(inmueble.getCp());
        binding.txtProvinciaEditInmueble.setText(inmueble.getProvincia());
        binding.txtCalleEditInumueble.setText(inmueble.getCalle());
        binding.rbValoracionEditInmueble.setRating(inmueble.getValoracion());

        binding.btnCrearEditInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Inmueble inmuebleUpdated = crearInmueble();
                if (inmuebleUpdated != null) {
                    Intent intent = new Intent();
                    Bundle bundle1 = new Bundle();
                    bundle1.putSerializable(Constantes.INMUEBLE, inmuebleUpdated);
                    int posicion = bundle.getInt(Constantes.POSICION);
                    bundle1.putInt(Constantes.POSICION, posicion);
                    intent.putExtras(bundle1);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });


        Log.d("INMU", inmueble.toString());
    }

    private Inmueble crearInmueble() {
        if (binding.txtCalleEditInumueble.getText().toString().isEmpty() ||
                binding.txtCiudadEditInmueble.getText().toString().isEmpty() ||
                binding.txtCPEditInmueble.getText().toString().isEmpty() ||
                binding.txtNumeroEditInmueble.getText().toString().isEmpty() ||
                binding.txtProvinciaEditInmueble.getText().toString().isEmpty() )
            return null;
        return new Inmueble(
                binding.txtCalleEditInumueble.getText().toString(),
                Integer.parseInt(binding.txtNumeroEditInmueble.getText().toString()),
                binding.txtCPEditInmueble.getText().toString(),
                binding.txtCiudadEditInmueble.getText().toString(),
                binding.txtProvinciaEditInmueble.getText().toString(),
                binding.rbValoracionEditInmueble.getRating()
        );
    }
}