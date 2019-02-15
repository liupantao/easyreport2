package com.easytoolsoft.easyreport.web.controller.membership;

import com.easytoolsoft.easyreport.data.helper.PageInfo;
import com.easytoolsoft.easyreport.membership.common.CurrentUser;
import com.easytoolsoft.easyreport.membership.example.PeopleExample;
import com.easytoolsoft.easyreport.membership.po.JobReplace;
import com.easytoolsoft.easyreport.membership.po.People;
import com.easytoolsoft.easyreport.membership.po.Shift;
import com.easytoolsoft.easyreport.membership.po.User;
import com.easytoolsoft.easyreport.membership.service.IPeopleService;
import com.easytoolsoft.easyreport.membership.service.IShiftService;
import com.easytoolsoft.easyreport.membership.service.IjobReplaceService;
import com.easytoolsoft.easyreport.web.controller.common.BaseController;
import com.easytoolsoft.easyreport.web.spring.aop.OpLog;
import com.easytoolsoft.easyreport.web.viewmodel.DataGridPager;
import com.easytoolsoft.easyreport.web.viewmodel.JsonResult;
import org.apache.commons.lang3.StringUtils;
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
    private IjobReplaceService jobReplaceService;

    @Resource
    private IShiftService shiftService;



    @GetMapping(value = "/listPeople")
    @OpLog(name = "分页获取柜员列表")
    @RequiresPermissions("membership.user:view")
    public Map<String, Object> listPeople(@CurrentUser User loginUser, DataGridPager pager,
                                    String name, String code,String orgCode,String card) {
        PageInfo pageInfo = pager.toPageInfo();
      //<People> list = this.service.getByPage(pageInfo, fieldName, "%" + keyword + "%");
        Map<String, Object> modelMap = new HashMap<>(2);
      //  modelMap.put("total", pageInfo.getTotals());
       // modelMap.put("rows", list);
       // pageInfo.setPageSize(100);
       // List<People> aa = this.service.getByPage(pageInfo, "a.id", "%" + 1 + "%");
        //this.
       /*Map<String,String> map= new HashMap<>();
       map.put("sid","123");
       List<People> bb= this.service.getList(map);
        System.out.println("---------");
        List<JobReplace> list1 = jobReplaceService.getList(map);
*/
       //分页重构
        Map<String,Object> map= new HashMap<>();
        map.put("sid","123");
        map.put("startIndex",pageInfo.getStartIndex());
        map.put("pageSize",pageInfo.getPageSize());
     //   List<People> bb= this.service.getList(map);
        if(StringUtils.isNotBlank(name)){
            map.put("name",name.trim());
        }
        if(StringUtils.isNotBlank(code)){
            map.put("code",code.trim());
        }
        if(StringUtils.isNotBlank(orgCode)){
            map.put("orgCode",orgCode.trim());
        }
        if(StringUtils.isNotBlank(card)){
            map.put("card",card.trim());
        }

        List<People> cc= this.service.getListPage(map);
        People total= this.service.getListPageTotal(map);
        modelMap.put("rows", cc);
        modelMap.put("total", total.getTotal());
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
       /* list.add("2017年9月9日，入职河南大学，就置于大三英语教师。");
        list.add("2018年10月10日，升值加薪，就任教导处处长。");
        list.add("2019年3月3日，离职，就任郑州大学.");*/
       /* PeopleExample p= new PeopleExample();
        PageInfo pageInfo = pager.toPageInfo();
        pageInfo.setPageSize(100);
        List<People> id1 = this.service.getByPage(pageInfo, "id", id);*/

        Map<String,String> map= new HashMap<>();
        map.put("sid",id);

        List<JobReplace> list1 = jobReplaceService.getList(map);

        System.out.println(list1.size()+"----------------geshu --");

        for (JobReplace job: list1) {
            //20121024 16:31:02
            String start=job.getStartTime();
            String str1=start.substring(0,4)+"年"+start.substring(4,6)+"月"+start.substring(6,8)+"日"+",";
            str1=str1+"在"+job.getShortName()+" 顶岗, ";
            if(StringUtils.isNotBlank(job.getEndTime())){
                String end=job.getEndTime();
                str1=str1+end.substring(0,4)+"年"+start.substring(4,6)+"月"+start.substring(6,8)+"结束";
            }
            list.add(str1);
        }
       if(list1.size()<1){
           //查询轮岗
           List<Shift> list2 = shiftService.getList(map);
           for (Shift s:list2) {
               String start=s.getStartTime();
               start=start.replaceAll("-","");
               String str1=start.substring(0,4)+"年"+start.substring(4,6)+"月"+start.substring(6,8)+"日"+",";
               str1=str1+"在"+s.getShortName()+" 轮岗, ";
               if(StringUtils.isNotBlank(s.getEndTime())){
                   String end=s.getEndTime();
                   end.replaceAll("-","");
                   str1=str1+end.substring(0,4)+"年"+start.substring(4,6)+"月"+start.substring(6,8)+"结束";
               }
               list.add(str1);
           }


       }
        return modelMap;
    }


}
