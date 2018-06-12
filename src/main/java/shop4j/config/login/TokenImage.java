package shop4j.config.login;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * @Author: weixuedong
 * @Date: 2018/6/4 15:07
 * @Description:验证码
 */
public class TokenImage {
    // 图片的宽度。
    private int width = 300;
    // 图片的高度。
    private int height = 150;
    // 验证码字符个数
    private int codeCount = 4;
    //需要检验得字符格式
    private int checkCount =2;

    // 验证码图片Buffer
    private BufferedImage buffImg = null;

    Random random = new Random();

    public TokenImage() throws IOException {
        creatImage();
    }

    public TokenImage(int width, int height) throws IOException {
        this.width = width;
        this.height = height;
        creatImage();
    }

    public TokenImage(int width, int height, int codeCount) throws IOException {
        this.width = width;
        this.height = height;
        this.codeCount = codeCount;
        creatImage();
    }

    public TokenImage(int width, int height, int codeCount, int checkCount) throws IOException {
        this.width = width;
        this.height = height;
        this.codeCount = codeCount;
        this.checkCount = checkCount;
        creatImage();
    }

    // 生成图片
    private void creatImage() throws IOException {
//        int fontWidth = width / codeCount;// 字体的宽度
//        int fontHeight = height - 5;// 字体的高度
//        int codeY = height - 8;
        // 图像buffer
        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = buffImg.getGraphics();
        g.drawImage(getBackGroundImage(),0,0,null);//先画背景图


//        //Graphics2D g = buffImg.createGraphics();
//        // 设置背景色
//        g.setColor(getRandColor(200, 250));
//        g.fillRect(0, 0, width, height);
//
//        // 设置字体
//        //Font font1 = getFont(fontHeight);
//        Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
//        g.setFont(font);
//
//
//        String str1 = randomStr(codeCount);// 得到随机字符
//        this.code = str1;
//        for (int i = 0; i < codeCount; i++) {
//            String strRand = str1.substring(i, i + 1);
//            g.setColor(getRandColor(1, 255));
//            // g.drawString(a,x,y);
//            // a为要画出来的东西，x和y表示要画的东西最左侧字符的基线位于此图形上下文坐标系的 (x, y) 位置处
//
//            g.drawString(strRand, i*fontWidth+3, codeY);
//        }


    }

    // 得到随机字符
    private String randomStr(int n) {
        String str1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        String str2 = "";
        int len = str1.length() - 1;
        double r;
        for (int i = 0; i < n; i++) {
            r = (Math.random()) * len;
            str2 = str2 + str1.charAt((int) r);
        }
        return str2;
    }

    // 得到随机颜色
    private Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    /**
     * 产生随机字体
     */
    private Font getFont(int size) {
        Random random = new Random();
        Font font[] = new Font[5];
        font[0] = new Font("Ravie", Font.PLAIN, size);
        font[1] = new Font("Antique Olive Compact", Font.PLAIN, size);
        font[2] = new Font("Fixedsys", Font.PLAIN, size);
        font[3] = new Font("Wide Latin", Font.PLAIN, size);
        font[4] = new Font("Gill Sans Ultra Bold", Font.PLAIN, size);
        return font[random.nextInt(5)];
    }

    // 扭曲方法
    private void shear(Graphics g, int w1, int h1, Color color) {
        shearX(g, w1, h1, color);
        shearY(g, w1, h1, color);
    }

    private void shearX(Graphics g, int w1, int h1, Color color) {

        int period = random.nextInt(2);

        boolean borderGap = true;
        int frames = 1;
        int phase = random.nextInt(2);

        for (int i = 0; i < h1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(0, i, w1, 1, (int) d, 0);
            if (borderGap) {
                g.setColor(color);
                g.drawLine((int) d, i, 0, i);
                g.drawLine((int) d + w1, i, w1, i);
            }
        }

    }

    private void shearY(Graphics g, int w1, int h1, Color color) {

        int period = random.nextInt(40) + 10; // 50;

        boolean borderGap = true;
        int frames = 20;
        int phase = 7;
        for (int i = 0; i < w1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(i, 0, 1, h1, 0, (int) d);
            if (borderGap) {
                g.setColor(color);
                g.drawLine(i, (int) d, i, 0);
                g.drawLine(i, (int) d + h1, i, h1);
            }

        }

    }



    public void write(OutputStream sos) throws IOException {
        ImageIO.write(buffImg, "png", sos);
        sos.close();
    }

    public BufferedImage getBuffImg() {
        return buffImg;
    }

//    public String getCode() {
//        return code.toLowerCase();
//    }

    //使用方法
 /*public void getCode3(HttpServletRequest req, HttpServletResponse response,HttpSession session) throws IOException{
        // 设置响应的类型格式为图片格式
            response.setContentType("image/jpeg");
            //禁止图像缓存。
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);


            CreateImageCode vCode = new CreateImageCode(100,30,5,10);
            session.setAttribute("code", vCode.getCode());
            vCode.write(response.getOutputStream());
     }*/

    /**
     * 获取随机背景图
     * @return 背景图
     */
    private Image getBackGroundImage() throws IOException {
        String url="";
        Image groundImage = ImageIO.read(new File(url));
        return groundImage;
    }
}
