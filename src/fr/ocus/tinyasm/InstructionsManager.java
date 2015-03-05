package fr.ocus.tinyasm;

import fr.ocus.tinyasm.vm.instructions.IVMInstructionCallback;
import fr.ocus.tinyasm.vm.instructions.VMThrowableHalt;
import fr.ocus.tinyasm.vm.instructions.VMThrowableJump;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class InstructionsManager {
    static private final String ASM_MNEMONIC_AND = "AND";
    static private final String ASM_MNEMONIC_OR = "OR";
    static private final String ASM_MNEMONIC_XOR = "XOR";
    static private final String ASM_MNEMONIC_NOT = "NOT";
    static private final String ASM_MNEMONIC_MOV = "MOV";
    static private final String ASM_MNEMONIC_RANDOM = "RANDOM";
    static private final String ASM_MNEMONIC_ADD = "ADD";
    static private final String ASM_MNEMONIC_SUB = "SUB";
    static private final String ASM_MNEMONIC_JMP = "JMP";
    static private final String ASM_MNEMONIC_JZ = "JZ";
    static private final String ASM_MNEMONIC_JEQ = "JEQ";
    static private final String ASM_MNEMONIC_JLS = "JLS";
    static private final String ASM_MNEMONIC_JGT = "JGT";
    static private final String ASM_MNEMONIC_HALT = "HALT";
    static private final String ASM_MNEMONIC_APRINT = "APRINT";
    static private final String ASM_MNEMONIC_DPRINT = "DPRINT";

    static private final String ASM_TEMPLATE_NONE = "";
    static private final String ASM_TEMPLATE_MEM = "[a]";
    static private final String ASM_TEMPLATE_VAL = "a";
    static private final String ASM_TEMPLATE_MEM_MEM = "[a] [a]";
    static private final String ASM_TEMPLATE_VAL_VAL = "a a";
    static private final String ASM_TEMPLATE_MEM_VAL = "[a] a";
    static private final String ASM_TEMPLATE_VAL_MEM = "a [a]";
    static private final String ASM_TEMPLATE_MEM_MEM_MEM = "[a] [a] [a]";
    static private final String ASM_TEMPLATE_VAL_MEM_MEM = "a [a] [a]";
    static private final String ASM_TEMPLATE_MEM_MEM_VAL = "[a] [a] a";
    static private final String ASM_TEMPLATE_VAL_MEM_VAL = "a [a] a";

    static private final Instruction[] INSTRUCTIONS = new Instruction[]{

            // M[a] = M[a] bit-wise and M[b]
            new Instruction(0x00, 2, ASM_MNEMONIC_AND, ASM_TEMPLATE_MEM_MEM) {
                @Override
                public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
                    callback.setMemory(arg1, memory[arg1] & memory[arg2]);
                }
            },

            // M[a] = M[a] bit-wise and b
            new Instruction(0x01, 2, ASM_MNEMONIC_AND, ASM_TEMPLATE_MEM_VAL) {
                @Override
                public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
                    callback.setMemory(arg1, memory[arg1] & arg2);
                }
            },

            // M[a] = M[a] bit-wise or M[b]
            new Instruction(0x02, 2, ASM_MNEMONIC_OR, ASM_TEMPLATE_MEM_MEM) {
                @Override
                public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
                    callback.setMemory(arg1, memory[arg1] | memory[arg2]);
                }
            },

            // M[a] = M[a] bit-wise or b
            new Instruction(0x03, 2, ASM_MNEMONIC_OR, ASM_TEMPLATE_MEM_VAL) {
                @Override
                public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
                    callback.setMemory(arg1, memory[arg1] | arg2);
                }
            },

            // M[a] = M[a] bit-wise xor M[b]
            new Instruction(0x04, 2, ASM_MNEMONIC_XOR, ASM_TEMPLATE_MEM_MEM) {
                @Override
                public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
                    callback.setMemory(arg1, memory[arg1] ^ memory[arg2]);
                }
            },

            // M[a] = M[a] bit-wise xor b
            new Instruction(0x05, 2, ASM_MNEMONIC_XOR, ASM_TEMPLATE_MEM_VAL) {
                @Override
                public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
                    callback.setMemory(arg1, memory[arg1] ^ arg2);
                }
            },

            // M[a] = bit-wise not M[a]
            new Instruction(0x06, 1, ASM_MNEMONIC_NOT, ASM_TEMPLATE_MEM) {
                @Override
                public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
                    callback.setMemory(arg1, ~memory[arg1]);
                }
            },

            // M[a] = M[b]
            new Instruction(0x07, 2, ASM_MNEMONIC_MOV, ASM_TEMPLATE_MEM_MEM) {
                @Override
                public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
                    callback.setMemory(arg1, memory[arg2]);
                }
            },

            // M[a] = b
            new Instruction(0x08, 2, ASM_MNEMONIC_MOV, ASM_TEMPLATE_MEM_VAL) {
                @Override
                public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
                    callback.setMemory(arg1, arg2);
                }
            },

            // M[a] = random value (0 to 25; equal probability distribution)
            new Instruction(0x09, 1, ASM_MNEMONIC_RANDOM, ASM_TEMPLATE_MEM) {
                @Override
                public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
                    final Random rand = new Random();
                    callback.setMemory(arg1, rand.nextInt(26));
                }
            },

            // M[a] = M[a] + M[b]; no overflow support
            new Instruction(0x0A, 2, ASM_MNEMONIC_ADD, ASM_TEMPLATE_MEM_MEM) {
                @Override
                public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
                    callback.setMemory(arg1, memory[arg1] + memory[arg2]);
                }
            },

            // M[a] = M[a] + b; no overflow support
            new Instruction(0x0B, 2, ASM_MNEMONIC_ADD, ASM_TEMPLATE_MEM_VAL) {
                @Override
                public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
                    callback.setMemory(arg1, memory[arg1] + arg2);
                }
            },

            // M[a] = M[a] - M[b]; no overflow support
            new Instruction(0x0C, 2, ASM_MNEMONIC_SUB, ASM_TEMPLATE_MEM_MEM) {
                @Override
                public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
                    callback.setMemory(arg1, memory[arg1] - memory[arg2]);
                }
            },

            // M[a] = M[a] - b; no overflow support
            new Instruction(0x0D, 2, ASM_MNEMONIC_SUB, ASM_TEMPLATE_MEM_VAL) {
                @Override
                public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
                    callback.setMemory(arg1, memory[arg1] - arg2);
                }
            },

            // Start executing instructions at index of value M[a] (So given a is
            // zero, and M[0] is 10, we then execute instruction 10)
            new Instruction(0x0E, 1, ASM_MNEMONIC_JMP, ASM_TEMPLATE_MEM) {
                @Override
                public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
                    callback.jump(memory[arg1]);
                }
            },

            // Start executing instructions at index of value "a" (So given a is
            // zero, and "a" is 10, we then execute instruction 10)
            new Instruction(0x0F, 1, ASM_MNEMONIC_JMP, ASM_TEMPLATE_VAL) {
                @Override
                public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
                    callback.jump(arg1);
                }
            },

            // Start executing instructions at index M[x] if M[a] == 0
            new Instruction(0x10, 2, ASM_MNEMONIC_JZ, ASM_TEMPLATE_MEM_MEM) {
                @Override
                public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
                    if (memory[arg2] == 0) {
                        callback.jump(memory[arg1]);
                    } else {
                        callback.noOp();
                    }
                }
            },

            // Start executing instructions at index M[x] if a == 0
            new Instruction(0x11, 2, ASM_MNEMONIC_JZ, ASM_TEMPLATE_MEM_VAL) {
                @Override
                public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
                    if (arg2 == 0) {
                        callback.jump(memory[arg1]);
                    } else {
                        callback.noOp();
                    }
                }
            },

            // Start executing instructions at index x if M[a] == 0
            new Instruction(0x12, 2, ASM_MNEMONIC_JZ, ASM_TEMPLATE_VAL_MEM) {
                @Override
                public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
                    if (memory[arg2] == 0) {
                        callback.jump(arg1);
                    } else {
                        callback.noOp();
                    }
                }
            },

            // Start executing instructions at index x if a == 0
            new Instruction(0x13, 2, ASM_MNEMONIC_JZ, ASM_TEMPLATE_VAL_VAL) {
                @Override
                public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
                    if (arg2 == 0) {
                        callback.jump(arg1);
                    } else {
                        callback.noOp();
                    }
                }
            },

            // Jump to M[x] if M[a] is equal to M[b]
            new Instruction(0x14, 3, ASM_MNEMONIC_JEQ, ASM_TEMPLATE_MEM_MEM_MEM) {
                @Override
                public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
                    if (memory[arg2] == memory[arg3]) {
                        callback.jump(memory[arg1]);
                    } else {
                        callback.noOp();
                    }
                }
            },

            // Jump to x if M[a] is equal to M[b]
            new Instruction(0x15, 3, ASM_MNEMONIC_JEQ, ASM_TEMPLATE_VAL_MEM_MEM) {
                @Override
                public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
                    if (memory[arg2] == memory[arg3]) {
                        callback.jump(arg1);
                    } else {
                        callback.noOp();
                    }
                }
            },

            // Jump to M[x] if M[a] is equal to b
            new Instruction(0x16, 3, ASM_MNEMONIC_JEQ, ASM_TEMPLATE_MEM_MEM_VAL) {
                @Override
                public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
                    if (memory[arg2] == arg3) {
                        callback.jump(memory[arg1]);
                    } else {
                        callback.noOp();
                    }
                }
            },

            // Jump to x if M[a] is equal to b
            new Instruction(0x17, 3, ASM_MNEMONIC_JEQ, ASM_TEMPLATE_VAL_MEM_VAL) {
                @Override
                public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
                    if (memory[arg2] == arg3) {
                        callback.jump(arg1);
                    } else {
                        callback.noOp();
                    }
                }
            },

            // Jump to M[x] if M[a] is less than M[b]
            new Instruction(0x18, 3, ASM_MNEMONIC_JLS, ASM_TEMPLATE_MEM_MEM_MEM) {
                @Override
                public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
                    if (memory[arg2] < memory[arg3]) {
                        callback.jump(memory[arg1]);
                    } else {
                        callback.noOp();
                    }
                }
            },

            // Jump to x if M[a] is less than M[b]
            new Instruction(0x19, 3, ASM_MNEMONIC_JLS, ASM_TEMPLATE_VAL_MEM_MEM) {
                @Override
                public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
                    if (memory[arg2] < memory[arg3]) {
                        callback.jump(arg1);
                    } else {
                        callback.noOp();
                    }
                }
            },

            // Jump to M[x] if M[a] is less than b
            new Instruction(0x1A, 3, ASM_MNEMONIC_JLS, ASM_TEMPLATE_MEM_MEM_VAL) {
                @Override
                public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
                    if (memory[arg2] < arg3) {
                        callback.jump(memory[arg1]);
                    } else {
                        callback.noOp();
                    }
                }
            },

            // Jump to x if M[a] is less than b
            new Instruction(0x1B, 3, ASM_MNEMONIC_JLS, ASM_TEMPLATE_VAL_MEM_VAL) {
                @Override
                public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
                    if (memory[arg2] < arg3) {
                        callback.jump(arg1);
                    } else {
                        callback.noOp();
                    }
                }
            },

            // Jump to M[x] if M[a] is more than M[b]
            new Instruction(0x1C, 3, ASM_MNEMONIC_JGT, ASM_TEMPLATE_MEM_MEM_MEM) {
                @Override
                public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
                    if (memory[arg2] > memory[arg3]) {
                        callback.jump(memory[arg1]);
                    } else {
                        callback.noOp();
                    }
                }
            },

            // Jump to x if M[a] is more than M[b]
            new Instruction(0x1D, 3, ASM_MNEMONIC_JGT, ASM_TEMPLATE_VAL_MEM_MEM) {
                @Override
                public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
                    if (memory[arg2] > memory[arg3]) {
                        callback.jump(arg1);
                    } else {
                        callback.noOp();
                    }
                }
            },

            // Jump to M[x] if M[a] is more than b
            new Instruction(0x1E, 3, ASM_MNEMONIC_JGT, ASM_TEMPLATE_MEM_MEM_VAL) {
                @Override
                public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
                    if (memory[arg2] > arg3) {
                        callback.jump(memory[arg1]);
                    } else {
                        callback.noOp();
                    }
                }
            },

            // Jump to x if M[a] is more than b
            new Instruction(0x1F, 3, ASM_MNEMONIC_JGT, ASM_TEMPLATE_VAL_MEM_VAL) {
                @Override
                public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
                    if (memory[arg2] > arg3) {
                        callback.jump(arg1);
                    } else {
                        callback.noOp();
                    }
                }
            },

            // Halts the program / freeze flow of execution
            new Instruction(0xFF, 0, ASM_MNEMONIC_HALT, ASM_TEMPLATE_NONE) {
                @Override
                public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableHalt {
                    callback.halt();
                }
            },

            // Print the contents of M[a] in ASCII
            new Instruction(0x20, 1, ASM_MNEMONIC_APRINT, ASM_TEMPLATE_MEM) {
                @Override
                public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
                    callback.printAscii(memory[arg1]);
                }
            },

            // Print the contents of a in ASCII
            new Instruction(0x21, 1, ASM_MNEMONIC_APRINT, ASM_TEMPLATE_VAL) {
                @Override
                public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
                    callback.printAscii(arg1);
                }
            },

            // Print the contents of M[a] in decimal
            new Instruction(0x22, 1, ASM_MNEMONIC_DPRINT, ASM_TEMPLATE_MEM) {
                @Override
                public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
                    callback.printDecimal(memory[arg1]);
                }
            },

            // Print the contents of a in decimal
            new Instruction(0x23, 1, ASM_MNEMONIC_DPRINT, ASM_TEMPLATE_VAL) {
                @Override
                public void exec(final IVMInstructionCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
                    callback.printDecimal(arg1);
                }
            },

    };

    private static InstructionsManager sInstructionsManager;

    static public synchronized InstructionsManager get() {
        if (sInstructionsManager == null) {
            sInstructionsManager = new InstructionsManager(INSTRUCTIONS);
        }
        return sInstructionsManager;
    }

    private final Map<Integer, Instruction> mVMInstructions = new TreeMap<Integer, Instruction>();
    private final Map<String, ASMVariants> mASMInstructions = new TreeMap<String, ASMVariants>(String.CASE_INSENSITIVE_ORDER);

    private InstructionsManager(final Instruction[] instructions) {
        super();

        for (final Instruction instruction : instructions) {
            if (mVMInstructions.containsKey(instruction.getOpcode())) {
                throw new RuntimeException(String.format("Adding multiple instructions for \"0x%02X\"", instruction.getOpcode()));
            }
            mVMInstructions.put(instruction.getOpcode(), instruction);

            if (!mASMInstructions.containsKey(instruction.getMnemonic())) {
                mASMInstructions.put(instruction.getMnemonic(), new ASMVariants());
            }
            mASMInstructions.get(instruction.getMnemonic()).put(instruction);
        }
    }

    public Instruction lookupOpcode(final int opcode) {
        return mVMInstructions.get(opcode);
    }

    public ASMVariants lookupMnemonic(final String mnemonic) {
        return mASMInstructions.get(mnemonic);
    }

    static public final class ASMVariants {
        final Map<String, Instruction> mInstructions = new TreeMap<String, Instruction>(String.CASE_INSENSITIVE_ORDER);

        protected void put(final Instruction instruction) {
            mInstructions.put(instruction.getTemplate(), instruction);
        }

        public Instruction lookupTemplate(final String template) {
            return mInstructions.get(template);
        }
    }
}
