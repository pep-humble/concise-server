package com.pep.platform.rbac.repository;

import com.pep.platform.rbac.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

/**
 * 员工信息数据库操作
 *
 * @author gang.liu
 */
@Repository
public interface EmployeeRepository extends JpaRepositoryImplementation<EmployeeEntity, String> {

}
