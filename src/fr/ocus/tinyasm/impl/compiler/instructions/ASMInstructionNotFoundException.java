package fr.ocus.tinyasm.impl.compiler.instructions;

public class ASMInstructionNotFoundException extends RuntimeException {

    public ASMInstructionNotFoundException(final String message) {
        super(message);
    }

    public ASMInstructionNotFoundException(final String message, Throwable exception) {
        super(message, exception);
    }

    @SuppressWarnings("UnusedDeclaration")
    private static final long serialVersionUID = -1230852139490886635L;

}
