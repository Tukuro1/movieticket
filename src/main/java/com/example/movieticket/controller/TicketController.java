package com.example.movieticket.controller;

import com.example.movieticket.model.Movie_Schedu;
import com.example.movieticket.model.RowChair;
import com.example.movieticket.model.Ticket;
import com.example.movieticket.service.RoomService;
import com.example.movieticket.service.RowChairService;
import com.example.movieticket.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;
//    @Autowired
//    private Movie ticketService;
//    @Autowired
//    private TicketService ticketService;


    @GetMapping
    public String showAllTicket(Model model)
    {
        List<Ticket> ticket =  ticketService.getAllTickets();
        model.addAttribute("tickets", ticket);
        model.addAttribute("row_name","tickets");
        model.addAttribute("chair_count","tickets");
        return "ticket/list";
    }

    @GetMapping("/add")
    public String addTicketForm(Model model){
        model.addAttribute("ticket",new Ticket());

        return "ticket/add";
    }


    @PostMapping("/add")
    public String addTicket(@Valid @ModelAttribute("ticket") Ticket ticket , BindingResult bindingResult , Model model){
        if(bindingResult.hasErrors())
        {

            return "ticket/add";
        }
        ticketService.createTicket(ticket);
        return "redirect:/tickets";
    }

    @GetMapping("/edit/{id}")
    public String editTicketForm(@PathVariable("id") long id, Model model){
        Ticket editTicket =  ticketService.getTicketById(id);
        if(editTicket != null){
            model.addAttribute("ticket", editTicket);

            return "ticket/edit";
        }else {
            return "not-found";
        }
    }
    @PostMapping("/edit")
    public String editTicket(@Valid @ModelAttribute("ticket") Ticket updateTicket, BindingResult bindingResult, Model model ){
        if (bindingResult.hasErrors()){
            model.addAttribute("tickets", ticketService.getAllTickets());

            return "ticket/edit";
        }
        ticketService.getAllTickets().stream()
                .filter(ticket -> ticket.getClass() == updateTicket.getClass())
                .findFirst()
                .ifPresent( rowchair -> {

                    ticketService.updateTicket(updateTicket);
                });
        return "redirect:/tickets";
    }
    @PostMapping("/delete/{id}")
    public String deleteTicket(@PathVariable("id") long id){
        ticketService.deleteTicket(id);
        return "redirect:/tickets";
    }
}
