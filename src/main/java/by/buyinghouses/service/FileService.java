package by.buyinghouses.service;

import by.buyinghouses.domain.Accommodation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {

    @Value("${upload.path}")
    private String uploadPath;

    public void saveImage(Accommodation accommodation, MultipartFile file) throws IOException {

        createUploadFolder();

        String uuidFile = createUUID();
        String resultFileName = createFileName(uuidFile, file);

        upload(file, resultFileName);

        accommodation.setFileName(resultFileName);

    }

    private void createUploadFolder(){

        File uploadFolder = new File(uploadPath);

        if(!uploadFolder.exists()){
            uploadFolder.mkdir();
        }

    }

    private String createFileName(String uuid, MultipartFile file){
        return uuid + "." + file.getOriginalFilename();
    }

    private void upload(MultipartFile file, String fileName) throws IOException{
        file.transferTo(new File(uploadPath + "/" + fileName));
    }

    private String createUUID(){
        return UUID.randomUUID().toString();
    }

}
