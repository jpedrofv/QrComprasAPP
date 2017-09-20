package com.example.logonrm.qrcomprasapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONStringer;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

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



    public void realizarCadastro(View v){
        Cadastrar task = new Cadastrar();
        task.execute("44818952842", "João Pedro", "06328080", "true", "jpedro.fv@hotmail.com", "123456");
    }

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

    private class Cadastrar extends AsyncTask<String, Void, Integer>{

        private ProgressDialog progress;

        @Override
        protected void onPreExecute(){

            progress = ProgressDialog.show(CadastrarClienteActivity.this, "Aguarde...", "Enviando Dados");
        }

        @Override
        protected Integer doInBackground(String... params) {
            URL url = null;
            Integer i = null;
            try{
                url = new URL("http://10.0.2.2:8087/api/ws/rest/qrcompras/clientes");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-type", "application/json");

                JSONStringer json = new JSONStringer();
                json.object();
                json.key("cpf").value(params[0]);
                json.key("nome").value(params[1]);
                json.key("cep").value(params[2]);
                json.key("tipo").value(params[3]);
                json.key("email").value(params[4]);
                json.key("senha").value(params[5]);
                json.endObject();

                OutputStreamWriter stream = new OutputStreamWriter(con.getOutputStream());
                stream.write(json.toString());
                stream.close();


                i = con.getResponseCode();

            }catch(Exception e){
                e.printStackTrace();
            }

            return i;
        }



        @Override
        protected void onPostExecute(Integer s){
            progress.dismiss();
            if(s == 201){
                Toast.makeText(CadastrarClienteActivity.this, "Cadastrado com Sucesso!!!", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(CadastrarClienteActivity.this, "Erro ao realizar o cadastro!!!", Toast.LENGTH_LONG).show();
            }
        }


    }
}
