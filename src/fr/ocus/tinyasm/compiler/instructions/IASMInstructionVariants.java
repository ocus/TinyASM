package fr.ocus.tinyasm.compiler.instructions;

import fr.ocus.tinyasm.IInstruction;

public interface IASMInstructionVariants {
    IInstruction lookupTemplate(final String template);
}
