package poc.spring.boot.controller;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {
    @PostMapping("/test/upload")
    public ResponseEntity<?> uploadFile(
    		@RequestParam("fileid") String fileId,
    		@RequestParam("description") String description,
            @RequestParam("thefile") MultipartFile uploadfile) {

    	FileInfo fileInfo = new FileInfo();

    	if (uploadfile.isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }

        try {

        	fileInfo.setFileId(fileId);
        	fileInfo.setDescription(description);
        	
        	String content = new String(uploadfile.getBytes());
        	fileInfo.setFileContent(content);
        	
        	
        	System.out.println(content);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<FileInfo>(fileInfo, new HttpHeaders(), HttpStatus.OK);

    }

}
