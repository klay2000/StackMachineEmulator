fun main(){

    val reg = Registers()
    val mem = MemoryController()
    val alu = Alu()
    val argStack = Stack()
    val addrStack = Stack()
    val ctrlUnit = ControlUnit(reg, argStack, addrStack, mem, alu)

    mem.save(0u, 0b00000000011100000000000000000000u)
    mem.save(1u, 0b00011100000000000000000000000000u)
    mem.save(2u, 0b01000000000000000000000000000000u)
    mem.save(2u, 0b01000000000000000000000000000000u)
    while(reg.ip < MemoryConstants.FLASH_SIZE+MemoryConstants.RAM_SIZE) {

        ctrlUnit.tick()

    }

    println("\nIP outside of memory.")

}