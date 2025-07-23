package com.example.validatesong.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongDto {
    @NotBlank(message = "Tên bài hát không được để trống")
    @Size(max = 800, message = "Tên bài hát không được vượt quá 800 ký tự")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Tên bài hát không được chứa ký tự đặc biệt")
    private String title;

    @NotBlank(message = "Nghệ sĩ không được để trống")
    @Size(max = 300, message = "Tên nghệ sĩ không được vượt quá 300 ký tự")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Tên nghệ sĩ không được chứa ký tự đặc biệt")
    private String artist;

    @NotBlank(message = "Thể loại không được để trống")
    @Size(max = 1000, message = "Thể loại không được vượt quá 1000 ký tự")
    @Pattern(regexp = "^[a-zA-Z0-9\\s,]+$", message = "Thể loại chỉ được phép chứa chữ, số, khoảng trắng và dấu phẩy")
    private String genre;
}
