package com.neo.web;

import com.neo.entity.Book;
import com.neo.entity.Booking;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RountController {
    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/index")
    public String toindex() {
        return "index";
    }

    @GetMapping("/toBookList")
    public String toBookList() {
        return "bookList";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/toCollect")
    public String toCollect() {
        return "collect";
    }

    @GetMapping("/toBorrow")
    public String toBorrow() {
        return "borrow";
    }

    @GetMapping("/toManager")
    public String toManager() {
        return "manager";
    }

    @GetMapping("/showMyElective")
    public String showMyElective() {
        return "showMyElective";
    }

    @GetMapping("/toRegister")
    public String toRegister() {
        return "register";
    }

    @GetMapping("/toAdduser")
    public String toAdduser() {
        return "adduser";
    }

    @GetMapping("/toBooklist")
    public String toBooklist() {
        return "booklist";
    }

    @GetMapping("/toAddbook")
    public String toAddbook() {
        return "addbook";
    }

    @GetMapping("/toManagerlist")
    public String toManagerlist() {
        return "managerlist";
    }

    @GetMapping("/toAddmanager")
    public String toAddmanager() {
        return "addmanager";
    }
    @GetMapping("/toBooking")
    public String toBooking(Booking booking,Model model) {
        model.addAttribute("bookID", booking.getBookID());
        return "booking";
    }
    @GetMapping("/toBookinglist")
    public String toBookinglist() {
        return "bookinglist";
    }
    @GetMapping("/toEditUser")
    public String toEditUser() {
        return "editUser";
    }

    @GetMapping("/toBookDetail")
    public String toBookDetail(Book book, Model model) {
        model.addAttribute("authorIntroduce", book.getAuthorIntroduce());
        model.addAttribute("bookIntroduce", book.getBookIntroduce());
        return "bookDetail";
    }
}