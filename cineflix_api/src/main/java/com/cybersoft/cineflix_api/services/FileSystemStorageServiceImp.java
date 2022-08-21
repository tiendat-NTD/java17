package com.cybersoft.cineflix_api.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileSystemStorageServiceImp {
	
	public void init();
	public boolean saveFile(MultipartFile file);
	public Resource loadFile(String fileName);
}
