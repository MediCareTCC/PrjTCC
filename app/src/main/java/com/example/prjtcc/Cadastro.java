package com.example.prjtcc;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.prjtcc.R;
import com.example.prjtcc.db.MediCareContract;
import com.example.prjtcc.db.MediCareDBHelper;

import org.w3c.dom.Text;

import static com.example.prjtcc.MaskEditText.MaskWatcher.buildCpf;
import static com.example.prjtcc.MaskEditText.MaskWatcher.buildPhone;

public class Cadastro extends AppCompatActivity {

    private SQLiteDatabase dataBase;
    private EditText editTextNome;
    private EditText editTextSenha;
    private EditText editTextSenhaConfirm;
    private EditText editTextCpf;
    private EditText editTextEmail;
    private EditText editTextPhone;
    private Toast toastConfirm;
    private Button buttonCadastrar;
    private ImageButton voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);



        MediCareDBHelper dbHelper = new MediCareDBHelper(this);

        dataBase = dbHelper.getWritableDatabase();
        editTextNome = findViewById(R.id.edit_text_cadastro_nome);
        editTextCpf = findViewById(R.id.edit_text_cadastro_cpf);
        editTextEmail = findViewById(R.id.edit_text_cadastro_email);
        editTextSenha = findViewById(R.id.edit_text_cadastro_senha);
        editTextSenhaConfirm = findViewById(R.id.edit_text_cadastro_confirme_senha);
        editTextPhone = findViewById(R.id.edit_text_cadastro_telefone);
        buttonCadastrar = findViewById(R.id.button_cadastrar);
        voltar = findViewById(R.id.voltar);

        editTextCpf.addTextChangedListener(buildCpf());
        editTextPhone.addTextChangedListener(buildPhone());

        buttonCadastrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(editTextNome.getText().toString().equals("") || editTextCpf.getText().toString().equals("")  || editTextSenha.getText().toString().equals("") || editTextPhone.getText().toString().equals("") ){
                    toastConfirm = Toast.makeText(getBaseContext(), "Insire todos os dados necessários", Toast.LENGTH_SHORT);
                    toastConfirm.show();
                    return;
                }
                if(editTextCpf.getText().toString().length() != 14){
                    toastConfirm = Toast.makeText(getBaseContext(), "INSIRA UM CPF VÁLIDO", Toast.LENGTH_SHORT);
                    toastConfirm.show();
                    return;
                }
                if (!(editTextSenha.getText().toString().equals(editTextSenhaConfirm.getText().toString()))){
                    toastConfirm = Toast.makeText(getBaseContext(), "SENHAS NÃO CONFEREM", Toast.LENGTH_SHORT);
                    toastConfirm.show();
                    return;
                }
                try {
                    addDB();
                    showToast("Sucesso", "Cadastro concluído", true);
                    startActivity(new Intent(Cadastro.this, MainActivity.class));
                    finish();
                }
                catch (Exception e) {
                    showToast("Falha", "Erro ao concluir o cadastro", false);
                }
            }
            private void showToast(String status, String msg, Boolean check) {
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast_root));
                TextView toastText = layout.findViewById(R.id.toast_text);
                ImageView toastImage = layout.findViewById(R.id.toast_image);
                toastText.setText(msg);
                toastImage.setImageResource(check?R.drawable.ic_baseline_thumb_up_24: R.drawable.ic_baseline_thumb_down_24);
                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.BOTTOM, 0, 16);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();
            }

        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // Método executado ao clicar no botão
                startActivity(new Intent(Cadastro.this, Login.class));
                finish();
            }
        });
    }
    private void addDB() {
        String nome = editTextNome.getText().toString();
        String senha = editTextSenha.getText().toString();
        String cpf = editTextCpf.getText().toString();
        String email = editTextEmail.getText().toString();
        String phone = editTextPhone.getText().toString();


        ContentValues cv = new ContentValues();
        cv.put(MediCareContract.MediCareEntry.COLUMN_NOME, nome);
        cv.put(MediCareContract.MediCareEntry.COLUMN_SENHA, senha);
        cv.put(MediCareContract.MediCareEntry.COLUMN_CPF, cpf);
        cv.put(MediCareContract.MediCareEntry.COLUMN_EMAIL, email);
        cv.put(MediCareContract.MediCareEntry.COLUMN_TELEFONE, phone);

        dataBase.insert(MediCareContract.MediCareEntry.TABLE_NAME, null, cv);

    }
    @Override
    public void onBackPressed(){
        startActivity(new Intent(Cadastro.this, Login.class));
        finish();
    }
}