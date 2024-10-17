package com.example.pc1_tdam_alexandervalverde;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.pc1_tdam_alexandervalverde.databinding.FragmentFirstBinding;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private StringBuilder codigoIngresado = new StringBuilder(); // Para almacenar los dígitos ingresados
    private String correoIngresado;
    Map<String, String> usuarios = new HashMap<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Añadir usuarios y sus contraseñas
        usuarios.put("alexander@uss.edu.pe", "1234");
        usuarios.put("hola@uss.edu.pe", "1597");
        usuarios.put("olam@uss.edu.pe", "4321");
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inicializar botones con números aleatorios
        asignarNumerosAleatorios();

        // Configurar OnClickListener para cada botón numérico
        binding.button.setOnClickListener(v -> agregarDigito(binding.button.getText().toString()));
        binding.button2.setOnClickListener(v -> agregarDigito(binding.button2.getText().toString()));
        binding.button3.setOnClickListener(v -> agregarDigito(binding.button3.getText().toString()));
        binding.button4.setOnClickListener(v -> agregarDigito(binding.button4.getText().toString()));
        binding.button5.setOnClickListener(v -> agregarDigito(binding.button5.getText().toString()));
        binding.button6.setOnClickListener(v -> agregarDigito(binding.button6.getText().toString()));
        binding.button7.setOnClickListener(v -> agregarDigito(binding.button7.getText().toString()));
        binding.button8.setOnClickListener(v -> agregarDigito(binding.button8.getText().toString()));
        binding.button9.setOnClickListener(v -> agregarDigito(binding.button9.getText().toString()));
        binding.button10.setOnClickListener(v -> agregarDigito(binding.button10.getText().toString()));
    }

    // Método para asignar números aleatorios a los botones
    private void asignarNumerosAleatorios() {
        // Desordenar números del 0 al 9
        List<Integer> numeros = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        Collections.shuffle(numeros);

        // Asignar números desordenados a los botones
        binding.button.setText(String.valueOf(numeros.get(0)));
        binding.button2.setText(String.valueOf(numeros.get(1)));
        binding.button3.setText(String.valueOf(numeros.get(2)));
        binding.button4.setText(String.valueOf(numeros.get(3)));
        binding.button5.setText(String.valueOf(numeros.get(4)));
        binding.button6.setText(String.valueOf(numeros.get(5)));
        binding.button7.setText(String.valueOf(numeros.get(6)));
        binding.button8.setText(String.valueOf(numeros.get(7)));
        binding.button9.setText(String.valueOf(numeros.get(8)));
        binding.button10.setText(String.valueOf(numeros.get(9)));
    }

    // Método para agregar los dígitos ingresados y verificar el código
    private void agregarDigito(String digito) {
        codigoIngresado.append(digito);  // Agregar el dígito seleccionado

        // Verificar si ya se han ingresado 4 dígitos
        if (codigoIngresado.length() == 4) {
            verificarCodigo();
        }
    }

    // Método para verificar si el correo y el código son válidos
    private void verificarCodigo() {
        correoIngresado = binding.edtCorreo.getText().toString(); // Obtener el correo ingresado

        if (correoIngresado.isEmpty()) {
            // Si no hay correo ingresado, mostrar mensaje de error
            Toast.makeText(getActivity(), "Por favor, ingrese un correo.", Toast.LENGTH_SHORT).show();
            codigoIngresado.setLength(0);  // Limpiar el código ingresado
            asignarNumerosAleatorios();     // Reordenar números en los botones
            return;
        }

        String codigoCorrecto = usuarios.get(correoIngresado); // Obtener la contraseña correcta para el correo ingresado

        if (codigoCorrecto == null) {
            // El correo no es válido, mostrar mensaje de error
            Toast.makeText(getActivity(), "Correo no válido. Inténtalo de nuevo.", Toast.LENGTH_SHORT).show();
            codigoIngresado.setLength(0);  // Limpiar el código ingresado
            asignarNumerosAleatorios();     // Reordenar números en los botones
        } else if (codigoIngresado.toString().equals(codigoCorrecto)) {
            // Código correcto, navegar a la siguiente pantalla
            Toast.makeText(getActivity(), "Inicio de Sesion correcto.", Toast.LENGTH_SHORT).show();
            //Pasamos el argumento del correo
            FirstFragmentDirections.ActionFirstFragmentToSecondFragment action =
                    FirstFragmentDirections.actionFirstFragmentToSecondFragment(correoIngresado);
            NavHostFragment.findNavController(FirstFragment.this).navigate(action);


        } else {
            // Código incorrecto, limpiar el código ingresado, reordenar números y mostrar mensaje
            Toast.makeText(getActivity(), "Contraseña incorrecta. Inténtalo de nuevo.", Toast.LENGTH_SHORT).show();
            codigoIngresado.setLength(0);  // Limpiar el código ingresado
            asignarNumerosAleatorios();     // Reordenar números en los botones
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
