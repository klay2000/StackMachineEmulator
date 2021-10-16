import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class InstructionTest {

    private val instruction = Instruction(0xAAAAAAAAu)

    @Test
    fun loadImmIsDecodedCorrectly(){
        assertTrue(instruction.ldimm)
    }

    @Test
    fun immediateIsDecodedCorrectly(){
        assertEquals(0x2AAAAAAAu, instruction.imm)
    }

    @Test
    fun loadAIsDecodedCorrectly(){
        assertFalse(instruction.lda)
    }

    @Test
    fun loadBIsDecodedCorrectly(){
        assertTrue(instruction.ldb)
    }

    @Test
    fun argSelIsDecodedCorrectly(){
        assertEquals(0b01u, instruction.argsel)
    }

    @Test
    fun pushArgIsDecodedCorrectly(){
        assertFalse(instruction.pusharg)
    }

    @Test
    fun  popArgIsDecodedCorrectly(){
        assertTrue(instruction.poparg)
    }

    @Test
    fun pushIPIsDecodedCorrecly(){
        assertFalse(instruction.puship)
    }

    @Test
    fun popIPIsDecodedCorrectly(){
        assertTrue(instruction.popip)
    }

    @Test
    fun fCodeIsDecodedCorrectly(){
        assertEquals(0x5u, instruction.fcode)
    }

    @Test
    fun wrMemIsDecodedCorrectly(){
        assertFalse(instruction.wrmem)
    }

    @Test
    fun rdMemIsDecodedCorrectly(){
        assertTrue(instruction.ldmem)
    }

    @Test
    fun condIsDecodedCorrectly(){
        assertEquals(0b01u, instruction.cond)
    }

}