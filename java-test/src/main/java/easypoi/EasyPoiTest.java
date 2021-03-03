package easypoi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/12/2
 **/
public class EasyPoiTest {
    public static void main(String[] args) throws Exception {
//        List<Integer> list = Arrays.asList(1, 2, 3, 4);
//        list.add(1);
//        list.remove(1);
        List<Student> students = new ArrayList<>();
        students.add(new Student().setName("围军儿").setSex(1).setBirthday(new Date()));
        students.add(new Student().setName("chengwj2").setSex(1).setBirthday(new Date()));
        //Array.asList返回的ArrayList并非java.util.ArrayList，此处使用会因为remove方法不支持而报错
//        List<Student> students2 = Arrays.asList(new Student().setName("围军儿").setSex(1).setBirthday(new Date()));
        //对象——>Excel
        exportExcel(students);
        //Excel——>对象
        List<Student> students1 = importFromExcel();
    }

    /**
     * 对象——>Excel
     * @param students
     * @throws IOException
     */
    private static void exportExcel(List<Student> students) throws IOException {
        Collection<Student> students1 = Collections.unmodifiableCollection(students);
        ExportParams exportParams = new ExportParams();
        exportParams.setSheetName("第一页");
        exportParams.setTitle("标题");
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,
                Student.class, students);
        OutputStream outputStream = new FileOutputStream("student.xls");
        workbook.write(outputStream);
    }

    /**
     * Excel——>对象
     * @return
     * @throws IOException
     */
    private static List<Student> importFromExcel() throws Exception {
        ImportParams importParams = new ImportParams();
//        //读第一个sheet
//        importParams.setStartSheetIndex(0);
//        importParams.setSheetNum(1);

//        //如果export设置了总标题，标题的行数就是2
//        importParams.setHeadRows(2);

        return ExcelImportUtil.importExcel(new FileInputStream("student.xls"), Student.class,
                importParams);
    }
}
