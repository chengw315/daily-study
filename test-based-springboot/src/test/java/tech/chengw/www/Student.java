package tech.chengw.www;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/12/2
 **/
@Data
@Accessors(chain = true)
@ExcelTarget("courseDomain")
public class Student {

    @Excel(name = "学生姓名",  width = 30, isImportField = "true_st")
    private String name;
    /**
     * 学生性别
     */
    @Excel(name = "学生性别", replace = {"男_1", "女_2"}, suffix = "生", isImportField = "true_st")
    private int sex;

    @Excel(name = "出生日期", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd", isImportField = "true_st", width = 20)
    private Date birthday;
}
