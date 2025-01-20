package com.pep.platform.rbac.service.employee;

import com.pep.platform.rbac.entity.EmployeeEntity;
import com.pep.platform.rbac.enums.GenderEnum;
import com.pep.platform.rbac.repository.EmployeeRepository;
import com.pep.platform.rbac.web.form.EmployeeCreateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * 员工管理服务
 *
 * @author gang.liu
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeMgtService {

    /**
     * 员工信息数据库操作
     */
    private final EmployeeRepository employeeRepository;

    public void createEmployee(EmployeeCreateForm createForm) {
        EmployeeEntity employeeEntity = safeEmployeeEntity(entity ->
                entity.setAccount(createForm.getAccount())
                        .setCreator("pep")
                        .setCreateTime(System.currentTimeMillis())
                        .setModifier("pep")
                        .setPassword("123456")
                        .setGender(GenderEnum.MALE)
                        .setModifyTime(System.currentTimeMillis())
                        .setNickname(createForm.getNickname()));
        employeeRepository.save(employeeEntity);
    }

    public EmployeeEntity safeEmployeeEntity(Consumer<EmployeeEntity> consumer) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        Optional
                .ofNullable(consumer)
                .ifPresent(element -> element.accept(employeeEntity));
        if (StringUtils.isBlank(employeeEntity.getAccount())) {
            throw new NullPointerException();
        }
        if (StringUtils.isBlank(employeeEntity.getNickname())) {
            throw new NullPointerException();
        }
        return employeeEntity;
    }

}
