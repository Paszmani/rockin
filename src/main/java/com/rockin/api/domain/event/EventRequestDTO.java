package com.rockin.api.domain.event;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public record EventRequestDTO(String title,
                              String description,
                              Long date,
                              String city,
                              String state,
                              String eventUrl,
                              MultipartFile image) {
}
