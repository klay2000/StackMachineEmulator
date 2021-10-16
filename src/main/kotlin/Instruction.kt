class Instruction(override val instruction: UInt) : IInstruction {

    override val ldimm = instruction.and((0b1u).shl(31)) != 0u
    override val imm = instruction.and(0x7FFFFFFFu)

    override val lda = instruction.and((0b1u).shl(30)) != 0u
    override val ldb = instruction.and((0b1u).shl(29)) != 0u
    override val argsel = instruction.and((0b11u).shl(27)).shr(27)
    override val pusharg = instruction.and((0b1u).shl(26)) != 0u
    override val poparg = instruction.and((0b1u).shl(25)) != 0u
    override val puship = instruction.and((0b1u).shl(24)) != 0u
    override val popip = instruction.and((0b1u).shl(23)) != 0u
    override val fcode = instruction.and((0b1111u).shl(19)).shr(19)
    override val wrmem = instruction.and((0b1u).shl(18)) != 0u
    override val ldmem = instruction.and((0b1u).shl(17)) != 0u
    override val cond = instruction.and((0b11u).shl(15)).shr(15)

}