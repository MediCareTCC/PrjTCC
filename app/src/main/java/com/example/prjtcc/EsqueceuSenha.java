package com.example.prjtcc;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.prjtcc.R;
import com.example.prjtcc.db.MediCareContract;
import com.example.prjtcc.db.MediCareDBHelper;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;
import com.example.prjtcc.db.MediCareContract.*;


public class EsqueceuSenha extends AppCompatActivity {


    private EditText email;
    private SQLiteDatabase db;
    private Button mudarSenha;
    private Toast toastConfirm;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueceu_senha);
        ImageButton voltar = findViewById(R.id.voltar);
        mudarSenha = findViewById(R.id.button_mudarSenha);
        email = findViewById(R.id.edit_text_esqueceu_senha);
        final MediCareDBHelper dbHelper = new MediCareDBHelper(this);
        //db = dbHelper.getWritableDatabase();

        

        mudarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailString = email.getText().toString();

                boolean check =  dbHelper.checkemail(emailString);

                if(check == true) {
                    Intent intent = new Intent(getApplicationContext(), ResetActivity.class);
                    intent.putExtra("email", emailString);
                    startActivity(intent);
                } else {
                    toastConfirm = Toast.makeText(getBaseContext(), "O email digitado não existe em nosso banco de dados", Toast.LENGTH_SHORT);
                    toastConfirm.show();
                }
            }
        });






        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // Método executado ao clicar no botão
                startActivity(new Intent(EsqueceuSenha.this, Login.class));
                finish();
            }
        });

    }

     @Override
    public void onBackPressed(){
        startActivity(new Intent(EsqueceuSenha.this, Login.class));
        finish();
    }
}
