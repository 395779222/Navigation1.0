package cn.eainfo.system.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 验证码
 * 
 * 
 */
public class ImgValidateAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	/**
	 * 文件下载属性
	 */
	private String contentDisposition;

	public InputStream getUpLoadImg() throws IOException {
		int iWidth = 70, iHeight = 30;
		BufferedImage image = new BufferedImage(iWidth, iHeight,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setColor(new Color(249, 249, 249));
		g.fillRect(0, 0, iWidth, iHeight);
		Random random = new Random();
		String rand = "" + random.nextInt(9) + random.nextInt(9)
				+ random.nextInt(9) + random.nextInt(9);
		rand = rand.toUpperCase();
		g.setColor(new Color(180, 180, 180));
		//g.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		g.setFont(new Font("Times", Font.PLAIN, 20));
		g.drawString(rand, 5, 23);
		g.dispose();
		ActionContext.getContext().getSession().put("imgValidate", rand);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] imagebute = null;
		ImageIO.write(image, "jpeg", bos);
		imagebute = bos.toByteArray();
		bos.close();
		this.setContentDisposition("filename=\"" + new Random().nextInt()
				+ "jpeg" + "\"");
		return new ByteArrayInputStream(imagebute);
	}

	public String getContentDisposition() {
		return contentDisposition;
	}

	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}

}
