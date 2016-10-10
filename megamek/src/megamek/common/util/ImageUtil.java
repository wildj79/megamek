/*
 * MegaMek - Copyright (C) 2000-2016 Ben Mazur (bmazur@sev.org)
 *
 *  This program is free software; you can redistribute it and/or modify it
 *  under the terms of the GNU General Public License as published by the Free
 *  Software Foundation; either version 2 of the License, or (at your option)
 *  any later version.
 *
 *  This program is distributed in the hope that it will be useful, but
 *  WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 *  or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 *  for more details.
 */
package megamek.common.util;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;

import megamek.client.ui.swing.util.ImprovedAveragingScaleFilter;
import megamek.common.Coords;

/**
 * Generic utility methods for image data
 */
public final class ImageUtil {
    /**
     * The graphics configuration of the local graphic card/monitor combination,
     * if we aren't running in "headless" mode.
     */
    private final static GraphicsConfiguration GC;
    static {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = null;
        try {
            gd = ge.getDefaultScreenDevice();
        } catch(HeadlessException he) {
        }
        GC = (null != gd) ? gd.getDefaultConfiguration() : null;
    }

    public static final int IMAGE_SCALE_BICUBIC = 1;
    public static final int IMAGE_SCALE_AVG_FILTER = 2;

    /**
     * @return an image in a format best fitting for hardware acceleration, if
     *         possible, else just the image passed to it
     */
    public static BufferedImage createAcceleratedImage(Image base) {
        if ((null == GC) || (null == base)) {
            return null;
        }
        BufferedImage acceleratedImage = GC.createCompatibleImage(
                base.getWidth(null), base.getHeight(null),
                Transparency.TRANSLUCENT);
        Graphics2D g2d = acceleratedImage.createGraphics();
        g2d.drawImage(base, 0, 0, base.getWidth(null), base.getHeight(null),
                null);
        g2d.dispose();
        return acceleratedImage;
    }

    /**
     * @return an image in a format best fitting for hardware acceleration, if possible
     */
    public static BufferedImage createAcceleratedImage(int w, int h) {
        if(null == GC) {
            return new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        }
        BufferedImage acceleratedImage = GC.createCompatibleImage(w, h, Transparency.TRANSLUCENT);
        return acceleratedImage;
    }

    /**
     * Get a scaled version of the input image.
     *
     * @param img
     * @return
     */
    public static BufferedImage getScaledImage(Image img, int newWidth,
            int newHeight) {
        return getScaledImage(img, newWidth, newHeight, IMAGE_SCALE_BICUBIC);
    }

    /**
     * Get a scaled version of the input image, using the supplied type to
     * select which scaling method to use.
     *
     * @param img
     * @return
     */
    public static BufferedImage getScaledImage(Image img, int newWidth,
            int newHeight, int scaleType) {
        if (scaleType == IMAGE_SCALE_BICUBIC) {
            BufferedImage scaled = createAcceleratedImage(newWidth, newHeight);
            Graphics2D g2 = (Graphics2D) scaled.getGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2.drawImage(img, 0, 0, newWidth, newHeight, null);
            return scaled;
        } else {
            ImageFilter filter;
            filter = new ImprovedAveragingScaleFilter(img.getWidth(null),
                    img.getHeight(null), newWidth, newHeight);

            ImageProducer prod;
            prod = new FilteredImageSource(img.getSource(), filter);
            return ImageUtil.createAcceleratedImage(
                    Toolkit.getDefaultToolkit().createImage(prod));
        }
    }

    /** Image loaders */
    private static final List<ImageLoader> IMAGE_LOADERS;
    static {
        IMAGE_LOADERS = new ArrayList<>();
        IMAGE_LOADERS.add(new TileMapImageLoader());
        IMAGE_LOADERS.add(new AWTImageLoader());
    }
    
    /** Add a new image loader to the first position of the list, if it isn't there already */
    public static void addImageLoader(ImageLoader loader) {
        if (null != loader && !IMAGE_LOADERS.contains(loader)) {
            IMAGE_LOADERS.add(0, loader);
        }
    }
    
    public static Image loadImageFromFile(String fileName) {
        if(null == fileName) {
            return null;
        }
        for (ImageLoader loader : IMAGE_LOADERS) {
            Image img = loader.loadImage(fileName);
            if (null != img) {
                return img;
            }
        }
        return null;
    }
    
    private ImageUtil() {}
    
    public interface ImageLoader {
        Image loadImage(String fileName);
    }
    
    public static class AWTImageLoader implements ImageLoader {
        @Override
        public Image loadImage(String fileName) {
            File fin = new File(fileName);
            if (!fin.exists()) {
                System.out.println("Trying to load image for a non-existant "
                        + "file! Path: " + fileName);
            }
            // Test if this is an animated image
            Boolean isAnimated = false;
            ImageReader reader = ImageIO.getImageReadersBySuffix("GIF").next();
            ImageInputStream in;
            try {
                in = ImageIO.createImageInputStream(new File(fileName));
                reader.setInput(in);
                if (reader.getNumImages(true) > 1) {
                    isAnimated = true;
                }
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            
            Image result = null;
            try {
                result = new ImageIcon(new URL(fileName)).getImage();
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(null == result) {
                return null;
            }
            return isAnimated ? result : ImageUtil.createAcceleratedImage(result);
        }
    }
    
    public static class TileMapImageLoader implements ImageLoader {
        private Coords parseCoords(String c) {
            if(null == c || c.isEmpty()) {
                return null;
            }
            String[] elements = c.split(",", -1); //$NON-NLS-1$
            if(elements.length != 2) {
                return null;
            }
            try {
                int x = Integer.parseInt(elements[0]);
                int y = Integer.parseInt(elements[1]);
                return new Coords(x, y);
            } catch(NumberFormatException nfe) {
                return null;
            }
        }
        
        @Override
        public Image loadImage(String fileName) {
            int tileStart = fileName.indexOf('(');
            int tileEnd = fileName.indexOf(')');
            if((tileStart == -1) || (tileEnd == -1) || (tileEnd < tileStart)) {
                return null;
            }
            String coords = fileName.substring(tileStart + 1, tileEnd);
            int coordsSplitter = coords.indexOf('-');
            if(coordsSplitter == -1) {
                return null;
            }
            Coords start = parseCoords(coords.substring(0, coordsSplitter));
            Coords size = parseCoords(coords.substring(coordsSplitter + 1));
            if((null == start) || (null == size) || (0 == size.getX()) || (0 == size.getY())) {
                return null;
            }
            String baseName = fileName.substring(0, tileStart);
            Image base = null;
            try {
                base = new ImageIcon(new URL(baseName)).getImage();
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(null == base) {
                return null;
            }
            BufferedImage result = ImageUtil.createAcceleratedImage(Math.abs(size.getX()), Math.abs(size.getY()));
            Graphics2D g2d = result.createGraphics();
            g2d.drawImage(base, 0, 0, result.getWidth(), result.getHeight(),
                start.getX(), start.getY(), start.getX() + size.getX(), start.getY() + size.getY(), null);
            g2d.dispose();
            return result;
        }
    }
}
