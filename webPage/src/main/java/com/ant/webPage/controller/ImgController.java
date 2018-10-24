package com.ant.webPage.controller;

import com.ant.webPage.tool.LoadFileTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 图片
 */
@RestController
@RequestMapping("/img")
@CrossOrigin
public class ImgController {

    private static final Logger log= LoggerFactory.getLogger(ImgController.class);

    /*图片加载连接*/
    @RequestMapping("/pay_img/{name}")
    public void PayImg(HttpServletResponse response, @PathVariable("name")String name){
        FileInputStream fis = null;
        response.setContentType("image/gif");
        try {
            OutputStream out = response.getOutputStream();
            File file = new File(LoadFileTool.Path()+"/img/pay/"+name);
            fis = new FileInputStream(file);
            byte[] b = new byte[fis.available()];
            fis.read(b);
            out.write(b);
            out.flush();
        } catch (Exception e) {
            log.error("显示图片发生了异常",e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    log.error("图片显示类，关闭流出现了异常",e);
                }
            }
        }
    }
}
