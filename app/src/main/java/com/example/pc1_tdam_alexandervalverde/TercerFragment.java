package com.example.pc1_tdam_alexandervalverde;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;

import com.example.pc1_tdam_alexandervalverde.databinding.FragmentSecondBinding;
import com.example.pc1_tdam_alexandervalverde.databinding.FragmentTercerBinding;

import java.util.HashSet;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TercerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TercerFragment extends Fragment {
    private FragmentTercerBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TercerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TercerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TercerFragment newInstance(String param1, String param2) {
        TercerFragment fragment = new TercerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentTercerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String strUsername = TercerFragmentArgs.fromBundle(getArguments()).getStrEmail();
        binding.txtBienvenida.setText("Listo, " + strUsername + "este es tu juego");
        String dificultad = TercerFragmentArgs.fromBundle(getArguments()).getStrDificultad();
        int totalBloqueadas = 0;
        // Usar if-else para determinar el número de casillas bloqueadas
        if (dificultad.equals("Fácil")) {
            totalBloqueadas = 40;
        } else if (dificultad.equals("Normal")) {
            totalBloqueadas = 20;
        } else if (dificultad.equals("Dificil")) {
            totalBloqueadas = 10;
        } else if (dificultad.equals("Muy Dificil")) {
            totalBloqueadas = 5;
        }

        // Configurar las cajas del Sudoku
        configurarGridSudoku(totalBloqueadas);
        binding.btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();

            }
        });

    }

    private void configurarGridSudoku(int totalBloqueadas) {
        Random random = new Random();
        HashSet<Integer> indicesBloqueados = new HashSet<>();

        // Añadir 81 EditText al GridLayout
        for (int i = 0; i < 81; i++) {
            EditText editText = new EditText(getContext());
            editText.setEms(2);  // Ancho del texto
            editText.setTextSize(24);  // Tamaño de fuente
            editText.setGravity(android.view.Gravity.CENTER);  // Centramos el texto
            editText.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);  // Solo números

            // Establecer el tamaño del EditText y centrarlo en el GridLayout
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 110;  // Ancho en píxeles
            params.height = 170; // Alto en píxeles
            params.setGravity(android.view.Gravity.CENTER);  // Centramos el EditText en el GridLayout
            editText.setLayoutParams(params);

            binding.gridSudoku.addView(editText);  // Añadir la caja de texto al GridLayout
        }

        // Bloquear las cajas de texto aleatoriamente
        while (indicesBloqueados.size() < totalBloqueadas) {
            int randomIndex = random.nextInt(81);  // Índice aleatorio entre 0 y 80
            if (indicesBloqueados.add(randomIndex)) { // Solo se añade si no está presente
                EditText editText = (EditText) binding.gridSudoku.getChildAt(randomIndex);
                editText.setEnabled(false);  // Deshabilitar la caja de texto
                editText.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));  // Cambiar color de fondo
            }
        }
    }




}