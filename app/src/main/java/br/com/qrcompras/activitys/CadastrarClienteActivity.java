package br.com.qrcompras.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import br.com.qrcompras.activitys.CadastrarEnderecoActivity;
import br.com.qrcompras.activitys.R;


public class CadastrarClienteActivity extends AppCompatActivity {


    EditText edtCPF;
    EditText edtNome;
    EditText edtNascimento;
    EditText edtTelefone;
    EditText edtCelular;
    EditText edtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cliente);

        edtCPF = (EditText) findViewById(R.id.edtCPF);
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtNascimento = (EditText) findViewById(R.id.edtNascimento);
        edtTelefone = (EditText) findViewById(R.id.edtTelefone);
        edtCelular = (EditText) findViewById(R.id.edtCelular);
        edtEmail = (EditText) findViewById(R.id.edtEmail);

    }



    /*public void realizarCadastro(View v){
        Cadastrar task = new Cadastrar();
        task.execute("44818952842", "João Pedro", "06328080", "true", "jpedro.fv@hotmail.com", "123456");
    }*/

    public void proximo(View v){

        String cpf = edtCPF.getText().toString();
        String nome = edtNome.getText().toString();
        String nascimento = edtNascimento.getText().toString();


        String telefone = edtTelefone.getText().toString();
        String celular = edtCelular.getText().toString();
        String email = edtEmail.getText().toString();

        Bundle b = new Bundle();
        b.putString("cpf", cpf);
        b.putString("nome", nome);
        b.putString("nascimento", nascimento);
        b.putString("telefone", telefone);
        b.putString("celular", celular);
        b.putString("email", email);

        Intent p = new Intent(this, CadastrarEnderecoActivity.class);
        p.putExtras(b);
        startActivity(p);




    }


}
