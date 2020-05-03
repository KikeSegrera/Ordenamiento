package com.ejercicio.ordenamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etNumeros;
    Button btAgregar, btOrdenar, btVaciar;
    TextView tvOrden;
    ArrayList<Integer> numeros = new ArrayList<Integer>();
    int i = 0, num;
    String textoOrden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumeros = findViewById(R.id.etNumeros);
        tvOrden = findViewById(R.id.tvOrden);
        btAgregar = findViewById(R.id.btAgregar);
        btOrdenar = findViewById(R.id.btOrdenar);
        btVaciar = findViewById(R.id.btVaciar);

        btAgregar.setOnClickListener(this);
        btOrdenar.setOnClickListener(this);
        btVaciar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btAgregar:
                if (etNumeros.getText().length() == 0) {
                    etNumeros.requestFocus();
                    etNumeros.setError(getResources().getString(R.string.add_error));
                } else {
                    num = Integer.parseInt(etNumeros.getText().toString());
                    numeros.add(num);
                    etNumeros.setText("");

                    textoOrden = tvOrden.getText().toString();
                    textoOrden = textoOrden + " " + num;
                    tvOrden.setText(textoOrden);
                }
                break;
            case R.id.btOrdenar:
                if(numeros.isEmpty()) {
                    //Log.d("DEPURACION", "Error");
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.empty_error),Toast.LENGTH_LONG).show();
                } else {

                    //Método de ordenamiento burbuja
                    int n = numeros.size();
                    for (int i = 0; i < n - 1; i++)
                        for(int j = 0; j < (n - i) - 1; j++)
                            if (numeros.get(j) > numeros.get(j + 1)){
                                int aux = numeros.get(j);
                                numeros.set(j, numeros.get(j+1));
                                numeros.set(j + 1, aux);
                            }

                    textoOrden = String.valueOf(numeros.get(0));
                    for (int i = 1; i < numeros.size(); i++)
                        textoOrden = textoOrden + " " + numeros.get(i);
                    tvOrden.setText(textoOrden);
                }
                break;
            case R.id.btVaciar:
                if(!numeros.isEmpty()) {
                    numeros.clear();
                    tvOrden.setText("");
                }
                break;
        }
    }
}
