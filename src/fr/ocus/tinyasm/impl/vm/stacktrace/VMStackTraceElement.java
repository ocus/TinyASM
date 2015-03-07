package fr.ocus.tinyasm.impl.vm.stacktrace;

import fr.ocus.tinyasm.IInstruction;
import fr.ocus.tinyasm.vm.stacktrace.IVMStackTraceElement;

public class VMStackTraceElement implements IVMStackTraceElement {
    final int address;
    final IInstruction instruction;

    public VMStackTraceElement(final int address, final IInstruction instruction) {
        super();
        this.address = address;
        this.instruction = instruction;
    }

    @Override
    public final String toString() {
        return String.format("[0x%1$02X] 0x%2$02X (%3$s)", address, instruction.getOpcode(), instruction.getMnemonic());
    }
}
