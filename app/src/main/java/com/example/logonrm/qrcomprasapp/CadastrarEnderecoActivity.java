package com.example.logonrm.qrcomprasapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class CadastrarEnderecoActivity extends AppCompatActivity {


    EditText edtCep;
    EditText edtEstado;
    EditText edtCidade;
    EditText edtBairro;
    EditText edtRua;
    EditText edtNumero;
    EditText edtComplemento;
    Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_endereco);

        Intent i = getIntent();
        b = i.getExtras();


        edtCep = (EditText) findViewById(R.id.edtCep);
        edtEstado = (EditText) findViewById(R.id.edtEstado) ;
        edtCidade = (EditText) findViewById(R.id.edtCidade);
        edtBairro = (EditText) findViewById(R.id.edtBairro);
        edtRua = (EditText)findViewById(R.id.edtRua);
        edtNumero = (EditText) findViewById(R.id.edtNumero);
        edtComplemento = (EditText) findViewById(R.id.edtComplemento);


        edtCep.addTextChangedListener(new PhoneNumberFormattingTextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

                if(s.length() == 8){
                   pesquisarCep();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

            }
        });

    }

    public void pesquisarCep(){
        String cep = edtCep.getText().toString();
        Pesquisar p = new Pesquisar();
        p.execute();
    }

    private class Pesquisar extends AsyncTask<String, Void, String> {

        private ProgressDialog progress;
        String cep = edtCep.getText().toString();

        @Override
        protected void onPreExecute(){

            progress = ProgressDialog.show(CadastrarEnderecoActivity.this, "Aguarde...", "Pesquisando Endereco");
        }

        @Override
        protected String doInBackground(String... params) {
            URL url = null;
            StringBuilder resposta = new StringBuilder();
            try{
                url = new URL("http://api.postmon.com.br/v1/cep/"+cep);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Accept", "application/json");

                if(con.getResponseCode() == 200){
                    BufferedReader stream = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String linha = "";

                    while((linha = stream.readLine()) != null){
                        resposta.append(linha);
                    }
                    con.disconnect();
                    return resposta.toString();
                }

            }catch(Exception e){
                e.printStackTrace();
            }

            return resposta.toString();
        }



        @Override
        protected void onPostExecute(String s){
            progress.dismiss();
            if(s != null){
                try{
                    JSONObject json = new JSONObject(s);
                    String estado = json.getString("estado");
                    String cidade = json.getString("cidade");
                    String bairro = json.getString("bairro");
                    String logradouro = json.getString("logradouro");

                    edtEstado.setText(estado);
                    edtCidade.setText(cidade);
                    edtBairro.setText(bairro);
                    edtRua.setText(logradouro);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                Toast.makeText(CadastrarEnderecoActivity.this, "Erro ao realizar a consulta!!!", Toast.LENGTH_LONG).show();
            }
        }


    }

    public void confirmar(View v){
        String cep = edtCep.getText().toString();
        String estado = edtEstado.getText().toString();
        String cidade = edtCidade.getText().toString();
        String bairro = edtBairro.getText().toString();
        String logradouro = edtRua.getText().toString();
        String numero = edtNumero.getText().toString();
        String complemento = edtComplemento.getText().toString();

        b.putString("cep", cep);
        b.putString("estado", estado);
        b.putString("cidade", cidade);
        b.putString("bairro", bairro);
        b.putString("logradouro", logradouro);
        b.putString("numero", numero);
        b.putString("complemento", complemento);

        Intent p = new Intent(this, ConfirmarCadastroActivity.class);
        p.putExtras(b);
        startActivity(p);
    }
}
