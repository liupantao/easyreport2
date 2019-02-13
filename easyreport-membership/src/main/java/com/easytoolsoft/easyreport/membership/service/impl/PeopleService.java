package com.easytoolsoft.easyreport.membership.service.impl;

import com.easytoolsoft.easyreport.data.service.AbstractCrudService;
import com.easytoolsoft.easyreport.membership.dao.IPeopleDao;
import com.easytoolsoft.easyreport.membership.dao.IUserDao;
import com.easytoolsoft.easyreport.membership.example.PeopleExample;
import com.easytoolsoft.easyreport.membership.example.UserExample;
import com.easytoolsoft.easyreport.membership.po.People;
import com.easytoolsoft.easyreport.membership.po.User;
import com.easytoolsoft.easyreport.membership.security.PasswordService;
import com.easytoolsoft.easyreport.membership.service.IPeopleService;
import com.easytoolsoft.easyreport.membership.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author tomdeng
 */
@Service("EzrptMemberPeopleService")
public class PeopleService
        extends AbstractCrudService<IPeopleDao, People, PeopleExample>
        implements IPeopleService {


    @Override
    protected PeopleExample getPageExample(String fieldName, String keyword) {
        PeopleExample example = new PeopleExample();
        example.createCriteria().andFieldLike(fieldName, keyword);
        return example;
    }


    @Override
    public User getUserByAccount(String account) {
        return null;
    }

    @Override
    public void encryptPassword(User user) {

    }
}