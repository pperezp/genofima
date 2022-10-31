package cl.prezdev.genofima;

import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.io.*;

@RestController
@AllArgsConstructor
public class ImageController {

    private final IconGeneratorService iconGeneratorService;

    @GetMapping("/image")
    public ResponseEntity generateImage(@RequestParam("name") String name) throws IOException {
        IconRequest iconRequest = IconRequest.builder()
                .value(name)
                .background(Color.BLACK)
                .color(Color.red)
                .width(65)
                .height(65)
                .build();

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(iconGeneratorService.generate(iconRequest));
    }
}
