package com.example.validateform.validator;

import com.example.validateform.repository.IUserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class CheckDuplicateEmail implements ConstraintValidator<DuplicateEmail, String> {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public void initialize(DuplicateEmail duplicateEmail) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) {
            return true;
        }
        return !userRepository.existsByEmail(email);
    }
}
