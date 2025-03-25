package com.springbootExercise1.Springboot_Exercise.Controller;

import com.springbootExercise1.Springboot_Exercise.DTO.DepartmentDTO;
import com.springbootExercise1.Springboot_Exercise.DTO.ResponseHandler;
import com.springbootExercise1.Springboot_Exercise.Entity.Department;
import com.springbootExercise1.Springboot_Exercise.Exceptions.DepartmentNotFoundException;
import com.springbootExercise1.Springboot_Exercise.Exceptions.EmployeeNotFoundException;
import com.springbootExercise1.Springboot_Exercise.Service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DeptService deptService;


    @PostMapping("/add")
    public ResponseEntity<ResponseHandler> saveDept(@RequestBody DepartmentDTO departmentDTO)
            throws EmployeeNotFoundException {
        Department department = deptService.saveDept(departmentDTO);
        ResponseHandler response = new ResponseHandler(
                department,
                "Department Created Successfully",
                HttpStatus.CREATED.value(),
                true,
                "department"
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseHandler> getDeptById(@PathVariable String id) {
        Optional<Department> department = deptService.getDeptById(id);
        ResponseHandler response = new ResponseHandler(
                department.orElse(null),
                department.isPresent() ? "Department Found" : "Department Not Found",
                department.isPresent() ? HttpStatus.OK.value() : HttpStatus.NOT_FOUND.value(),
                department.isPresent(),
                "department"
        );
        return ResponseEntity.status(department.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(response);
    }


    @PostMapping("/update")
    public ResponseEntity<ResponseHandler> updateDept(@RequestBody DepartmentDTO departmentDTO) {
        Department updatedDept = deptService.updateDept(departmentDTO);
        ResponseHandler response = new ResponseHandler(
                updatedDept,
                "Department Updated Successfully",
                HttpStatus.OK.value(),
                true,
                "department"
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseHandler> deleteDept(@PathVariable String id)
            throws DepartmentNotFoundException {
        Optional<Department> deletedDept = deptService.deleteDept(id);
        ResponseHandler response = new ResponseHandler(
                deletedDept.orElse(null),
                "Department Deleted Successfully",
                HttpStatus.OK.value(),
                true,
                "department"
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseHandler> getAllDepartments() {
        List<Department> departments = deptService.getDept();
        ResponseHandler response = new ResponseHandler(
                departments,
                "Departments Retrieved Successfully",
                HttpStatus.OK.value(),
                true,
                "departments"
        );
        return ResponseEntity.ok(response);
    }
}
