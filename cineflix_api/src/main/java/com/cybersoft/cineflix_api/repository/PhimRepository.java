package com.cybersoft.cineflix_api.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cybersoft.cineflix_api.entity.Phim;

@Repository
public interface PhimRepository extends JpaRepository<Phim, Long>{
	
	@Query(value = "call GetPhimByQuocGia(:id)", nativeQuery = true)
	List<Map<String, ?>> getPhimByQuocGia(@Param("id") int id);
	
	@Query(value = "call GetPhimAndLoaiPhim()", nativeQuery = true)
	List<Map<String, ?>> getPhimAndLoaiPhim();
}
