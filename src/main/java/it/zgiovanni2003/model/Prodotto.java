package it.zgiovanni2003.model;

public class Prodotto {
    private int prodotto_id;
    private String nome_prodotto;
    private String descrizione;
    private double prezzo;
    private String img;
    private String nome_categoria;


    public Prodotto(int prodotto_id, String nome_prodotto, String descrizione, double prezzo, String img, String nome_categoria) {
        this.prodotto_id = prodotto_id;
        this.nome_prodotto = nome_prodotto;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.img = img;
        this.nome_categoria = nome_categoria;
    }


    public int getProdottoId() {
        return prodotto_id;
    }

    public void setProdottoId(int prodotto_id) {
        this.prodotto_id = prodotto_id;
    }

    public String getNomeProdotto() {
        return nome_prodotto;
    }

    public void setNomeProdotto(String nome_prodotto) {
        this.nome_prodotto = nome_prodotto;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNomeCategoria() {
        return nome_categoria;
    }

    public void setNomeCategoria(String nome_categoria) {
        this.nome_categoria = nome_categoria;
    }
}
