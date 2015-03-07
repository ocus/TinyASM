package fr.ocus.tinyasm.compiler.instructions;

public class ASMWrongArgumentCountException extends IllegalArgumentException {

    public ASMWrongArgumentCountException(final String message) {
        super(message);
    }

    @SuppressWarnings("UnusedDeclaration")
    private static final long serialVersionUID = 9174655124407793885L;
}
