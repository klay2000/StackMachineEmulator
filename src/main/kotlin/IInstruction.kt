interface IInstruction {
    val instruction: UInt
    val ldimm: Boolean
    val imm: UInt
    val lda: Boolean
    val ldb: Boolean
    val argsel: UInt
    val pusharg: Boolean
    val poparg: Boolean
    val puship: Boolean
    val popip: Boolean
    val fcode: UInt
    val wrmem: Boolean
    val ldmem: Boolean
    val cond: UInt
}