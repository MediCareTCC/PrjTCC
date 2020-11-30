package com.example.prjtcc;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.prjtcc.R;
import com.example.prjtcc.db.MediCareContract;
import com.example.prjtcc.Cadastro.*;
import com.example.prjtcc.db.MediCareDBHelper;
import com.example.prjtcc.db.MediCareContract.*;
import com.example.prjtcc.db.MediCareDBHelper;
import org.w3c.dom.Text;
import com.example.prjtcc.Edit;
import static com.example.prjtcc.MaskEditText.MaskWatcher.buildCpf;

public class Login extends AppCompatActivity {

    //private Cursor cursor;
   // private Cursor cursorLogin;
    //private SQLiteDatabase dataBase;
    private EditText cpf;
    private EditText senha;
    private boolean entrar = false;
    private Toast toastConfirm;
    //private Toast erro;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button_entrar = findViewById(R.id.button_entrar);
        Button button_cadastro = findViewById(R.id.button_cadastro);



        cpf = findViewById(R.id.edit_text_cpf);
        senha = findViewById(R.id.edit_text_senha);
        cpf.addTextChangedListener(buildCpf());

        TextView esqueceu_senha = findViewById(R.id.esqueceu_senha);
        TextView clique_aqui = findViewById(R.id.clique_aqui);
        SpannableString content = new SpannableString("Esqueceu a senha?");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        esqueceu_senha.setText(content);



        button_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // Método executado ao clicar no botão
                startActivity(new Intent(Login.this, Cadastro.class));
                finish();
            }
        });

        esqueceu_senha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // Método executado ao clicar no botão
                startActivity(new Intent(Login.this, EsqueceuSenha.class));
                finish();
            }
        });

        clique_aqui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // Método executado ao clicar no botão
                startActivity(new Intent(Login.this, EsqueceuSenha.class));
                finish();
            }
        });

        final MediCareDBHelper DB = new MediCareDBHelper(this);

        button_entrar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                if(cpf.getText().toString().equals("") || senha.getText().toString().equals("")){

                    toastConfirm = Toast.makeText(getBaseContext(), "Não deixe nenhum dos campos vazios", Toast.LENGTH_SHORT);
                    toastConfirm.show();

                } else if(cpf.getText().toString().length() != 14){

                    toastConfirm = Toast.makeText(getBaseContext(), "Insira um cpf válido", Toast.LENGTH_SHORT);
                    toastConfirm.show();

                } else {

                    Boolean checkuserpass = DB.checkusernamepassword(cpf.getText().toString(), senha.getText().toString());

                    if(checkuserpass==true){

                        toastConfirm = Toast.makeText(getBaseContext(), "Login efetuado com sucesso", Toast.LENGTH_SHORT);
                        toastConfirm.show();
                        Intent intent  = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        toastConfirm = Toast.makeText(getBaseContext(), "cpf e/ou senha incorretos", Toast.LENGTH_SHORT);
                        toastConfirm.show();
                    }


                }
            }

        });

    }
    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}