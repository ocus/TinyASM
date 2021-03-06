package fr.ocus.tinyasm.impl;

import fr.ocus.tinyasm.IInstruction;
import fr.ocus.tinyasm.IInstructionsManager;
import fr.ocus.tinyasm.compiler.instructions.IASMInstructionVariants;
import fr.ocus.tinyasm.impl.vm.instructions.VMThrowableHalt;
import fr.ocus.tinyasm.impl.vm.instructions.VMThrowableJump;
import fr.ocus.tinyasm.vm.instructions.IVMInstructionCallback;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

@SuppressWarnings("MagicNumber")
public final class InstructionsManager implements IInstructionsManager {
    private static final String ASM_MNEMONIC_AND = "AND";
    private static final String ASM_MNEMONIC_OR = "OR";
    private static final String ASM_MNEMONIC_XOR = "XOR";
    private static final String ASM_MNEMONIC_NOT = "NOT";
    private static final String ASM_MNEMONIC_MOV = "MOV";
    private static final String ASM_MNEMONIC_RANDOM = "RANDOM";
    private static final String ASM_MNEMONIC_ADD = "ADD";
    private static final String ASM_MNEMONIC_SUB = "SUB";
    private static final String ASM_MNEMONIC_JMP = "JMP";
    private static final String ASM_MNEMONIC_JZ = "JZ";
    private static final String ASM_MNEMONIC_JEQ = "JEQ";
    private static final String ASM_MNEMONIC_JLS = "JLS";
    private static final String ASM_MNEMONIC_JGT = "JGT";
    private static final String ASM_MNEMONIC_HALT = "HALT";
    private static final String ASM_MNEMONIC_APRINT = "APRINT";
    private static final String ASM_MNEMONIC_DPRINT = "DPRINT";

    private static final String ASM_TEMPLATE_NONE = "";
    private static final String ASM_TEMPLATE_MEM = "[a]";
    private static final String ASM_TEMPLATE_VAL = "a";
    private static final String ASM_TEMPLATE_MEM_MEM = "[a] [a]";
    private static final String ASM_TEMPLATE_VAL_VAL = "a a";
    private static final String ASM_TEMPLATE_MEM_VAL = "[a] a";
    private static final String ASM_TEMPLATE_VAL_MEM = "a [a]";
    private static final String ASM_TEMPLATE_MEM_MEM_MEM = "[a] [a] [a]";
    private static final String ASM_TEMPLATE_VAL_MEM_MEM = "a [a] [a]";
    private static final String ASM_TEMPLATE_MEM_MEM_VAL = "[a] [a] a";
    private static final String ASM_TEMPLATE_VAL_MEM_VAL = "a [a] a";

    private static final int RANDOM_MAX = 26;

    // M[a] = M[a] bit-wise and M[b]
    @SuppressWarnings("WeakerAccess")
    public static final IInstruction INSTRUCTION_AND_0x00 = new Instruction(0x00, 2, ASM_MNEMONIC_AND, ASM_TEMPLATE_MEM_MEM) {
        @Override
        public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
            callback.setMemory(arg1, memory[arg1] & memory[arg2]);
        }
    };

    // M[a] = M[a] bit-wise and b
    @SuppressWarnings("WeakerAccess")
    public static final IInstruction INSTRUCTION_AND_0x01 = new Instruction(0x01, 2, ASM_MNEMONIC_AND, ASM_TEMPLATE_MEM_VAL) {
        @Override
        public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
            callback.setMemory(arg1, memory[arg1] & arg2);
        }
    };

    // M[a] = M[a] bit-wise or M[b]
    @SuppressWarnings("WeakerAccess")
    public static final IInstruction INSTRUCTION_OR_0x02 = new Instruction(0x02, 2, ASM_MNEMONIC_OR, ASM_TEMPLATE_MEM_MEM) {
        @Override
        public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
            callback.setMemory(arg1, memory[arg1] | memory[arg2]);
        }
    };

    // M[a] = M[a] bit-wise or b
    @SuppressWarnings("WeakerAccess")
    public static final IInstruction INSTRUCTION_OR_0x03 = new Instruction(0x03, 2, ASM_MNEMONIC_OR, ASM_TEMPLATE_MEM_VAL) {
        @Override
        public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
            callback.setMemory(arg1, memory[arg1] | arg2);
        }
    };

    // M[a] = M[a] bit-wise xor M[b]
    @SuppressWarnings("WeakerAccess")
    public static final IInstruction INSTRUCTION_XOR_0x04 = new Instruction(0x04, 2, ASM_MNEMONIC_XOR, ASM_TEMPLATE_MEM_MEM) {
        @Override
        public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
            callback.setMemory(arg1, memory[arg1] ^ memory[arg2]);
        }
    };

    // M[a] = M[a] bit-wise xor b
    @SuppressWarnings("WeakerAccess")
    public static final IInstruction INSTRUCTION_XOR_0x05 = new Instruction(0x05, 2, ASM_MNEMONIC_XOR, ASM_TEMPLATE_MEM_VAL) {
        @Override
        public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
            callback.setMemory(arg1, memory[arg1] ^ arg2);
        }
    };

    // M[a] = bit-wise not M[a]
    @SuppressWarnings("WeakerAccess")
    public static final IInstruction INSTRUCTION_NOT_0x06 = new Instruction(0x06, 1, ASM_MNEMONIC_NOT, ASM_TEMPLATE_MEM) {
        @Override
        public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
            callback.setMemory(arg1, ~memory[arg1]);
        }
    };

    // M[a] = M[b]
    @SuppressWarnings("WeakerAccess")
    public static final IInstruction INSTRUCTION_MOV_0x07 = new Instruction(0x07, 2, ASM_MNEMONIC_MOV, ASM_TEMPLATE_MEM_MEM) {
        @Override
        public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
            callback.setMemory(arg1, memory[arg2]);
        }
    };

    // M[a] = b
    @SuppressWarnings("WeakerAccess")
    public static final IInstruction INSTRUCTION_MOV_0x08 = new Instruction(0x08, 2, ASM_MNEMONIC_MOV, ASM_TEMPLATE_MEM_VAL) {
        @Override
        public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
            callback.setMemory(arg1, arg2);
        }
    };

    // M[a] = random value (0 to 25; equal probability distribution)
    @SuppressWarnings("WeakerAccess")
    public static final IInstruction INSTRUCTION_RANDOM_0x09 = new Instruction(0x09, 1, ASM_MNEMONIC_RANDOM, ASM_TEMPLATE_MEM) {
        @Override
        public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
            final Random rand = new Random();
            callback.setMemory(arg1, rand.nextInt(RANDOM_MAX));
        }
    };

    // M[a] = M[a] + M[b]; no overflow support
    @SuppressWarnings("WeakerAccess")
    public static final IInstruction INSTRUCTION_ADD_0x0A = new Instruction(0x0A, 2, ASM_MNEMONIC_ADD, ASM_TEMPLATE_MEM_MEM) {
        @Override
        public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
            callback.setMemory(arg1, memory[arg1] + memory[arg2]);
        }
    };

    // M[a] = M[a] + b; no overflow support
    @SuppressWarnings("WeakerAccess")
    public static final IInstruction INSTRUCTION_ADD_0x0B = new Instruction(0x0B, 2, ASM_MNEMONIC_ADD, ASM_TEMPLATE_MEM_VAL) {
        @Override
        public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
            callback.setMemory(arg1, memory[arg1] + arg2);
        }
    };

    // M[a] = M[a] - M[b]; no overflow support
    @SuppressWarnings("WeakerAccess")
    public static final IInstruction INSTRUCTION_SUB_0x0C = new Instruction(0x0C, 2, ASM_MNEMONIC_SUB, ASM_TEMPLATE_MEM_MEM) {
        @Override
        public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
            callback.setMemory(arg1, memory[arg1] - memory[arg2]);
        }
    };

    // M[a] = M[a] - b; no overflow support
    @SuppressWarnings("WeakerAccess")
    public static final IInstruction INSTRUCTION_SUB_0x0D = new Instruction(0x0D, 2, ASM_MNEMONIC_SUB, ASM_TEMPLATE_MEM_VAL) {
        @Override
        public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
            callback.setMemory(arg1, memory[arg1] - arg2);
        }
    };

    // Start executing instructions at index of value M[a] (So given a is
    // zero, and M[0] is 10, we then execute instruction 10)
    @SuppressWarnings("WeakerAccess")
    public static final IInstruction INSTRUCTION_JMP_0x0E = new Instruction(0x0E, 1, ASM_MNEMONIC_JMP, ASM_TEMPLATE_MEM) {
        @Override
        public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
            callback.jump(memory[arg1]);
        }
    };

    // Start executing instructions at index of value "a" (So given a is
    // zero, and "a" is 10, we then execute instruction 10)
    @SuppressWarnings("WeakerAccess")
    public static final IInstruction INSTRUCTION_JMP_0x0F = new Instruction(0x0F, 1, ASM_MNEMONIC_JMP, ASM_TEMPLATE_VAL) {
        @Override
        public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
            callback.jump(arg1);
        }
    };

    // Start executing instructions at index M[x] if M[a] == 0
    @SuppressWarnings("WeakerAccess")
    public static final IInstruction INSTRUCTION_JZ_0x10 = new Instruction(0x10, 2, ASM_MNEMONIC_JZ, ASM_TEMPLATE_MEM_MEM) {
        @Override
        public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
            if (memory[arg2] == 0) {
                callback.jump(memory[arg1]);
            } else {
                callback.noOp();
            }
        }
    };

    // Start executing instructions at index M[x] if a == 0
    @SuppressWarnings("WeakerAccess")
    public static final IInstruction INSTRUCTION_JZ_0x11 = new Instruction(0x11, 2, ASM_MNEMONIC_JZ, ASM_TEMPLATE_MEM_VAL) {
        @Override
        public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
            if (arg2 == 0) {
                callback.jump(memory[arg1]);
            } else {
                callback.noOp();
            }
        }
    };

    // Start executing instructions at index x if M[a] == 0
    @SuppressWarnings("WeakerAccess")
    public static final IInstruction INSTRUCTION_JZ_0x12 = new Instruction(0x12, 2, ASM_MNEMONIC_JZ, ASM_TEMPLATE_VAL_MEM) {
        @Override
        public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
            if (memory[arg2] == 0) {
                callback.jump(arg1);
            } else {
                callback.noOp();
            }
        }
    };

    // Start executing instructions at index x if a == 0
    @SuppressWarnings("WeakerAccess")
    public static final IInstruction INSTRUCTION_JZ_0x13 = new Instruction(0x13, 2, ASM_MNEMONIC_JZ, ASM_TEMPLATE_VAL_VAL) {
        @Override
        public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
            if (arg2 == 0) {
                callback.jump(arg1);
            } else {
                callback.noOp();
            }
        }
    };

    // Jump to M[x] if M[a] is equal to M[b]
    @SuppressWarnings("WeakerAccess")
    public static final IInstruction INSTRUCTION_JEQ_0x14 = new Instruction(0x14, 3, ASM_MNEMONIC_JEQ, ASM_TEMPLATE_MEM_MEM_MEM) {
        @Override
        public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
            if (memory[arg2] == memory[arg3]) {
                callback.jump(memory[arg1]);
            } else {
                callback.noOp();
            }
        }
    };

    // Jump to x if M[a] is equal to M[b]
    @SuppressWarnings("WeakerAccess")
    public static final IInstruction INSTRUCTION_JEQ_0x15 = new Instruction(0x15, 3, ASM_MNEMONIC_JEQ, ASM_TEMPLATE_VAL_MEM_MEM) {
        @Override
        public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
            if (memory[arg2] == memory[arg3]) {
                callback.jump(arg1);
            } else {
                callback.noOp();
            }
        }
    };

    // Jump to M[x] if M[a] is equal to b
    @SuppressWarnings("WeakerAccess")
    public static final IInstruction INSTRUCTION_JEQ_0x16 = new Instruction(0x16, 3, ASM_MNEMONIC_JEQ, ASM_TEMPLATE_MEM_MEM_VAL) {
        @Override
        public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
            if (memory[arg2] == arg3) {
                callback.jump(memory[arg1]);
            } else {
                callback.noOp();
            }
        }
    };

    // Jump to x if M[a] is equal to b
    @SuppressWarnings("WeakerAccess")
    public static final IInstruction INSTRUCTION_JEQ_0x17 = new Instruction(0x17, 3, ASM_MNEMONIC_JEQ, ASM_TEMPLATE_VAL_MEM_VAL) {
        @Override
        public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
            if (memory[arg2] == arg3) {
                callback.jump(arg1);
            } else {
                callback.noOp();
            }
        }
    };

    // Jump to M[x] if M[a] is less than M[b]
    @SuppressWarnings("WeakerAccess")
    public static final IInstruction INSTRUCTION_JLS_0x18 = new Instruction(0x18, 3, ASM_MNEMONIC_JLS, ASM_TEMPLATE_MEM_MEM_MEM) {
        @Override
        public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
            if (memory[arg2] < memory[arg3]) {
                callback.jump(memory[arg1]);
            } else {
                callback.noOp();
            }
        }
    };

    // Jump to x if M[a] is less than M[b]
    @SuppressWarnings("WeakerAccess")
    public static final IInstruction INSTRUCTION_JLS_0x19 = new Instruction(0x19, 3, ASM_MNEMONIC_JLS, ASM_TEMPLATE_VAL_MEM_MEM) {
        @Override
        public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
            if (memory[arg2] < memory[arg3]) {
                callback.jump(arg1);
            } else {
                callback.noOp();
            }
        }
    };

    // Jump to M[x] if M[a] is less than b
    @SuppressWarnings("WeakerAccess")
    public static final IInstruction INSTRUCTION_JLS_0x1A = new Instruction(0x1A, 3, ASM_MNEMONIC_JLS, ASM_TEMPLATE_MEM_MEM_VAL) {
        @Override
        public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
            if (memory[arg2] < arg3) {
                callback.jump(memory[arg1]);
            } else {
                callback.noOp();
            }
        }
    };

    // Jump to x if M[a] is less than b
    @SuppressWarnings("WeakerAccess")
    public static final IInstruction INSTRUCTION_JLS_0x1B = new Instruction(0x1B, 3, ASM_MNEMONIC_JLS, ASM_TEMPLATE_VAL_MEM_VAL) {
        @Override
        public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
            if (memory[arg2] < arg3) {
                callback.jump(arg1);
            } else {
                callback.noOp();
            }
        }
    };

    // Jump to M[x] if M[a] is more than M[b]
    @SuppressWarnings("WeakerAccess")
    public static final IInstruction INSTRUCTION_JGT_0x1C = new Instruction(0x1C, 3, ASM_MNEMONIC_JGT, ASM_TEMPLATE_MEM_MEM_MEM) {
        @Override
        public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
            if (memory[arg2] > memory[arg3]) {
                callback.jump(memory[arg1]);
            } else {
                callback.noOp();
            }
        }
    };

    // Jump to x if M[a] is more than M[b]
    @SuppressWarnings("WeakerAccess")
    public static final IInstruction INSTRUCTION_JGT_0x1D = new Instruction(0x1D, 3, ASM_MNEMONIC_JGT, ASM_TEMPLATE_VAL_MEM_MEM) {
        @Override
        public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
            if (memory[arg2] > memory[arg3]) {
                callback.jump(arg1);
            } else {
                callback.noOp();
            }
        }
    };

    // Jump to M[x] if M[a] is more than b
    @SuppressWarnings("WeakerAccess")
    public static final IInstruction INSTRUCTION_JGT_0x1E = new Instruction(0x1E, 3, ASM_MNEMONIC_JGT, ASM_TEMPLATE_MEM_MEM_VAL) {
        @Override
        public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
            if (memory[arg2] > arg3) {
                callback.jump(memory[arg1]);
            } else {
                callback.noOp();
            }
        }
    };

    // Jump to x if M[a] is more than b
    @SuppressWarnings("WeakerAccess")
    public static final IInstruction INSTRUCTION_JGT_0x1F = new Instruction(0x1F, 3, ASM_MNEMONIC_JGT, ASM_TEMPLATE_VAL_MEM_VAL) {
        @Override
        public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
            if (memory[arg2] > arg3) {
                callback.jump(arg1);
            } else {
                callback.noOp();
            }
        }
    };

    // Halts the program / freeze flow of execution
    @SuppressWarnings("WeakerAccess")
    public static final IInstruction INSTRUCTION_HALT_0xFF = new Instruction(0xFF, 0, ASM_MNEMONIC_HALT, ASM_TEMPLATE_NONE) {
        @Override
        public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableHalt {
            callback.halt();
        }
    };

    // Print the contents of M[a] in ASCII
    @SuppressWarnings("WeakerAccess")
    public static final IInstruction INSTRUCTION_APRINT_0x20 = new Instruction(0x20, 1, ASM_MNEMONIC_APRINT, ASM_TEMPLATE_MEM) {
        @Override
        public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
            callback.printAscii(memory[arg1]);
        }
    };

    // Print the contents of a in ASCII
    @SuppressWarnings("WeakerAccess")
    public static final IInstruction INSTRUCTION_APRINT_0x21 = new Instruction(0x21, 1, ASM_MNEMONIC_APRINT, ASM_TEMPLATE_VAL) {
        @Override
        public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
            callback.printAscii(arg1);
        }
    };

    // Print the contents of M[a] in decimal
    @SuppressWarnings("WeakerAccess")
    public static final IInstruction INSTRUCTION_DPRINT_0x22 = new Instruction(0x22, 1, ASM_MNEMONIC_DPRINT, ASM_TEMPLATE_MEM) {
        @Override
        public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
            callback.printDecimal(memory[arg1]);
        }
    };

    // Print the contents of a in decimal
    @SuppressWarnings("WeakerAccess")
    public static final IInstruction INSTRUCTION_DPRINT_0x23 = new Instruction(0x23, 1, ASM_MNEMONIC_DPRINT, ASM_TEMPLATE_VAL) {
        @Override
        public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
            callback.printDecimal(arg1);
        }
    };

    private static final IInstruction[] INSTRUCTIONS = {
            INSTRUCTION_AND_0x00,
            INSTRUCTION_AND_0x01,
            INSTRUCTION_OR_0x02,
            INSTRUCTION_OR_0x03,
            INSTRUCTION_XOR_0x04,
            INSTRUCTION_XOR_0x05,
            INSTRUCTION_NOT_0x06,
            INSTRUCTION_MOV_0x07,
            INSTRUCTION_MOV_0x08,
            INSTRUCTION_RANDOM_0x09,
            INSTRUCTION_ADD_0x0A,
            INSTRUCTION_ADD_0x0B,
            INSTRUCTION_SUB_0x0C,
            INSTRUCTION_SUB_0x0D,
            INSTRUCTION_JMP_0x0E,
            INSTRUCTION_JMP_0x0F,
            INSTRUCTION_JZ_0x10,
            INSTRUCTION_JZ_0x11,
            INSTRUCTION_JZ_0x12,
            INSTRUCTION_JZ_0x13,
            INSTRUCTION_JEQ_0x14,
            INSTRUCTION_JEQ_0x15,
            INSTRUCTION_JEQ_0x16,
            INSTRUCTION_JEQ_0x17,
            INSTRUCTION_JLS_0x18,
            INSTRUCTION_JLS_0x19,
            INSTRUCTION_JLS_0x1A,
            INSTRUCTION_JLS_0x1B,
            INSTRUCTION_JGT_0x1C,
            INSTRUCTION_JGT_0x1D,
            INSTRUCTION_JGT_0x1E,
            INSTRUCTION_JGT_0x1F,
            INSTRUCTION_HALT_0xFF,
            INSTRUCTION_APRINT_0x20,
            INSTRUCTION_APRINT_0x21,
            INSTRUCTION_DPRINT_0x22,
            INSTRUCTION_DPRINT_0x23
    };

    private static IInstructionsManager sInstructionsManager = null;

    public static synchronized IInstructionsManager get() {
        if (sInstructionsManager == null) {
            sInstructionsManager = new InstructionsManager(INSTRUCTIONS);
        }
        return sInstructionsManager;
    }

    private final Map<Integer, IInstruction> mVMInstructions = new TreeMap<Integer, IInstruction>();
    private final Map<String, ASMInstructionVariants> mASMInstructions = new TreeMap<String, ASMInstructionVariants>(String.CASE_INSENSITIVE_ORDER);

    private InstructionsManager(final IInstruction... instructions) {
        super();

        for (final IInstruction instruction : instructions) {
            if (mVMInstructions.containsKey(instruction.getOpcode())) {
                throw new RuntimeException(String.format("Adding multiple instructions for \"0x%02X\"", instruction.getOpcode()));
            }
            mVMInstructions.put(instruction.getOpcode(), instruction);

            if (!mASMInstructions.containsKey(instruction.getMnemonic())) {
                mASMInstructions.put(instruction.getMnemonic(), new ASMInstructionVariants());
            }
            mASMInstructions.get(instruction.getMnemonic()).put(instruction);
        }
    }

    @Override
    public IInstruction lookupOpcode(final int opcode) {
        return mVMInstructions.get(opcode);
    }

    @Override
    public IASMInstructionVariants lookupMnemonic(final String mnemonic) {
        return mASMInstructions.get(mnemonic);
    }

    public static final class ASMInstructionVariants implements IASMInstructionVariants {
        final Map<String, IInstruction> mInstructions = new TreeMap<String, IInstruction>(String.CASE_INSENSITIVE_ORDER);

        void put(final IInstruction instruction) {
            mInstructions.put(instruction.getTemplate(), instruction);
        }

        @Override
        public IInstruction lookupTemplate(final String template) {
            return mInstructions.get(template);
        }

        @Override
        public String toString() {
            return "ASMInstructionVariants{}";
        }
    }

    @Override
    public String toString() {
        return "InstructionsManager{}";
    }
}
