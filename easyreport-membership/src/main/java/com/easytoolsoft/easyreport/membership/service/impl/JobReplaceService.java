package com.easytoolsoft.easyreport.membership.service.impl;

import com.easytoolsoft.easyreport.data.service.AbstractCrudService;
import com.easytoolsoft.easyreport.membership.dao.IJobReplaceDao;
import com.easytoolsoft.easyreport.membership.dao.IUserDao;
import com.easytoolsoft.easyreport.membership.example.JobReplaceExample;
import com.easytoolsoft.easyreport.membership.example.UserExample;
import com.easytoolsoft.easyreport.membership.po.JobReplace;
import com.easytoolsoft.easyreport.membership.po.User;
import com.easytoolsoft.easyreport.membership.security.PasswordService;
import com.easytoolsoft.easyreport.membership.service.IUserService;
import com.easytoolsoft.easyreport.membership.service.IjobReplaceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author tomdeng
 */
@Service("EzrptMemberJobReplaceService")
public class JobReplaceService
        extends AbstractCrudService<IJobReplaceDao, JobReplace, JobReplaceExample>
        implements IjobReplaceService {

    @Resource
    private PasswordService passwordService;

    @Override
    protected JobReplaceExample getPageExample(String fieldName, String keyword) {
        JobReplaceExample example = new JobReplaceExample();
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