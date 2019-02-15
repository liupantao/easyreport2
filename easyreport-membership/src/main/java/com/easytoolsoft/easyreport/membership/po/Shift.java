package com.easytoolsoft.easyreport.membership.po;

import lombok.*;

import java.io.Serializable;

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
public class Shift<priva> implements Serializable {
    private String id;
    private String startTime;
    private String endTime;
    private String shortName;
}
