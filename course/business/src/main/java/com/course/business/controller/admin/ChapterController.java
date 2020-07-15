package com.course.business.controller.admin;


import com.course.server.domain.Chapter;
import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.ChapterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/chapter")
public class ChapterController {

    private static final Logger LOG = LoggerFactory.getLogger(ChapterController.class);

    @Resource
    private ChapterService chapterService;

    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto){
        LOG.info("pageDto:{}",pageDto);
        ResponseDto responseDto = new ResponseDto();

        this.chapterService.list(pageDto);

        responseDto.setContent(pageDto);

        return responseDto;
    }

    @PostMapping("/save")
    public ResponseDto save(@RequestBody ChapterDto chapterDto){
        LOG.info("chapterDto：{}",chapterDto);
        ResponseDto responseDto = new ResponseDto();

        chapterService.save(chapterDto);
        responseDto.setContent(chapterDto);
        return responseDto;
    }


    @DeleteMapping("/delete/{id}")
    public ResponseDto save(@PathVariable String id){
        LOG.info("id：{}",id);
        ResponseDto responseDto = new ResponseDto();
        chapterService.delete(id);
        return responseDto;
    }
}

