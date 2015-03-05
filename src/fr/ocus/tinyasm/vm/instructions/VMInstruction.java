package fr.ocus.tinyasm.vm.instructions;

abstract public class VMInstruction implements IVMInstruction {
    private final int opcode;
    private final int argc;

    VMInstruction(final int opcode, final int argc) {
        this.opcode = opcode;
        this.argc = argc;
    }

    @Override
    public final int getOpcode() {
        return this.opcode;
    }

    @Override
    public int getArgc() {
        return this.argc;
    }

    @Override
    abstract public void exec(IVMCallback callback, int[] memory, int arg1, int arg2, int arg3) throws VMThrowableJump, VMThrowableHalt;
}
