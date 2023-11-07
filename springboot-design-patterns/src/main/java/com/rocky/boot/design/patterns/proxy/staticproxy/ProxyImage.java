package com.rocky.boot.design.patterns.proxy.staticproxy;

/**
 * @author : rocky
 * @date : created in 2023/11/7
 */
public class ProxyImage implements Image {

    private final String fileName;

    private RealImage realImage;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}
