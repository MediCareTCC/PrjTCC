package com.example.prjtcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prjtcc.db.MediCareContract.*;
import com.example.prjtcc.db.MediCareDBHelper;


public class ResetActivity extends AppCompatActivity {

    TextView email;
    private EditText novaSenha;
    private EditText repetirNovaSenha;
    private Button novaSenhaButton;
    private Toast toastConfirm;
    MediCareDBHelper dbHelper = new MediCareDBHelper(this);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        email = findViewById(R.id.textView_email_reset);
        novaSenha = findViewById(R.id.edit_text_novaSenha);
        repetirNovaSenha = findViewById(R.id.edit_text_repetirNovaSenha);
        novaSenhaButton = findViewById(R.id.button_novaSenha);


        Intent intent = getIntent();
        email.setText(intent.getStringExtra("email"));


        //final MediCareDBHelper dbHelper = new MediCareDBHelper(this );

            novaSenhaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // Método executado ao clicar no botão
                String emailString = email.getText().toString();
                String senha = novaSenha.getText().toString();
                String senhaRepetida = repetirNovaSenha.getText().toString();

                if(senha.equals(senhaRepetida)) {
                    boolean checkpasswordupdate = dbHelper.updatepassword(senha, emailString);

                    if(checkpasswordupdate == true) {
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        toastConfirm = Toast.makeText(getBaseContext(), "A senha foi atualizada com sucesso", Toast.LENGTH_SHORT);
                        toastConfirm.show();
                        startActivity(intent);
                    } else {
                        toastConfirm = Toast.makeText(getBaseContext(), "A senha não foi atualizada com sucesso", Toast.LENGTH_SHORT);
                        toastConfirm.show();
                    }
                } else {
                    toastConfirm = Toast.makeText(getBaseContext(), "As senhas digitadas não são iguais", Toast.LENGTH_SHORT);
                    toastConfirm.show();
                }

            }
        });
    }
}