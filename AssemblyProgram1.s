/* AssemblyProgram1.s */

.data

.balign 4
message1: .asciz "Hey, type a number: "

.balign 4

message2: .asciz "Hey, type another number:"

.balign 4
sumout: .asciz "Sum: %d\n"

.balign 4
diffout: .asciz "Difference: %d\n"

.balign 4
scan_pattern: .asciz "%d"

.balign 4
number_read: .word 0

.balign 4
number_read2: .word 0

.balign 4
return: .word 0

.balign 4
return2: .word 0


.text

@function to add and subtract 2 inputed numbers

add_sub:
        ldr r1, address_of_return2
        str lr, [r1]

        add r4,r9,r8
        sub r5,r9,r8

        ldr lr, address_of_return2
        ldr lr, [lr]
        bx lr

address_of_return2: .word return2


.global main
main:
        ldr r1, address_of_return
        str lr, [r1]

        ldr r0, address_of_message1
        bl printf


        ldr r0,address_of_scan_pattern
        ldr r1, address_of_number_read
        bl scanf
        ldr r0, address_of_number_read
        ldr r0,[r0]
        mov r9, r0

        @good to here.

        ldr r0, address_of_message2
        bl printf

        ldr r0, address_of_scan_pattern
        ldr r1, address_of_number_read2
        bl scanf
        ldr r0, address_of_number_read2
        ldr r0, [r0]


        mov r8, r0
        bl add_sub

        mov r1, r4
        ldr r0, address_of_sumout
        bl printf

        mov r1,r5
        ldr r0, address_of_diffout
        bl printf

        mov r0, r5

        ldr lr, address_of_return
        ldr lr, [lr]
        bx lr



address_of_message1: .word message1
address_of_message2: .word message2
address_of_sumout: .word sumout
address_of_diffout: .word diffout
address_of_scan_pattern: .word scan_pattern
address_of_number_read: .word number_read
address_of_number_read2: .word number_read2
address_of_return: .word return
.global printf
.global scanf
