package br.com.qrcompras.activitys;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import br.com.qrcompras.activitys.R;

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
    LogarTask task = new LogarTask();
    task.execute(
            edtEmail.getText().toString(),
            edtSenha.getText().toString()
    );

    }


    private class LogarTask extends AsyncTask<String,Void,Integer>{

        @Override
        protected void onPostExecute(Integer integer) {
            if(integer == 200){
                //chamar activity
                //Intent i = new Intent(this, MenuActivity.class);
                //startActivity(i);
            }else{
                Toast.makeText(LoginActivity.this, "Erro", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected Integer doInBackground(String... params) {

            String urlParameters = "email="+params[0]+"&senha="+params[1];
            byte[] postData = new byte[0];
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.GINGERBREAD) {
                postData = urlParameters.getBytes( StandardCharsets.UTF_8 );
            }
            int postDataLength = postData.length;

            try {
                URL url = new URL("http://localhost:8087/api/ws/rest/qrcompras/auth");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                connection.setRequestProperty("charset", "utf-8");
                connection.setRequestProperty("Content-Length", Integer.toString(postDataLength ));
                connection.setUseCaches(false);

                DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
                wr.write(postData);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
