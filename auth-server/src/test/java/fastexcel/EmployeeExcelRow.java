package fastexcel;

import cn.idev.excel.annotation.ExcelProperty;
import cn.idev.excel.annotation.write.style.ColumnWidth;
import cn.idev.excel.annotation.write.style.ContentRowHeight;
import cn.idev.excel.annotation.write.style.HeadRowHeight;
import cn.idev.excel.annotation.write.style.HeadStyle;
import cn.idev.excel.enums.poi.HorizontalAlignmentEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 账户excel导入导出行
 *
 * @author gang.liu
 */
@Data
@HeadRowHeight(value = 35)
@ContentRowHeight(value = 30)
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeExcelRow {

    /**
     * 账户名
     */
    @ExcelProperty(value = "账户")
    @ColumnWidth(value = 20)
    @HeadStyle(horizontalAlignment = HorizontalAlignmentEnum.LEFT)
    private String account;

    /**
     * 昵称
     */
    @ExcelProperty(value = "昵称")
    @ColumnWidth(value = 20)
    @HeadStyle(horizontalAlignment = HorizontalAlignmentEnum.LEFT)
    private String nickname;

    /**
     * 手机号
     */
    @ExcelProperty(value = "手机号")
    @ColumnWidth(value = 40)
    @HeadStyle(horizontalAlignment = HorizontalAlignmentEnum.LEFT)
    private String phone;

    /**
     * 邮箱
     */
    @ExcelProperty(value = "邮箱")
    @ColumnWidth(value = 40)
    @HeadStyle(horizontalAlignment = HorizontalAlignmentEnum.LEFT)
    private String email;

    /**
     * 角色名称集合 多个以英文逗号分割
     */
    @ExcelProperty(value = "角色名称,多个以英文逗号分割")
    @ColumnWidth(value = 50)
    @HeadStyle(horizontalAlignment = HorizontalAlignmentEnum.LEFT)
    private String roleNames;
}
