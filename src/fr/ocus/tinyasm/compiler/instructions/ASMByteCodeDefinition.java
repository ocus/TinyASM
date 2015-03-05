package fr.ocus.tinyasm.compiler.instructions;

public class ASMByteCodeDefinition {
    public final String template;
    public final int code;

    /**
     * @param template
     * @param code
     */
    public ASMByteCodeDefinition(final String template, final int code) {
        super();

        this.template = template;
        this.code = code;
    }

    @Override
    public String toString() {
        return "ByteCodeDefinition [template=" + template + ", code=" + String.format("0x%02X", code) + "]";
    }
}
