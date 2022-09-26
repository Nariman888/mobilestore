package com.mobileonlinestore.mobilestore.controllers;

import com.mobileonlinestore.mobilestore.entities.*;
import com.mobileonlinestore.mobilestore.repositories.RoleRepository;
import com.mobileonlinestore.mobilestore.services.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
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
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class MainController {
    private final FileUploadService fileUploadService;
    private final UserService userService;
    private final PhoneService phoneService;
    private final RoleRepository roleRepository;
    private final BasketService basketService;
    private final CategoryService categoryService;
    private final FavoritesService favoritesService;
    private final CommentaryService commentaryService;
    private final OrderService orderService;
    private final BrandsService brandsService;

    @GetMapping(value = "/")
    public String homePage(Model model) {
        List<Category> categoryList = categoryService.getAllCategory();
        model.addAttribute("categories",categoryList);
        return "home";
    }
    @GetMapping(value = "/check")
    @PreAuthorize("isAuthenticated()")
    public String check(Model model) {
        List<Category> categoryList = categoryService.getAllCategory();
        model.addAttribute("categories",categoryList);
        List<Basket> baskets = basketService.getBasketByUserId(userService.getCurrentUser().getId());
        model.addAttribute("basket",baskets);
        int sum=0;
        for (Basket b:baskets) {
            sum+=b.getPhone().getPrice();
        }
        model.addAttribute("sum",sum);
        return "checkout";
    }
    @PostMapping(value = "/addOrder")
    @PreAuthorize("isAuthenticated()")
    public String addOrder() {
        List<Basket> baskets = basketService.getBasketByUserId(userService.getCurrentUser().getId());
        for (Basket b:baskets) {
            Order order = new Order();
            order.setPhone(b.getPhone());
            order.setUser(userService.getCurrentUser());
            orderService.addOrder(order);
        }
        basketService.deleteBasketsbyUser(userService.getCurrentUser().getId());
        return "redirect:/orderhistory";
    }

    @GetMapping(value = "/shop")
    public String getAllPhones(Model model) {
        List<Category> categoryList = categoryService.getAllCategory();
        List<Phone> phones = phoneService.getAllPhones();
        List<Brands> brands = brandsService.getAllBrands();
        model.addAttribute("allPhones", phones);
        model.addAttribute("categories",categoryList);
        model.addAttribute("brands",brands);
        return "shop";
    }
    @PostMapping(value = "/search")
    public String getPhoneBySearch(Model model,@RequestParam(name = "search") String search){
        List<Phone> phones = phoneService.getPhonesByModel(search);
        List<Category> categoryList = categoryService.getAllCategory();
        List<Brands> brands = brandsService.getAllBrands();
        model.addAttribute("allPhones", phones);
        model.addAttribute("categories",categoryList);
        model.addAttribute("brands",brands);
        return "shop";
    }
    @PostMapping(value = "/searchbyprice")
    public String getPhoneByPrice(Model model,@RequestParam(name = "from") int from,
                                  @RequestParam(name = "till") int till){
        List<Phone> phones = phoneService.getAllByPriceRange(from, till);
        List<Category> categoryList = categoryService.getAllCategory();
        List<Brands> brands = brandsService.getAllBrands();
        model.addAttribute("allPhones", phones);
        model.addAttribute("categories",categoryList);
        model.addAttribute("brands",brands);
        return "shop";
    }
    @GetMapping(value = "/mintomaxprice")
    public String getPhonesByMinToMax(Model model){
        List<Phone> phones = phoneService.getAllByPriceMinToMax();
        List<Category> categoryList = categoryService.getAllCategory();
        List<Brands> brands = brandsService.getAllBrands();
        model.addAttribute("allPhones", phones);
        model.addAttribute("categories",categoryList);
        model.addAttribute("brands",brands);
        return "shop";
    }
    @GetMapping(value = "/maxtominprice")
    public String getPhonesByMaxToMin(Model model){
        List<Phone> phones = phoneService.getAllByPriceMaxToMin();
        List<Category> categoryList = categoryService.getAllCategory();
        List<Brands> brands = brandsService.getAllBrands();
        model.addAttribute("allPhones", phones);
        model.addAttribute("categories",categoryList);
        model.addAttribute("brands",brands);
        return "shop";
    }
    @GetMapping(value = "/findbybrand/{id}")
    public String getPhonebyBrand(Model model,@PathVariable(name = "id") Long id){
        List<Phone> phones = phoneService.getPhonesByBrand(id);
        List<Category> categoryList = categoryService.getAllCategory();
        List<Brands> brands = brandsService.getAllBrands();
        model.addAttribute("allPhones", phones);
        model.addAttribute("categories",categoryList);
        model.addAttribute("brands",brands);
        return "shop";
    }
    @GetMapping(value = "/shop/{id}")
    public String getAllPhones(Model model,@PathVariable(name = "id") Long id) {
        List<Category> categoryList = categoryService.getAllCategory();
        List<Phone> phones = phoneService.getPhonesByCategoryId(id);
        List<Brands> brands = brandsService.getAllBrands();
        model.addAttribute("allPhones", phones);
        model.addAttribute("categories",categoryList);
        model.addAttribute("brands",brands);
        return "shop";
    }

    @GetMapping(value = "/forbidden")
    public String forbidden(Model model) {
        List<Category> categoryList = categoryService.getAllCategory();
        model.addAttribute("categories",categoryList);
        return "forbidden";
    }

    @GetMapping(value = "/login")
    public String login(Model model) {
        List<Category> categoryList = categoryService.getAllCategory();
        model.addAttribute("categories",categoryList);
        return "login";
    }

    @GetMapping(value = "/signup")
    public String signup(Model model) {

        List<Category> categoryList = categoryService.getAllCategory();
        model.addAttribute("categories",categoryList);
        return "signup";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/profile")
    public String profile(Model model) {
        List<Category> categoryList = categoryService.getAllCategory();
        model.addAttribute("categories",categoryList);
        return "profile";
    }

    @PostMapping(value = "/signup")
    public String signup(@RequestParam(name = "user_email") String email,
                         @RequestParam(name = "user_password") String password,
                         @RequestParam(name = "user_re_password") String rePassword,
                         @RequestParam(name = "user_full_name") String fullName) {
        List<Role> roles = new ArrayList<>();
        Role role = roleRepository.findRoleByRole("ROLE_USER");
        roles.add(role);
        Users user = new Users();
        user.setEmail(email);
        user.setPassword(password);
        user.setFullName(fullName);
        user.setAvatar("default");
        user.setRoles(roles);
        if (userService.addUser(user, rePassword) != null) {
            return "redirect:/login";
        }
        return "redirect:signup?error";
    }

    @PostMapping("/updatePassword")
    @PreAuthorize("isAuthenticated()")
    public String updatePassword(@RequestParam(name = "user_old_password") String oldPassword,
                                 @RequestParam(name = "user_new_password") String newPassword,
                                 @RequestParam(name = "user_re_new_password") String reNewPassword) {
        if (userService.updateUserPassword(oldPassword, newPassword, reNewPassword)) {
            return "redirect:/profile?success";
        }
        return "redirect:/profile?error";
    }

    @Value("${loadURL}")
    private String loadURL;

    @PostMapping(value = "/uploadava")
    @PreAuthorize("isAuthenticated()")
    public String uploadAvatar(@RequestParam(name = "avatar") MultipartFile file) {
        if (file.getContentType().equals("image/jpeg")) {
            fileUploadService.uploadAvatar(file, userService.getCurrentUser());
        }
        return "redirect:/profile";
    }
    @GetMapping(value = "/getavatar/{token}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getAvatar(@PathVariable(name = "token", required = false) String token) throws IOException {
        String pictureURL = loadURL + "default.jpg";
        if (token != null) {
            pictureURL = loadURL + token + ".jpg";
        }
        InputStream in;
        try {
            ClassPathResource resource = new ClassPathResource(pictureURL);
            in = resource.getInputStream();
        } catch (Exception e) {
            pictureURL = loadURL + "default.jpg";
            ClassPathResource resource = new ClassPathResource(pictureURL);
            in = resource.getInputStream();
        }
        return IOUtils.toByteArray(in);
    }

    @PostMapping(value = "/addtobasket")
    @PreAuthorize("isAuthenticated()")
    public String addToBasket(@RequestParam(name = "phone_id") Long id,
                              @RequestParam(name = "direction") String direction) {

        basketService.addToBasket(id);
        return "redirect:" + direction;
    }

    @GetMapping(value = "/details/{id}")
    public String phoneDetails(Model model, @PathVariable(name = "id") Long id) {
        List<Category> categoryList = categoryService.getAllCategory();
        model.addAttribute("categories",categoryList);
        List<Commentary> commentaryList = commentaryService.allCommentariesByPhone(id);
        model.addAttribute("commentaries",commentaryList);
        Phone phone = phoneService.getPhoneDetails(id);
        model.addAttribute("phone", phone);
        return "detail";
    }
    @GetMapping(value = "/basket")
    @PreAuthorize("isAuthenticated()")
    public String basket(Model model){
        List<Category> categoryList = categoryService.getAllCategory();
        List<Basket> basketList = basketService.getBasketByUserId(userService.getCurrentUser().getId());
        int sum=0;
        for (Basket b:basketList) {
            sum+=b.getPhone().getPrice();
        }
        model.addAttribute("basket",basketList);
        model.addAttribute("categories",categoryList);
        model.addAttribute("sum",sum);
        return "basket";
    }
    @PostMapping(value = "/deletefrombasket")
    @PreAuthorize("isAuthenticated()")
    public String deleteFromBasket(@RequestParam(name = "basket_id") Long id) {
        basketService.deleteFromBasket(id);
        return "redirect:/basket";
    }
    @PostMapping(value = "/addtofavorites")
    @PreAuthorize("isAuthenticated()")
    public String addToFavorites(@RequestParam(name = "phone_id") Long id,
                                 @RequestParam(name = "direction") String direction) {
        favoritesService.addtoFavorite(id);
        return "redirect:" + direction;
    }
    @PostMapping(value = "/deletefromfavorites")
    @PreAuthorize("isAuthenticated()")
    public String deleteFromFavorites(@RequestParam(name = "favorites_id") Long id) {
        favoritesService.deleteFromFavorites(id);
        return "redirect:/favorites";
    }
    @GetMapping(value = "/favorites")
    @PreAuthorize("isAuthenticated()")
    public String favorites(Model model){
        List<Category> categoryList = categoryService.getAllCategory();
        List<Favorites> favorites = favoritesService.getFavoritesByUserId(userService.getCurrentUser().getId());
        List<Favorites> addedFavorites = basketService.getAddedFavorites(userService.getCurrentUser(), favorites);
        model.addAttribute("favorites",favorites);
        model.addAttribute("categories",categoryList);
        model.addAttribute("addedFavorites", addedFavorites);
        return "favorites";
    }
    @PostMapping(value = "/deletefrombasketbyPhoneId")
    @PreAuthorize("isAuthenticated()")
    public String deleteFromBasketByPhoneId(@RequestParam(name = "phone_id") Long id) {
        basketService.deleteByPhoneId(id);
        return "redirect:/favorites";
    }
    @PostMapping(value = "/addcommentary")
    @PreAuthorize("isAuthenticated()")
    public String addCommentary(@RequestParam(name = "commentary") String commentary,
                                @RequestParam(name = "phone_id") Long id) {
        Commentary commentary1=new Commentary();
        commentary1.setCommentary(commentary);
        commentary1.setPhone(phoneService.getPhoneDetails(id));
        commentary1.setUser(userService.getCurrentUser());
        commentaryService.addCommentary(commentary1);
        return "redirect:/details/" + commentary1.getPhone().getId();
    }
    @GetMapping(value = "/orderhistory")
    @PreAuthorize("isAuthenticated()")
    public String orderHistory(Model model){
        List<Category> categoryList = categoryService.getAllCategory();
        List<Order> orders = orderService.getOrdersByUser(userService.getCurrentUser().getId());
        model.addAttribute("orders",orders);
        model.addAttribute("categories",categoryList);
        return "orderhistory";
    }

}