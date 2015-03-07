package fr.ocus.tinyasm.compiler;

import fr.ocus.tinyasm.impl.compiler.instructions.ASMInstructionNotFoundException;
import fr.ocus.tinyasm.impl.compiler.instructions.ASMWrongArgumentCountException;

import java.io.IOException;
import java.io.OutputStream;

public interface ICompiler {
    int[] compile(final String content) throws ASMInstructionNotFoundException, ASMWrongArgumentCountException;

    int[] compile(final String... lines) throws ASMInstructionNotFoundException, ASMWrongArgumentCountException;

    int[] compileLine(final String line) throws ASMInstructionNotFoundException, ASMWrongArgumentCountException;

    void compile(final String content, final OutputStream out) throws ASMInstructionNotFoundException, ASMWrongArgumentCountException, IOException;

    String compileDump(final String content);
}
