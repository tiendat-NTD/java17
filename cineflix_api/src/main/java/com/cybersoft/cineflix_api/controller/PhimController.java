package com.cybersoft.cineflix_api.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cybersoft.cineflix_api.entity.Phim;
import com.cybersoft.cineflix_api.entity.QuocGia;
import com.cybersoft.cineflix_api.pojo.PhimShow;
import com.cybersoft.cineflix_api.services.FileSystemStorageServiceImp;
import com.cybersoft.cineflix_api.services.PhimServiceImp;

@RestController
@RequestMapping("/phim")
public class PhimController {
	
	@Autowired
	PhimServiceImp serviceImp;
	
	@Autowired
	FileSystemStorageServiceImp fileSystemStorageServiceImp;
	
	//@Secured({"ROLE_ADD" , "ROLE_USER"})
	@PreAuthorize("hasRole('ROLE_UPDATE')")
	@GetMapping()
	public ResponseEntity<?> getAllPhim(HttpServletRequest request){
		List<Map<String, ?>> listphim = serviceImp.getAllPhim();
		
		List<PhimShow> listphim2 = new ArrayList<PhimShow>();
		
		for (Map<String, ?> map : listphim) {
			String hinhanh = (String) map.get("hinh_anh");
			
			String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request)
		            .replacePath(null)
		            .build()
		            .toUriString();
			
			PhimShow phimShow = new PhimShow();
			
			phimShow.setId((BigInteger)map.get("id"));
			phimShow.setTen_phim((String) map.get("ten_phim"));
			phimShow.setLoai_phim((String) map.get("loai_phim"));
			phimShow.setHinh_anh(baseUrl+"/file/"+hinhanh);
			
			listphim2.add(phimShow);
			
		}
		return new ResponseEntity<List<PhimShow>>(listphim2, HttpStatus.OK);
		//return new ResponseEntity<List<Map<String, ?>>>(listphim, HttpStatus.OK);
	}
	
	@GetMapping("/{id_quocgia}")
	public ResponseEntity<?> getPhimByQuocGia(@PathVariable("id_quocgia") int id_quocgia){
		return new ResponseEntity<List<Map<String, ?>>>(serviceImp.getPhimByQuocGia(id_quocgia), HttpStatus.OK);
	}
	
	@Secured("ROLE_USER")
	@PostMapping("")
	public ResponseEntity<?> insertPhim(@RequestParam("hinh_anh") MultipartFile hinhAnh, @RequestParam("ten_phim") String tenPhim, @RequestParam("mo_ta") String moTa){
		
		fileSystemStorageServiceImp.init();
		fileSystemStorageServiceImp.saveFile(hinhAnh);
		
		Phim phim = new Phim();
		phim.setHinh_anh(hinhAnh.getOriginalFilename());
		phim.setMo_ta(moTa);
		phim.setTen_phim(tenPhim);
		
		QuocGia qGia = new QuocGia();
		qGia.setId(1);
		phim.setIdQuocGia(qGia);
		
		serviceImp.createPhim(phim);
		
		return new ResponseEntity<String>("Thêm thành công", HttpStatus.OK);
	}
	
}
