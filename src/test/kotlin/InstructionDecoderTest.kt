import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class InstructionDecoderTest {

    private val instruction = Instruction(0xAAAAAAAAu)

    @Test
    fun LoadImmIsDecodedCorrectly(){
        assertTrue(instruction.ldimm)
    }

    @Test
    fun ImmediateIsdecodedCorrectly(){
        assertEquals(0x2AAAAAAAu, instruction.imm)
    }

    @Test
    fun LoadAIsDecodedCorrectly(){
        assertFalse(instruction.lda)
    }

    @Test
    fun LoadBIsDecodedCorrectly(){
        assertTrue(instruction.ldb)
    }

    @Test
    fun ArgSelIsDecodedCorrectly(){
        assertEquals(0b01u, instruction.argsel)
    }

    @Test
    fun PushArgIsDecodedCorrectly(){
        assertFalse(instruction.pusharg)
    }

    @Test
    fun  PopArgIsDecodedCorrectly(){
        assertTrue(instruction.poparg)
    }

    @Test
    fun PushIPIsDecodedCorrecly(){
        assertFalse(instruction.puship)
    }

    @Test
    fun PopIPIsDecodedCorrectly(){
        assertTrue(instruction.popip)
    }

    @Test
    fun FCodeIsDecodedCorrectly(){
        assertEquals(0x5u, instruction.fcode)
    }

    @Test
    fun WrMemIsDecodedCorrectly(){
        assertFalse(instruction.wrmem)
    }

    @Test
    fun RdMemIsDecodedCorrectly(){
        assertTrue(instruction.ldmem)
    }

    @Test
    fun CondIsDecodedCorrectly(){
        assertEquals(0b01u, instruction.cond)
    }

}