package in.mahesh.helper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Random;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component

public class FileUpoadHelper {
	
//	public final String UPLOAD_DIR ="D:\\Spring Boot Workspace\\01-ImageUpload\\src\\main\\resources\\static\\images";
	public final String UPLOAD_DIR ="C:\\upload-images";
	
	
	
	public boolean uploadFile(MultipartFile file) {
		
		boolean fileUploadStatus = false;
		
		try {
			
//			InputStream inputStream = file.getInputStream();  // read input stream from file
//			byte data[] = new byte[inputStream.available()];   // create byte array to store input stream data
//			inputStream.read(data); // read file data
//			
//			// now write file to location
//			FileOutputStream fileOutputStream = new FileOutputStream(UPLOAD_DIR+File.separator+file.getOriginalFilename());
//			
//			fileOutputStream.write(data);
//			
//			fileOutputStream.flush();
//			fileOutputStream.close();
			// set fileUploadStatus to true
			Random random = new Random();
			int fileCnt = random.nextInt(10000);
			
			Files.copy(file.getInputStream(), 
					Paths.get(UPLOAD_DIR+File.separator+(LocalDate.now()+"-"+fileCnt+"-"+file.getOriginalFilename())),
					StandardCopyOption.REPLACE_EXISTING);
			
			fileUploadStatus=true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return fileUploadStatus;
	}

}
