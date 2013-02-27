# Dave Hubbard
# CSE 230 Section 14007


.data
	numbers:  .space 40
	name:     .asciiz "Dave Hubbard\n"
	prompt:   .asciiz "Enter a number: "
	leftp:    .asciiz " ("
	rightp:   .asciiz ")\n"

.text

############################################################# main ####
#
# Asks user to enter up to 10 integers. Reads and prints the data. No
# parameters and no return values.
#######################################################################

main:
	la $a0, name
	li $v0, 4
	syscall					# print name

	la $a0, numbers				# set array address as parameter for readData
	jal readData
	
	la $a0, numbers				# set array address as parameter for print
	move $a1, $v0				# set array count as parameter for print
	jal print

	li, $v0, 10
	syscall					# end program


########################################################## readData ####
#
# Asks the user to enter 10 integers into an array. Input ends when the 
# user enters a 0, or 10 numbers are read. Parameter ($a0) holds the
# array address. Return value ($v0) holds the number of integers read.
########################################################################

readData:
	
	addi $sp, $sp, -4
	sw $s0, ($sp)
	move $s0, $a0
	li $t0, 0				# $t0 - doubles as loop counter/array count
	li $t1, 10				# $t1 - array limit

 loopR:	beq $t0, $t1, endr			# end loop when counter = 10
	la, $a0, prompt
	li, $v0, 4
	syscall					# print prompt
	li $v0, 5
	syscall					# get integer from user
	beq $v0, $0, endr			# end loop if user input was 0
	sll $t2, $t0, 2
	add $t2, $t2, $s0
	sw $v0, ($t2)				# store user input in array[$t2]
	addi $t0, $t0, 1			# increment counter
	j loopR

  endr:	move $v0, $t0				# move array count to return register
	lw $s0, ($sp)
	addi $sp, $sp, 4
	jr $ra

 
############################################################ print ####
#
# Prints each value in the array and the difference from each value to
# the average of the numbers. Calls the function 'average', takes the
# parameters array address ($a0) and number of integers held ($a1).
#######################################################################

print:
	addi $sp, $sp, -8
	sw $ra, ($sp)
	sw $s0, 4($sp)
	move $s0, $a0				# Store base address in $s0
	jal average				# Call average with same parameters
	li $t0, 0				# $t0 - counter

 loopP: beq $t0, $a1, endp			# end loop when counter = array count
	lw $t1, ($s0)
	move $a0, $t1 
	li $v0, 1
	syscall 				# print array [$t1]
	li $v0, 4
	la $a0, leftp
	syscall					# print left parenthesis
	mtc1 $t1, $f4				# move int $t1 into float register
	cvt.s.w $f5, $f4			# convert int to float
	sub.s $f12, $f0, $f5
	li $v0, 2
	syscall 				# print difference from average
	li $v0, 4
	la $a0, rightp
	syscall					# print right parenthesis
	addi $t0, $t0, 1			# increment counter
	addi $s0, $s0, 4			# increment pointer
	j loopP

  endp: lw $ra, ($sp)
	lw $s0, 4($sp)
	addi $sp, $sp, 8
	jr $ra
	
	
########################################################## average ####
#
# Calculates the average of the numbers in the user generated array.
# Result is a floating point number stored in $f0. Parameters $a0 and
# $a1 are the array base address and number of integers respectively.
#######################################################################

average:
	addi $sp, $sp, -8
	sw $ra, ($sp)
	sw $s0, 4($sp)
	li $t0, 0				# $t0 - counter
	li $s0, 0 				# $s0 - sum

 loopA: beq $t0, $a1, enda			# end loop when counter = array count
	lw $t1, ($a0)
	add $s0, $s0, $t1
	addi $a0, $a0, 4
	addi $t0, $t0, 1
	j loopA

  enda: mtc1 $s0, $f4				# Move sum to float register
	cvt.s.w $f5, $f4			# Convert int sum to float
	mtc1 $a1, $f4				# Move array count to float register
	cvt.s.w $f6, $f4
	div.s $f0, $f5, $f6			# Calulate average, store in return register
	lw $ra, ($sp)
	lw $s0, 4($sp)
	addi $sp, $sp, 8
	jr $ra
	

	