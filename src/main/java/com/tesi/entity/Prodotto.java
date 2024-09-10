package com.tesi.entity;

import jakarta.persistence.*;

import java.io.Serializable;

import java.util.List;

@Entity
public class Prodotto implements Serializable {
	public static final String[] brands = {
			"Zara", "H&M", "Uniqlo", "GAP",
			"Nike", "Adidas", "Puma", "Levi's", "Supreme",
			"Under Armour", "Reebok", "Columbia Sportswear",
			"Gucci", "Prada", "Emporio Armani"
	};

	public static final String[] categorie = {
			"Magliette", "Camicie", "Felpe", "Giubbotti", "Cappotti",
			"Pantaloni", "Jeans", "Shorts", "Gonne", "Abiti", "Costumi da Bagno",
			"Biancheria Intima", "Calze", "Scarpe", "Stivali", "Sandali",
			"Accessori", "Cappelli", "Guanti", "Sciarpe", "Borse",
			"Abbigliamento Sportivo", "Abbigliamento da Notte", "Abbigliamento da Casa",
			"Abbigliamento Maternità", "Abbigliamento da Lavoro", "Abbigliamento per Bambini"
	};

	public static final String[] condizioni = {
			"Nuovo con cartellino", "Nuovo senza cartellino", "Ottime",
			"Buone", "Discrete"
	};

	private String titolo;
	private String descrizione;
	private String brand, categoria, condizione;
	private double prezzo;
	private String proprietario;
	@Id @GeneratedValue
	private Long id;
	private String compratore;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FotoByteArray> foto;
	private int likes;

	public Prodotto(String proprietario, String titolo, String descrizione, String categoria, String brand, String condizione, double prezzo) {
		this.proprietario = proprietario;
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.categoria = categoria;
		this.brand = brand;
		this.condizione = condizione;
		this.prezzo = prezzo;
		likes=0;
		//likes = new HashSet<>();
	}

	public Prodotto() {}

	public String getProprietario() {
		return proprietario;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCondizione() {
		return condizione;
	}

	public void setCondizione(String condizioni) {
		this.condizione = condizioni;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void update(Prodotto newProdotto) {
		titolo = newProdotto.titolo;
		descrizione = newProdotto.descrizione;
		categoria = newProdotto.categoria;
		brand = newProdotto.brand;
		condizione = newProdotto.condizione;
		prezzo = newProdotto.prezzo;
		compratore = newProdotto.compratore;
		//likes.addAll(newProdotto.likes);
	}

	public void setIdCompratore(String compratore) {
		this.compratore = compratore;
	}

	public String getCompratore() {
		return compratore;
	}

	public boolean isBought() {
		return compratore!=null;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	//	public Set<Likes> getLikes() {
//		return likes;
//	}
//
//	public void setLikes(Set<Likes> likes) {
//		this.likes = likes;
//	}
//
//	public int getMiPiace() {
//		return likes.size();
//	}
}
