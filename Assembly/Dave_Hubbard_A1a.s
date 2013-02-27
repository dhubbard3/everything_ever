.data
	prompt: .asciiz "Enter a number: "
	psum:   .asciiz "The sum is: "
	plow:   .asciiz "\nThe lowest number is: "
	phigh:  .asciiz "\nThe highest number is: "
	newl:   .asciiz " \n"

.text
	.globl main

main:
	la $a0,prompt
	li $v0,4
	syscall
	li $v0,5
	syscall
	move $t0,$v0		# $t0 holds the current number.
	move $s0,$v0		# $s0 holds the sum.
	move $s1,$v0		# $s1 holds the lowest number.
	move $s2,$v0		# $s2 holds the highest number.
	addi $t1,$0,9		# $t1 is the counter.

loop:
	beq $t1,0,endloop
	la $a0,prompt
	li $v0,4
	syscall
	li $v0,5
	syscall
	move $t0,$v0
	add $s0,$s0,$t0
	addi $t1,$t1,-1
	blt $v0,$s1,less
	bgt $v0,$s2,more
	j loop
less:
	move $s1,$v0
	bgt $v0,$s2,more
	j loop
more:
	move $s2,$v0
	j loop
endloop:
	la $a0,psum
	li $v0,4
	syscall
	li $v0,1	
	move $a0,$s0		
	syscall			# Output Sum


	la $a0,plow
	li $v0,4
	syscall
	li $v0,1
	move $a0,$s1
	syscall			# Output Low


	la $a0,phigh
	li $v0,4
	syscall
	li $v0,1
	move $a0,$s2
	syscall			# Output High
	li $v0,10
	syscall