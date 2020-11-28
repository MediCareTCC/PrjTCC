package com.example.prjtcc;

import android.provider.BaseColumns;


public class EditContract {

    private EditContract(){}

    public static final class EditEntry implements BaseColumns {
        public static final String TABLE_NAME = "editList";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_CPF = "cpf";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_SIZE = "size";
        public static final String COLUMN_PASS = "password";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_CEP = "cep";
        public static final String COLUMN_STREET = "street";
        public static final String COLUMN_NUMBER = "number";
        public static final String COLUMN_COMPLEMENT = "complement";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}
