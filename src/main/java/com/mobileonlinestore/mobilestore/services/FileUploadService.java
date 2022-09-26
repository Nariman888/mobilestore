package com.mobileonlinestore.mobilestore.services;

import com.mobileonlinestore.mobilestore.entities.Phone;
import com.mobileonlinestore.mobilestore.entities.Users;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    boolean uploadAvatar(MultipartFile file, Users user);
    boolean uploadPicture(MultipartFile file, Phone phone);
}
