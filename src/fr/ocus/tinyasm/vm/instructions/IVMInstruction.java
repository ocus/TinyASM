package fr.ocus.tinyasm.vm.instructions;

public interface IVMInstruction {
    int getOpcode();

    int getArgc();

    void exec(IVMCallback callback, int[] memory, int arg1, int arg2, int arg3) throws VMThrowableJump, VMThrowableHalt;
}
