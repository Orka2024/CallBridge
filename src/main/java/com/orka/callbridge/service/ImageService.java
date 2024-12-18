package com.orka.callbridge.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

	String uploadImage(MultipartFile cImage, String cImageFileName);

	String getUrlFromPublicId(String publicId);

	String cibiluploadImage(MultipartFile cibilupload,String filename);

	String getUrlFromPublicIdsec(String publicIdsec);
}
