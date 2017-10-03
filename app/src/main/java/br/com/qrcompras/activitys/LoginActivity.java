package br.com.qrcompras.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.logonrm.activitys.R;

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

    public void registrar(View v){
        Intent i = new Intent(this, CadastrarClienteActivity.class);
        startActivity(i);
    }

    public void entrar(View v){
        Intent i = new Intent(this, MenuActivity.class);
        startActivity(i);
    }
}
