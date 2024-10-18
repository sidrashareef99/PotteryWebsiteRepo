package com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.controller;

import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.model.Customer;
import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
        @Autowired
        private CustomerService customerService;
//        @Autowired
//        private PasswordEncoder passwordEncoder;


        @GetMapping("/")
        public String getHome(){
            return "index";
        }

        @GetMapping("/login")
        public String getLogin(){
            return "login";
        }

        @PostMapping("/login")
        public String Authentication(){
                return "index";
        }

        @GetMapping("/showRegister")
        public String getRegister(Model model) {
                model.addAttribute("customer", new Customer());  // Initialize empty customer object for the form
                return "register";
        }

        @GetMapping("/custom")
        public String getCustom(){
                return "custom";
        }
        @GetMapping("/about")
        public String getAbout(){
                return "about";
        }
        @GetMapping("/ready-to-use")
        public String getRTU(){
                return "ready-to-use";
        }
        @GetMapping("/styles")
        public String getStyles(){
                return "styles";
        }
        @GetMapping("/contact")
        public String getContact(){
                return "contact";
        }
//        @GetMapping("/cart")
//        public String getCart(){
//                return "cart";
//        }


        @PostMapping("/register")
        public String registerUser(@Valid @ModelAttribute("customer") Customer customer, BindingResult result) {
                System.out.println("In registerUser");
                System.out.print("email: " + customer.getUsername());
                if (result.hasErrors()) {
                        return "register";  // Return back to the registration form if there are validation errors
                }

                //customer.setPassword(passwordEncoder.encode(customer.getPassword()));
                customerService.registerCustomer(customer);  // Use the method that creates the customer and their cart
                return "redirect:/login";
        }


        @GetMapping("/productspage")
        public String getProducts(@RequestParam(value = "style", required = false) String style, Model model) {
                if (style != null) {
                        model.addAttribute("products", customerService.getProductsByStyle(style));
                } else {
                        model.addAttribute("products", customerService.getAllProducts());
                }
                return "products";
        }

}

