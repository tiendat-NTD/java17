package com.cybersoft.cineflix_api.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "phim")
public class Phim {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToMany(mappedBy = "idPhim")
	private Set<ChiTietPhim> lisChiTietPhim;
	
	@OneToMany(mappedBy = "phim")
	private Set<LoaiPhimPhim> listLoaiPhimPhim;
	
	@ManyToOne
	@JoinColumn(name = "id_quocgia")
	private QuocGia idQuocGia;
	
	@Column(name = "ten_phim")
	private String ten_phim;
	
	@Column(name = "hinh_anh")
	private String hinh_anh;
	
	@Column(name = "ngay_tao")
	private String ngay_tao;
	
	@Column(name = "mo_ta")
	private String mo_ta;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

//	public Set<ChiTietPhim> getLisChiTietPhim() {
//		return lisChiTietPhim;
//	}
//
//	public void setLisChiTietPhim(Set<ChiTietPhim> lisChiTietPhim) {
//		this.lisChiTietPhim = lisChiTietPhim;
//	}
//
//	public Set<LoaiPhimPhim> getListLoaiPhimPhim() {
//		return listLoaiPhimPhim;
//	}
//
//	public void setListLoaiPhimPhim(Set<LoaiPhimPhim> listLoaiPhimPhim) {
//		this.listLoaiPhimPhim = listLoaiPhimPhim;
//	}

//	public QuocGia getIdQuocGia() {
//		return idQuocGia;
//	}
//
	public void setIdQuocGia(QuocGia idQuocGia) {
		this.idQuocGia = idQuocGia;
	}

	public String getTen_phim() {
		return ten_phim;
	}

	public void setTen_phim(String ten_phim) {
		this.ten_phim = ten_phim;
	}

	public String getHinh_anh() {
		return hinh_anh;
	}

	public void setHinh_anh(String hinh_anh) {
		this.hinh_anh = hinh_anh;
	}

	public String getNgay_tao() {
		return ngay_tao;
	}

	public void setNgay_tao(String ngay_tao) {
		this.ngay_tao = ngay_tao;
	}

	public String getMo_ta() {
		return mo_ta;
	}

	public void setMo_ta(String mo_ta) {
		this.mo_ta = mo_ta;
	}
	
	
}
