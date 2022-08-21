package com.cybersoft.cineflix_api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cybersoft.cineflix_api.entity.LoaiPhim;
import com.cybersoft.cineflix_api.pojo.LoaiPhimShow;
import com.cybersoft.cineflix_api.services.LoaiPhimServiceImp;

@RestController
@RequestMapping("/loaiphim")
public class LoaiPhimController {
	
	@Autowired
	LoaiPhimServiceImp serviceImp;
	
	@GetMapping("")
	public ResponseEntity<?> getAllLoaiPhim(HttpServletRequest request){
		//return new ResponseEntity<List<LoaiPhim>>(serviceImp.getAllLoaiPhim(), HttpStatus.OK);
		
		List<LoaiPhim> listphim = serviceImp.getAllLoaiPhim();
		
		List<LoaiPhimShow> listphim2 = new ArrayList<LoaiPhimShow>();
		
		String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request)
	            .replacePath(null)
	            .build()
	            .toUriString();
		for (LoaiPhim loaiPhim : listphim) {
			LoaiPhimShow phimShow = new LoaiPhimShow();
			
			phimShow.setId(loaiPhim.getId());
			phimShow.setHinh_anh(baseUrl+"/file/"+loaiPhim.getHinh_anh());
			phimShow.setTen_loai(loaiPhim.getTen_loai());
			
			listphim2.add(phimShow);
		}
		
		return new ResponseEntity<List<LoaiPhimShow>>(listphim2, HttpStatus.OK);
	}
}
