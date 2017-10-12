package com.rocky.boot.filters;

import com.alibaba.fastjson.JSONObject;
import com.rocky.boot.exceptions.ServiceException;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * Created by Rocky on 2017-10-10.
 */
public class SpecialStrFilter implements Filter {

    //	private static final String[] badStrs = {"insert", "select", "delete", "update","create","drop",
//		"<",">", "/*", "*/", "'", "|", ";", "&", "$", "%", "@", "\"", "\\", "()", "+", ",",
//		"0x0d", "0x0a"};
    //private static final String[] badStrs = {"select","create","drop","<",">", "/*", "*/", "'", "0x0d", "0x0a"};
    public static final String[] badStrs = {/*"select","create","drop",*/"<",">", "/*", "*/", "'","|" , "0x0d", "0x0a"/*,"insert", "delete", "update","join", "count", "chr", "mid"*/};
    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest args0, ServletResponse args1,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest)args0;
        HttpServletResponse res=(HttpServletResponse)args1;

        String toUrl = req.getRequestURL().toString();
        if (StringUtils.isNotBlank(toUrl) && toUrl.contains("exportToWord")) {
            chain.doFilter(args0,args1);
        }
        //获得所有请求参数名
        Enumeration params = req.getParameterNames();
        String sql = "";
        while (params.hasMoreElements()) {
            //得到参数名
            String name = params.nextElement().toString();
            //System.out.println("name===========================" + name + "--");
            //得到参数对应值
            String[] value = req.getParameterValues(name);
            for (int i = 0; i < value.length; i++) {
                sql = sql + value[i];
                //System.out.println(name + "   " + value[i]);
            }
        }
        //System.out.println("============================SQL"+sql);
        //有sql关键字，跳转到error.html
        if (StringUtils.isNotBlank(sql) && sqlValidate(sql)) {
            String xRequestedWith = req.getHeader("X-Requested-With");
            if (!org.springframework.util.StringUtils.isEmpty(xRequestedWith)) {
                // ajax请求
                JSONObject jobj = new JSONObject();
                jobj.put("ajaxErrorMsg","包含非法关键字" + Arrays.toString(badStrs));
                try {
                    res.setContentType("text/html;charset=UTF-8");
                    res.getWriter().print(jobj);
                } catch (IOException e) {
                    throw new ServiceException("传入response异常",e);
                }
                return;
            }else{
                res.sendRedirect(req.getContextPath() + "/illegal.html");
                //req.getRequestDispatcher(req.getContextPath() + "/illegal.html").forward(req, res);
            }
        } else {
            chain.doFilter(args0,args1);
        }
    }

    //效验
    private boolean sqlValidate(String str) {
        str = str.toLowerCase();//统一转为小写
//        String badStr = "insert|select|delete|";//过滤掉的sql关键字，可以手动添加
//        String[] badStrs = badStr.split("\\|");
        for (int i = 0; i < badStrs.length; i++) {
            if (str.indexOf(badStrs[i]) >= 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}
