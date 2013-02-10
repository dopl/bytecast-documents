	.file	"main.c"
	.section	.rodata
.LC0:
	.string	"fun1: [%d %d]\n"
	.text
.globl fun1
	.type	fun1, @function
fun1:
.LFB0:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	movq	%rsp, %rbp
	.cfi_offset 6, -16
	.cfi_def_cfa_register 6
	subq	$16, %rsp
	movl	%edi, -4(%rbp)
	movl	%esi, -8(%rbp)
	movl	$.LC0, %eax
	movl	-8(%rbp), %edx
	movl	-4(%rbp), %ecx
	movl	%ecx, %esi
	movq	%rax, %rdi
	movl	$0, %eax
	call	printf
	leave
	ret
	.cfi_endproc
.LFE0:
	.size	fun1, .-fun1
	.section	.rodata
.LC1:
	.string	"fun2: [%d %d]\n"
	.text
.globl fun2
	.type	fun2, @function
fun2:
.LFB1:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	movq	%rsp, %rbp
	.cfi_offset 6, -16
	.cfi_def_cfa_register 6
	subq	$16, %rsp
	movl	%edi, -4(%rbp)
	movl	%esi, -8(%rbp)
	movl	$.LC1, %eax
	movl	-8(%rbp), %edx
	movl	-4(%rbp), %ecx
	movl	%ecx, %esi
	movq	%rax, %rdi
	movl	$0, %eax
	call	printf
	leave
	ret
	.cfi_endproc
.LFE1:
	.size	fun2, .-fun2
.globl main
	.type	main, @function
main:
.LFB2:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	movq	%rsp, %rbp
	.cfi_offset 6, -16
	.cfi_def_cfa_register 6
	subq	$16, %rsp
	movl	%edi, -4(%rbp)
	movq	%rsi, -16(%rbp)
	movl	$20, %esi
	movl	$10, %edi
	call	fun1
	movl	$40, %esi
	movl	$30, %edi
	call	fun2
	movl	$60, %esi
	movl	$50, %edi
	call	fun1
	movl	$0, %eax
	leave
	ret
	.cfi_endproc
.LFE2:
	.size	main, .-main
	.ident	"GCC: (Debian 4.4.5-8) 4.4.5"
	.section	.note.GNU-stack,"",@progbits
