package fr.ocus.tinyasm.vm.instructions;

import fr.ocus.tinyasm.impl.vm.instructions.VMThrowableHalt;
import fr.ocus.tinyasm.impl.vm.instructions.VMThrowableJump;

public interface IVMInstructionCallback {
    void noOp();

    void jump(int address) throws VMThrowableJump;

    void setMemory(int address, int value);

    void halt() throws VMThrowableHalt;

    void printAscii(int value);

    void printDecimal(int value);
}
