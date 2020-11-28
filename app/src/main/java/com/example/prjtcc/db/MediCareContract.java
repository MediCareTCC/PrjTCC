package com.example.prjtcc.db;

import android.provider.BaseColumns;

public class MediCareContract {

    private MediCareContract() {}

    public static final class MediCareEntry  implements BaseColumns {
        public static final String TABLE_NAME ="usuario";
        public static final String COLUMN_CPF = "cpf";
        public static final String COLUMN_SENHA = "senha";
        public static final String COLUMN_NOME = "nome";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_TELEFONE = "telefone";
        public static final String COLUMN_CEP = "cep";
        public static final String COLUMN_RUA = "rua";
        public static final String COLUMN_NUMERO = "numero";
        public static final String COLUMN_COMPLEMENTO = "complemento";
    }
}
