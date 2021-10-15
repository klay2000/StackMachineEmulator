class ControlUnit(private val reg: Registers,
                  private val argStack: Stack,
                  private val ipStack: Stack,
                  private val mem: MemoryController,
                  private val alu: Alu) {

    fun tick(){
        var instruction = Instruction(mem.loadIns(reg.ip))

        if(instruction.ldimm){
            argStack.push(instruction.imm)
        }else{

            //save values of registers etc
            var lastA = reg.a
            var lastB = reg.b
            var lastIP = reg.ip
            var lastRes = alu.result

            //do address stuff based off of cond
            when(instruction.cond){
                0u -> reg.ip += 1u

                1u -> if(lastRes.toInt() < 0){
                    var nextAddr = mem.loadIns(reg.ip+1u)

                    if(nextAddr == 0xFFFFFFFFu) reg.ip = lastA
                    else reg.ip = nextAddr
                }
                else reg.ip += 2u

                2u -> if(lastRes.toInt() > 0){
                    var nextAddr = mem.loadIns(reg.ip+1u)

                    if(nextAddr == 0xFFFFFFFFu) reg.ip = lastA
                    else reg.ip = nextAddr
                }
                else reg.ip += 2u

                3u -> if(lastRes.toInt() == 0){
                    var nextAddr = mem.loadIns(reg.ip+1u)

                    if(nextAddr == 0xFFFFFFFFu) reg.ip = lastA
                    else reg.ip = nextAddr
                }
                else reg.ip += 2u
            }

            // do the rest of the operations (loading saving shifting stuff around registers and stacks)
            if(instruction.lda) reg.a = argStack.getTop()
            if(instruction.ldb) reg.b = argStack.getSecond()

            if(instruction.pusharg){
                when(instruction.argsel){
                    0u -> argStack.push(lastA)

                    1u -> argStack.push(lastB)

                    2u -> argStack.push(mem.lastRead)

                    3u -> argStack.push(lastRes)
                }
            }
            if(instruction.poparg) argStack.pop()


            if(instruction.puship) ipStack.push(lastIP)
            if(instruction.popip){
                reg.ip = ipStack.getTop()
                ipStack.pop()
            }

            alu.fcode = instruction.fcode

            if(instruction.wrmem) mem.save(lastA, lastB)
            if(instruction.ldmem) mem.load(lastA)
        }
    }

}
