package uta.edu.ec.android_app_002;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import uta.edu.ec.android_app_002.DAL.ContactDAL;
import uta.edu.ec.android_app_002.Entities.Contact;

public class MainActivity extends AppCompatActivity {

    private TextView textViewMessage;
    private EditText editTextCode;
    private EditText editTextName;
    private EditText editTextLastName;
    private EditText editTextAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textViewMessage = findViewById(R.id.textViewMessage);

        editTextCode = findViewById(R.id.editTextCode);
        editTextName = findViewById(R.id.editTextName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextAge = findViewById(R.id.editTextAge);

        // parametros
        Bundle bundle = getIntent().getExtras();
        String user = bundle.getString("userParameter");
        String password = bundle.getString("passwordParameter");

        textViewMessage.setText("Usuario:" + user + "   " + "Clave:" + password);

    }
    public void buttonInsert_Clic(View view) {
        String name = editTextName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String age = editTextAge.getText().toString();

        ContactDAL contactDAL = new ContactDAL(this);

        Contact contact = new Contact();
        contact.setName(name);
        contact.setLastName(lastName);
        contact.setAge(Integer.parseInt(age));

        long count = contactDAL.insert(contact);

        if (count > 0) {
            Toast.makeText(this, "Registro insertado",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Registro no insertado",
                    Toast.LENGTH_SHORT).show();
        }

        cleanControls();
    }

    private void cleanControls() {
        // limpiar los controles
        editTextCode.setText("");
        editTextName.setText("");
        editTextLastName.setText("");
        editTextAge.setText("");
    }
    public void buttonSearch_Clic(View view) {
        String code = editTextCode.getText().toString();

        Contact contact;

        ContactDAL contactDAL = new ContactDAL(this);

        contact = contactDAL.searchByCode(Integer.parseInt(code));

        if (contact != null) {
            editTextName.setText(contact.getName());
            editTextLastName.setText(contact.getLastName());
            editTextAge.setText(String.valueOf(contact.getAge()));
        }
        else {
            Toast.makeText(this, "Registro no encontrado",
                    Toast.LENGTH_SHORT).show();
            cleanControls();
        };
    }
    public void buttonDelete_Clic(View view) {


    }
    public void buttonUpdate_Clic(View view) {

    }
    public void buttonShowAll_Clic(View view) {
        Intent intent = new Intent(this, ShowAllActivity.class );
        startActivity(intent);;
    }
}