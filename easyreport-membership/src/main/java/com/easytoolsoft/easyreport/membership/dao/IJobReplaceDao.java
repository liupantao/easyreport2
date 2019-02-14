package com.easytoolsoft.easyreport.membership.dao;

import com.easytoolsoft.easyreport.data.dao.ICrudDao;
import com.easytoolsoft.easyreport.membership.example.JobReplaceExample;
import com.easytoolsoft.easyreport.membership.example.PeopleExample;
import com.easytoolsoft.easyreport.membership.po.JobReplace;
import com.easytoolsoft.easyreport.membership.po.People;
import org.springframework.stereotype.Repository;

/**
 * 系统用户(ezrpt_member_user表)数据访问类
 *
 * @author Tom Deng
 */
@Repository("EzrptMemberIJobReplaceDao")
public interface IJobReplaceDao extends ICrudDao<JobReplace, JobReplaceExample> {

}
