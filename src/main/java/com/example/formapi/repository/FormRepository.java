package com.example.formapi.repository;

import com.example.formapi.model.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FormRepository extends JpaRepository<Form, Long> {
    List<Form> findByPublishedTrue(); // Custom query to fetch published forms
}
