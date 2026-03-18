package uta.edu.ec.android_app_002;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {
private EditText editTextUser;
private  EditText editTextPassword;
private Button buttonOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // obtener las referencias de las variables de la UI
        editTextUser = (EditText) findViewById(R.id.editTextUser);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonOk = findViewById(R.id.buttonOk);

        /*
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = editTextUser.getText().toString();
                String password = editTextPassword.getText().toString();

                // mostrar la activity (MainActivity)
                if (user.equals("admin") && password.equals("admin")) {
                    // Una app de Android (Activities, BroadCast, Receivers, Services...)
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class );

                    // mostrar el activity
                    startActivity(intent);
                }
                else  {
                    Toast.makeText(getApplicationContext(), "Usuario/Clave incorrectos",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        */
    }

    public  void buttonOk_clic(View view){
        String user = editTextUser.getText().toString();
        String password = editTextPassword.getText().toString();

        // mostrar la activity (MainActivity)
        if (user.equals("admin") && password.equals("admin")) {
            // Una app de Android (Activities, BroadCast, Receivers, Services...)
            Intent intent = new Intent(this, MainActivity.class );

            // pasar parametros a la siguiente ventana
            intent.putExtra("userParameter", user);
            intent.putExtra("passwordParameter", password);

            // mostrar el activity
            startActivity(intent);
        }
        else  {
            Toast.makeText(this, "Usuario/Clave incorrectos",
                    Toast.LENGTH_SHORT).show();
        }
    }
}