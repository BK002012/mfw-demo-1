package com.qf.web;

import com.qf.pojo.vo.AuthorVo;
import com.qf.pojo.po.UserAuthor;
import com.qf.pojo.po.UserInfo;
import com.qf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;


//在spring-mvc已经配置扫描
@Controller
public class UserAction {

    //    注入属性
    @Autowired
    private UserService userService;

    @PostMapping("user/login")
    @ResponseBody
    public UserInfo login(AuthorVo data) {
        return userService.login(data);

    }

    @PostMapping("user")
    @ResponseBody
    public int register(UserAuthor data) {
        return userService.register(data);
    }

    /**
     * * 1.添加依赖  commons-fileupload
     * * 2.spring-mvc.xml 上传解析器
     * * 3.添加表单 enctype
     * * 4.添加action MultipartFile
     * * 5.测试
     *
     * @param request
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/uimage")
    public String upload(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
//            获取路径
            String path = request.getServletContext().getRealPath("/file/");
//            获取原始图片的名称
            String filename = file.getOriginalFilename();
//            获取File对象
            File file1 = new File(path, filename);

            if (!file1.getParentFile().exists()) {
                file1.getParentFile().mkdirs();
            }

            file.transferTo(new File(path + File.separator + filename));
        }
        return "uimage";
    }
}
