object StackConstants{
    val STACK_WORDS = 4194304u
}

class Stack {

    private val stack = mutableListOf<UInt>()

    fun getTop(): UInt {
        return if(stack.size.toUInt() > 0u) stack[0]
        else 0u
    }

    fun getSecond(): UInt{
        return if(stack.size.toUInt() > 1u) stack[1]
        else 0u
    }

    fun push(a: UInt){
        if(stack.size.toUInt() < StackConstants.STACK_WORDS) stack.add(0, a)
    }

    fun pop(){
        if(stack.size.toUInt() > 0u) stack.removeAt(0)
    }
}