package code;

import image.APImage;
import image.Pixel;

public class ImageManipulation {

    /** CHALLENGE 0: Display Image
     *  Write a statement that will display the image in a window
     */
    public static void main(String[] args) {
        APImage image = new APImage("cyberpunk2077.jpg");
        image.draw();
    }

    /** CHALLENGE ONE: Grayscale
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a grayscale copy of the image
     *
     * To convert a colour image to grayscale, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * Set the red, green, and blue components to this average value. */
    public static void grayScale(String pathOfFile) {
        APImage image = new APImage(pathOfFile);
        int width = image.getWidth();
        int height = image.getHeight();
        for(int i=0; i<width; i++){
            for(int k=0; k<height; k++){
                Pixel pixel = image.getPixel(i,k);
                int avg = getAverageColour(pixel);
                pixel.setRed(avg);
                pixel.setGreen(avg);
                pixel.setBlue(avg);
            }
        }
        image.draw();
    }

    /** A helper method that can be used to assist you in each challenge.
     * This method simply calculates the average of the RGB values of a single pixel.
     * @param pixel
     * @return the average RGB value
     */
    private static int getAverageColour(Pixel pixel) {
        return (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
    }

    /** CHALLENGE TWO: Black and White
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a black and white copy of the image
     *
     * To convert a colour image to black and white, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * If the average is less than 128, set the pixel to black
     * If the average is equal to or greater than 128, set the pixel to white */
    public static void blackAndWhite(String pathOfFile) {
        APImage image = new APImage(pathOfFile);

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Pixel pixel = image.getPixel(x, y);
                int avg = getAverageColour(pixel);

                if (avg < 128) {
                    pixel.setRed(0);
                    pixel.setGreen(0);
                    pixel.setBlue(0);
                } else {
                    pixel.setRed(255);
                    pixel.setGreen(255);
                    pixel.setBlue(255);
                }
            }
        }
        image.draw();
    }

    /** CHALLENGE Three: Edge Detection
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: an outline of the image. The amount of information will correspond to the threshold.
     *
     * Edge detection is an image processing technique for finding the boundaries of objects within images.
     * It works by detecting discontinuities in brightness. Edge detection is used for image segmentation
     * and data extraction in areas such as image processing, computer vision, and machine vision.
     *
     * There are many different edge detection algorithms. We will use a basic edge detection technique
     * For each pixel, we will calculate ...
     * 1. The average colour value of the current pixel
     * 2. The average colour value of the pixel to the left of the current pixel
     * 3. The average colour value of the pixel below the current pixel
     * If the difference between 1. and 2. OR if the difference between 1. and 3. is greater than some threshold value,
     * we will set the current pixel to black. This is because an absolute difference that is greater than our threshold
     * value should indicate an edge and thus, we colour the pixel black.
     * Otherwise, we will set the current pixel to white
     * NOTE: We want to be able to apply edge detection using various thresholds
     * For example, we could apply edge detection to an image using a threshold of 20 OR we could apply
     * edge detection to an image using a threshold of 35
     *  */
    public static void edgeDetection(String pathToFile, int threshold) {
        APImage image = new APImage(pathToFile);
        APImage newImage = image.clone();

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Pixel pixel = image.getPixel(x, y);
                int avg1 = getAverageColour(pixel);

                int avg2 = (x > 0) ? getAverageColour(image.getPixel(x - 1, y)) : avg1;
                int avg3 = (y > 0) ? getAverageColour(image.getPixel(x, y - 1)) : avg1;

                if (Math.abs(avg1 - avg2) > threshold || Math.abs(avg1 - avg3) > threshold) {
                    newImage.getPixel(x, y).setRed(0);
                    newImage.getPixel(x, y).setGreen(0);
                    newImage.getPixel(x, y).setBlue(0);
                } else {
                    newImage.getPixel(x, y).setRed(255);
                    newImage.getPixel(x, y).setGreen(255);
                    newImage.getPixel(x, y).setBlue(255);
                }
            }
        }
        newImage.draw();
    }

    /** CHALLENGE Four: Reflect Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image reflected about the y-axis
     *
     */
    public static void reflectImage(String pathToFile) {
        APImage image = new APImage(pathToFile);
        int width = image.getWidth();

        for (int x = 0; x < width / 2; x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Pixel leftPixel = image.getPixel(x, y);
                Pixel rightPixel = image.getPixel(width - 1 - x, y);

                // Swap the pixels
                Pixel temp = leftPixel.clone();
                image.setPixel(x, y, rightPixel);
                image.setPixel(width - 1 - x, y, temp);
            }
        }
        image.draw();
    }

    /** CHALLENGE Five: Rotate Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image rotated 90 degrees CLOCKWISE
     *
     *  */
    public static void rotateImage(String pathToFile) {
        APImage image = new APImage(pathToFile);
        int width = image.getWidth();
        int height = image.getHeight();
        APImage newImage = new APImage(height, width); // Swapped width and height

        for (int x=0; x<width; x++) {
            for (int y=0; y<height; y++) {
                Pixel pixel = image.getPixel(x, y);
                newImage.setPixel(height-1-y, x, pixel.clone());
            }
        }
        newImage.draw();
    }

}