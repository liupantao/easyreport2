package com.easytoolsoft.easyreport.membership.po;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class JobReplace<priva> implements Serializable {
    private String id;
    private String startTime;
    private String endTime;
    private String shortName;
}
