package io.cliffton.chota.controller;

import io.cliffton.chota.model.Link;
import io.cliffton.chota.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.net.MalformedURLException;
import java.net.URL;

@Controller
@RequestMapping("/r")
public class RedirectController {

    @Autowired
    LinkRepository linkRepository;

    @RequestMapping("/{shorturl}")
    public RedirectView redirect(@PathVariable(value = "shorturl") String shorturl){

        String alpha = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        long linkId = 0;
        for(int i = shorturl.length() - 1; i >= 0 ; i--){
            char currentChar = shorturl.charAt(i);
            int currentPlace = alpha.indexOf(currentChar);
            linkId += currentPlace * Math.pow(62, i);
        }

        Link link = linkRepository.findOne(linkId);
        String redirectStr = null;
        try{
            URL redirectUrl = new URL(link.getLongurl());
            redirectStr = redirectUrl.toString();
        }
        catch (MalformedURLException e){
            System.err.println(e.toString());
            redirectStr = "http://" + link.getLongurl();
        }

        return new RedirectView(redirectStr);
    }
}
