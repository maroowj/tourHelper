package com.field.muzi.web.common.controller;


import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;

@Slf4j
@RestController
@RequestMapping("/filemanager")
@RequiredArgsConstructor
public class FileController {

//    private String pathPrefix() throws IOException {
//        return new ClassPathResource("/tourhelperUser").getFile().getAbsolutePath();
//    }

    @GetMapping(value="/**"
    )
    public @ResponseBody byte[] file(HttpServletRequest request) throws Exception {
        String filePath = new File("").getAbsolutePath() + "/filemanager/" + request.getRequestURI()
                .split(request.getContextPath() + "/filemanager/")[1];
        FileInputStream in = new FileInputStream(filePath);
        byte[] image = IOUtils.toByteArray(in);
        in.close();
        return image;
    }

}
