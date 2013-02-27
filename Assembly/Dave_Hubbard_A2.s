# Dave Hubbard
# CSE 230 - Assignment 2
# 2/24/10
# This program asks the user to fill in an integer array.
# The size of the array (Max: 20) is chosen by the user.




.data
	numbers:  .space 80 					#declare 80 bytes 
	prompt:   .asciiz   "How many values to read? "
	space:    .asciiz   "  "
	prompt2:  .asciiz   "Enter a value: "
	arr:      .asciiz   "\nArray:\n"
	sarr:	  .asciiz   "\n\nSorted:\n"

.text
	.globl main

main:

	la $a0, numbers 		#Array address loaded for parameter
	jal readData			#jump and return to the Read Data function
	move $s0, $v0 			#save number of integers read into $s0
	
	la $a0, arr
	li $v0, 4
	syscall

	la $a0, numbers 		#array address loaded for parameter
	move $a1, $s0			#count loaded for parameter
	jal print 			#call print function

	la $a0, numbers 		#array address loaded for parameter
	move $a1, $s0 			#count loaded for parameter
	jal sort			#call print function

	la $a0, sarr
	li $v0, 4
	syscall

	la $a0, numbers 		#array address loaded for parameter
	move $a1, $s0 			#count loaded for parameter
	jal print 			#call print function


	li $v0, 10 			#set command to end program
	syscall 			#end program
	
swap:
	lw $t0, ($a0)			#get first value
	lw $t1, ($a1) 			#get second value
	sw $t0, ($a1) 			#set second value to first value
	sw $t1, ($a0)			#set first value to second value
	jr $ra

readData:	
	addi $sp, $sp, -12
	sw $s0, ($sp)			#s0 = count
	sw $s1, 4($sp)			#s1 = user number 
	sw $s2, 8($sp)			#s2 = loop counter
	la $t0, ($a0)			#t0 = array base [0]
	la $a0, prompt
	li $v0, 4
	syscall
	li $v0, 5
	syscall
	move $s0, $v0
	li $s2, 0
 
 rloop:	beq $s2, $s0, rnext
	li $v0, 4
	la $a0, prompt2
	syscall
	li $v0, 5
	syscall
	sll $t1, $s2, 2
	add $t1, $t1, $t0
	move $s1, $v0
	sw $s1, ($t1)
	addi $s2, $s2, 1
	j rloop
 
 rnext:	move $v0, $s0
	lw $s0, ($sp)
	lw $s1, 4($sp)
	lw $s2, 8($sp)	
	addi $sp, $sp, 12
	jr $ra 


print:
	la $t0, ($a0)
	li $t1, 0
 ploop: beq $t1, $a1, pnext
	sll $t2, $t1, 2
	add $t2, $t2, $t0
	lw $a0, ($t2)
	li $v0, 1
	syscall
	li $v0, 4
	la $a0, space
	syscall
	addi $t1, $t1, 1
	j ploop

 pnext: jr $ra
	


sort:
	la $t0, ($a0)
	addi $t1, $a1, -1		#t1 = outer loop counter(i)
 outer: blt $t1, $0, snext 
	li $t2, 1 			#t2 = inner loop counter(j)
 inner: bgt $t2, $t1, done
	sll $t3, $t2, 2			#t3 = array[j]
	add $t3, $t3, $t0		
	lw $a0, -4($t3)
	lw $a1, ($t3)		
	bgt $a0, $a1, nswap
	addi $t2, $t2, 1
	j inner
 done:	addi $t1, $t1, -1
	j outer

 nswap: addi $sp, $sp, -12
	sw $ra, ($sp)
	sw $t0, 4($sp)
	sw $t1, 8($sp)
	la $a0, -4($t3)
	la $a1, ($t3)
	jal swap
	addi $t2, $t2, 1
 	lw $ra, ($sp)
	lw $t0, 4($sp)
	lw $t1, 8($sp)
	addi $sp, $sp, 12
	j inner
	
 snext: 
	jr $ra
	
	
