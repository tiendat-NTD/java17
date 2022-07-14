package com.cybersoft.cineflix_api.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "quocgia")
public class QuocGia {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "ten_quocgia")
	private String ten_quocgia;
	
	@OneToMany(mappedBy = "idQuocGia")
	private Set<Phim> listPhim;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTen_quocgia() {
		return ten_quocgia;
	}

	public void setTen_quocgia(String ten_quocgia) {
		this.ten_quocgia = ten_quocgia;
	}

	public Set<Phim> getListPhim() {
		return listPhim;
	}

	public void setListPhim(Set<Phim> listPhim) {
		this.listPhim = listPhim;
	}
	
}
