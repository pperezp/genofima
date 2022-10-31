package cl.prezdev.genofima;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Random;

@Service
public class IconGeneratorServiceImpl implements IconGeneratorService {
    @Override
    public InputStreamResource generate(IconRequest iconRequest) {
        int width = iconRequest.getWidth();
        int height = iconRequest.getHeight();
        String value = getValue(iconRequest);
        Color background = iconRequest.getBackground();
        Color color = iconRequest.getColor();

        value = value.substring(0, 3).toLowerCase();

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics2D graphics2D = bufferedImage.createGraphics();

        renderingHints(graphics2D);
        graphics2D.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));

        FontMetrics fm = graphics2D.getFontMetrics();

        graphics2D.setColor(background);
        graphics2D.fillRect(0, 0, width, height);
        graphics2D.setColor(color);
        graphics2D.drawString(value, 5, fm.getAscent() + 8);

        graphics2D.dispose();
        try {
            ImageIO.write(bufferedImage, "png", new File("asd.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            return new InputStreamResource(new FileInputStream("asd.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String getValue(IconRequest iconRequest) {
        if (iconRequest.hasNotValue()) {
            return generateRandomValue();
        }

        return iconRequest.getValue();
    }

    private String generateRandomValue() {
        int length = 3;
        boolean useLetters = true;
        boolean useNumbers = false;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }

    private void renderingHints(Graphics2D graphics2D) {
        graphics2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        graphics2D.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
    }
}
