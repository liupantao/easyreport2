package com.easytoolsoft.easyreport.web.controller.membership;

import com.easytoolsoft.easyreport.data.helper.PageInfo;
import com.easytoolsoft.easyreport.membership.common.CurrentUser;
import com.easytoolsoft.easyreport.membership.example.PeopleExample;
import com.easytoolsoft.easyreport.membership.example.UserExample;
import com.easytoolsoft.easyreport.membership.po.People;
import com.easytoolsoft.easyreport.membership.po.User;
import com.easytoolsoft.easyreport.membership.security.PasswordService;
import com.easytoolsoft.easyreport.membership.service.IPeopleService;
import com.easytoolsoft.easyreport.membership.service.IUserService;
import com.easytoolsoft.easyreport.web.controller.common.BaseController;
import com.easytoolsoft.easyreport.web.spring.aop.OpLog;
import com.easytoolsoft.easyreport.web.viewmodel.DataGridPager;
import com.easytoolsoft.easyreport.web.viewmodel.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tomdeng
 */
@RestController
@RequestMapping(value = "/rest/membership/people")
public class PeopleController
        extends BaseController<IPeopleService, People, PeopleExample> {





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

        //this.
        return modelMap;
    }
}
