package fr.ocus.tinyasm.impl.vm.instructions;

public class VMUnexpectedEndOfInputException extends RuntimeException {

    public VMUnexpectedEndOfInputException(final String message) {
        super(message);
    }

    @SuppressWarnings("UnusedDeclaration")
    private static final long serialVersionUID = -5086826511038191827L;

}
