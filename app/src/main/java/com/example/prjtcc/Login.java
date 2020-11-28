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
    private Cursor cursorLogin;
    private SQLiteDatabase dataBase;
    private EditText cpf;
    private EditText senha;
    private boolean entrar = false;
    private Toast erro;


    public Cursor cursor() {
        MediCareDBHelper dbHelper = new MediCareDBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursorLogin = db.rawQuery("select * from usuario where cpf = ? and senha = ?", new String[]{cpf.getText().toString(), senha.getText().toString()});
        return cursorLogin;
    }
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


        button_entrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(cpf.getText().toString().equals("") || senha.getText().toString().equals("")){
                    erro = Toast.makeText(getBaseContext(), "Insira o CPF e/ou a senha", Toast.LENGTH_SHORT);
                    erro.show();
                    return;
                }
                if(cpf.getText().toString().length() != 14){
                    erro = Toast.makeText(getBaseContext(), "INSIRA UM CPF VÁLIDO", Toast.LENGTH_SHORT);
                    erro.show();
                    return;
                }
                cursorLogin = cursor();
                if (cursorLogin.getCount() > 0) {
                    startActivity(new Intent(Login.this, MainActivity.class));
                    return;
                }
                else {
                    erro = Toast.makeText(getBaseContext(), "CPF e/ou senha incorretos", Toast.LENGTH_SHORT);
                    erro.show();
                    return;
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