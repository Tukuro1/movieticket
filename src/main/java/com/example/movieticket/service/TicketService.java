package com.example.movieticket.service;

import com.example.movieticket.model.Ticket;
import com.example.movieticket.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    @Autowired
    private TicketRepository iticketRepository;

    public List<Ticket> getAllTickets() {
        return iticketRepository.findAll();
    }

    public Ticket getTicketById(Long id)

    {
        Optional<Ticket> optionalTicket = iticketRepository.findById(id);
        if (optionalTicket.isPresent()) {
            return optionalTicket.get();
        } else {
            throw new RuntimeException("ticket not found");
        }
    }

    public Ticket saveTicket(Ticket ticket) {
        return iticketRepository.save(ticket);
    }

    public List<Ticket> saveTickets(List<Ticket> ticket) {
        return iticketRepository.saveAll(ticket);
    }

    
    public Ticket createTicket(Ticket ticket) {
        return iticketRepository.save(ticket);
    }

    public void updateTicket(Ticket ticket) {
        iticketRepository.save(ticket);
    }

    public void deleteTicket(Long id) {
        iticketRepository.deleteById(id);
    }

    public List<Ticket> getAllByBillId(Long id) {
        return iticketRepository.findAllByBillId(id);
    }
}
