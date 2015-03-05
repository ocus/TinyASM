package fr.ocus.tinyasm;

import fr.ocus.tinyasm.compiler.instructions.IASMByteCodeDefinition;
import fr.ocus.tinyasm.vm.instructions.IVMInstructionCallback;
import fr.ocus.tinyasm.vm.instructions.IVMInstruction;
import fr.ocus.tinyasm.vm.instructions.VMThrowableHalt;
import fr.ocus.tinyasm.vm.instructions.VMThrowableJump;

abstract public class Instruction implements IVMInstruction, IASMByteCodeDefinition {
    private final int opcode;
    private final int argc;
    private final String asmMnemonic;
    private final String asmTemplate;

    Instruction(final int opcode, final int argc, final String asmMnemonic, final String asmTemplate) {
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
    abstract public void exec(IVMInstructionCallback callback, int[] memory, int arg1, int arg2, int arg3) throws VMThrowableJump, VMThrowableHalt;
}
