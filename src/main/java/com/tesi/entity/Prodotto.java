package com.tesi.entity;

import com.tesi.entity.entityoptions.Brand;
import com.tesi.entity.entityoptions.Categoria;
import com.tesi.entity.entityoptions.Condizioni;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Prodotto implements Serializable {
	private String titolo;
	private String descrizione;
	private Categoria categoria;
	private Brand brand;
	private Condizioni condizioni;
	private double prezzo;
	private int miPiace;
	private String proprietario;
	@Id @GeneratedValue
	private Long id;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FotoByteArray> foto;
	private String compratore;

	public Prodotto(String proprietario, String titolo, String descrizione, Categoria categoria, Brand brand, Condizioni condizioni, double prezzo) {
		this.proprietario = proprietario;
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.categoria = categoria;
		this.brand = brand;
		this.condizioni = condizioni;
		this.prezzo = prezzo;
		this.miPiace = 0;
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Condizioni getCondizioni() {
		return condizioni;
	}

	public void setCondizioni(Condizioni condizioni) {
		this.condizioni = condizioni;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public int getMiPiace() {
		return miPiace;
	}

	public void setMiPiace(int miPiace) {
		this.miPiace = miPiace;
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
		condizioni = newProdotto.condizioni;
		prezzo = newProdotto.prezzo;
		miPiace = newProdotto.miPiace;
		compratore = newProdotto.compratore;
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
}
