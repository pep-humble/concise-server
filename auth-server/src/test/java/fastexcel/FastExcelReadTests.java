package fastexcel;

import cn.idev.excel.FastExcel;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * fast excel读excel
 *
 * @author gang.liu
 */
@Slf4j
class FastExcelReadTests {

    @Test
    void read() {
        String fileName = "employee.xlsx";
        // 读取 Excel 文件
        FastExcel.read(fileName, EmployeeExcelRow.class, new EmployeeRowReadListener()).sheet().doRead();
    }

}
