package fr.ocus.tinyasm.vm.stacktrace;

import fr.ocus.tinyasm.Instruction;

public class VMStackTraceElement {
    final int address;
    final Instruction instruction;

    public VMStackTraceElement(int address, Instruction instruction) {
        this.address = address;
        this.instruction = instruction;
    }

    @Override
    public String toString() {
        return String.format("[0x%1$02X] 0x%2$02X (%3$s)", address, instruction.getOpcode(), instruction.getMnemonic());
    }
}
