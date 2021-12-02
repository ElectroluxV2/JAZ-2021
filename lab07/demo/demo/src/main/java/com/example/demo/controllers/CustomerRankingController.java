package com.example.demo.controllers;

import com.example.demo.model.Rental;
import com.example.demo.repositories.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("customers/ranking")
public class CustomerRankingController {
    private final CustomerRepository repository;

    public CustomerRankingController(final CustomerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("bySpentMoney")
    public ResponseEntity<List<CustomerRankingBySpentMoneyEntry>> bySpentMoney(@RequestParam("chart") final Optional<String> chart) {
        final var list = repository.get10CustomersByMostSpentMoney()
                .stream()
                .map(CustomerRankingBySpentMoneyEntry::from)
                .toList();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("byWatchedMovies")
    public ResponseEntity<List<CustomerRankingByWatchedMoviesEntry>> byWatchedMovies(@RequestParam("chart") final Optional<String> chart) {
        final var list = repository.get10CustomersByMostMoviesWatched()
                .stream()
                .map(CustomerRankingByWatchedMoviesEntry::from)
                .toList();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
