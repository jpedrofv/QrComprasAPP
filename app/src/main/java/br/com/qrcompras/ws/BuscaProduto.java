package br.com.qrcompras.ws;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import br.com.qrcompras.activitys.MenuActivity;
import br.com.qrcompras.activitys.RegistrarSenhaActivity;
import br.com.qrcompras.models.Produto;

/**
 * Created by Pee on 02/10/2017.
 */

public class BuscaProduto {

    Produto prod = new Produto();

    public Produto buscarProdutosQr(String codigo){

        BuscarProduto task = new BuscarProduto();
        task.execute(codigo);

        return prod;



    }

    private class BuscarProduto extends AsyncTask<String, Void, String> {

        private ProgressDialog progress;

        @Override
        protected void onPreExecute(){


        }

        @Override
        protected String doInBackground(String... params) {
            URL url = null;
            Integer i = null;
            StringBuilder resp = new StringBuilder();
            try{
                url = new URL("http://10.0.2.2:8087/api/ws/rest/qrcompras/produtos/"+params[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Accept", "application/json");

                if(con.getResponseCode() == 200){
                    BufferedReader stream = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String linha = "";

                    while((linha = stream.readLine()) != null){
                        resp.append(linha);
                    }
                    con.disconnect();

                    return resp.toString();

                }
            }catch(Exception e){
                e.printStackTrace();
            }

            return resp.toString();
        }



        @Override
        protected void onPostExecute(String s){
            try{

                JSONObject json = new JSONObject(s);

                int id = json.getInt("id_produto");
                String ean = json.getString("ean");
                double valor = json.getDouble("valor");
                String descricao = json.getString("descricao");
                long idParceiro = json.getLong("parceiro");



                // Cria o objeto que será retornado
                prod.setId_produto(id);
                prod.setEan(ean);
                prod.setValor(valor);
                prod.setDescricao(descricao);

            }catch(Exception e){
                e.printStackTrace();
            }
        }


    }
}
