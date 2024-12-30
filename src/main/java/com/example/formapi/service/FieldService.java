package com.example.formapi.service;

import com.example.formapi.model.Field;
import com.example.formapi.model.Form;
import com.example.formapi.repository.FieldRepository;
import com.example.formapi.repository.FormRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldService {
    private final FieldRepository fieldRepository;
    private final FormRepository formRepository;

    public FieldService(FieldRepository fieldRepository, FormRepository formRepository) {
        this.fieldRepository = fieldRepository;
        this.formRepository = formRepository;
    }

    public List<Field> getFieldsByFormId(Long formId) {
        Form form = formRepository.findById(formId).orElseThrow(() -> new RuntimeException("Form not found"));
        return form.getFields();
    }

    public Field addFieldToForm(Long formId, Field field) {
        Form form = formRepository.findById(formId).orElseThrow(() -> new RuntimeException("Form not found"));
        field.setForm(form); // ارتباط دوطرفه را تنظیم می‌کند
        return fieldRepository.save(field);
    }

    public List<Field> updateFieldsForForm(Long formId, List<Field> fields) {
        // Fetch the form by ID
        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new RuntimeException("Form not found"));

        // Remove existing fields (if necessary) and save new ones
        form.getFields().clear();
        fields.forEach(field -> {
            field.setForm(form); // Set the relationship
            fieldRepository.save(field); // Save the field
        });

        // Save the updated form
        formRepository.save(form);

        return form.getFields();
    }
    public void deleteField(Long fieldId) {
        fieldRepository.deleteById(fieldId);
    }
}
