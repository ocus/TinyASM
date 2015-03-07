package fr.ocus.tinyasm.impl.vm.instructions;

public class VMInstructionPointer {
    public int address = 0;

    @Override
    public String toString() {
        return String.format("VMInstructionPointer{address=0x%02X}", address);
    }
}
