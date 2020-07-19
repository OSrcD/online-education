package com.course.server.service;

import com.course.server.domain.Section;
import com.course.server.domain.SectionExample;
import com.course.server.dto.SectionDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.SectionMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SectionService {

    @Resource
    private SectionMapper SectionMapper;

    /**
     * 列表查询
     * @param pageDto
     */
    public void list(PageDto pageDto){
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        SectionExample SectionExample = new SectionExample();
        List<Section> SectionList = this.SectionMapper.selectByExample(SectionExample);
        PageInfo<Section> pageInfo = new PageInfo<>(SectionList);

        pageDto.setTotal(pageInfo.getTotal());



        List<SectionDto> SectionDtoList = CopyUtil.copyList(SectionList,SectionDto.class);
        pageDto.setList(SectionDtoList);

    }

    /**
     * 保存，id有值时更新，无值时新增
     * @param SectionDto
     */
    public void save(SectionDto SectionDto){
        Section Section = CopyUtil.copy(SectionDto,Section.class);
        if(StringUtils.isEmpty(SectionDto.getId())){
            this.insert(Section);
        }else{
            this.update(Section);
        }

    }

    /**
     * 新增
     * @param Section
     */
    public void insert(Section Section){
        Section.setId(UuidUtil.getShortUuid());
        this.SectionMapper.insert(Section);
    }

    /**
     * 更新
     * @param Section
     */
    private void update(Section Section){
        this.SectionMapper.updateByPrimaryKey(Section);
    }

    /**
     * 删除
     * @param id
     */
    public void delete(String id) {

        SectionMapper.deleteByPrimaryKey(id);


    }
}
