package io.github.paulosdoliveira.livrariaapi.model.enums;

import org.springframework.http.MediaType;

import java.util.Arrays;

public enum ExtensaoFoto {
    PNG(MediaType.IMAGE_PNG),
    JPEG(MediaType.IMAGE_JPEG);


    private MediaType mediaType;

    public MediaType getMediaType() {
        return mediaType;
    }

    ExtensaoFoto(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public static ExtensaoFoto valueOf(MediaType mediaType){
        return Arrays.stream(values())
                .filter(ir -> ir.mediaType.equals(mediaType))
                .findFirst()
                .orElse(null);
    }

    public static ExtensaoFoto ofName(String nome){
        return Arrays.stream(values())
                .filter(extensaoFoto -> extensaoFoto.name().equals(nome))
                .findFirst()
                .orElse(null);

    }

}
