package com.field.muzi.web.admin.controller.API;


import com.field.muzi.web.admin.dto.banner.BannerDetailResponse;
import com.field.muzi.web.admin.dto.banner.BannerListResponse;
import com.field.muzi.web.admin.dto.banner.BannerSaveRequest;
import com.field.muzi.web.admin.dto.banner.BannerUpdateRequest;
import com.field.muzi.web.admin.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class BannerAPI {

    private final BannerService bannerService;

    @GetMapping("/banner/list")
    public Page<BannerListResponse> adminBannerList(@PageableDefault(sort = "bannerSeq", direction = Sort.Direction.DESC) Pageable pageable) {
        return bannerService.bannerList(pageable);
    }

    @GetMapping("/banner/detail")
    public BannerDetailResponse adminBannerDetail(String bannerSeq) {
        return bannerService.bannerDetail(bannerSeq);
    }

    @PostMapping("/banner/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void bannerSave(BannerSaveRequest request) throws Exception {
        bannerService.bannerInsert(request);
    }

    @PostMapping("/banner/update")
    @ResponseStatus(HttpStatus.CREATED)
    public void bannerUpdate(BannerUpdateRequest request) throws Exception{
        bannerService.updateBanner(request);
    }

    @GetMapping("/banner/delete")
    @ResponseStatus(HttpStatus.CREATED)
    public void bannerDelete(@RequestParam(value="bannerSeqList[]") List<String> bannerSeqList) {
        bannerService.deleteBanner(bannerSeqList);
    }
}
