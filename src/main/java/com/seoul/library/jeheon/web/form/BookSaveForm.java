package com.seoul.library.jeheon.web.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class BookSaveForm {

    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @NotBlank
    private String publisher;
    @NotNull
    @Positive
    private Integer quantity;
}
