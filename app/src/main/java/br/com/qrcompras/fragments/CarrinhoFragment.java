package br.com.qrcompras.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.logonrm.activitys.R;

import java.util.ArrayList;
import java.util.List;

import br.com.qrcompras.models.Produto;
import br.com.qrcompras.ws.BuscaProduto;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CarrinhoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CarrinhoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarrinhoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    int i = 0;
    TableLayout tl;
    List<Produto> carrinho = new ArrayList<Produto>();
    EditText codigo;


    private OnFragmentInteractionListener mListener;



    public CarrinhoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CarrinhoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CarrinhoFragment newInstance(String param1, String param2) {
        CarrinhoFragment fragment = new CarrinhoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tl = (TableLayout) getActivity().findViewById(R.id.carrinhoTable);
    codigo = (EditText) getActivity().findViewById(R.id.edtCodigo);
        codigo.addTextChangedListener(new PhoneNumberFormattingTextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

                if(s.length() == 5){
                    add();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_carrinho, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void add(){
        BuscaProduto ws = new BuscaProduto();
        Produto prod = new Produto();
        prod = ws.buscarProdutosQr(codigo.getText().toString());

        if(prod != null){
            carrinho.add(prod);

            //Adiciona Linha
            TableRow tr = new TableRow(getActivity());
            tr.setId(i);
            TableRow.LayoutParams trp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.MATCH_PARENT);
            tr.setLayoutParams(trp);
            tl.addView(tr);

            //Adiciona Imagem na linha
            ImageView img = new ImageView(getActivity());
            //img.setImageDrawable();
            tr.addView(img);

            TextView produto = new TextView(getActivity());
            produto.setText(prod.getDescricao());
            tr.addView(produto);

            TextView preco = new TextView(getActivity());
            preco.setText(String.valueOf(prod.getValor()));
            tr.addView(preco);

            EditText qtd = new EditText(getActivity());
            qtd.setText("1");
            tr.addView(qtd);

            Button remover = new Button(getActivity());
            remover.setText("Remover");
            tr.addView(remover);

            Toast.makeText(getActivity(), "Produto Adicionado", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(getActivity(), "Produto Não Encontrado!!", Toast.LENGTH_SHORT).show();
        }

    }
}
