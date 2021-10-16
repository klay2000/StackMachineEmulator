import org.junit.Test
import kotlin.test.assertEquals

internal class RegistersTest {

    val registers = Registers()

    @Test
    public fun aCanBeStoredAndRecalled() {
        registers.a = 5u
        registers.a = 10u
        assertEquals(10u, registers.a)
    }

    @Test
    public fun bCanBeStoredAndRecalled() {
        registers.b = 7u
        registers.b = 23u
        assertEquals(23u, registers.b)
    }

    @Test
    public fun ipCanBeStoredAndRecalled() {
        registers.ip = 12u
        registers.ip = 234u
        assertEquals(234u, registers.ip)
    }

}