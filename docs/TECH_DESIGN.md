# Business requirements

* One of the critical functions that we provide for our clients is the ability to pay for their employees’ 
benefits packages. A portion of these costs are deducted from their paycheck
* The cost of benefits is $1000/year for each employee
* Each dependent (children and possibly spouses) incurs a cost of $500/year
* Anyone whose name starts with ‘A’ gets a 10% discount, employee or dependent
* Employees may use one or more devices to choose their benefits package as part of a multi-step process that involves 
inputting dependents and need a preview of the costs
* Administrators need to preview payroll before it is run to double check the numbers.
* The costs may change in between, so the calculation needs to reflect the current state of the calculation, 
rather than the state at the time the employee entered it.

# Assumptions

* All employees are paid $2000 per paycheck before deductions
* There are 26 paychecks in a year
* Benefit discount is cumulative. If both, the employee's name and their dependent's name start with `A` they get a discount of each linked benefit.
* All amounts are in **US Cents**
* I assume that a new Payroll object is created automatically by the system after the last Payroll has been completed. 
So there is always exactly one payroll instance in the `DRAFT` state for each employee.

# Guiding principles

* Since we're dealing with finances/payroll domain a full backwards auditability of the benefits and payroll records is required. 
This will have an impact on the data store architecture as we will have to keep separate versions of all source data used for 
benefits / payroll calculations over the course of time. 
* 