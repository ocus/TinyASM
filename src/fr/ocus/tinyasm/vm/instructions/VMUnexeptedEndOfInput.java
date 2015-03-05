package fr.ocus.tinyasm.vm.instructions;

public class VMUnexeptedEndOfInput extends RuntimeException {

    public VMUnexeptedEndOfInput(final String message) {
        super(message);
    }

    private static final long serialVersionUID = -5086826511038191827L;

}
