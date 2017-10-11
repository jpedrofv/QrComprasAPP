package br.com.qrcompras.activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.logonrm.activitys.R;

import org.json.JSONStringer;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class RegistrarSenhaActivity extends AppCompatActivity {

    EditText edtSenha;
    EditText edtConfirmSenha;
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_senha);

        edtSenha = (EditText) findViewById(R.id.edtSenha);
        edtConfirmSenha = (EditText) findViewById(R.id.edtConfirmSenha);
        Intent i = getIntent();
        b = i.getExtras();

    }

    public void realizarCadastro(View v){

        String senha = edtSenha.getText().toString();
        String confirmSenha = edtConfirmSenha.getText().toString();


            Cadastrar cad = new Cadastrar();
            cad.execute(b.getString("cpf"),
                    b.getString("nome"),
                    b.getString("nascimento"),
                    b.getString("telefone"),
                    b.getString("celular"),
                    b.getString("cep"),
                    b.getString("logradouro"),
                    b.getString("numero"),
                    b.getString("bairro"),
                    b.getString("cidade"),
                    "false",
                    b.getString("email"),
                    b.getString("senha"));



    }

    private class Cadastrar extends AsyncTask<String, Void, Integer> {

        private ProgressDialog progress;

        @Override
        protected void onPreExecute(){

            progress = ProgressDialog.show(RegistrarSenhaActivity.this, "Aguarde...", "Enviando Dados");
        }

        @Override
        protected Integer doInBackground(String... params) {
            URL url = null;
            Integer i = null;
            try{
                url = new URL("http://qrcompras.ddns.net:8087/api/ws/rest/qrcompras/clientes");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-type", "application/json");

                JSONStringer json = new JSONStringer();
                json.object();
                json.key("cpf").value(params[0]);
                json.key("nome").value(params[1]);
                json.key("dt_nascimento").value(params[2]);
                json.key("telefone").value(params[3]);
                json.key("celular").value(params[4]);
                json.key("cep").value(params[5]);
                json.key("rua").value(params[6]);
                json.key("numero").value(params[7]);
                json.key("bairro").value(params[8]);
                json.key("cidade").value(params[9]);
                json.key("tipo").value(params[10]);
                json.key("email").value(params[11]);
                json.key("senha").value(params[12]);
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
                Toast.makeText(RegistrarSenhaActivity.this, "Cadastrado com Sucesso!!!", Toast.LENGTH_LONG).show();
                concluir();

            }else{
                Toast.makeText(RegistrarSenhaActivity.this, "Erro ao realizar o cadastro!!!", Toast.LENGTH_LONG).show();
            }
        }


    }

    public void concluir(){

        Intent i = new Intent(RegistrarSenhaActivity.this, LoginActivity.class);
        startActivity(i);
    }
}
