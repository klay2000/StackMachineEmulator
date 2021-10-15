class Instruction(val instruction: UInt)
{

    val ldimm = instruction.and((0b1u).shl(31)) != 0u
    val imm = instruction.and(0x7FFFFFFFu)

    val lda = instruction.and((0b1u).shl(30)) != 0u
    val ldb = instruction.and((0b1u).shl(29)) != 0u
    val argsel = instruction.and((0b11u).shl(27)).shr(27)
    val pusharg = instruction.and((0b1u).shl(26)) != 0u
    val poparg = instruction.and((0b1u).shl(25)) != 0u
    val puship = instruction.and((0b1u).shl(24)) != 0u
    val popip = instruction.and((0b1u).shl(23)) != 0u
    val fcode = instruction.and((0b1111u).shl(19)).shr(19)
    val wrmem = instruction.and((0b1u).shl(18)) != 0u
    val ldmem = instruction.and((0b1u).shl(17)) != 0u
    val cond = instruction.and((0b11u).shl(15)).shr(15)

}