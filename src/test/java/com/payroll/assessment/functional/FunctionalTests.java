package com.payroll.assessment.functional;

import static com.payroll.assessment.testutils.TestUtils.businessTestFile;
import static com.payroll.assessment.testutils.TestUtils.currentTest;
import static com.payroll.assessment.testutils.TestUtils.yakshaAssert;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.payroll.assessment.EmployeePayrollSystem;

public class FunctionalTests {

	private EmployeePayrollSystem payrollSystem;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	@BeforeEach
	public void setUp() {
		payrollSystem = new EmployeePayrollSystem();
		System.setOut(new PrintStream(outputStreamCaptor)); // Redirect System.out to capture output for verification
	}

	@Test
	public void testAddAndCalculateMonthlySalary() throws IOException {
		// Adding an employee
		payrollSystem.addEmployee("Alice Johnson", "E003", "Marketing", 84000);
		// Calculate the monthly salary and capture the output
		payrollSystem.calculateMonthlySalaries();

		String expectedOutput = "Employee ID: E003, Name: Alice Johnson, Monthly Salary: 7000.00\n";
//		        assertEquals(expectedOutput, outputStreamCaptor.toString().trim(),
//		            "The monthly salary calculation should match the expected output.");
		yakshaAssert(currentTest(),
				expectedOutput.toString().equals(outputStreamCaptor.toString().trim()) ? "true" : "false",
				businessTestFile);

	}
}