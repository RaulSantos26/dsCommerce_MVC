package com.devsuperior.dscommerce.controllers;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.services.ProductService;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class ProductController {

    @Autowired
    private ProductService service;


    @GetMapping("/{id}")
    public ModelAndView findById(@PathVariable Long id) {
        List list = new ArrayList();
        ModelAndView mv = new ModelAndView("index");
        ProductDTO dto = service.findById(id);
        list.add(dto);
        mv.addObject("produtos", list);

        return mv;
    }

    @GetMapping("/show/{id}")
    public ModelAndView show(@PathVariable Long id) {
        try {
            ProductDTO dto = service.findById(id);
            ModelAndView mv = new ModelAndView("show");
            System.out.println("$$$$ " + dto + "$$$$");
            mv.addObject("product", dto);
            return mv;
        } catch (NoSuchElementException e) {
            System.out.println("$$$$$$ NÃO ACHOU O PRODUTO DE ID " + id + "$$$$$$");
            return new ModelAndView("redirect:/");
        }

    }


    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id, ProductDTO dto) {
        try {
            dto = service.findById(id);
            ModelAndView mv = new ModelAndView("edit");
            mv.addObject("product", dto);
            System.out.println("$$$$ " + dto + "$$$$");
            return mv;
        } catch (NoSuchElementException e) {
            System.out.println("$$$$$$ NÃO ACHOU O PRODUTO DE ID " + id + "$$$$$$");
            return new ModelAndView("redirect:/");

        }
    }


    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable Long id, @Valid ProductDTO dto, @NotNull BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("\n******Tem erros******\n");
            ModelAndView mv = new ModelAndView("edit");
            mv.addObject("product", dto);
            mv.addObject("idProduct", id);
            return mv;

        } else {
            dto = service.update(id, dto);
            return new ModelAndView("redirect:/show/" + dto.getId());
        }
    }


    @GetMapping("/")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView("index"); /*"hello"*/
        mv.addObject("products", service.findAll());
        return mv;
    }

    @GetMapping("/hello")
    public ModelAndView newForm(ProductDTO dto) {
        ModelAndView mv = new ModelAndView("hello");
        return mv;
    }

    @PostMapping("/hello")
    public ModelAndView insert(@Valid ProductDTO dto, @NotNull BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("\n******Tem erros******\n");
            ModelAndView mv = new ModelAndView("hello");
            return mv;
        } else {
            dto = service.insert(dto);
            return new ModelAndView("redirect:/show/" + dto.getId());
        }

    }

    @GetMapping("delete/{id}")
    public ModelAndView deleteProduct(@PathVariable("id") long id) {
        ProductDTO dto = service.findById(id);
        service.delete(dto.getId());
        return new ModelAndView("redirect:/");
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
