package com.example.prjtcc;

import android.app.Application;

public class Edit extends Application {

        private String CPF;

        public String getCPF() {
            return CPF;
        }

        public void setCPF(String CPF) {
            this.CPF = CPF;
        }
}

