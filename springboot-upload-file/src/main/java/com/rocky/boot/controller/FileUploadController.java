package com.rocky.boot.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.stream.Collectors;

import com.rocky.boot.service.StorageService;
import com.rocky.boot.support.exception.StorageFileNotFoundException;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;


@Controller
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

    // ===============================================================================

    /**
     * 单文件上传
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "/singleFileUpload", method = RequestMethod.POST)
    @ResponseBody
    public String singleFileUpload(MultipartFile file, HttpServletRequest request) {
        try {
            // 上传路径
            String uploadDir = request.getSession().getServletContext().getRealPath("/") + "upload-dir/";
            // 路径路径不存在，自动创建
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            executeUpload(uploadDir, file);
        } catch (IOException ie) {
            ie.printStackTrace();
            return "上传失败";
        }
        return "上传成功";
    }

    /**
     *
     * @param files
     * @param request
     * @return
     */
    @RequestMapping(value = "/multiFileUpload", method = RequestMethod.POST)
    @ResponseBody
    public String multiFileUpload(MultipartFile[] files, HttpServletRequest request) {
        try {
            // 上传路径
            String uploadDir = request.getSession().getServletContext().getRealPath("/") + "upload-dir/";
            // 路径路径不存在，自动创建
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            // 遍历文件数组执行上传
            for (MultipartFile file : files) {
                if (file != null) {
                    executeUpload(uploadDir, file);
                }
            }
        } catch (IOException ie) {
            ie.printStackTrace();
            return "上传失败";
        }
        return "上传成功";
    }

    /**
     *
     * @param uploadDir
     * @param file
     * @throws IOException
     */
    private void executeUpload(String uploadDir, MultipartFile file) throws IOException {
        // 文件名
        // String filename = file.getOriginalFilename();
        // 注：一般情况下我们不会使用上传时文件的名字作为存储在服务器端的名字，一般都会采用UUID或者时间戳的形式来保存，这里使用UUID来作为文件名称
        // 还有一种使用时间戳，一般都是微毫秒作为文件名(System.nanoTime())

        // 文件后缀名
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        // 上传文件名
        String filename = UUID.randomUUID() + suffix;
        // String filename = System.nanoTime() + suffix;

        // 服务器端保存的文件对象
        File serverFile = new File(uploadDir + filename);

        // 传输文件
        file.transferTo(serverFile);
    }

}
