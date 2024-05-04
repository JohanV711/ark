package com.example.miapp;

import android.os.Bundle;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class profile extends AppCompatActivity {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Inicializar la lista de encabezados y datos de los hijos
        prepareListData();

        // Configurar el adaptador para la lista expandible
        expandableListView = findViewById(R.id.expandableListView);
        expandableListAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        expandableListView.setAdapter(expandableListAdapter);
    }

    /*
     * Preparar los datos para la lista expandible
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        // Agregar encabezados de grupos
        listDataHeader.add("Enfermedades");
        listDataHeader.add("Vacunas");
        listDataHeader.add("Medicación");

        // Agregar datos de los hijos para cada grupo
        List<String> enfermedades = new ArrayList<>();
        enfermedades.add("Enfermedad 1");
        enfermedades.add("Enfermedad 2");
        enfermedades.add("Enfermedad 3");

        List<String> vacunas = new ArrayList<>();
        vacunas.add("Vacuna 1");
        vacunas.add("Vacuna 2");
        vacunas.add("Vacuna 3");

        List<String> medicacion = new ArrayList<>();
        medicacion.add("Medicación 1");
        medicacion.add("Medicación 2");
        medicacion.add("Medicación 3");

        // Mapear encabezados de grupos con datos de los hijos
        listDataChild.put(listDataHeader.get(0), enfermedades);
        listDataChild.put(listDataHeader.get(1), vacunas);
        listDataChild.put(listDataHeader.get(2), medicacion);
    }
}