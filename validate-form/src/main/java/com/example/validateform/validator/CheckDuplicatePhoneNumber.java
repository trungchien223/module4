package com.example.validateform.validator;

import com.example.validateform.model.User;
import com.example.validateform.repository.IUserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class CheckDuplicatePhoneNumber implements ConstraintValidator<DuplicatePhoneNumber, String> {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public void initialize(DuplicatePhoneNumber duplicatePhoneNumber) {
    }

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {
        User user = userRepository.findByPhoneNumber(contactField);
        return user == null;
    }
}
