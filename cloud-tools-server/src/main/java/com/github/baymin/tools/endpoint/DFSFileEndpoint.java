package com.github.baymin.tools.endpoint;

import com.github.baymin.tools.repository.entity.DFSFileInfo;
import com.github.baymin.tools.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.ParameterStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 文件管理接口
 *
 * @author Zongwei
 * @date 2020/4/13 22:17
 */
@Slf4j
@Api(tags = "文件管理接口")
@RestController
@RequestMapping("/api/v1/file")
public class DFSFileEndpoint {

    @Autowired
    private FileService fileService;

    @Operation(summary = "文件上传")
    @Parameters({
            @Parameter(name = "file", description = "要上传的附件", style = ParameterStyle.FORM, required = true)
    })
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public DFSFileInfo uploadFile(@RequestPart("file") MultipartFile filePart) {
        log.info("upload file:{}", filePart.getOriginalFilename());
        return fileService.uploadFile(filePart);
    }

    @Operation(summary = "根据fileId文件下载")
    @Parameters({
            @Parameter(name = "fileId", description = "文件唯一ID", in = ParameterIn.PATH, required = true)
    })
    @GetMapping("download/{fileId}")
    public void downloadFile(@PathVariable String fileId, HttpServletResponse response) throws IOException {
        DFSFileInfo fileInfo = fileService.getFileById(fileId);
        try (InputStream fileIs = fileService.getFileInputSteamByFileInfo(fileInfo)) {
            byte[] buf = new byte[1024];
            int length = 0;
            response.reset();
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileInfo.getOriginFileName(), StandardCharsets.UTF_8.name()));
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            OutputStream outputStream = response.getOutputStream();
            // 输出文件
            while ((length = fileIs.read(buf)) > 0) {
                outputStream.write(buf, 0, length);
            }
            // 关闭输出流
            outputStream.close();
        } catch (Exception e) {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            String data = "文件下载失败";
            OutputStream ps = response.getOutputStream();
            ps.write(data.getBytes(StandardCharsets.UTF_8));
        }
    }

    @Operation(summary = "根据fileId查询文件详情")
    @Parameters({
            @Parameter(name = "fileId", description = "文件唯一ID", in = ParameterIn.PATH, required = true)
    })
    @GetMapping("{fileId}")
    public DFSFileInfo queryFile(@PathVariable String fileId) {
        return fileService.getFileById(fileId);
    }

    @Operation(summary = "根据fileId删除文件")
    @Parameters({
            @Parameter(name = "fileId", description = "文件唯一ID", in = ParameterIn.PATH, required = true)
    })
    @DeleteMapping("{fileId}")
    public void deleteFile(@PathVariable String fileId) {
        fileService.deleteById(fileId);
    }

}
