package com.example.pc1_tdam_alexandervalverde;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.pc1_tdam_alexandervalverde.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String strUsername = SecondFragmentArgs.fromBundle(getArguments()).getStrEmail();
        binding.txtBienvenida.setText("Bienvenido, "+strUsername);
        binding.btnRepartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = binding.rgDificultades.getCheckedRadioButtonId();
                if (selectedId != -1) {
                    // Obtener el RadioButton seleccionado utilizando el ID, asegurándose de buscar en el View raíz
                    RadioButton selectedRadioButton = binding.getRoot().findViewById(selectedId);
                    String textoSeleccionado = selectedRadioButton.getText().toString();
                    SecondFragmentDirections.ActionSecondFragmentToTercerFragment action1 =
                            SecondFragmentDirections.actionSecondFragmentToTercerFragment(textoSeleccionado,strUsername);
                    NavHostFragment.findNavController(SecondFragment.this).navigate(action1);
                } else {
                    Toast.makeText(getActivity(), "¡Por favor, selecciona una opción!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}