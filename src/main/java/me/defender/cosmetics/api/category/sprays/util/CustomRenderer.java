

package me.defender.cosmetics.api.category.sprays.util;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapPalette;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class CustomRenderer extends MapRenderer
{
    private BufferedImage image;
    private boolean done;
    
    public CustomRenderer() {
        this.done = false;
    }
    
    public CustomRenderer(String url) {
        this.done = false;
    }
    public CustomRenderer(File file) {
        this.done = false;
    }
    
    public boolean load(String url) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new URL(url));
            image = MapPalette.resizeImage(image);
        }
        catch (final IOException e) {
            return false;
        }
        this.image = image;
        return true;
    }
    public boolean load(File file) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
            image = MapPalette.resizeImage(image);
        }
        catch (final IOException e) {
            return false;
        }
        this.image = image;
        return true;
    }
    
    public void render(final MapView view, final MapCanvas canvas, final Player p) {
        if (this.done) {
            return;
        }
        canvas.drawImage(0, 0, this.image);
        this.done = true;
    }
}
