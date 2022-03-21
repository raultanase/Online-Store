package com.example.proiect_v3;

import java.util.ArrayList;
import java.util.List;

import com.example.proiect_v3.product.Product;
import com.example.proiect_v3.product.ProductRepository;
import com.example.proiect_v3.product.ProductService;
import com.example.proiect_v3.user.User;
import com.example.proiect_v3.user.UserRepository;
import com.example.proiect_v3.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AppController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private ProductService service;

    @Autowired
    private UserService userService;

    @Autowired
    private static List<Product> cart = new ArrayList<>();

    float total = 0;

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "register";
    }

    @GetMapping("/loginAdmin")
    public String showLoginAdminForm(Model model) {
        List<User> listUsers = userRepo.findAll();
        model.addAttribute("listUsers", listUsers);

        return "admin";
    }

    @GetMapping("/loginManager")
    public String showLoginManagerForm(Model model) {
        List<Product> listProducts = productRepo.findAll();
        model.addAttribute("listProducts", listProducts);

        return "manager";
    }

    @RequestMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(name = "id") int id) {
        userService.deleteUser(id);

        return "redirect:/";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepo.save(user);

        return "register_success";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<Product> listProducts = productRepo.findAll();
        model.addAttribute("listProducts", listProducts);

        return "users";
    }

    @GetMapping("/products")
    public String listProducts(Model model) {
        List<Product> listProducts = productRepo.findAll();
        model.addAttribute("listProducts", listProducts);

        return "products_list";
    }

    @RequestMapping("/newUser")
    public String showNewUserPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);

        return "new_user";
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);

        return "redirect:/";
    }

    @RequestMapping("/newProduct")
    public String showNewProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);

        return "new_product";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") Product product) {
        service.save(product);

        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_product");
        Product product = service.get(id);
        mav.addObject("product", product);

        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/";
    }

    @RequestMapping("/adaugaInCos/{id}")
    public String addToCart(@PathVariable(name = "id") int id, Model model) {
        Product product = service.get(id);
        total = total + product.getPrice();
        cart.add(product);
        model.addAttribute("listCart", cart);
        model.addAttribute("total", total);

        return "cart";
    }

    @GetMapping("/bill")
    public String plateste(Model model) {
        model.addAttribute("total", total);
        cart.clear();
        total = 0;

        return "bill";
    }



}
