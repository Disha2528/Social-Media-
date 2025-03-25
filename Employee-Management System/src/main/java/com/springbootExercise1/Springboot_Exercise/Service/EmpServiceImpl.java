package com.springbootExercise1.Springboot_Exercise.Service;

import com.springbootExercise1.Springboot_Exercise.DTO.EmployeeDTO;
import com.springbootExercise1.Springboot_Exercise.DTO.SalaryRangeDTO;
import com.springbootExercise1.Springboot_Exercise.Entity.Department;
import com.springbootExercise1.Springboot_Exercise.Entity.Employee;
import com.springbootExercise1.Springboot_Exercise.Entity.Project;
import com.springbootExercise1.Springboot_Exercise.Exceptions.EmployeeNotFoundException;
import com.springbootExercise1.Springboot_Exercise.Exceptions.InvalidSalaryException;
import com.springbootExercise1.Springboot_Exercise.Repository.DepartmentRepository;
import com.springbootExercise1.Springboot_Exercise.Repository.EmployeeRepository;
import com.springbootExercise1.Springboot_Exercise.DTO.EmployeeRequestDTO;
import com.springbootExercise1.Springboot_Exercise.Repository.ProjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DeptService deptService;  // Injecting Department Service

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Transactional
    @Override

    public Employee saveEmp(EmployeeDTO employeeDTO) throws InvalidSalaryException, IllegalAccessException {

        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());

        // Fetch and set department from the database
        Department department = departmentRepository.findById(employeeDTO.getDept().getDeptId())
                .orElseThrow(() -> new IllegalAccessException("Department does not Exist"));

        employee.setDepartment(department);
        employee.setRole(employeeDTO.getRole());

        // Fetch and set projects from the database
        List<Project> projects= projectRepository.findAllByProjectIdIn(employeeDTO.getProjects());
        employee.setProjects(projects);

        for (Project project : projects) {
            project.getEmp().add(employee);
        }

        return employeeRepository.save(employee);
    }


    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }


    @Override
    public Employee getEmployeeById(int id) throws EmployeeNotFoundException {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));
    }


    @Transactional
    @Override
    public Employee modifyEmployee(Employee employeeDTO) throws EmployeeNotFoundException, InvalidSalaryException {

            Employee emp= employeeRepository.findById(employeeDTO.getId()).orElse(null);
            if(emp==null){
                throw new EmployeeNotFoundException("Employee not Found");
            }

            emp.setProjects(employeeDTO.getProjects());
            emp.setRole(employeeDTO.getRole());
            emp.setDepartment(employeeDTO.getDept());
            emp.setSalary(employeeDTO.getSalary());

        return employeeRepository.save(emp);
    }


    @Override
    @Transactional
    public Employee deleteEmployee(int empId) throws EmployeeNotFoundException {
        Employee emp= employeeRepository.findById(empId).orElse(null);
        if (emp==null) {
            throw new EmployeeNotFoundException("Employee not found with ID: " + empId);
        }
        employeeRepository.delete(emp);
        return emp;

    }


    @Override
    public List<Employee> getEmployeesByDepartment(String department) {
        return employeeRepository.findAll().stream()
                .filter(emp -> emp.getDepartment() != null && department != null
                        && department.equalsIgnoreCase(emp.getDepartment().getDeptId()))
                .collect(Collectors.toList());
    }


    @Override
    public List<Employee> getEmployeesBySalaryRange(SalaryRangeDTO salaryRangeDTO) {
        return employeeRepository.findBySalaryBetween(salaryRangeDTO.getMinSalary(), salaryRangeDTO.getMaxSalary());
    }


    @Override
    public Page<Employee> getEmployeesById(EmployeeRequestDTO requestDTO) {

        String sortDirection = (requestDTO.getSortDirection() != null) ? requestDTO.getSortDirection() : "ASC";
        String sortBy = (requestDTO.getSortBy() != null) ? requestDTO.getSortBy() : "name";

        //sortDirection -ASC or DSC
        //sortBy-parameter to sortBy
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);

        //page to retrieve - getPageNumber
        //page size
        //sorts
        Pageable pageable = PageRequest.of(requestDTO.getPageNumber(), requestDTO.getPageSize(), sort);

        return employeeRepository.findByDepartment_DeptId(requestDTO.getDeptId(), pageable);

    }

}
