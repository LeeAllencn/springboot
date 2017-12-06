package com.rocky.boot.controller;

import com.rocky.boot.entry.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

/**
 * Created by Rocky on 2017-09-19.
 */
@Controller
public class HomeController {
    @RequestMapping({"/","/index"})
    public String index(){
        return"/index";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, Map<String, Object> map) throws Exception{
        System.out.println("HomeController.login()");
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        String exception = (String) request.getAttribute("shiroLoginFailure");
        System.out.println("exception=" + exception);
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> "+exception;
                System.out.println("else -- >" + exception);
            }
        }
        map.put("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理
        return "/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public void login(@RequestParam("username")String username, @RequestParam("password")String password, @RequestParam("verifyCode")String verifyCode, @RequestParam(value = "rememberMe", required = false) boolean rememberMe) {
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(
                username, password.toCharArray());
        token.setRememberMe(rememberMe);
        try {
            currentUser.login(token);	// 执行到这时就会跳转到shiro的认证方法doGetAuthenticationInfo中去
        } catch ( UnknownAccountException uae ) {
            //username wasn't in the system, show them an error message?
            System.out.println("1-用户名不存在");
        } catch ( IncorrectCredentialsException ice ) {
            //password didn't match, try again?
            System.out.println("2-密码不正确");
        } catch (ExcessiveAttemptsException eae) {
            System.out.println("3.1-账户锁定");
        } catch ( LockedAccountException lae ) {
            //account for that username is locked - can't login.  Show them a message?
            System.out.println("3-用户已锁定");
        } catch ( AuthenticationException ae ) {
            //unexpected condition - error?
            System.out.println("4-未知错误");
        }
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "403";
    }

    @RequestMapping(value = "/verifycode", method = RequestMethod.GET)
    @ResponseBody
    public void verifycode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //禁止缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "No-cache");
        response.setDateHeader("Expires", 0);

        //指定生成的响应图片
        response.setContentType("image/jpeg");
        int width = 100;
        int height = 35;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Graphics2D g2d = (Graphics2D)g;
        Random random = new Random();
        Font font = new Font("华文宋体", Font.BOLD,26);
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);

        //画一条折线
        BasicStroke basicStroke = new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
        g2d.setStroke(basicStroke);
        g.setColor(Color.DARK_GRAY);

        int[] xPoints = new int[3];
        int[] yPoints = new int[3];
        for(int i=0;i<3;i++){
            xPoints[i] = random.nextInt(width - 1);
            yPoints[i] = random.nextInt(height - 1);
        }

        g.drawPolyline(xPoints,yPoints,3);

        //生成并输出随机的验证文字
        g.setFont(font);
        String randsString = "";
        int itmp = 0;
        for(int i=0;i<4;i++){
            if(random.nextInt(2) == 1){
                itmp = random.nextInt(26) + 65;	//生成A-Z的字母
            }else{
                itmp = random.nextInt(10) + 48;	//生成0-9的数字
            }

            char ctmp = (char)itmp;
            randsString += String.valueOf(ctmp);
            Color color = new Color(20 + random.nextInt(110),20 + random.nextInt(110),20 + random.nextInt(110));
            g.setColor(color);

            //文字旋转指定角度
            Graphics2D g2d_word = (Graphics2D)g;
            AffineTransform trans = new AffineTransform();
            trans.rotate(random.nextInt(10) * 3.14 / 180,i,7);

            g2d_word.setTransform(trans);
            g.drawString(String.valueOf(ctmp),i*20+10,25);
        }

        //将生成的验证码保存到session中
        HttpSession session = request.getSession(true);
        session.setAttribute("verifycode", randsString);
        g.dispose();
        ImageIO.write(image, "JPEG", response.getOutputStream());
    }

    public Color getRandColor(int s, int e){
        Random random = new Random();
        if(s > 255) s = 255;
        if(e > 255) e = 255;

        int r = s + random.nextInt(e - s);
        int g = s + random.nextInt(e - s);
        int b = s + random.nextInt(e - s);

        return new Color(r,g,b);
    }

}
