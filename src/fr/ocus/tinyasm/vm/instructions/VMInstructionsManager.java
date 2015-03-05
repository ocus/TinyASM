package fr.ocus.tinyasm.vm.instructions;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class VMInstructionsManager {
    static private final VMInstruction[] INSTRUCTIONS = new VMInstruction[] {

        // M[a] = M[a] bit-wise and M[b]
        new VMInstruction(0x00, 2) {
            @Override
            public void exec(final IVMCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
                callback.setMemory(arg1, memory[arg1] & memory[arg2]);
            }
        },

        // M[a] = M[a] bit-wise and b
        new VMInstruction(0x01, 2) {
            @Override
            public void exec(final IVMCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
                callback.setMemory(arg1, memory[arg1] & arg2);
            }
        },

        // M[a] = M[a] bit-wise or M[b]
        new VMInstruction(0x02, 2) {
            @Override
            public void exec(final IVMCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
                callback.setMemory(arg1, memory[arg1] | memory[arg2]);
            }
        },

        // M[a] = M[a] bit-wise or b
        new VMInstruction(0x03, 2) {
            @Override
            public void exec(final IVMCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
                callback.setMemory(arg1, memory[arg1] | arg2);
            }
        },

        // M[a] = M[a] bit-wise xor M[b]
        new VMInstruction(0x04, 2) {
            @Override
            public void exec(final IVMCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
                callback.setMemory(arg1, memory[arg1] ^ memory[arg2]);
            }
        },

        // M[a] = M[a] bit-wise xor b
        new VMInstruction(0x05, 2) {
            @Override
            public void exec(final IVMCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
                callback.setMemory(arg1, memory[arg1] ^ arg2);
            }
        },

        // M[a] = bit-wise not M[a]
        new VMInstruction(0x06, 1) {
            @Override
            public void exec(final IVMCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
                callback.setMemory(arg1, ~memory[arg1]);
            }
        },

        // M[a] = M[b]
        new VMInstruction(0x07, 2) {
            @Override
            public void exec(final IVMCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
                callback.setMemory(arg1, memory[arg2]);
            }
        },

        // M[a] = b
        new VMInstruction(0x08, 2) {
            @Override
            public void exec(final IVMCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
                callback.setMemory(arg1, arg2);
            }
        },

        // M[a] = random value (0 to 25; equal probability distribution)
        new VMInstruction(0x09, 1) {
            @Override
            public void exec(final IVMCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
                final Random rand = new Random();
                callback.setMemory(arg1, rand.nextInt(26));
            }
        },

        // M[a] = M[a] + M[b]; no overflow support
        new VMInstruction(0x0A, 2) {
            @Override
            public void exec(final IVMCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
                callback.setMemory(arg1, memory[arg1] + memory[arg2]);
            }
        },

        // M[a] = M[a] + b; no overflow support
        new VMInstruction(0x0B, 2) {
            @Override
            public void exec(final IVMCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
                callback.setMemory(arg1, memory[arg1] + arg2);
            }
        },

        // M[a] = M[a] - M[b]; no overflow support
        new VMInstruction(0x0C, 2) {
            @Override
            public void exec(final IVMCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
                callback.setMemory(arg1, memory[arg1] - memory[arg2]);
            }
        },

        // M[a] = M[a] - b; no overflow support
        new VMInstruction(0x0D, 2) {
            @Override
            public void exec(final IVMCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
                callback.setMemory(arg1, memory[arg1] - arg2);
            }
        },

        // Start executing instructions at index of value M[a] (So given a is
        // zero, and M[0] is 10, we then execute instruction 10)
        new VMInstruction(0x0E, 1) {
            @Override
            public void exec(final IVMCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
                callback.jump(memory[arg1]);
            }
        },

        // Start executing instructions at index of value "a" (So given a is
        // zero, and "a" is 10, we then execute instruction 10)
        new VMInstruction(0x0F, 1) {
            @Override
            public void exec(final IVMCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
                callback.jump(arg1);
            }
        },

        // Start executing instructions at index M[x] if M[a] == 0
        new VMInstruction(0x10, 2) {
            @Override
            public void exec(final IVMCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
                if (memory[arg2] == 0) {
                    callback.jump(memory[arg1]);
                } else {
                    callback.noOp();
                }
            }
        },

        // Start executing instructions at index M[x] if a == 0
        new VMInstruction(0x11, 2) {
            @Override
            public void exec(final IVMCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
                if (arg2 == 0) {
                    callback.jump(memory[arg1]);
                } else {
                    callback.noOp();
                }
            }
        },

        // Start executing instructions at index x if M[a] == 0
        new VMInstruction(0x12, 2) {
            @Override
            public void exec(final IVMCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
                if (memory[arg2] == 0) {
                    callback.jump(arg1);
                } else {
                    callback.noOp();
                }
            }
        },

        // Start executing instructions at index x if a == 0
        new VMInstruction(0x13, 2) {
            @Override
            public void exec(final IVMCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
                if (arg2 == 0) {
                    callback.jump(arg1);
                } else {
                    callback.noOp();
                }
            }
        },

        // Jump to M[x] if M[a] is equal to M[b]
        new VMInstruction(0x14, 3) {
            @Override
            public void exec(final IVMCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
                if (memory[arg2] == memory[arg3]) {
                    callback.jump(memory[arg1]);
                } else {
                    callback.noOp();
                }
            }
        },

        // Jump to x if M[a] is equal to M[b]
        new VMInstruction(0x15, 3) {
            @Override
            public void exec(final IVMCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
                if (memory[arg2] == memory[arg3]) {
                    callback.jump(arg1);
                } else {
                    callback.noOp();
                }
            }
        },

        // Jump to M[x] if M[a] is equal to b
        new VMInstruction(0x16, 3) {
            @Override
            public void exec(final IVMCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
                if (memory[arg2] == arg3) {
                    callback.jump(memory[arg1]);
                } else {
                    callback.noOp();
                }
            }
        },

        // Jump to x if M[a] is equal to b
        new VMInstruction(0x17, 3) {
            @Override
            public void exec(final IVMCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
                if (memory[arg2] == arg3) {
                    callback.jump(arg1);
                } else {
                    callback.noOp();
                }
            }
        },

        // Jump to M[x] if M[a] is less than M[b]
        new VMInstruction(0x18, 3) {
            @Override
            public void exec(final IVMCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
                if (memory[arg2] < memory[arg3]) {
                    callback.jump(memory[arg1]);
                } else {
                    callback.noOp();
                }
            }
        },

        // Jump to x if M[a] is less than M[b]
        new VMInstruction(0x19, 3) {
            @Override
            public void exec(final IVMCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
                if (memory[arg2] < memory[arg3]) {
                    callback.jump(arg1);
                } else {
                    callback.noOp();
                }
            }
        },

        // Jump to M[x] if M[a] is less than b
        new VMInstruction(0x1A, 3) {
            @Override
            public void exec(final IVMCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
                if (memory[arg2] < arg3) {
                    callback.jump(memory[arg1]);
                } else {
                    callback.noOp();
                }
            }
        },

        // Jump to x if M[a] is less than b
        new VMInstruction(0x1B, 3) {
            @Override
            public void exec(final IVMCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
                if (memory[arg2] < arg3) {
                    callback.jump(arg1);
                } else {
                    callback.noOp();
                }
            }
        },

        // Jump to M[x] if M[a] is more than M[b]
        new VMInstruction(0x1C, 3) {
            @Override
            public void exec(final IVMCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
                if (memory[arg2] > memory[arg3]) {
                    callback.jump(memory[arg1]);
                } else {
                    callback.noOp();
                }
            }
        },

        // Jump to x if M[a] is more than M[b]
        new VMInstruction(0x1D, 3) {
            @Override
            public void exec(final IVMCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
                if (memory[arg2] > memory[arg3]) {
                    callback.jump(arg1);
                } else {
                    callback.noOp();
                }
            }
        },

        // Jump to M[x] if M[a] is more than b
        new VMInstruction(0x1E, 3) {
            @Override
            public void exec(final IVMCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
                if (memory[arg2] > arg3) {
                    callback.jump(memory[arg1]);
                } else {
                    callback.noOp();
                }
            }
        },

        // Jump to x if M[a] is more than b
        new VMInstruction(0x1F, 3) {
            @Override
            public void exec(final IVMCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableJump {
                if (memory[arg2] > arg3) {
                    callback.jump(arg1);
                } else {
                    callback.noOp();
                }
            }
        },

        // Halts the program / freeze flow of execution
        new VMInstruction(0xFF, 0) {
            @Override
            public void exec(final IVMCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) throws VMThrowableHalt {
                callback.halt();
            }
        },

        // Print the contents of M[a] in ASCII
        new VMInstruction(0x20, 1) {
            @Override
            public void exec(final IVMCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
                callback.printAscii(memory[arg1]);
            }
        },

        // Print the contents of a in ASCII
        new VMInstruction(0x21, 1) {
            @Override
            public void exec(final IVMCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
                callback.printAscii(arg1);
            }
        },

        // Print the contents of M[a] in decimal
        new VMInstruction(0x22, 1) {
            @Override
            public void exec(final IVMCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
                callback.printDecimal(memory[arg1]);
            }
        },

        // Print the contents of a in decimal
        new VMInstruction(0x23, 1) {
            @Override
            public void exec(final IVMCallback callback, final int[] memory, final int arg1, final int arg2, final int arg3) {
                callback.printDecimal(arg1);
            }
        },

    };

    private static VMInstructionsManager sVMInstructionsManager;

    static public synchronized VMInstructionsManager get() {
        if (sVMInstructionsManager == null) {
            sVMInstructionsManager = new VMInstructionsManager(INSTRUCTIONS);
        }
        return sVMInstructionsManager;
    }

    private final Map<Integer, VMInstruction> mInstructions = new HashMap<Integer, VMInstruction>();

    private VMInstructionsManager(final VMInstruction[] instructions) {
        super();

        for (final VMInstruction instruction : instructions) {
            if (mInstructions.containsKey(instruction.getOpcode())) {
                throw new RuntimeException(String.format("Adding multiple instructions for \"0x%02X\"", instruction.getOpcode()));
            }
            mInstructions.put(instruction.getOpcode(), instruction);
        }
    }

    public VMInstruction lookup(final int opcode) {
        return mInstructions.get(opcode);
    }
}
