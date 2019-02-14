package com.easytoolsoft.easyreport.web.controller.membership;

import com.easytoolsoft.easyreport.data.helper.PageInfo;
import com.easytoolsoft.easyreport.membership.common.CurrentUser;
import com.easytoolsoft.easyreport.membership.example.JobReplaceExample;
import com.easytoolsoft.easyreport.membership.example.PeopleExample;
import com.easytoolsoft.easyreport.membership.example.UserExample;
import com.easytoolsoft.easyreport.membership.po.JobReplace;
import com.easytoolsoft.easyreport.membership.po.People;
import com.easytoolsoft.easyreport.membership.po.User;
import com.easytoolsoft.easyreport.membership.security.PasswordService;
import com.easytoolsoft.easyreport.membership.service.IPeopleService;
import com.easytoolsoft.easyreport.membership.service.IUserService;
import com.easytoolsoft.easyreport.membership.service.impl.JobReplaceService;
import com.easytoolsoft.easyreport.web.controller.common.BaseController;
import com.easytoolsoft.easyreport.web.spring.aop.OpLog;
import com.easytoolsoft.easyreport.web.viewmodel.DataGridPager;
import com.easytoolsoft.easyreport.web.viewmodel.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author tomdeng
 */
@RestController
@RequestMapping(value = "/rest/membership/people")
public class PeopleController
        extends BaseController<IPeopleService, People, PeopleExample> {

    @Resource
    private  JobReplaceService jobReplaceService;
     



    @GetMapping(value = "/listPeople")
    @OpLog(name = "分页获取柜员列表")
    @RequiresPermissions("membership.user:view")
    public Map<String, Object> listPeople(@CurrentUser User loginUser, DataGridPager pager,
                                    String fieldName, String keyword) {
        PageInfo pageInfo = pager.toPageInfo();
        List<People> list = this.service.getByPage(pageInfo, fieldName, "%" + keyword + "%");
        Map<String, Object> modelMap = new HashMap<>(2);
        modelMap.put("total", pageInfo.getTotals());
        modelMap.put("rows", list);
       // pageInfo.setPageSize(100);
       // List<People> aa = this.service.getByPage(pageInfo, "a.id", "%" + 1 + "%");
        //this.
       // Map<String,String> map= new HashMap<>();
      //  map.put("sid","123");
       // List<People> bb= this.service.getList(map);
        return modelMap;
    }

    @GetMapping(value = "/editOne")
    @OpLog(name = "分页获取柜员列表")
    @RequiresPermissions("membership.user:view")
    @ResponseBody
    public Map<String, Object> editOne(@CurrentUser User loginUser, DataGridPager pager,
                                          String fieldName, String keyword,String id) {

        Map<String, Object> modelMap = new HashMap<>(2);
        List<String> list= new ArrayList<>();


        modelMap.put("total", "123");
        modelMap.put("rows", "3434");
        modelMap.put("list", list);
        //1.根据idhuoqu获取柜员信息
       People people= this.service.getById(id);
       String join="";
        String[] split = people.getJoinDate().split("-");
        join=split[0]+"年"+split[1]+"月"+split[2]+"日,"+people.getName()+"入职中国邮政银行--"
                +people.getShortName();
        list.add(join);

       // jobReplaceService.getByPage(pageInfo,"")
        list.add("2017年9月9日，入职河南大学，就置于大三英语教师。");
        list.add("2018年10月10日，升值加薪，就任教导处处长。");
        list.add("2019年3月3日，离职，就任郑州大学.");
        PeopleExample p= new PeopleExample();
        PageInfo pageInfo = pager.toPageInfo();
        pageInfo.setPageSize(100);
        List<People> id1 = this.service.getByPage(pageInfo, "id", id);

        return modelMap;
    }


}
