package com.example.validateform.dto;

import com.example.validateform.validator.DuplicateEmail;
import com.example.validateform.validator.DuplicatePhoneNumber;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationDto {
    @NotBlank(message = "Họ không được để trống")
    @Size(min = 5, max = 45, message = "Họ phải từ 5 đến 45 ký tự")
    private String firstName;

    @NotBlank(message = "Tên không được để trống")
    @Size(min = 5, max = 45, message = "Tên phải từ 5 đến 45 ký tự")
    private String lastName;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(
            regexp = "^(0|\\+84)(\\d{9})$",
            message = "Số điện thoại không hợp lệ. Phải bắt đầu bằng 0 hoặc +84 và có 10 chữ số"
    )
    @DuplicatePhoneNumber
    private String phoneNumber;

    @NotNull(message = "Tuổi không được để trống")
    @Min(value = 18, message = "Tuổi phải từ 18 trở lên")
    private Integer age;

    @NotBlank(message = "Email không được để trống")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Email không đúng định dạng (vd: abc@gmail.com)"
    )
    @DuplicateEmail
    private String email;

    @NotBlank(message = "Mật khẩu không được để trống")
    private String password;
}
