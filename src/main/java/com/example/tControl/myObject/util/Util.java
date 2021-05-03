package com.example.tControl.myObject.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.InputStreamFactory;
import com.vaadin.flow.server.StreamResource;

public class Util {
	
	
	public static Image getImageFromInputStream(final byte[] bytes) {
		return null;
//		Image image = null;
//        try {
//            byte[] bytes = IOUtils.toByteArray(inputStream);
//            image = convertToImage(bytes);
////            System.out.println("bytes"+bytes.length);
////            StreamResource resource = new StreamResource("name.jpg", () -> new ByteArrayInputStream(bytes));
////            System.out.println("resource"+resource);
////            image = new Image(resource, "name");
//            
////            image.getElement().setAttribute("src", new StreamResource(
////                    "photo", () -> new ByteArrayInputStream(bytes)));
////            try (ImageInputStream in = ImageIO.createImageInputStream(
////                    new ByteArrayInputStream(bytes))) {
////                final Iterator<ImageReader> readers = ImageIO
////                        .getImageReaders(in);
////                if (readers.hasNext()) {
////                    ImageReader reader = readers.next();
////                    try {
////                        reader.setInput(in);
////                        image.setWidth(reader.getWidth(0) + "px");
////                        image.setHeight(reader.getHeight(0) + "px");
////                    } finally {
////                        reader.dispose();
////                    }
////                }
////            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return image;
	
//		
//		StreamResource streamResource = new StreamResource("isr", new InputStreamFactory() {
//	        @Override
//	        public InputStream createInputStream() {
//	            return inputStream;
//	        }
//	    });
//	    return new Image(streamResource, "photo");
	}
	
	public static Image convertToImage(byte[] imageData) {
		System.out.println(imageData.length);
	    StreamResource streamResource = new StreamResource("r.jpg", new InputStreamFactory() {
	        @Override
	        public InputStream createInputStream() {
	            return new ByteArrayInputStream(imageData);
	        }
	    });
	    System.out.println(streamResource);
	    streamResource.setContentType("image/jpg");
	    return new Image(streamResource, "photo");
	}
	

}
