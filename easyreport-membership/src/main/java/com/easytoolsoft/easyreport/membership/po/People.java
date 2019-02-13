package com.easytoolsoft.easyreport.membership.po;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 柜员信息表(T_TELLER_INFO表)持久化类
 *
 * @author Tom Deng
 */
@SuppressWarnings("serial")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class People<priva> implements Serializable {
    /**
     * 柜员信息表标识
     */
    private String id;

    private String name;

    private String code;

    private String orgCode;

    private String flag;

    private String idCard;

    private String sex;

    private String dlploma;

    private String jobId;

    private String status;

    private String type;

    private String phone;

    private String address;

    private String school;

    private String major;

    private String workDate;

    private String contractStartDate;

    private String contractEndTime;

    private String contractType;

    private String registerDate;

    private String createTime;

    private String updateTime;

    private String createUser;
    private String updateUser;

    private String dept;

    private String birthday;

    private String jobDate;

    private String personNo;

    private String operateStatus;

    private String personFlag;

    private String email;

    private String cert;

    private String trail;

    private String systems;

    private String agencyLevelCode;

    private String agencyLevel;


    //
    //机构名称
    private String shortName;
}
