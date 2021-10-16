interface IMemoryController {
    fun load(loc: UInt)
    fun loadIns(loc: UInt): UInt
    fun save(loc: UInt, value: UInt)
    fun saveDiskImage()
    fun clearRam()
    fun loadDiskImage()
}