package com.example.formapi.service;

import com.example.formapi.model.Form;
import com.example.formapi.repository.FormRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormService {
    private final FormRepository formRepository;

    public FormService(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    public List<Form> getAllForms() {
        return formRepository.findAll();
    }

    public List<Form> getAllPublishedForms() {
        return formRepository.findByPublishedTrue();
    }
    public Form getFormById(Long id) {
        return formRepository.findById(id).orElseThrow(() -> new RuntimeException("Form not found"));
    }

    public Form createForm(Form form) {
        if (form.getFields() != null) {
            form.getFields().forEach(field -> field.setForm(form)); // تنظیم ارتباط دوطرفه
        }
        return formRepository.save(form);
    }

    public Form updateForm(Long id, Form updatedForm) {
        Form form = getFormById(id);
        form.setName(updatedForm.getName());
        form.setPublished(updatedForm.isPublished());
        return formRepository.save(form);
    }

    public void deleteForm(Long id) {
        formRepository.deleteById(id);
    }

    public Form togglePublishForm(Long id) {
        // Fetch the form by ID
        Form form = formRepository.findById(id).orElseThrow(() -> new RuntimeException("Form not found"));

        // Toggle the 'published' status
        form.setPublished(!form.isPublished());

        // Save the updated form back to the database
        return formRepository.save(form);
    }


}
