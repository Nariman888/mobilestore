package com.mobileonlinestore.mobilestore.services.imp;

import com.mobileonlinestore.mobilestore.entities.Phone;
import com.mobileonlinestore.mobilestore.entities.Users;
import com.mobileonlinestore.mobilestore.services.FileUploadService;
import com.mobileonlinestore.mobilestore.services.PhoneService;
import com.mobileonlinestore.mobilestore.services.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileUploadServiceImp implements FileUploadService {

    @Autowired
    private UserService userService;
    @Autowired
    private PhoneService phoneService;
    @Value("${targetURL}")
    private String targetURl;
    @Override
    public boolean uploadAvatar(MultipartFile file, Users user) {
        try {
            String filetoken = DigestUtils.sha1Hex(user.getId() + "_!$oks1&");
            String filename = filetoken + ".jpg";
            byte bytes[] = file.getBytes();
            Path path = Paths.get(targetURl, filename);
            Files.write(path,bytes);
            user.setAvatar(filetoken);
            userService.updateUser(user);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean uploadPicture(MultipartFile file,Phone phone) {
        try{
            String imgtoken = DigestUtils.sha1Hex(phone.getId() + "!_!52$img8#");
            String imgname = imgtoken + ".jpg";
            byte bytes[] = file.getBytes();
            Path path = Paths.get(targetURl, imgname);
            Files.write(path,bytes);
            phone.setPhoto(imgtoken);
            phoneService.updatePhone(phone);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
