import junit.framework.Assert
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.assertEquals

internal class MemoryControllerTest{

    var mem = MemoryController()

    @BeforeEach
    fun initializeMem() {
        mem = MemoryController()
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 124,
                         7, 12,
                         332445, 3776,
                         262144, -2354])
    fun savedValuesCanBeLoaded(location : Int, value : Int) {
        mem.save(location.toUInt(), value.toUInt())
        mem.load(location.toUInt())

        assertEquals(value, mem.lastRead.toInt())
    }

    @Test
    fun allValuesAreInitiallyZero() {
        var zero = true
        for(i in 0 .. (MemoryConstants.FLASH_SIZE+MemoryConstants.RAM_SIZE).toInt()) {
            mem.load(i.toUInt())
            zero = zero && mem.lastRead == 0u
        }

        assertTrue(zero)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 12,
                         5, -132,
                         1, 14,
                         262144, 5486,
                         272635, -1374])
    fun savedInstructionsCanBeLoaded(location: Int, value: Int) {
        mem.save(location.toUInt(), value.toUInt())

        assertEquals(value, mem.loadIns(location.toUInt()).toInt())
    }

    @Test
    fun clearRamClearsRam(){
        mem.save(MemoryConstants.FLASH_SIZE, 9674u)
        mem.clearRam()

        mem.load(MemoryConstants.FLASH_SIZE)

        assertEquals(0u, mem.lastRead)
    }

    @Test
    fun clearRamDoesNotClearFlash() {
        mem.save(0u, 9674u)
        mem.clearRam()

        mem.load(0u)

        assertEquals(9674u, mem.lastRead)
    }

}