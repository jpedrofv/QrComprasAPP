package br.com.qrcompras.models;

/**
 * Created by Pee on 02/10/2017.
 */

public class Parceiro {

    private Integer id_PJ;
    private String razao_social;
    private String cnpj;
    private String telefone;
    private String rua;
    private String cep;
    private String numero;
    private String bairro;
    private String cidade;

    public Integer getId_PJ() {
        return id_PJ;
    }

    public void setId_PJ(Integer id_PJ) {
        this.id_PJ = id_PJ;
    }

    public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
