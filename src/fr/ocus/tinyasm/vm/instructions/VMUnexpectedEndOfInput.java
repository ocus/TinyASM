package fr.ocus.tinyasm.vm.instructions;

public class VMUnexpectedEndOfInput extends RuntimeException {

    public VMUnexpectedEndOfInput(final String message) {
        super(message);
    }

    @SuppressWarnings("UnusedDeclaration")
    private static final long serialVersionUID = -5086826511038191827L;

}
