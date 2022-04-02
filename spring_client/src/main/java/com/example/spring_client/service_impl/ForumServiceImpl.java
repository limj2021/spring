package com.example.spring_client.service_impl;

import com.example.spring_client.dao_mapper.ForumMapper;
import com.example.spring_client.entity.Forum;
import com.example.spring_client.entity.PageRequest;
import com.example.spring_client.entity.PageResult;
import com.example.spring_client.service.ForumService;
import com.example.spring_client.service.UserTaskService;
import com.example.spring_client.util.PageUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ForumServiceImpl implements ForumService {
    @Autowired
    private ForumMapper forumMapper;

    @Override
    public String forumAdd(String publisher, String time, String title, String content,
                        int salary, String workingOneHours, String workingTwoHours,
                        String address, String phoneNumber,String taskid) {
        int add=forumMapper.forumAdd(publisher,time,title,content,salary,workingOneHours,workingTwoHours,address,phoneNumber,taskid);
        if (add == 0) {
            return "用户添加失败";
        } else {
            return "用户添加成功";
        }
    }

    @Override
    public String forumDelete(int id) {
        int dle=forumMapper.forumDelete(id);
        if (dle == 0) {
            return "用户删除失败";
        } else {
            return "用户删除成功";
        }
    }

    @Override
    public String forumtaskidDelete(String taskid){
        int dle=forumMapper.forumtaskidDelete(taskid);
        if (dle == 0) {
            return "用户删除失败";
        } else {
            return "用户删除成功";
        }
    }


    @Override
    public List<Forum> forumQueAll() {
        List<Forum> all=forumMapper.forumQueAll();
        return all;
    }

    @Override
    public List<Forum> forumQueLike(String title) {
        List<Forum> like=forumMapper.forumQueLike(title);
        return like;
    }




    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest));
    }
    private PageInfo<Forum> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        //最重要的代码
        PageHelper.startPage(pageNum, pageSize);//PageHelper 只对其后的第一个查询有效、

        //调用查询全部
        List<Forum> formMenus = forumMapper.forumQuePage();// 执行分页查询
//        两个一样的
        List<Forum> list=forumMapper.forumQueAll();

        //List<Forum> formMenus1 = forumMapper.forumQuePage();// 不会执行分页查询
        //将分页属性建立对象-调用方法
        //类包给的
        PageInfo<Forum> pageInfo = new PageInfo<Forum>(formMenus);
        //具体看源码
        pageInfo.getPages();
        pageInfo.getTotal();
        pageInfo.getPageNum();
        pageInfo.getPageSize();
        pageInfo.getEndRow();
        pageInfo.getList();
        pageInfo.getNavigateFirstPage();
        pageInfo.getNavigateLastPage();
        pageInfo.getNavigatepageNums();
        pageInfo.getNavigatePages();
        pageInfo.getNextPage();
        pageInfo.getPrePage();
        pageInfo.getSize();
        pageInfo.getStartRow();
        return pageInfo;
        //return new PageInfo<Forum>(formMenus);
    }




    //执行分页查询
    @Override
    public PageResult<Forum> selAllfindPage(Integer page, Integer rows) {
        Page pages = PageHelper.startPage(page, rows);

        List<Forum> list=forumMapper.forumQueAll();
        System.out.println(list);
//        自己定义的-目前为空，需要赋值
        PageResult<Forum> result = new PageResult<Forum>();

        //数据
        result.setContent(list);
/*        //list还要强转
        List<Forum> con = (List<Forum>) result.getContent();
        result.setContent(con);*/

        //当前页码
        int ff = result.getPageNum();
        System.out.println("ff="+ff);
        result.setPageNum(pages.getPageNum());
        int ff1 = result.getPageNum();
        System.out.println("ff1="+ff1);
        //页面数量
        result.setPageSize(pages.getPageSize());
        //总数据量
        result.setTotalSize(pages.getTotal());
        //总页数
        result.setTotalPages(pages.getPages());
        return result;
    }


}
