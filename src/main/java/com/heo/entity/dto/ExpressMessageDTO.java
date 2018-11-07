package com.heo.entity.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ExpressMessageDTO {
    private Long id;

    private Long providerId;

    private Date createdAt;
}
