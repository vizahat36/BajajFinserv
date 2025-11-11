package com.bajaj.bajajfinserv.util;

/**
 * Utility that provides the final SQL query for the assigned problems.
 * For regNo ending in even digits (Question 2) we return the SQL that
 * counts younger employees in the same department.
 */
public class SQLSolver {

	/**
	 * Return the final SQL for the "even" assignment (Question 2) which
	 * counts employees younger than each employee within the same department.
	 */
	public static String getEvenQuestionFinalQuery() {
		return "SELECT e.emp_id, e.first_name, e.last_name, d.department_name, " +
				"(SELECT COUNT(*) FROM employee e2 WHERE e2.department = e.department AND e2.dob > e.dob) AS younger_employees_count " +
				"FROM employee e JOIN department d ON e.department = d.department_id " +
				"ORDER BY e.emp_id DESC;";
	}

}
