package hello.hellospring.controller;

import hello.hellospring.domain.Book;
import hello.hellospring.domain.Readinglist;
import hello.hellospring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BaseController {

    @Autowired
    BookService bookService;

    // 책 목록 보여주기
    @GetMapping("/main")
    public String AllBookList(Model model) {

        List<Book> bookList = bookService.getAllBookList();
        model.addAttribute("bookList",bookList);
        return "main";
    }

    //책 목록에서 삭제하기
    @ResponseBody
    @PostMapping("/deleteFromlist")
    public Map<Object,Boolean> deleteBookFromList(@RequestParam int bookId) {
        boolean delete;
        Map<Object,Boolean> checkingDelete = new HashMap<>();
        delete = bookService.deleteBookFromList(bookId);
        System.out.println(delete);
        checkingDelete.put("deleteComplete", delete);

        return checkingDelete;
    }

    //나의 책목록
    @GetMapping("/myList")
    public String myBookList(Model model){
        List<Book> myBookList = bookService.getMyBookList(1);
        model.addAttribute("mybooklist",myBookList);
        return "myList";
    }

    //나의 책 목록에서 삭제하기
    @ResponseBody
    @PostMapping("/deleteFromMylist")
    public Map<Object,Boolean> deleteMyList(@RequestParam int bookId) {
        boolean delete;
        Map<Object,Boolean> checkingDelete = new HashMap<>();
        delete = bookService.deleteMyBook(bookId);
        System.out.println(delete);
        checkingDelete.put("deleteComplete", delete);

        return checkingDelete;
    }

    //나의 책 목록에 추가하기
    @ResponseBody
    @PostMapping("/addmylist")
    public Map<Object,Boolean> addMyList(@RequestParam int bookId) {
        boolean cheakIn;
        Map<Object,Boolean> checkingAdd = new HashMap<>();
        cheakIn = bookService.addMyBookList(bookId);
        checkingAdd.put("alreadyIn", cheakIn);

        return checkingAdd;
    }

    // 책 등록 페이지로 가기
    @GetMapping("/addBookList")
    public String addBookList(){

        return "addBookList";
    }

    //책 목록에 책 등록하기
    @PostMapping("/registerBook")
    public String registerBook(
            @RequestParam String title,
            @RequestParam String writer,
            @RequestParam int price
    ){
        bookService.insertBook(title,writer,price);
        return "redirect:/main";
    }
}

