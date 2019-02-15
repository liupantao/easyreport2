package com.easytoolsoft.easyreport.membership.service.impl;

import com.easytoolsoft.easyreport.data.service.AbstractCrudService;
import com.easytoolsoft.easyreport.membership.dao.IShiftDao;
import com.easytoolsoft.easyreport.membership.example.ShiftExample;
import com.easytoolsoft.easyreport.membership.po.Shift;
import com.easytoolsoft.easyreport.membership.po.User;
import com.easytoolsoft.easyreport.membership.security.PasswordService;
import com.easytoolsoft.easyreport.membership.service.IShiftService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author tomdeng
 */
@Service("EzrptMemberShiftService")
public class ShiftService
        extends AbstractCrudService<IShiftDao, Shift, ShiftExample>
        implements IShiftService {

    @Resource
    private PasswordService passwordService;

    @Override
    protected ShiftExample getPageExample(String fieldName, String keyword) {
        ShiftExample example = new ShiftExample();
        example.createCriteria().andFieldLike(fieldName, keyword);
        return example;
    }



}