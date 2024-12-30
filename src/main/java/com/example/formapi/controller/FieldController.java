package com.example.formapi.controller;

import com.example.formapi.model.Field;
import com.example.formapi.service.FieldService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forms/{formId}/fields")
public class FieldController {
    private final FieldService fieldService;

    public FieldController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @GetMapping
    public List<Field> getFieldsByFormId(@PathVariable Long formId) {
        return fieldService.getFieldsByFormId(formId);
    }

    @PutMapping
    public List<Field> updateFieldsForForm(@PathVariable Long formId, @RequestBody List<Field> fields) {
        return fieldService.updateFieldsForForm(formId, fields);
    }

    @PostMapping
    public Field addFieldToForm(@PathVariable Long formId, @RequestBody Field field) {
        return fieldService.addFieldToForm(formId, field);
    }

    @DeleteMapping("/{fieldId}")
    public void deleteField(@PathVariable Long fieldId) {
        fieldService.deleteField(fieldId);
    }
}
