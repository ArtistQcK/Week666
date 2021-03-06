package com.shpp.p2p.cs.akartel.assignment6.sg;

import acm.graphics.*;

/**
 * Class describe two methods
 * 1st findMessage - to find message
 * 2nd hideMessage - to hide message
 * LOL
 */

public class SteganographyLogic {
    /**
     * Given a GImage containing a hidden message, finds the hidden message
     * contained within it and returns a boolean array containing that message.
     * <p/>
     * A message has been hidden in the input image as follows.  For each pixel
     * in the image, if that pixel has a red component that is an even number,
     * the message value at that pixel is false.  If the red component is an odd
     * number, the message value at that pixel is true.
     *
     * @param source The image containing the hidden message.
     * @return The hidden message, expressed as a boolean array.
     */
    public static boolean[][] findMessage(GImage source) {
        // Array of source image's pixels
        int[][] pixels = source.getPixelArray();

        //Boolean Array to save result in
        boolean[][] result = new boolean[pixels.length][pixels[0].length];
        for (int row = 0; row < pixels.length; ++row) {
            for (int col = 0; col < pixels[0].length; ++col) {
                int red = GImage.getRed(pixels[row][col]);
                // find odd red colour value and save as true in result
                result[row][col] = red % 2 > 0;
            }
        }
        return result;
    }

    /**
     * Hides the given message inside the specified image.
     * <p/>
     * The image will be given to you as a GImage of some size, and the message will
     * be specified as a boolean array of pixels, where each white pixel is denoted
     * false and each black pixel is denoted true.
     * <p/>
     * The message should be hidden in the image by adjusting the red channel of all
     * the pixels in the original image.  For each pixel in the original image, you
     * should make the red channel an even number if the message color is white at
     * that position, and odd otherwise.
     * <p/>
     * You can assume that the dimensions of the message and the image are the same.
     * <p/>
     *
     * @param message The message to hide.
     * @param source  The source image.
     * @return A GImage whose pixels have the message hidden within it.
     */
    public static GImage hideMessage(boolean[][] message, GImage source) {
        // Array of source image's pixels
        int[][] pixels = source.getPixelArray();

        for (int row = 0; row < pixels.length; ++row) {
            for (int col = 0; col < pixels[0].length; ++col) {

                //red value of pixel
                int red = GImage.getRed(pixels[row][col]);
                //green value of pixel
                int green = GImage.getGreen(pixels[row][col]);
                //blue value of pixel
                int blue = GImage.getBlue(pixels[row][col]);

                if (red % 2 == 0 && message[row][col]) {
                    red += 1;
                    // assign RGB to every pixel
                    pixels[row][col] = GImage.createRGBPixel(red, green, blue);
                } else if (red % 2 > 0 && !message[row][col]) {
                    red -= 1;
                    pixels[row][col] = GImage.createRGBPixel(red, green, blue);
                }
            }
        }

        return new GImage(pixels);
    }
}