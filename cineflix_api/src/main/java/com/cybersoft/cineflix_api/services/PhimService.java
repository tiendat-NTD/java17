package com.cybersoft.cineflix_api.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybersoft.cineflix_api.entity.Phim;
import com.cybersoft.cineflix_api.repository.PhimRepository;

@Service
public class PhimService implements PhimServiceImp{
	
	@Autowired
	PhimRepository phimRepository;
	
	@Override
	public List<Map<String, ?>> getAllPhim() {
		// TODO Auto-generated method stub
		return phimRepository.getPhimAndLoaiPhim();
	}

	@Override
	public List<Map<String, ?>> getPhimByQuocGia(Integer id) {
		// TODO Auto-generated method stub
		return phimRepository.getPhimByQuocGia(id);
		//return null;
	}

	@Override
	public void createPhim(Phim phim) {
		// TODO Auto-generated method stub
		phimRepository.save(phim);
	}
	


}
