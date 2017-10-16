package br.com.qrcompras.activitys;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import br.com.qrcompras.activitys.R;

import java.util.ArrayList;
import java.util.List;

import br.com.qrcompras.fragments.CarrinhoFragment;
import br.com.qrcompras.models.Produto;
import br.com.qrcompras.ws.BuscaProduto;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    int i = 0;
    TableLayout tl;
    List<Produto> carrinho = new ArrayList<Produto>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        tl = (TableLayout) findViewById(R.id.carrinhoTable);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.carrinho) {
            fragmentManager.beginTransaction().replace(R.id.content_menu, new CarrinhoFragment()).commit();

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void addProduto(View v){
    // Acionado para scanear o produto

    }

    private void addCarrinho(String ean, String idParceiro){
        // Acionado para adicionar o produto ao carrinho

        Produto produto = new Produto();
        BuscaProduto busca = new BuscaProduto();

        produto = busca.buscarProdutosQr(ean, idParceiro);

        if(produto != null){
            carrinho.add(produto);

            //Adiciona Linha
            TableRow tr = new TableRow(this);
            tr.setId(i);
            TableRow.LayoutParams trp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.MATCH_PARENT);
            tr.setLayoutParams(trp);
            tl.addView(tr);

            //Adiciona Imagem na linha
            ImageView img = new ImageView(this);
            //img.setImageDrawable();
            tr.addView(img);

            TextView prod = new TextView(this);
            prod.setText(produto.getDescricao());
            tr.addView(prod);

            TextView preco = new TextView(this);
            prod.setText(String.valueOf(produto.getValor()));
            tr.addView(preco);

            EditText qtd = new EditText(this);
            qtd.setText("1");
            tr.addView(qtd);

            Button remover = new Button(this);
            remover.setText("Remover");
            tr.addView(remover);

            Toast.makeText(this, "Produto Adicionado", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "Produto Não Encontrado!!", Toast.LENGTH_SHORT).show();
        }


    }
}
