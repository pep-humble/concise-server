package fastexcel;

import cn.idev.excel.FastExcel;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * fast excel写excel
 *
 * @author gang.liu
 */
@Slf4j
class FastExcelWriteTests {

    /**
     * 生成导出数据
     *
     * @return 导出数据
     */
    private List<EmployeeExcelRow> data() {
        return Arrays.asList(
                new EmployeeExcelRow("pep", "pep", "pep@gmail.com", "15093472310", "管理员,超级管理员"),
                new EmployeeExcelRow("admin", "admin", "admin@gmail.com", "15093472310", "管理员"),
                new EmployeeExcelRow("root", "root", "root@gmail.com", "15093472310", "超级管理员")
        );
    }

    @Test
    void write() {
        String fileName = "employee.xlsx";
        // 创建一个名为“模板”的 sheet 页，并写入数据
        FastExcel.write(fileName, EmployeeExcelRow.class).sheet("模板").doWrite(data());
    }
}
