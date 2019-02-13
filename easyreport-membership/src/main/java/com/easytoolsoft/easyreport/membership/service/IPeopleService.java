package com.easytoolsoft.easyreport.membership.service;

import com.easytoolsoft.easyreport.data.service.ICrudService;
import com.easytoolsoft.easyreport.membership.example.PeopleExample;
import com.easytoolsoft.easyreport.membership.example.UserExample;
import com.easytoolsoft.easyreport.membership.po.People;
import com.easytoolsoft.easyreport.membership.po.User;

/**
 * 系统用户服务类
 *
 * @author Tom Deng
 */
public interface IPeopleService extends ICrudService<People, PeopleExample> {
    User getUserByAccount(String account);

    void encryptPassword(User user);

}