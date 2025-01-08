package com.orka.callbridge.service.impl;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.orka.callbridge.helper.AppConstants;
import com.orka.callbridge.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

	private Cloudinary cloudinary;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public ImageServiceImpl(Cloudinary cloudinary) {
		this.cloudinary = cloudinary;
	}

	@Override
	public String uploadImage(MultipartFile cImage, String cImageFileName) {
	    // Check if the file is empty
	    if (cImage.isEmpty()) {
	        return getUrlFromPublicId(AppConstants.DEFAULT_PROFILE_IMAGE_ID);
	    }
	    // Check for size/type constraints
	    if (cImage.getSize() > AppConstants.MAX_IMAGE_SIZE) {
	        return "File size exceeds limit of 5 MB";
	    }    
		// Code which will be uploading image
		try {
			byte[] data = new byte[cImage.getInputStream().available()];
			cImage.getInputStream().read(data);
			cloudinary.uploader().upload(data, ObjectUtils.asMap(
					"public_id", cImageFileName
					));			
			return this.getUrlFromPublicId(cImageFileName);			
		} catch (IOException e) {
	        logger.error("Error uploading image: ", e);
	        return "Error uploading file";
	    } catch (Exception e) {
	        logger.error("Unexpected error: ", e);
	        return "Unexpected error during upload";
	    }
	}

	@Override
	public String getUrlFromPublicId(String publicId) {
		return cloudinary
				.url()
				.transformation(
						new Transformation<>()
						.width(AppConstants.CLIENT_IMAGE_WIDTH)
						.height(AppConstants.CLIENT_IMAGE_HEIGHT)
						.crop(AppConstants.CLIENT_IMAGE_CROP)
						)
				.generate(publicId);
	}

	@Override
	public String cibiluploadImage(MultipartFile cibilupload,String filename)
	{			
		try {			
			byte [] dataup= new byte[cibilupload.getInputStream().available()];
			cibilupload.getInputStream().read(dataup);
			cloudinary.uploader().upload(dataup,ObjectUtils.asMap(
					"public_idsec",cibilupload.getOriginalFilename()));			
			return this.getUrlFromPublicIdsec(filename);
			}
		catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}		
	}

	@Override
	public String getUrlFromPublicIdsec(String publicIdsec) {
		return cloudinary
		.url()
		.transformation(
				new Transformation<>()
				.width(AppConstants.CLIENT_IMAGE_WIDTH)
				.height(AppConstants.CLIENT_IMAGE_HEIGHT)
				.crop(AppConstants.CLIENT_IMAGE_CROP)
				)
		.generate(publicIdsec);
	}

	

	 
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}


