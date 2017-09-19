package com.example.logonrm.qrcomprasapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText edtEmail;
    EditText edtSenha;
    CheckBox ckbLembrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        ckbLembrar = (CheckBox) findViewById(R.id.ckbLembrar);


    }

    public Intent registrar(View v){
        Intent i = new Intent();
        i.setClass(this, CadastrarClienteActivity.class);
        startActivity(i);

        return i;
    }
}
