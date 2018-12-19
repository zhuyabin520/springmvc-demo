package com.qf.admin.web;


import com.qf.admin.pojo.po.User;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserAction {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/h2")
    public void h2() {
        System.out.println("222222222");
    }

    //pageIndex当前页的页码  pageSize每页显示的条数
    @GetMapping("/default")
    public String testDefaultValue(@RequestParam(defaultValue = "1") Integer pageIndex, @RequestParam(defaultValue = "10") Integer pageSize) {
        System.out.println("++++++" + pageIndex);
        System.out.println("++++++" + pageSize);
        return "hello";
    }

    //http://localhost:8080/springmvc/user/list/123456
    @GetMapping("/list/{id}")
    public String getUserById(@PathVariable("id") Integer id) {
        System.out.println("+++++" + id);
        return "hello";
    }

    /**
     * 三个步骤：
     * 1. 添加依赖 jackson-databind.jar
     * 2. 在spring-mvc.xml添加一句话：<mvc:annotation-driven/>
     * 3. @ResponseBody 既可以加在类上，也可以加在方法上
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/json")
    public Map<String, Object> getJson() {
        //应该是你自己通过service查询出来的内容
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("age", 18);
        map.put("name", "mjst");
        return map;
    }

    @GetMapping("/cookie")
    public String getCookieValue(@CookieValue("JSESSIONID") String id) {
        System.out.println("__________" + id);
        return "hello";
    }

    /**
     * ? 表示一个字符（但是不能为空） /ant/?abc/list  ====>  /ant/jabc/list
     * * 表示任意字符 （可以为空）
     * ** 表示多层的任意字符（可以为空）
     *
     * @return
     */
//    @RequestMapping(value = "/ant/?abc/list",method = RequestMethod.GET,params = {"!username"})
//    @GetMapping("/ant/?abc/list")
    @GetMapping("/ant/**/list") // /ant/a/b/c/d/list
    public String testAnt() {
        System.out.println("xxxx");
        return "hello";
    }

    //requestmapping的URL要唯一
    @PostMapping("/addUser")
    public String addUser(User user) {
        System.out.println(user);
        return "abc";
    }

//    @GetMapping("/toAddUser")
//    public ModelAndView toAddUser(){
//        //1 service
//        //2 request
//        //3 dispatch
//
//        //1 service
//        //2 mv
//        //3 setViewName addObject
//        Map<String, Object> map = new HashMap<String,Object>();
//        map.put("name", "xdd");
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("addUser");//下一个页面使用的页面//转发//new RedirectView("/listUsers")//重定向
//        mv.addObject("map", map);//携带过去的数据
//        return mv;
//    }

//    @GetMapping("/toAddUser")
//    public String toAddUser(Model model) {
//        //1. 调用service层将数据查询出来
//        //2. 将内容存放到request范围之内
//        model.addAttribute("name", "ldd");
//        //3. 转发
//        return "addUser";
//    }

    //变量没有顺序
    @GetMapping("/toAddUser")
    public void toAddUser(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("name", "zdd");
        //转发
//        request.getRequestDispatcher("addUser").forward(request,response);
        try {
            //重定向
            response.sendRedirect("addUser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String removeUserById(@PathVariable("id") int id) {
        System.out.println(id);
        return "hello";
    }

    @ResponseBody
    @GetMapping("/json2")
    public List<User> listUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("xxxx"));
        userList.add(new User("yyyy"));
        return userList;
    }

    @GetMapping("/toUpload")
    public String toUpload() {
        return "upload";
    }


    /**
     * springmvc文件上传的步骤
     * 1.添加依赖  commons-fileupload
     * 2.spring-mvc.xml 上传解析器
     * 3.添加表单 enctype
     * 4.添加action MultipartFile
     * 5.测试
     *
     * @param request
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public String upload(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("pppppp");
        //如果文件不为空进行如下操作
        if (!file.isEmpty()) {
            //获取路径 //images
            String path = request.getServletContext().getRealPath("/images/");
            //获取原始图片的名称 //a.jpg
            String filename = file.getOriginalFilename();
            //获取File对象
            File filePath = new File(path, filename);
            //判断是否存在
            if (!filePath.getParentFile().exists()) {
                //意味着不存在
                filePath.getParentFile().mkdirs();
            }
            file.transferTo(new File(path + File.separator + filename));
        }
        return "upload";
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> download(HttpServletRequest request,@RequestParam("filename") String filename) throws IOException {
        //1. 要知道从哪里下载文件
        String path = request.getServletContext().getRealPath("/images/");
        //2. 拼接出我们要下载的文件
        File file = new File(path + File.separator + filename);
        //3. 创建http头
        HttpHeaders headers = new HttpHeaders();
        //4. 设置属性
        headers.setContentDispositionFormData("attachment",new String(filename.getBytes("UTF-8"),"ISO-8859-1"));
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        //5. 返回ResponseEntity
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }
}
