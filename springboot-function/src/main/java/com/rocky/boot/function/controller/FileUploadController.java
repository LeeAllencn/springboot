package com.rocky.boot.function.controller;

import com.rocky.boot.common.enums.ResultCodeEnum;
import com.rocky.boot.common.model.BaseResult;
import com.rocky.boot.common.model.ResultGenerator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * @author : rocky
 * @date : created in 2024/1/15
 */
@RestController
public class FileUploadController {

    /**
     * 单文件上传
     * @param file file
     * @param request request
     * @return BaseResult
     */
    @PostMapping(value = "/singleFileUpload")
    public BaseResult<Void> singleFileUpload(MultipartFile file, HttpServletRequest request) {
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
            ResultGenerator.getFailResult(ResultCodeEnum.FILE_UPLOAD_FAILED,ie.getMessage());
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 多文件上传
     * @param files files
     * @param request request
     * @return BaseResult
     */
    @PostMapping(value = "/multiFileUpload")
    public BaseResult<Void> multiFileUpload(MultipartFile[] files, HttpServletRequest request) {
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
            ResultGenerator.getFailResult(ResultCodeEnum.FILE_UPLOAD_FAILED,ie.getMessage());
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 执行上传操作
     * 文件名：String filename = file.getOriginalFilename();
     * 注：一般情况下我们不会使用上传时文件的名字作为存储在服务器端的名字，一般都会采用UUID或者时间戳的形式来保存，这里使用UUID来作为文件名称
     * 还有一种使用时间戳，一般都是微毫秒作为文件名(System.nanoTime())
     * @param uploadDir 上传目录
     * @param file 文件
     * @throws IOException io异常
     */
    private void executeUpload(String uploadDir, MultipartFile file) throws IOException {
        // 文件后缀名
        String suffix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));
        // 上传文件名
        String filename = UUID.randomUUID() + suffix;
        // String filename = System.nanoTime() + suffix;

        // 服务器端保存的文件对象
        File serverFile = new File(uploadDir + filename);

        // 传输文件
        file.transferTo(serverFile);
    }
}
