package com.example.logonrm.qrcomprasapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.json.JSONStringer;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class CadastrarClienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cliente);
    }



    public void realizarCadastro(View v){
        Cadastrar task = new Cadastrar();
        task.execute("44818952842", "João Pedro", "06328080", "true", "jpedro.fv@hotmail.com", "123456");

    }

    private class Cadastrar extends AsyncTask<String, Void, Integer>{
        private ProgressDialog progress;

        @Override
        protected Integer doInBackground(String... params) {
            URL url = null;
            try{
                url = new URL("http://localhost:8087/api/ws/rest/qrcompras/clientes");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-type", "aplication/json");

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


                return con.getResponseCode();

            }catch(Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute(){

            progress = ProgressDialog.show(CadastrarClienteActivity.this, "Aguarde...", "Enviando Dados");
        }

        @Override
        protected void onPostExecute(Integer s){
            progress.dismiss();
            if(s == 201){
                Toast.makeText(CadastrarClienteActivity.this, "Cadastrado com Sucesso!!!", Toast.LENGTH_LONG).show();
            }
        }


    }
}
