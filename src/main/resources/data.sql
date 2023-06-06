insert into employee(first_name, last_name) values ('John', 'Doe');
insert into employee(first_name, last_name) values ('Jane', 'Dee');
insert into employee(first_name, last_name) values ('Anna', 'Green');

insert into employee_salary(employee_id, amount, annual_payroll_count) values (1, 2000, 26);  -- John
insert into employee_salary(employee_id, amount, annual_payroll_count) values (2, 2000, 26);  -- Jane
insert into employee_salary(employee_id, amount, annual_payroll_count) values (3, 2000, 26);  -- Anna

insert into payroll(payroll_date, payroll_status, gross_amount, benefit_deductions, net_amount, employee_id, employee_salary_id) values ('2023-07-06 13:00:00.000', 'DRAFT', 2000, 0, 2000, 1, 1); -- John
insert into payroll(payroll_date, payroll_status, gross_amount, benefit_deductions, net_amount, employee_id, employee_salary_id) values ('2023-07-06 13:00:00.000', 'DRAFT', 2000, 0, 2000, 2, 2); -- Jane
insert into payroll(payroll_date, payroll_status, gross_amount, benefit_deductions, net_amount, employee_id, employee_salary_id) values ('2023-07-06 13:00:00.000', 'DRAFT', 2000, 0, 2000, 3, 3); -- Anna