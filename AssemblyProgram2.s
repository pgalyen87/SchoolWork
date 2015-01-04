/* AssemblyProgram2.s */

.data

.balign 4
welcome: .asciz "Welcome to the Assembly-Grader. How many students do you have? \n"

.balign 4

name: .asciz "Enter the student's name.\n"

.balign 4

grade: .asciz "Enter grade %d for %s\n"

.balign 4
report: .asciz "Grade Report for %s\nSum: %d\nHighest Grade: %d\n"

.balign 4
report2: .asciz "Lowest Grade: %d\nAverage: %d\n"

.balign 4
scan_pattern: .asciz "%d"

.balign 4
scan_pattern2: .asciz "%s"


.balign 4
number_read: .word 0

.balign 4
name_read: .asciz "                   "                

.balign 4
return: .word 0

.balign 4
return2: .word 0


.text


.global main
main:
	ldr r1, address_of_return
	str lr, [r1]

	@@@@@@@@@@@@@@ get number of students @@@@@@@@@@@@@

	ldr r0, address_of_welcome
	bl printf

	ldr r0,address_of_scan_pattern
	ldr r1, address_of_number_read
	bl scanf

	ldr r0, address_of_number_read
	ldr r0, [r0]
	mov r11,r0   @register 11 contains number of students

	getall:          @ beginning of loop to get all students and grades

	@@@@@@@get name@@@@@@@@@@@@@@@@@@@@@
	
	ldr r0, address_of_name
	bl printf

	ldr r0,address_of_scan_pattern2
	ldr r1, address_of_name_read
	bl scanf

	@ldr r0, address_of_name_read
	mov r10,r1   @register 10 contains name of student

	@@@@@@@@@@@@@@ get grades
	
	mov r9, #1	@counter
	mov r4, #0      @greatest
	mov r5, #101    @least
	mov r6, #0	@average	

	grades:          @ beginning of loop to get all grades for 1 student
	
	mov r1, r9  @counter
	ldr r2, address_of_name_read  @name

	ldr r0, address_of_grade
	bl printf	
	
	ldr r0,address_of_scan_pattern
	ldr r1, address_of_number_read
	bl scanf
	ldr r1, address_of_number_read
	ldr r1, [r1]
	
	cmp r1,r4        @see if new grade is highest
	bgt  greatest
	
	greatest_return:
	
	cmp r5,r1     @see if new grade is lowest
	bgt least
	
	least_return:	
	
	add r6,r6,r1

	add r9,r9,#1
	cmp r9, #5
	bne grades   @@@@ end of grades loop

	mov r9,r6  @Sum in r9

	average:
	sub r6,r6,#4
	add r8, r8, #1
	cmp r6, #0
	bge average 	


	@ Sum: r9 Highest: r4 Lowest: r5 Average r8
	

	ldr r0, address_of_report
	ldr r1, address_of_name_read
	mov r2, r9
	mov r3, r4
	bl printf

	ldr r0, address_of_report2
	mov r1, r5
	mov r2, r8
	bl printf			

	sub r11,r11,#1
	cmp r11,#0
	bgt getall	

	ldr lr, address_of_return
	ldr lr, [lr]
	bx lr

	greatest:
	mov r4, r1
	bal greatest_return

	least:
	mov r5, r1
	bal least_return


address_of_welcome: .word welcome
address_of_name: .word name
address_of_grade: .word grade
address_of_report: .word report
address_of_report2: .word report2
address_of_scan_pattern: .word scan_pattern
address_of_scan_pattern2: .word scan_pattern2
address_of_number_read: .word number_read
address_of_name_read: .word name_read
address_of_return: .word return
.global printf
.global scanf
