//package com.codewithtarun.url_shortner.service;
//
//import com.codewithtarun.url_shortner.dto.UrlRequest;
//import com.codewithtarun.url_shortner.dto.UrlResponse;
//import com.codewithtarun.url_shortner.entity.Url;
//import com.codewithtarun.url_shortner.repository.UrlRepository;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.util.UUID;
//
//@Service
//public class UrlService {
//
//    private final UrlRepository urlRepository;
//
//    public UrlService(UrlRepository urlRepository) {
//        this.urlRepository = urlRepository;
//    }
//
//    public UrlResponse createShortUrl(UrlRequest request) {
//
//        String shortCode = UUID.randomUUID()
//                .toString()
//                .substring(0, 8);
//
//        Url url = new Url();
//        url.setOriginalUrl(request.getOriginalUrl());
//        url.setShortCode(shortCode);
//
//        Url savedUrl = urlRepository.save(url);
//
//
//        String shortUrl = "http://localhost:8080/" + savedUrl.getShortCode();
//
//        return new UrlResponse(
//                savedUrl.getId(),
//                savedUrl.getOriginalUrl(),
//                shortUrl
//        );
//    }
//
//    public String getOriginalUrl(String shortCode) {
//
//        Url url = urlRepository.findByShortCode(shortCode)
//                .orElseThrow(() -> new RuntimeException("Short URL not found"));
//
//        return url.getOriginalUrl();
//    }
//}
package com.codewithtarun.url_shortner.service;

import com.codewithtarun.url_shortner.dto.UrlRequest;
import com.codewithtarun.url_shortner.dto.UrlResponse;
import com.codewithtarun.url_shortner.entity.Url;
import com.codewithtarun.url_shortner.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    @Value("${app.base-url:http://localhost:8080}")
    private String baseUrl;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public UrlResponse createShortUrl(UrlRequest request) {

        String shortCode = UUID.randomUUID()
                .toString()
                .substring(0, 8);

        Url url = new Url();
        url.setOriginalUrl(request.getOriginalUrl());
        url.setShortCode(shortCode);

        Url savedUrl = urlRepository.save(url);

        String shortUrl = baseUrl + "/" + savedUrl.getShortCode();

        return new UrlResponse(
                savedUrl.getId(),
                savedUrl.getOriginalUrl(),
                shortUrl
        );
    }

    public String getOriginalUrl(String shortCode) {

        Url url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("Short URL not found"));

        return url.getOriginalUrl();
    }
}