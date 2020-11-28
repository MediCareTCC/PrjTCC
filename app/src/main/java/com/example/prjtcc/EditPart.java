package com.example.prjtcc;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.prjtcc.Login;
import com.example.prjtcc.db.MediCareDBHelper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditPart extends AppCompatActivity {

    private SQLiteDatabase mDatabase;
    private EditAdapter mAdapter;
    private EditText mEditText1;
    private EditText mEditText2;
    private EditText mEditText3;
    private EditText mEditText4;
    private EditText mEditText5;
    private Cursor cursor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EditDBHelper dbHelper = new EditDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        mAdapter = new EditAdapter(this, getAllItems());

        mEditText1 = findViewById(R.id.txtUser);
        mEditText2 = findViewById(R.id.txtCPF);
        mEditText3 = findViewById(R.id.txtEmail);
        mEditText4 = findViewById(R.id.txtSize);
        mEditText5 = findViewById(R.id.txtPhone);

        Button buttonChange = findViewById(R.id.btnAlterar);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("select * from usuario where cpf = ? and senha = ?", new String[]{mEditText1.getText().toString(), mEditText2.getText().toString()});

        //cursor = Login.getCursor();
        buttonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    private void Change(){
        String name = mEditText1.getText().toString();
        String cpf = mEditText2.getText().toString();
        String email = mEditText3.getText().toString();
        String size = mEditText4.getText().toString();
        String phone = mEditText5.getText().toString();

        ContentValues cv = new ContentValues();
        cv.put(EditContract.EditEntry.COLUMN_NAME, name);
        cv.put(EditContract.EditEntry.COLUMN_CPF, cpf);
        cv.put(EditContract.EditEntry.COLUMN_EMAIL, email);
        cv.put(EditContract.EditEntry.COLUMN_SIZE, size);
        cv.put(EditContract.EditEntry.COLUMN_PHONE, phone);

        mDatabase.insert(EditContract.EditEntry.TABLE_NAME, null, cv);
        mAdapter.swapCursor(getAllItems());
    }

    private Cursor getAllItems(){
        return mDatabase.query(
                EditContract.EditEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                EditContract.EditEntry.COLUMN_TIMESTAMP + " DESC"
        );
    }
}
