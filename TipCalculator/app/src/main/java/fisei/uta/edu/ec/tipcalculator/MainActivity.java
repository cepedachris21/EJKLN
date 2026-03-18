package fisei.uta.edu.ec.tipcalculator;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // Formateadores para dólares estadounidenses
    private static final NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance(Locale.US);
    private static final NumberFormat percentFormat =
            NumberFormat.getPercentInstance(Locale.US);

    private double billAmount = 0.0;
    private double percent = 0.15;
    private TextView amountTextView;
    private TextView percentTextView;
    private TextView tipTextView;
    private TextView totalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener referencias a las vistas
        amountTextView = findViewById(R.id.amountTextView);
        percentTextView = findViewById(R.id.percentTextView);
        tipTextView = findViewById(R.id.tipTextView);
        totalTextView = findViewById(R.id.totalTextView);

        // Establecer valores iniciales con $
        tipTextView.setText(currencyFormat.format(0));
        totalTextView.setText(currencyFormat.format(0));

        // Configurar listener para EditText
        EditText amountEditText = findViewById(R.id.amountEditText);
        amountEditText.addTextChangedListener(amountEditTextWatcher);

        // Forzar que aparezca el teclado
        amountEditText.requestFocus();

        // Configurar listener para SeekBar
        SeekBar percentSeekBar = findViewById(R.id.percentSeekBar);
        percentSeekBar.setOnSeekBarChangeListener(seekBarListener);
    }

    // Calcular propina y total
    private void calculate() {
        // Mostrar porcentaje
        percentTextView.setText(percentFormat.format(percent));

        // Calcular propina y total
        double tip = billAmount * percent;
        double total = billAmount + tip;

        // Mostrar resultados con formato de moneda ($) sin esto la valores muestra sin
        // el sigo de dolar 
        tipTextView.setText(currencyFormat.format(tip));
        totalTextView.setText(currencyFormat.format(total));
    }

    // Listener del SeekBar
    private final OnSeekBarChangeListener seekBarListener =
            new OnSeekBarChangeListener() {

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress,
                                              boolean fromUser) {
                    percent = progress / 100.0;
                    calculate();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) { }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) { }
            };

    // Listener del EditText
    private final TextWatcher amountEditTextWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {

            try {
                // Convertir el texto a número y dividir por 100.0
                billAmount = Double.parseDouble(s.toString()) / 100.0;

                // Mostrar en amountTextView con formato de moneda
                amountTextView.setText(currencyFormat.format(billAmount));
            }
            catch (NumberFormatException e) {
                // Si está vacío o no es válido
                amountTextView.setText("");
                billAmount = 0.0;
            }

            // Calcular propina y total
            calculate();
        }

        @Override
        public void afterTextChanged(Editable s) { }

        @Override
        public void beforeTextChanged(CharSequence s, int start,
                                      int count, int after) { }
    };
}