package com.springbootExercise1.Springboot_Exercise.Controller;

import com.springbootExercise1.Springboot_Exercise.DTO.EmployeeDTO;
import com.springbootExercise1.Springboot_Exercise.DTO.ResponseHandler;
import com.springbootExercise1.Springboot_Exercise.DTO.SalaryRangeDTO;
import com.springbootExercise1.Springboot_Exercise.DTO.EmployeeRequestDTO;
import com.springbootExercise1.Springboot_Exercise.Entity.Employee;
import com.springbootExercise1.Springboot_Exercise.Exceptions.EmployeeNotFoundException;
import com.springbootExercise1.Springboot_Exercise.Exceptions.InvalidSalaryException;
import com.springbootExercise1.Springboot_Exercise.Service.DeptService;
import com.springbootExercise1.Springboot_Exercise.Service.EmpService;
import com.springbootExercise1.Springboot_Exercise.Service.ProjectService;
import com.springbootExercise1.Springboot_Exercise.Service.SalaryDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmpService empService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private SalaryDetailService salaryDetailService;


    @PostMapping("/addEmployee")
    public ResponseEntity<ResponseHandler> saveEmp(@RequestBody EmployeeDTO empDTO)
            throws InvalidSalaryException, IllegalAccessException {
        Employee emp = empService.saveEmp(empDTO);
        ResponseHandler response = new ResponseHandler(
                emp,
                "Employee Created Successfully",
                HttpStatus.CREATED.value(),
                true,
                "employee"
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/employees")
    public ResponseEntity<ResponseHandler> getAllEmployees() {
        List<Employee> employees = empService.getAllEmployee();
        ResponseHandler response = new ResponseHandler(
                employees,
                "Employees Fetched Successfully",
                HttpStatus.OK.value(),
                true,
                "employees"
        );
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResponseHandler> getEmployeeById(@PathVariable int id)
            throws EmployeeNotFoundException {
        Employee employee = empService.getEmployeeById(id);
        ResponseHandler response = new ResponseHandler(
                employee,
                "Employee Found",
                HttpStatus.OK.value(),
                true,
                "employee"
        );
        return ResponseEntity.ok(response);
    }


    @PostMapping("/modifyEmployee")
    public ResponseEntity<ResponseHandler> modifyEmployee(@RequestBody EmployeeDTO employeeDTO)
            throws EmployeeNotFoundException, InvalidSalaryException, IllegalAccessException {
        Employee emp = empService.modifyEmployee(empService.saveEmp(employeeDTO));
        ResponseHandler response = new ResponseHandler(
                emp,
                "Employee Modified Successfully",
                HttpStatus.OK.value(),
                true,
                "employee"
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<ResponseHandler> getEmployeesByDepartment(@PathVariable String department) {
        List<Employee> employees = empService.getEmployeesByDepartment(department);
        ResponseHandler response = new ResponseHandler(
                employees,
                "Employees Retrieved Successfully",
                HttpStatus.OK.value(),
                true,
                "employees"
        );
        return ResponseEntity.ok(response);
    }


    @GetMapping("/salaryRange/{salaryRangeDTO}")
    public ResponseEntity<ResponseHandler> getEmployeesBySalaryRange(SalaryRangeDTO salaryRangeDTO) {
        List<Employee> employees = empService.getEmployeesBySalaryRange(salaryRangeDTO);
        ResponseHandler response = new ResponseHandler(
                employees,
                "Employees Retrieved by Salary Range",
                HttpStatus.OK.value(),
                true,
                "employees"
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseHandler> deleteEmployee(@PathVariable int id)
            throws EmployeeNotFoundException {
        Employee deletedEmployee = empService.deleteEmployee(id);
        ResponseHandler response = new ResponseHandler(
                deletedEmployee,
                "Employee Deleted Successfully",
                HttpStatus.OK.value(),
                true,
                "employee"
        );
        return ResponseEntity.ok(response);
    }


    @PostMapping("/byEmpId")
    public ResponseEntity<ResponseHandler> getEmployeesById(@RequestBody EmployeeRequestDTO requestDTO) {
        Page<Employee> employees = empService.getEmployeesById(requestDTO);
        ResponseHandler response = new ResponseHandler(
                employees,
                "Employees Retrieved by ID",
                HttpStatus.OK.value(),
                true,
                "employees"
        );
        return ResponseEntity.ok(response);
    }
}
