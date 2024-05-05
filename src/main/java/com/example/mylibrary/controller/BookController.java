package com.example.mylibrary.controller;

import com.example.mylibrary.entity.Book;
import com.example.mylibrary.repo.AuthorRepository;
import com.example.mylibrary.repo.BookRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class BookController {

    private static final Logger logger  = LoggerFactory.getLogger(BookController.class);




    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    // класс с помощью которого можно по расписанию выполнять задания
    // можно указывать дату + время для однократных задач
    // можно узазывать дату + время для первого запуска и интервал времени между повторными запусками
    private TaskScheduler taskScheduler;




    // GET http://localhost:8080/schedule
    @GetMapping("/schedule")
    @ResponseBody
    public String schedule()
    {

        // задача которую будем выполнять
        Runnable task = () -> logger.info("SCHEDULE " + System.currentTimeMillis());
        taskScheduler.schedule(
                task,
                new Date(
                        System.currentTimeMillis() + 5_000
                )
        );




        return "scheduled";
    }



    // GET http://localhost:8080/books
    @GetMapping("/books")
    public String getAll(Model model)
    {

         /*
        trace - трассировка
        debug - информация отладочная
        info - информирование
        warn - предупреждение
        error - ошибка
         */
        logger.info("getAll");


        model.addAttribute("books", bookRepository.findAll());
        return "list";
    }

    // GET http://localhost:8080/books/create
    @GetMapping("/books/create")
    public String showCreateABookForm(
            Book book, Model model
    )
    {
        model.addAttribute("book", book);
        model.addAttribute("authors", authorRepository.findAll());
        return "book-create";
    }


    @GetMapping("/books/edit/{bookId}")
    public String showEditABookForm(
            @PathVariable Integer bookId,
            Model model
    )
    {
        Book book = bookRepository.findById(bookId).orElse(null);
        model.addAttribute("book", book);
        model.addAttribute("authors", authorRepository.findAll());
        return "book-modify";
    }


    @PostMapping("/books/modify")
    public String modifyABook(
            @Valid Book book, // для экземпляра будут выполнены проверки
            BindingResult result, // тут будут результаты проверки
            Model model // используется для возвращения некорректно заполненного
            // экземпляра и ошибок
    )
    {

        // проверить
        if(result.hasErrors())
        {
            // если есть ошибки, вернуться с ними на страницу создания
            // вернуть ошибочный экземпляр Book и сами ошибки на
            // страницу создания книги
            model.addAttribute("authors", authorRepository.findAll());
            return "book-modify";
        }
        // если проблем нет, сохранить книжку через репо
        bookRepository.save(book);
        // перенаправить пользователя в список книг
        return "redirect:/books";
    }


    @PostMapping("/books")
    public String createABook(
            @Valid Book book, // для экземпляра будут выполнены проверки
            BindingResult result, // тут будут результаты проверки
            Model model // используется для возвращения некорректно заполненного
            // экземпляра и ошибок
    )
    {

        // проверить
        if(result.hasErrors())
        {
            // если есть ошибки, вернуться с ними на страницу создания
            // вернуть ошибочный экземпляр Book и сами ошибки на
            // страницу создания книги
            model.addAttribute("authors", authorRepository.findAll());
            return "book-create";
        }
        // если проблем нет, сохранить книжку через репо
        bookRepository.save(book);
        // перенаправить пользователя в список книг
        return "redirect:/books";
    }
}
