package com.cybersoft.cineflix_api.pojo;

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PhimShow {
	@JsonProperty("ten_phim")
	private String ten_phim;
	@JsonProperty("hinh_anh")
	private String hinh_anh;
	@JsonProperty("loai_phim")
	private String loai_phim;
	private BigInteger id;
	
	
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
	public String getLoai_phim() {
		return loai_phim;
	}
	public void setLoai_phim(String ten_loai) {
		this.loai_phim = ten_loai;
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	
	
	
	
	
}
