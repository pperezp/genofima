package cl.prezdev.genofima;

import org.springframework.core.io.InputStreamResource;

public interface IconGeneratorService {
    InputStreamResource generate(IconRequest iconRequest);
}
