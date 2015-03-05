package fr.ocus.tinyasm.compiler.instructions;

import java.util.HashMap;
import java.util.Map;

public class ASMInstructions implements ASMLookup<ASMInstruction> {
    /**
     * Note that brackets mean “content of address” while non-brackets mean
     * number-literal
     */
    static private final ASMInstruction[] DEFINITIONS = new ASMInstruction[] {
        // M[a] = M[a] bit-wise and M[b]
        new ASMInstruction("and", new ASMByteCodeDefinition[] {
            new ASMByteCodeDefinition("[a] [a]", 0x00),
            new ASMByteCodeDefinition("[a] a", 0x01) }),
        // M[a] = M[a] bit-wise or M[b]
        new ASMInstruction("or", new ASMByteCodeDefinition[] {
            new ASMByteCodeDefinition("[a] [a]", 0x02),
            new ASMByteCodeDefinition("[a] a", 0x03) }),
        // M[a] = M[a] bit-wise xor M[b]
        new ASMInstruction("xor", new ASMByteCodeDefinition[] {
            new ASMByteCodeDefinition("[a] [a]", 0x04),
            new ASMByteCodeDefinition("[a] a", 0x05) }),
        // M[a] = bit-wise not M[a]
        new ASMInstruction("not", new ASMByteCodeDefinition[] { new ASMByteCodeDefinition("[a]", 0x06) }),
        // M[a] = M[b], or the literal-set M[a] = b
        new ASMInstruction("mov", new ASMByteCodeDefinition[] {
            new ASMByteCodeDefinition("[a] [a]", 0x07),
            new ASMByteCodeDefinition("[a] a", 0x08) }),
        // M[a] = random value (0 to 25; equal probability distribution)
        new ASMInstruction("random", new ASMByteCodeDefinition[] { new ASMByteCodeDefinition("[a]", 0x09) }),
        // M[a] = M[a] + b; no overflow support
        new ASMInstruction("add", new ASMByteCodeDefinition[] {
            new ASMByteCodeDefinition("[a] [a]", 0x0A),
            new ASMByteCodeDefinition("[a] a", 0x0B) }),
        // M[a] = M[a] - b; no underflow support
        new ASMInstruction("sub", new ASMByteCodeDefinition[] {
            new ASMByteCodeDefinition("[a] [a]", 0x0C),
            new ASMByteCodeDefinition("[a] a", 0x0D) }),
        // Start executing instructions at index of value M[a] (So given a is
        // zero, and M[0] is 10, we then execute instruction 10) or the literal
        // a-value
        new ASMInstruction("jmp", new ASMByteCodeDefinition[] {
            new ASMByteCodeDefinition("[a]", 0x0E),
            new ASMByteCodeDefinition("a", 0x0F) }),
        // Start executing instructions at index x if M[a] == 0 (This is a nice
        // short-hand version of )
        new ASMInstruction("jz", new ASMByteCodeDefinition[] {
            new ASMByteCodeDefinition("[a] [a]", 0x10),
            new ASMByteCodeDefinition("[a] a", 0x11),
            new ASMByteCodeDefinition("a [a]", 0x12),
            new ASMByteCodeDefinition("a a", 0x13) }),
        // Jump to x or M[x] if M[a] is equal to M[b] or if M[a] is equal to the
        // literal b.
        new ASMInstruction("jeq", new ASMByteCodeDefinition[] {
            new ASMByteCodeDefinition("[a] [a] [a]", 0x14),
            new ASMByteCodeDefinition("a [a] [a]", 0x15),
            new ASMByteCodeDefinition("[a] [a] a", 0x16),
            new ASMByteCodeDefinition("a [a] a", 0x17) }),
        // Jump to x or M[x] if M[a] is less than M[b] or if M[a] is less than
        // the literal b.
        new ASMInstruction("jls", new ASMByteCodeDefinition[] {
            new ASMByteCodeDefinition("[a] [a] [a]", 0x18),
            new ASMByteCodeDefinition("a [a] [a]", 0x19),
            new ASMByteCodeDefinition("[a] [a] a", 0x1A),
            new ASMByteCodeDefinition("a [a] a", 0x1B) }),
        // Jump to x or M[x] if M[a] is greater than M[b] or if M[a] is greater
        // than the literal b.
        new ASMInstruction("jgt", new ASMByteCodeDefinition[] {
            new ASMByteCodeDefinition("[a] [a] [a]", 0x1C),
            new ASMByteCodeDefinition("a [a] [a]", 0x1D),
            new ASMByteCodeDefinition("[a] [a] a", 0x1E),
            new ASMByteCodeDefinition("a [a] a", 0x1F) }),
        // Halts the program / freeze flow of execution
        new ASMInstruction("halt", new ASMByteCodeDefinition[] { new ASMByteCodeDefinition("", 0xFF) }),
        // Print the contents of M[a] in either ASCII (if using APRINT) or as
        // decimal (if using DPRINT). Memory ref or literals are supported in
        // both instructions.
        new ASMInstruction("aprint", new ASMByteCodeDefinition[] {
            new ASMByteCodeDefinition("a", 0x21),
            new ASMByteCodeDefinition("[a]", 0x20) }),
        new ASMInstruction("dprint", new ASMByteCodeDefinition[] {
            new ASMByteCodeDefinition("a", 0x23),
            new ASMByteCodeDefinition("[a]", 0x22) }) };

    static private ASMInstructions sInstructions;

    static public synchronized ASMInstructions get() {
        if (sInstructions == null) {
            sInstructions = new ASMInstructions(DEFINITIONS);
        }
        return sInstructions;
    }

    private final Map<String, ASMInstruction> mInstructions = new HashMap<String, ASMInstruction>();

    private ASMInstructions(final ASMInstruction[] instructions) {
        super();

        for (final ASMInstruction instruction : instructions) {
            mInstructions.put(instruction.mnemonic, instruction);
        }
    }

    @Override
    public ASMInstruction lookup(final String key) {
        return mInstructions.get(key);
    }
}
