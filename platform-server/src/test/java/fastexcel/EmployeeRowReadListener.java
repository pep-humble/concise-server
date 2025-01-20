package fastexcel;

import cn.idev.excel.context.AnalysisContext;
import cn.idev.excel.read.listener.ReadListener;
import lombok.extern.slf4j.Slf4j;

/**
 * 行读取监听器
 *
 * @author gang.liu
 */
@Slf4j
public class EmployeeRowReadListener implements ReadListener<EmployeeExcelRow> {

    @Override
    public void invoke(EmployeeExcelRow data, AnalysisContext context) {
        log.info(data.toString());
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("it is end");
    }
}
