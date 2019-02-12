package com.easytoolsoft.easyreport.engine.query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.easytoolsoft.easyreport.engine.data.ReportDataSource;
import com.easytoolsoft.easyreport.engine.data.ReportParameter;

/**
 *
 * @see https://cwiki.apache.org/confluence/display/Hive/HiveServer2+Clients
 * @author tomdeng
 *
 */
public class HiveQueryer extends AbstractQueryer implements Queryer {
	public HiveQueryer(ReportDataSource dataSource, ReportParameter parameter) {
		super(dataSource, parameter);

	}

	@Override
	protected Connection getJdbcConnection() {
		try {
			Class.forName(this.dataSource.getDriverClass());
			return DriverManager.getConnection(this.dataSource.getJdbcUrl(), this.dataSource.getUser(),
					this.dataSource.getPassword());
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	protected String preprocessSqlText(String sqlText) {
		sqlText = StringUtils.stripEnd(sqlText.trim(), ";");
		Pattern pattern = Pattern.compile("limit.*?$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(sqlText);
		if (matcher.find()) {
			sqlText = matcher.replaceFirst("");
		}
		return sqlText + " limit 1";
	}
}