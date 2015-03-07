package fr.ocus.tinyasm.impl.vm.instructions;

public class VMInstructionNotFoundException extends RuntimeException {

    public VMInstructionNotFoundException(final String message) {
        super(message);
    }

    @SuppressWarnings("UnusedDeclaration")
    private static final long serialVersionUID = -2186980759610057783L;

}
