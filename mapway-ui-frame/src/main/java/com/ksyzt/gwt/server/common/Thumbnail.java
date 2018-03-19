package com.ksyzt.gwt.server.common;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 *  thumbnail
 * @author 54powerman
 * @version 1.0
 */
public class Thumbnail {
	
	/** The src file. */
	private String srcFile;
	
	/** The dest file. */
	private String destFile;
	
	/** The width. */
	private int width;
	
	/** The height. */
	private int height;
	
	/** The img. */
	private Image img;

    /**
     * The main method.
     *
     * @param args the arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {

	}

    /**
     * 构造函数.
     *
     * @param fileName String
     * @param dest     the dest
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public Thumbnail(String fileName, String dest) throws IOException {
		File _file = new File(fileName); // 读入文件
		this.srcFile = _file.getName();
		this.destFile = dest;
		img = javax.imageio.ImageIO.read(_file); // 构造Image对象
		width = img.getWidth(null); // 得到源图宽
		height = img.getHeight(null); // 得到源图长
	}

    /**
     * 强制压缩/放大图片到固定的大小.
     *
     * @param w int 新宽度
     * @param h int 新高度
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void resize(int w, int h) throws IOException {
		BufferedImage _image = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_RGB);

		_image.getGraphics().drawImage(
				img.getScaledInstance(w, h, Image.SCALE_SMOOTH), 0, 0, w, h,
				null); // 绘制缩小后的图
		// FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流
		// JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		// encoder.encode(_image); // 近JPEG编码
		saveImage(_image, destFile);
		// out.close();
	}

    /**
     * Save image.
     *
     * @param dstImage the dst image
     * @param dstName  the dst name
     * @throws IOException Signals that an I/O exception has occurred.
     */
    static void saveImage(BufferedImage dstImage, String dstName)
			throws IOException {
		String formatName = dstName.substring(dstName.lastIndexOf(".") + 1);
		// FileOutputStream out = new FileOutputStream(dstName);
		// JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		// encoder.encode(dstImage);
		ImageIO.write(dstImage, /* "GIF" */formatName /* format desired */,
				new File(dstName) /* target */);
	}

    /**
     * 按照固定的比例缩放图片.
     *
     * @param t double 比例
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void resize(double t) throws IOException {
		int w = (int) (width * t);
		int h = (int) (height * t);
		resize(w, h);
	}

    /**
     * 以宽度为基准，等比例放缩图片.
     *
     * @param w int 新宽度
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void resizeByWidth(int w) throws IOException {
		int h = (int) (height * w / width);
		resize(w, h);
	}

    /**
     * 以高度为基准，等比例缩放图片.
     *
     * @param h int 新高度
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void resizeByHeight(int h) throws IOException {
		int w = (int) (width * h / height);
		resize(w, h);
	}

    /**
     * 按照最大高度限制，生成最大的等比例缩略图.
     *
     * @param w int 最大宽度
     * @param h int 最大高度
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void resizeFix(int w, int h) throws IOException {
		if (width / height > w / h) {
			resizeByWidth(w);
		} else {
			resizeByHeight(h);
		}
	}

    /**
     * 设置目标文件名 setDestFile.
     *
     * @param fileName String 文件名字符串
     * @throws Exception the exception
     */
    public void setDestFile(String fileName) throws Exception {
		if (!fileName.endsWith(".jpg")) {
			throw new Exception("Dest File Must end with \".jpg\".");
		}
		destFile = fileName;
	}

    /**
     * 获取目标文件名 getDestFile.
     *
     * @return the dest file
     */
    public String getDestFile() {
		return destFile;
	}

    /**
     * 获取图片原始宽度 getSrcWidth.
     *
     * @return the src width
     */
    public int getSrcWidth() {
		return width;
	}

    /**
     * 获取图片原始高度 getSrcHeight.
     *
     * @return the src height
     */
    public int getSrcHeight() {
		return height;
	}
}
