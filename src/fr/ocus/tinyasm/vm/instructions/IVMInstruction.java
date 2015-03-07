package fr.ocus.tinyasm.vm.instructions;

import fr.ocus.tinyasm.impl.vm.instructions.VMThrowableHalt;
import fr.ocus.tinyasm.impl.vm.instructions.VMThrowableJump;

public interface IVMInstruction {
    int getOpcode();

    int getArgc();

    void exec(IVMInstructionCallback callback, int[] memory, int arg1, int arg2, int arg3) throws VMThrowableJump, VMThrowableHalt;
}
