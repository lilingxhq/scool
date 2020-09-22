package com.man.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.man.common.constant.CommonConstants;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RevertInfo {
	private Integer id;
	private RevertUser commentUser;
	private RevertUser targetUser;

	private String content;

	@JSONField(format = CommonConstants.DATE_PATTERN)
	private Date createDate;

	private List<RevertInfo> childrenList;
}
