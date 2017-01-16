package com.client.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class testDialog extends Dialog {

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public testDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);

		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}
	/**
	    * 打印模版
	    *
	    * @param imgWidth    图片的宽度
	    * @param imgHeight   图片的高度
	    * @param x           起始x轴
	    * @param y           起始y轴
	    * @param rowHeight   每行的高度
	    * @param dataStart   数据缩进
	    * @param firstWidth  第一列间距x坐标
	    * @param secondWidth 第二列间距x坐标
	    * @param thirdWidth  第三列间距x坐标
	    * @param fourWidth   第四列间距x坐标
	    * @param tb  实体对象
	    * @return BufferedImage
	    *(参数自己定，我的是340, 200, 0, 0, 20, 10, 60, 190, 250, 340, tb)
	    */
	   private static BufferedImage createTbGraphics(int imgWidth, int imgHeight, int x, int y, int rowHeight,
	                                               int dataStart, int firstWidth, int secondWidth, int thirdWidth, int fourWidth) {
	       //空白面板  也可是张图片
	       BufferedImage image = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
	       Graphics2D g = null;

	       try {

	           g = image.createGraphics();// 得到图形上下文
	           g.setBackground(Color.WHITE);//设置背景色
	           g.fillRect(x, y, imgWidth, imgHeight);//填充整个屏幕
	           g.setColor(Color.BLACK);//设置画笔颜色
//	           g.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 12));// 设置字体   这种以及“非国产”是乱码  有办法解决的话也可为实线， 我认为乱码是因为条码打印机的缘故，这样就要二次开发打印机，所以我没要这种方法
	           g.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 12));// 设置字体 还可设置为Font.ITALIC 就这两种条码打印机打印出来的的线是实体线
//	           g.setFont(new Font("simsun", Font.TYPE1_FONT, 12));// jvm里   想将字体格式simsun存入jvm里直接调用，打印出来也不成

	           g.drawLine(x, y, imgWidth, y);// 第一条横线

	           g.drawLine(x, y, x, rowHeight);//竖线
//	           g.drawString(new String("名称".getBytes("utf-8"),"utf-8"), dataStart, rowHeight - 5);
	           g.drawString("名称", dataStart, rowHeight - 5);
	           g.drawLine(firstWidth, y, firstWidth, rowHeight);
	           g.drawString("cagfsd" , firstWidth + dataStart, rowHeight - 5);
	           g.drawLine(imgWidth - 1, y, imgWidth - 1, rowHeight);

	           g.drawLine(x, rowHeight, imgWidth, rowHeight); //横线
	           g.drawLine(x, rowHeight, x, rowHeight * 2);
	           g.drawString("代码", dataStart, rowHeight * 2 - 5);    // * 2代表第二行
	           g.drawLine(firstWidth, rowHeight, firstWidth, rowHeight * 2);
	           g.drawString("sdfsdf", firstWidth + dataStart, rowHeight * 2 - 5);
	           g.drawLine(secondWidth, rowHeight, secondWidth, rowHeight * 2);

	           //其他数据....

	           //最后一条线
	           g.drawLine(x, imgHeight - 1, imgWidth, imgHeight - 1);

	           //在指定坐标(198,61)处 写入二维码或其它图片
	           //g.drawImage(ImageIO.read(getServletContext().getResourceAsStream(t.getPicUrl())), null, 198, 61);
	           g.dispose();
	       } catch (Exception e) {
	           e.printStackTrace();
	       }

	       return image;
	   }
}
