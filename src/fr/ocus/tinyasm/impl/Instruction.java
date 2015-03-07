package fr.ocus.tinyasm.impl;

import fr.ocus.tinyasm.IInstruction;
import fr.ocus.tinyasm.impl.vm.instructions.VMThrowableHalt;
import fr.ocus.tinyasm.impl.vm.instructions.VMThrowableJump;
import fr.ocus.tinyasm.vm.instructions.IVMInstructionCallback;

public abstract class Instruction implements IInstruction {
    private final int opcode;
    private final int argc;
    private final String asmMnemonic;
    private final String asmTemplate;

    Instruction(final int opcode, final int argc, final String asmMnemonic, final String asmTemplate) {
        super();
        this.opcode = opcode;
        this.argc = argc;
        this.asmMnemonic = asmMnemonic;
        this.asmTemplate = asmTemplate;
    }

    @Override
    public final int getOpcode() {
        return opcode;
    }

    @Override
    public final int getArgc() {
        return argc;
    }

    @Override
    public final String getMnemonic() {
        return asmMnemonic;
    }

    @Override
    public final String getTemplate() {
        return asmTemplate;
    }

    @Override
    public abstract void exec(IVMInstructionCallback callback, int[] memory, int arg1, int arg2, int arg3) throws VMThrowableJump, VMThrowableHalt;

    @Override
    public String toString() {
        return "Instruction{" +
                "opcode=" + opcode +
                ", argc=" + argc +
                ", asmMnemonic='" + asmMnemonic + '\'' +
                ", asmTemplate='" + asmTemplate + '\'' +
                '}';
    }
}
