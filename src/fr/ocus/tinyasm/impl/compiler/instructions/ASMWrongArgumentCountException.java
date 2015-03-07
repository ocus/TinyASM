package fr.ocus.tinyasm.impl.compiler.instructions;

public class ASMWrongArgumentCountException extends IllegalArgumentException {

    public ASMWrongArgumentCountException(final String message) {
        super(message);
    }

    public ASMWrongArgumentCountException(final String message, Throwable exception) {
        super(message, exception);
    }

    @SuppressWarnings("UnusedDeclaration")
    private static final long serialVersionUID = 9174655124407793885L;
}
