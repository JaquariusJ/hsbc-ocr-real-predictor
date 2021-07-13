package com._4paradim.hsbc.ocr.server.common.utils;

import com._4paradim.hsbc.ocr.server.common.exception.BusinessException;
import com.google.common.collect.Lists;
import com.twelvemonkeys.imageio.plugins.tiff.TIFFImageReader;
import com.twelvemonkeys.imageio.plugins.tiff.TIFFImageReaderSpi;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class FileToImages {

    private FileToImages() {
    }

    public static boolean supportedFileTypes(String fileName) {
        String fileType = StringUtils.substringAfterLast(fileName, ".");
        return StringUtils.equalsAnyIgnoreCase(fileType, "pdf", "tif", "tiff", "jpg", "jpeg", "png", "bmp");
    }

    /**
     * pdf PNG、BMP、GIF、WebP、TIF
     * 文件格式转换为jpg
     */
    public static synchronized List<File> toImages(File sourceFile, File destDirectory) throws BusinessException {
        try {
            String fileType = StringUtils.substringAfterLast(sourceFile.getName(), ".");
            if (StringUtils.equalsIgnoreCase(fileType, "pdf")) {
                return pdfToJpg(sourceFile, destDirectory);
            } else if (StringUtils.equalsAnyIgnoreCase(fileType, "tif", "tiff")) {
                return tiffToJpg(sourceFile, destDirectory);
            } else if (StringUtils.equalsAnyIgnoreCase(fileType, "jpg", "jpeg", "png", "bmp")) {
                return imageToJpg(sourceFile, destDirectory);
            } else {
                throw new BusinessException("Unsupported file type: " + fileType);
            }
        } catch (Exception ex) {
            throw new BusinessException("Image format conversion error: error info:"+ex);
        }
    }

    private static List<File> pdfToJpg(File sourceFile, File destDirectory) throws IOException {
        PDDocument document = PDDocument.load(sourceFile);
        PDFRenderer renderer = new PDFRenderer(document);
        List<File> pageImagesFiles = Lists.newArrayList();
        for (int i = 0; i < document.getNumberOfPages(); i++) {
            BufferedImage image = renderer.renderImageWithDPI(i, 200, ImageType.RGB);
            File imageFile = new File(destDirectory, sourceFile.getName() + "." + (i + 1) + ".jpg");
            writeJpegImage(image, imageFile);
            pageImagesFiles.add(imageFile);
        }
        return pageImagesFiles;
    }

    private static List<File> tiffToJpg(File sourceFile, File destDirectory) throws IOException {
        List<File> pageImagesFiles = Lists.newArrayList();
        //
        TIFFImageReaderSpi tiffImageReaderSpi = new TIFFImageReaderSpi();
        TIFFImageReader tiffImageReader = (TIFFImageReader) tiffImageReaderSpi.createReaderInstance();
        ImageInputStream stream = new FileImageInputStream(sourceFile);
        tiffImageReader.setInput(stream);
        for (int i = tiffImageReader.getMinIndex(); i < tiffImageReader.getNumImages(false); i++) {
            BufferedImage bufferedImage = tiffImageReader.read(i);
            File imageFile = new File(destDirectory, sourceFile.getName() + "." + (i + 1) + ".jpg");
            writeJpegImage(bufferedImage, imageFile);
            pageImagesFiles.add(imageFile);
        }
        return pageImagesFiles;
    }

    private static List<File> imageToJpg(File sourceFile, File destDirectory) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(sourceFile);
        File imageFile = new File(destDirectory, sourceFile.getName() + "." + "1.jpg");
        writeJpegImage(bufferedImage, imageFile);
        return Lists.newArrayList(imageFile);
    }

    private static void writeJpegImage(BufferedImage bufferedImage, File destFile) throws IOException {
        bufferedImage = ensureOpaque(bufferedImage);
        Exception exception = null;
        try {
            ImageIO.write(bufferedImage, "JPEG", destFile);
            return;
        } catch (Exception ex) {
            exception = ex;
            log.warn(ex.getMessage());
        }
        try {
            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("JPEG");
            ArrayList<ImageWriter> imageWriters = Lists.newArrayList(writers);
            for (ImageWriter imageWriter : imageWriters) {
                try (ImageOutputStream output = ImageIO.createImageOutputStream(destFile)) {
                    imageWriter.setOutput(output);
                    imageWriter.write(bufferedImage);
                    return;
                }
            }
        } catch (Exception ex) {
            exception = ex;
            log.warn(ex.getMessage());
        }
        throw new IOException(exception);
    }

    private static BufferedImage ensureOpaque(BufferedImage bi) {
        if (bi.getTransparency() == BufferedImage.OPAQUE) {
            return bi;
        }
        int w = bi.getWidth();
        int h = bi.getHeight();
        int[] pixels = new int[w * h];
        bi.getRGB(0, 0, w, h, pixels, 0, w);
        BufferedImage bi2 = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        bi2.setRGB(0, 0, w, h, pixels, 0, w);
        return bi2;
    }
}
