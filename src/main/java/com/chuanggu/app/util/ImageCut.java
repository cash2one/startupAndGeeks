package com.chuanggu.app.util;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import jxl.common.Logger;

import com.chuanggu.app.entity.Image;

public class ImageCut {


	private static Logger logger = Logger.getLogger(ImageCut.class);
	private Image image;
	
	// ===源图片路径名称如:c:/1.jpg

	private String srcpath;

	// ===剪切图片存放路径名称.如:c:/2.jpg

	private String subpath;

	public ImageCut() {

	}

	public ImageCut(Image image,String srcpath, String subpath) {
		
		this.image = image;
		
		this.srcpath = srcpath;

		this.subpath = subpath;

	}


	/**
	 * 
	 * 对图片裁剪，并把裁剪完蛋新图片保存 。
	 */

	public  boolean cut() {

		if(!zoomImage(srcpath,subpath,image))
			return false;
		
		FileInputStream is = null;

		ImageInputStream iis = null;
		
		boolean success= false;

		try {

			// 读取图片文件

			is = new FileInputStream(subpath);

			/*
			 * 
			 * 返回包含所有当前已注册 ImageReader 的 Iterator，这些 ImageReader
			 * 
			 * 声称能够解码指定格式。 参数：formatName - 包含非正式格式名称 .
			 * 
			 * （例如 "jpeg" 或 "tiff"）等 。
			 */

			Iterator<ImageReader> it = ImageIO
					.getImageReadersByFormatName("jpg");

			ImageReader reader = it.next();

			// 获取图片流

			iis = ImageIO.createImageInputStream(is);

			/*
			 * 
			 * <p>iis:读取源.true:只向前搜索 </p>.将它标记为 ‘只向前搜索’。
			 * 
			 * 此设置意味着包含在输入源中的图像将只按顺序读取，可能允许 reader
			 * 
			 * 避免缓存包含与以前已经读取的图像关联的数据的那些输入部分。
			 */

			reader.setInput(iis, true);

			/*
			 * 
			 * <p>描述如何对流进行解码的类<p>.用于指定如何在输入时从 Java Image I/O
			 * 
			 * 框架的上下文中的流转换一幅图像或一组图像。用于特定图像格式的插件
			 * 
			 * 将从其 ImageReader 实现的 getDefaultReadParam 方法中返回
			 * 
			 * ImageReadParam 的实例。
			 */

			ImageReadParam param = reader.getDefaultReadParam();

			/*
			 * 
			 * 图片裁剪区域。Rectangle 指定了坐标空间中的一个区域，通过 Rectangle 对象
			 * 
			 * 的左上顶点的坐标（x，y）、宽度和高度可以定义这个区域。
			 */

			Rectangle rect = new Rectangle(image.getX(), image.getY(), image.getWidth(), image.getHeight());

			// 提供一个 BufferedImage，将其用作解码像素数据的目标。

			param.setSourceRegion(rect);

			/*
			 * 
			 * 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象，并将
			 * 
			 * 它作为一个完整的 BufferedImage 返回。
			 */

			BufferedImage bi = reader.read(0, param);

			// 保存新图片

			success = ImageIO.write(bi, "jpg", new File(subpath));
			

		}catch(IOException  e){
			
			logger.error(e.getMessage());
			
		}

		finally {

			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}

			if (iis != null)
				try {
					iis.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}

		}
		return success;

	}
	
	/**

     * 对图片进行缩放

     * @param originalImage 原始图片

     * @param times 缩放倍数

     * @return

     */

    public  BufferedImage  zoomImage(BufferedImage  originalImage, Image image){

        int width = image.getWidthZoom();

        int height = image.getHeightZoom();

        BufferedImage newImage = new BufferedImage(width,height,originalImage.getType());

        Graphics g = newImage.getGraphics();

        g.drawImage(originalImage, 0,0,width,height,null);

        g.dispose();

        return newImage;

    }

    /**

     * 对图片进行缩放

     * @param srcPath 原始图片路径(绝对路径)

     * @param newPath 放大后图片路径（绝对路径）

     * @param times 缩放倍数

     * @return 是否缩放成功

     */

    public  boolean zoomImage(String srcpath,String subpath,Image image){

        BufferedImage bufferedImage = null;

        try {

            File of = new File(srcpath);

            if(of.canRead()){

                bufferedImage =  ImageIO.read(of);

            }

        } catch (IOException e) {

        	logger.error(e.getMessage());
        	
            return false;

        }

        if(bufferedImage != null){

            bufferedImage = zoomImage(bufferedImage,image);

            try {

                ImageIO.write(bufferedImage, "JPG", new File(subpath)); //保存修改后的图像,全部保存为JPG格式

            } catch (IOException e) {

            	logger.error(e.getMessage());

                return false;

            }

        }

        return true;

    }


	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
 

}
