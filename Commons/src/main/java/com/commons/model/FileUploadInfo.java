package com.commons.model;


import lombok.Data;

@Data
public class FileUploadInfo {

    private String fileLink;
    private String thumbnailUrl;
    private String contentType;
    private String size;
    private String fileName;
    private byte[] bytes;
}

