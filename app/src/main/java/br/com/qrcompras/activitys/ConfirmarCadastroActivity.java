package br.com.qrcompras.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import br.com.qrcompras.activitys.R;

public class ConfirmarCadastroActivity extends AppCompatActivity {

    TextView txtResp;
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_cadastro);
        Intent i = getIntent();
        b = i.getExtras();

        txtResp = (TextView) findViewById(R.id.txtResp);

        txtResp.setText("CPF: "+
                b.getString("cpf")+
                "\nNome: " +b.getString("nome")+
                "\nData de Nascimento: " +b.getString("nascimento")+
                "\nTelefone: " +b.getString("telefone")+
                "\nCelular: " +b.getString("celular")+
                "\nEmail: " +b.getString("email")+
                "\nCep: " +b.getString("cep")+
                "\nEstado: " +b.getString("estado")+
                "\nRua/Av: " +b.getString("logradouro")+
                "\nCidade: " +b.getString("cidade")+
                "\nBairro: " +b.getString("bairro")+
                "\nNumero: " +b.getString("numero")+
                "\nComplemento: " +b.getString("complemento")


        );

    }

    public void proximo(View v){
     Intent i = new Intent(this, RegistrarSenhaActivity.class);
      i.putExtras(b);
        startActivity(i);



    }

}
