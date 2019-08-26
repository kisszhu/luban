package com.zhl.concurrency.chapter6;


import java.util.ArrayList;
import java.util.List;

/**
 * 页面渲染器.
 */
public class SingleThreadRenderer {

    void renderPage(CharSequence source) {
        renderText(source);
        List<ImageData> imageData = new ArrayList<>();
        for (ImageInfo imageInfo : scanForImageInfo(source)) {
            imageData.add(imageInfo.downloadImage());
        }
        for (ImageData data : imageData) {
            renderImage(data);
        }
    }

    void renderText(CharSequence source) {

    }

    void renderImage(ImageData data) {

    }

    public List<ImageInfo> scanForImageInfo(CharSequence source) {
        return null;
    }

}

class ImageData {

}

class ImageInfo {

    public ImageData downloadImage() {
        return null;
    }
}
