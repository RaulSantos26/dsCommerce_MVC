package com.devsuperior.dscommerce.controllers;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService service;


//    @GetMapping("/hello/{id}")
//    public String findById(@NotNull Model model, @PathVariable Long id){
//        List list = new ArrayList();
//        ProductDTO dto =  service.findById(id);
//        list.add(dto);
//        model.addAttribute("products", list);
//        return "hello";
//    }

//    @GetMapping("/hello/{id}")
//    public ModelAndView findById(@PathVariable Long id){
//        ModelAndView modelAndView = new ModelAndView("hello");
//        List list = new ArrayList();
//        ProductDTO dto =  service.findById(id);
//        list.add(dto);
//        modelAndView.addObject ("products",list);
//        return modelAndView;
//    }

    @GetMapping("/{id}")
    public ModelAndView findById(@PathVariable Long id) {
        List list = new ArrayList();
        ModelAndView mv = new ModelAndView("index");
        ProductDTO dto = service.findById(id);
        list.add(dto);
        mv.addObject("produtos", list);

        return mv;
    }

//    @GetMapping(value = "/products/{id}")
//    public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
//       ProductDTO dto = service.findById(id);
//       return ResponseEntity.ok(dto);
//    }


//    @GetMapping("/hello")
//    public String findAll(@NotNull Model model){
//          model.addAttribute("products", service.findAll());
//        return "hello";
//    }

    @GetMapping("/")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView("index"); /*"hello"*/
        mv.addObject("products", service.findAll());
        return mv;
    }


    @GetMapping("/hello")
    public String form() {

        return "hello";
    }

    @PostMapping("/hello")
    public ModelAndView create(@Valid ProductDTO dto, BindingResult result) {
        if (result.hasErrors()){
            System.out.println("\n******Tem erros******\n");
            ModelAndView mv = new ModelAndView("hello");
            return mv;
        }
        else {
            dto= service.insert(dto);
            return new ModelAndView("redirect:/index");
        }

    }

//    @PostMapping
//    public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO dto){
//        dto= service.insert(dto);
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
//                .buildAndExpand(dto.getId()).toUri();
//        return ResponseEntity.created(uri).body(dto);
//    }
//
//    @PutMapping(value = "/{id}")
//    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @Valid @RequestBody ProductDTO dto){
//        dto = service.update(id, dto);
//        return ResponseEntity.ok(dto);
//    }
//
//    @DeleteMapping(value = "/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id){
//        service.delete(id);
//        return ResponseEntity.noContent().build();
//    }

}
