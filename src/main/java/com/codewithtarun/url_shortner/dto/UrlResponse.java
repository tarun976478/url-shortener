package com.codewithtarun.url_shortner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UrlResponse {
    private Long id;
    private String originalUrl;
    private String shorturl;
}
