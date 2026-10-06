package com.codewithtarun.url_shortner.controller;

import com.codewithtarun.url_shortner.dto.UrlRequest;
import com.codewithtarun.url_shortner.dto.UrlResponse;
import com.codewithtarun.url_shortner.entity.Url;
import com.codewithtarun.url_shortner.service.UrlService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/urls")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping
    public UrlResponse createShortUrl(@Valid @RequestBody UrlRequest request) {
        return urlService.createShortUrl(request);
    }

}
