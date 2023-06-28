package com.field.muzi.web.common.controller;

import com.field.muzi.TourApplication;
import com.field.muzi.utils.FileManagerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Controller
@RequiredArgsConstructor
public class CkEditorController {

    private final TourApplication application;
    /**ck에디터 파일업로드 이벤트 발생 시 처리
     * @param model
     * @param img
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/ck/fileUpload", method = {RequestMethod.POST, RequestMethod.GET})
    public String fileUpload(Model model,
                             @RequestParam(value="upload", required = false) MultipartFile img,
                             HttpServletRequest req) throws UnknownHostException {


        //서버에 파일을 저장할 때에는 파일명을 시간값으로 변경
        //DB에 저장할 때에는 원본 파일명과 시간값을 모두 저장
        //filename 취득
        String originalFileExtension = img.getOriginalFilename().substring(img.getOriginalFilename().lastIndexOf("."));
        String fileName =  System.currentTimeMillis() + originalFileExtension;

//        System.out.println(">>>"+fileName);
        //upload 경로 설정(tomcat realpath)
        String uploadPath = new File("").getAbsolutePath() + "/filemanager/editor/";

//        System.out.println("<<<" + req.getRequestURI());
        File file = new File(uploadPath);

        // 디렉터리가 존재하지 않을 경우
        if(!file.exists() && !file.mkdirs()) {
            // 디렉터리 생성에 실패했을 경우
            throw new RuntimeException("file: was not successful");
        }

        /** 배포 시 꼭 web으로 바꿔주기 **/
        String local = "http://localhost:8080/filemanager/editor/";
//        String web="https://adtool.cafe24.com/filemanager/editor/";
        String web="https://tourhelper.co.kr/filemanager/editor/";

//        String hostAddress = "https://" + InetAddress.getLocalHost().getHostAddress() + "/filemanager/editor/";

//        String address= req.getScheme() + "://" + req.getServerName() + "/filemanager/editor/";
        String address= "https://" + req.getServerName() + "/filemanager/editor/";

        try {
            //실제 파일이 업로드 되는 부분
            file = new File(uploadPath + fileName);
            img.transferTo(file);
            return "{ \"uploaded\" : true, \"url\" : \""+ address + fileName + "\" }"; /** 배포 시 꼭 web으로 바꿔주기 **/
        } catch (Exception e) {
            // TODO Auto-generated catch block
            FileManagerUtils.delete(file);
            return "{ \"uploaded\" : false, \"error\": { \"message\": \"업로드 중 에러가 발생했습니다. 다시 시도해 주세요.\" } }";
        }
    }
}
