package fr.ocus.tinyasm;

import fr.ocus.tinyasm.compiler.instructions.IASMInstructionVariants;

public interface IInstructionsManager {
    IInstruction lookupOpcode(final int opcode);

    IASMInstructionVariants lookupMnemonic(final String mnemonic);
}
