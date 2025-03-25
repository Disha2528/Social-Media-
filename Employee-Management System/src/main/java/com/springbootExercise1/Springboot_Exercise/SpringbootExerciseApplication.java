package com.springbootExercise1.Springboot_Exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootExerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootExerciseApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner loadData(
//			EmployeeRepository employeeRepository,
//			DepartmentRepository departmentRepository,
//			ProjectRepository projectRepository) {
//
//		return args -> {
//			Random random = new Random();
//
//			// Creating 10 Departments
//			List<Department> departments = Arrays.asList(
//					new Department("HR001"),
//					new Department("IT001"),
//					new Department("FIN001"),
//					new Department("MKT001"),
//					new Department("OPS001"),
//					new Department("ENG001"),
//					new Department("SALES001"),
//					new Department("SUP001"),
//					new Department("QA001"),
//					new Department("ADMIN001")
//			);
//			departmentRepository.saveAll(departments);
//
//			// Creating 50 Employees
//			List<Employee> employees = IntStream.range(1, 51)  // Generate 50 employees
//					.mapToObj(i -> {
//						return new Employee(
//								"Employee" + i, // Unique name
//								departments.get(random.nextInt(departments.size())), // Assign random department
//								new ArrayList<>(), // Empty project list (to be assigned later)
//								null, // SalaryDetail (initialize if required)
//								35000 + random.nextInt(90000), // Salary between 35K and 125K
//								random.nextInt(100) < 20 ? "MANAGER" : "EMPLOYEE" // Role as String
//						);
//					})
//					.collect(Collectors.toList());
//
//			employeeRepository.saveAll(employees);
//			alaryDetailRepository.saveAll(salaryDetail);s
//
//			// Creating 10 Projects and Assigning Employees
//			List<Project> projects = IntStream.range(1, 11)
//					.mapToObj(i -> {
//						LocalDate startDate = LocalDate.of(2023, random.nextInt(12) + 1, random.nextInt(28) + 1);
//						LocalDate endDate = startDate.plusMonths(random.nextInt(36) + 6); // 6-42 months duration
//
//						// Select 5-15 random employees per project
//						List<Employee> projectEmployees = new ArrayList<>(employees);
//						Collections.shuffle(projectEmployees);
//						projectEmployees = projectEmployees.subList(0, random.nextInt(11) + 5);
//
//						return new Project(
//								"P" + String.format("%03d", i),  // Unique project IDs (P001, P002, ...)
//								"Project " + i,
//								startDate,
//								endDate,
//								random.nextInt(60) + 20, // Work hours per week (20-80)
//								projectEmployees
//						);
//					})
//					.collect(Collectors.toList());
//
//			projectRepository.saveAll(projects);
//
//
//			System.out.println("Generated 50 Employees and 10 Projects Successfully!");
//		};
	}



