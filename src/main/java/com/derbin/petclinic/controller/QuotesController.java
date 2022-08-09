package com.derbin.petclinic.controller;

import com.derbin.petclinic.model.Quotes;
import com.derbin.petclinic.service.QuotesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quotes")
public class QuotesController {
    private final QuotesService quotesService;

    public QuotesController(QuotesService quotesService) {
        this.quotesService = quotesService;
    }

    @GetMapping
    public List<Quotes> getAllQuotes() {
        return quotesService.getAllQuotes();
    }

    @GetMapping("/{id}")
    public Quotes getQuotesById(@PathVariable final Long id) {
        return quotesService.getQuotesById(id);
    }

    @GetMapping("/random")
    public Quotes getRandomQuote() throws Exception {
        final Long random = quotesService.randomQuotes();
        return quotesService.getQuotesById(random);
    }

    @PostMapping()
    public Quotes createQuotes(@RequestBody final Quotes quotes) {
        return quotesService.addQuotes(quotes);
    }

    @DeleteMapping("/{id}")
    public void deleteQuotes(@PathVariable final Long id) {
        quotesService.deleteQuotes(id);
    }
}
