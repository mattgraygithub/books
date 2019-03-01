package com.books;

import com.books.domain.SearchResults;
import com.books.dao.GoogleBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.util.StringUtils.hasText;

@Controller
public class BookController {

    @Autowired
    private GoogleBooksRepository googleBooksRepository;

    @Autowired
    private Pagination pagination;

    @GetMapping("/")
    public String index(
            @RequestParam(name = "q", required = false) String query,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            Model model) {
        populateModelForQueryOrEmptyQuery(query, page, pageSize, model);
        return "index";
    }

    @GetMapping("/*")
    public String defaultMapping(
            @RequestParam(name = "q", required = false) String query,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            Model model) {
        populateModelForQueryOrEmptyQuery(query, page, pageSize, model);
        if (!hasText(query)) {
            model.addAttribute("invalidUrl", "Where are you going?! Come back!");
        }
        return "index";
    }

    private void populateModelForQueryOrEmptyQuery(
            @RequestParam(name = "q", required = false) String query,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize, Model model) {
        if (hasText(query)) {
            populateModel(query, page, pageSize, model);
        } else if (query != null) {
            model.addAttribute("emptySearch", true);
        }
    }

    private void populateModel(String query, int page, int pageSize, Model model) {
        SearchResults searchResults = googleBooksRepository.getSearchResults(query, page, pageSize);
        model.addAttribute("searchResults", searchResults);
        model.addAttribute("query", query);
        model.addAttribute("page", page);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pages", pagination.getPaginationPages(page, pageSize, searchResults));
        model.addAttribute("emptySearch", false);
    }
}
