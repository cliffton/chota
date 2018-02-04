package io.cliffton.chota.controller;

import io.cliffton.chota.model.Link;
import io.cliffton.chota.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class LinkController {

    @Value("${app.domain}")
    private String domain;

    @Autowired
    LinkRepository linkRepository;

    @PostMapping("/link")
    public ResponseEntity<Link> createLink(@Valid @RequestBody Link link) {
        String alpha = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        linkRepository.save(link);
        long linkId = link.getId();
        long remainder = 0;
        long base = alpha.length();
        ArrayList<Integer> digits = new ArrayList<>();
        while (linkId > 0) {
            remainder = linkId % base;
            digits.add((int) remainder);
            linkId = linkId / base;
        }

        String shorturl = "";
        for (int i = digits.size() - 1; i >= 0; i--) {
            shorturl += alpha.charAt(digits.get(i));
        }

        link.setShorturl(shorturl);
        linkRepository.save(link);
        link.setShorturl(domain + "r/" + shorturl);
        return ResponseEntity.ok(link);
    }
}
