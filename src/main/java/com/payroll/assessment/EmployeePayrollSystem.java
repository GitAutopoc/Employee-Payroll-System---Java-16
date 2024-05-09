package com.payroll.assessment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeePayrollSystem {
	public record Employee(String name, String id, String department, double annualSalary) {
	}

	private List<Employee> employees = new ArrayList<>();

	public void addEmployee(String name, String id, String department, double annualSalary) {
		employees.add(new Employee(name, id, department, annualSalary));
		System.out.println("Employee added successfully: " + name);
	}

	public void updateAnnualSalary(String id, double newAnnualSalary) {
		Optional<Employee> employeeToUpdate = employees.stream().filter(employee -> employee.id().equals(id))
				.findFirst();

		if (employeeToUpdate.isPresent()) {
			Employee updatedEmployee = new Employee(employeeToUpdate.get().name(), employeeToUpdate.get().id(),
					employeeToUpdate.get().department(), newAnnualSalary);
			employees.set(employees.indexOf(employeeToUpdate.get()), updatedEmployee);
			System.out.println("Updated annual salary for employee ID " + id);
		} else {
			System.out.println("Employee not found with ID: " + id);
		}
	}

	public void calculateMonthlySalaries() {
		employees.forEach(employee -> {
			double monthlySalary = employee.annualSalary() / 12;
			System.out.printf("Employee ID: %s, Name: %s, Monthly Salary: %.2f%n", employee.id(), employee.name(),
					monthlySalary);
		});
	}

	public static void main(String[] args) {
		EmployeePayrollSystem payrollSystem = new EmployeePayrollSystem();

		payrollSystem.addEmployee("John Doe", "E001", "HR", 60000);
		payrollSystem.addEmployee("Jane Smith", "E002", "IT", 72000);

		payrollSystem.updateAnnualSalary("E001", 65000);

		payrollSystem.calculateMonthlySalaries();
	}
}
