package com.seoul.library.jeheon.web.api;

import com.seoul.library.jeheon.domain.Book;
import com.seoul.library.jeheon.service.BookService;
import com.seoul.library.jeheon.web.form.BookSaveForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/book/api")
public class BookRestController {
    final private BookService bookService;

    @GetMapping("/v1/{bookId}")
    public BookDTO findBook(@PathVariable Long bookId){
        final Book book = bookService.findBookById(bookId);
        return new BookDTO(bookId, book.getTitle(), book.getAuthor(), book.getPublisher(), book.getQuantity());
    }

    @Data
    @AllArgsConstructor
    static class BookDTO {
        private Long id;
        private String title;
        private String author;
        private String publisher;
        private Integer quantity;
    }

    @PostMapping("/v1/create")
    public ResponseEntity createBook(@RequestBody @Validated BookSaveForm bookSaveForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        final Long id = bookService.saveBook(bookSaveForm);
        return ResponseEntity.ok(new CreateResponse(id));
    }

    @Data
    @AllArgsConstructor
    static class CreateResponse {
        private Long id;
    }
}
