import java.io.BufferedReader
import java.util.*

object MemoryConstants{
    val RAM_SIZE = 262144u
    val FLASH_SIZE = 262144u
}

class MemoryController {
    private val ram = mutableMapOf<UInt, UInt>()
    private val flash = mutableMapOf<UInt, UInt>()

    private val inScanner = Scanner(System.`in`)

    var lastRead = 0u
    private set

    fun load(loc: UInt){
        if(loc > MemoryConstants.FLASH_SIZE+MemoryConstants.RAM_SIZE){
            //address is in IO
            val ioAddr = loc-(MemoryConstants.RAM_SIZE + MemoryConstants.FLASH_SIZE)

            when(ioAddr){
                0xffffu -> lastRead = if(inScanner.hasNextInt()) inScanner.nextInt().toUInt() else 0u
            }
        } else if(loc > MemoryConstants.FLASH_SIZE){
            //address is in ram
            val ramAddr = loc-MemoryConstants.FLASH_SIZE

            lastRead = ram[ramAddr] ?: 0u
        } else{
            //address is in flash
            lastRead = flash[loc] ?: 0u
        }
    }

    fun loadIns(loc: UInt): UInt{
        if(loc > MemoryConstants.FLASH_SIZE+MemoryConstants.RAM_SIZE){
            //address is in IO
            val ioAddr = loc-(MemoryConstants.RAM_SIZE + MemoryConstants.FLASH_SIZE)

            when(ioAddr){
                0xffffu -> return if(inScanner.hasNextInt()) inScanner.nextInt().toUInt() else 0u
            }
        } else if(loc > MemoryConstants.FLASH_SIZE){
            //address is in ram
            val ramAddr = loc-MemoryConstants.FLASH_SIZE

            return ram[ramAddr] ?: 0u
        } else{
            //address is in flash
            return flash[loc] ?: 0u
        }

        return 0u
    }

    fun save(loc: UInt, value: UInt){
        if(loc > MemoryConstants.FLASH_SIZE+MemoryConstants.RAM_SIZE){
            //address is in IO
            val ioAddr = loc-(MemoryConstants.RAM_SIZE + MemoryConstants.FLASH_SIZE)

            when(ioAddr){
                0xfffeu -> print(value as Char)
            }

        }else if(loc > MemoryConstants.FLASH_SIZE){
            //address is in ram
            val ramAddr = loc-MemoryConstants.FLASH_SIZE

            ram[ramAddr] = value
        }
        else{
            //address is in flash

            flash[loc] = value

        }
    }

    fun saveDiskImage(){
        // TODO save flash to file on system
    }

    fun clearRam(){
        ram.clear()
    }

    fun loadDiskImage(){
        // TODO load flash from file on system
    }
}