package fr.ocus.tinyasm.compiler.instructions;

import java.util.HashMap;

public class ASMInstruction implements ASMLookup<ASMByteCodeDefinition> {
    final String mnemonic;

    private final HashMap<String, ASMByteCodeDefinition> mByteCodeDefinitions = new HashMap<String, ASMByteCodeDefinition>();

    /**
     * @param name
     * @param byteCodeDefinitions
     */
    public ASMInstruction(final String name, final ASMByteCodeDefinition[] byteCodeDefinitions) {
        super();

        this.mnemonic = name;
        for (final ASMByteCodeDefinition byteCodeDefinition : byteCodeDefinitions) {
            mByteCodeDefinitions.put(byteCodeDefinition.template, byteCodeDefinition);
        }
    }

    @Override
    public ASMByteCodeDefinition lookup(final String key) {
        return mByteCodeDefinitions.get(key);
    }

    @Override
    public String toString() {
        return "Instruction [mnemonic=" + mnemonic + ", ByteCodeDefinitions=" + mByteCodeDefinitions + "]";
    }

}
