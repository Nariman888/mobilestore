package com.mobileonlinestore.mobilestore.controllers;

import com.mobileonlinestore.mobilestore.entities.Brands;
import com.mobileonlinestore.mobilestore.entities.Category;
import com.mobileonlinestore.mobilestore.entities.Phone;
import com.mobileonlinestore.mobilestore.services.BrandsService;
import com.mobileonlinestore.mobilestore.services.CategoryService;
import com.mobileonlinestore.mobilestore.services.FileUploadService;
import com.mobileonlinestore.mobilestore.services.PhoneService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RequestMapping(value = "/admin")
@Controller
@RequiredArgsConstructor
public class AdminController {
    private final PhoneService phoneService;
    private final CategoryService categoryService;
    private final BrandsService brandsService;
    private final FileUploadService fileUploadService;

    @GetMapping(value = "/")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String getAllPhones(Model model) {
        List<Phone> phoneList = phoneService.getAllByOrderById();
        model.addAttribute("allPhones", phoneList);
        return "adminpage";
    }

    @GetMapping(value = "/addbrand")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String addBrand(Model model) {
        List<Brands> brandsList = brandsService.getAllBrands();
        model.addAttribute("brands", brandsList);
        return "addBrand";
    }

    @GetMapping(value = "/addcategory")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String addCategory(Model model) {
        List<Category> categoryList = categoryService.getAllCategory();
        model.addAttribute("categories", categoryList);
        return "addCategory";
    }

    @GetMapping(value = "/addphone")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String addPhone(Model model) {
        List<Brands> brandsList = brandsService.getAllBrands();
        List<Category> categoryList = categoryService.getAllCategory();
        model.addAttribute("brandList", brandsList);
        model.addAttribute("categoryList", categoryList);
        return "addPhone";
    }

    @PostMapping(value = "/addBrand")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String addBrand(@RequestParam(name = "name") String name,
                           @RequestParam(name = "c_name") String country) {
        Brands brand = new Brands(name, country);
        brandsService.addBrand(brand);
        return "redirect:/admin/addbrand";
    }

    @PostMapping(value = "/deleteBrand")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String deleteBrand(@RequestParam(name = "brand_id") Long id) {
      brandsService.deleteBrand(id);
        return "redirect:/admin/addbrand";
    }

    @PostMapping(value = "/addCategory")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String addCategory(@RequestParam(name = "name") String name) {
        Category category = new Category(name);
        categoryService.addCategory(category);
        return "redirect:/admin/addcategory";
    }
    @PostMapping(value = "/deleteCategory")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String deleteCategory(@RequestParam(name = "category_id") Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/admin/addcategory";
    }
    @Value("${loadURL}")
    private String loadURL;

    @PostMapping(value = "/addPhone")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String uploadPicture(Phone phone,@RequestParam(name = "img") MultipartFile file) {
        phoneService.addPhone(phone);
        if (file.getContentType().equals("image/jpeg")) {
            fileUploadService.uploadPicture(file, phone);
        }
        return "redirect:/admin/";
    }

    @GetMapping(value = "/getpicture/{token}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getAvatar(@PathVariable(name = "token", required = false) String token) throws IOException {
        String pictureURL = loadURL + "defaultPhone.jpg";
        if (token != null) {
            pictureURL = loadURL + token + ".jpg";
//            pictureURL = loadURL + "default.jpg";
        }
        InputStream in;
        try {
            ClassPathResource resource = new ClassPathResource(pictureURL);
            in = resource.getInputStream();
        } catch (Exception e) {
            pictureURL = loadURL + "defaultPhone.jpg";
            ClassPathResource resource = new ClassPathResource(pictureURL);
            in = resource.getInputStream();
        }
        return IOUtils.toByteArray(in);
    }
    @GetMapping(value = "/details/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String details(Model model,@PathVariable(name = "id") Long id) {
        Phone phone = phoneService.getPhoneDetails(id);
        model.addAttribute("ph",phone);
        List<Brands> brandsList = brandsService.getAllBrands();
        List<Category> categoryList = categoryService.getAllCategory();
        model.addAttribute("brandList", brandsList);
        model.addAttribute("categoryList", categoryList);
        return "detailsPhone";
    }
    @PostMapping(value = "/updatePhone")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String update(Phone phone,@RequestParam(name = "img") MultipartFile file) {
        phone.setPhoto(phoneService.getPhoneDetails(phone.getId()).getPhoto());
        phoneService.updatePhone(phone);
        if (file.getContentType().equals("image/jpeg")) {
            fileUploadService.uploadPicture(file, phone);
        }
        return "redirect:/admin/";
    }
    @PostMapping(value = "/deletephone")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String deletePhone(@RequestParam(name = "phone_id") Long id){
        phoneService.delete(id);
        return "redirect:/admin/";
    }
}