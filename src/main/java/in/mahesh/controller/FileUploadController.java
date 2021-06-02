package in.mahesh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import in.mahesh.helper.FileUpoadHelper;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class FileUploadController {
	
	@Autowired
	private FileUpoadHelper fileUploadHelper;
	
	
	
	
	
	@PostMapping("/upload-file")
	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file){
		
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		System.out.println(file.getContentType());
		System.out.println(file.getName());
		
		try {
		
		// check file size validation
		
		if(file.isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File Must contain th data!!!");
		}
		// which type file is accepting
		if(!file.getContentType().equals("image/jpeg")) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only JPEG content allowed");
		}
		
		// file upload code
		
		boolean uploadFile = fileUploadHelper.uploadFile(file);
		if(uploadFile) {
			System.out.println("File Upload Sucessfully");
			
			return ResponseEntity.ok("File Sucessfully Uploaded");
		}
		
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some Thing went Wrong...TRY AGAIN..."+file.getOriginalFilename());
	}

}
