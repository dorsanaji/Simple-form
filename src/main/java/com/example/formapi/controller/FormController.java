package com.example.formapi.controller;

import com.example.formapi.model.Form;
import com.example.formapi.service.FormService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forms/")
public class FormController {
    private final FormService formService;

    public FormController(FormService formService) {
        this.formService = formService;
    }

    @GetMapping
    public List<Form> getAllForms() {
        return formService.getAllForms();
    }

    @GetMapping("published")
    public List<Form> getAllPublishedForms() {
        return formService.getAllPublishedForms();
    }

    @GetMapping("{id}")
    public Form getFormById(@PathVariable Long id) {
        return formService.getFormById(id);
    }

    @PostMapping
    public Form createForm(@RequestBody Form form) {
        return formService.createForm(form);
    }

    @PutMapping("{id}")
    public Form updateForm(@PathVariable Long id, @RequestBody Form updatedForm) {
        return formService.updateForm(id, updatedForm);
    }

    @DeleteMapping("{id}")
    public void deleteForm(@PathVariable Long id) {
        formService.deleteForm(id);
    }

    @PostMapping("{id}/publish")
    public Form togglePublishForm(@PathVariable Long id) {
        return formService.togglePublishForm(id);
    }


}
