package com.pep.platform.rbac.web.controller;

import com.pep.platform.rbac.service.employee.EmployeeMgtService;
import com.pep.platform.rbac.web.form.EmployeeCreateForm;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 员工管理控制器
 *
 * @author gang.liu
 */
@RestController
@RequestMapping("/employee/mgt")
@AllArgsConstructor
public class EmployeeMgtController {

    /**
     * 员工管理服务
     */
    private final EmployeeMgtService employeeMgtService;

    @PostMapping("/create")
    public void createEmployee(@Valid @RequestBody EmployeeCreateForm createForm) {
        employeeMgtService.createEmployee(createForm);
    }
}
