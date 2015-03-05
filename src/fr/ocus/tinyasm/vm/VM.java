package fr.ocus.tinyasm.vm;

import fr.ocus.tinyasm.IScreen;
import fr.ocus.tinyasm.vm.instructions.VMCallback;
import fr.ocus.tinyasm.vm.instructions.VMInstruction;
import fr.ocus.tinyasm.vm.instructions.VMInstructionNotFoundException;
import fr.ocus.tinyasm.vm.instructions.VMInstructionPointer;
import fr.ocus.tinyasm.vm.instructions.VMInstructionsManager;
import fr.ocus.tinyasm.vm.instructions.VMThrowableHalt;
import fr.ocus.tinyasm.vm.instructions.VMThrowableJump;
import fr.ocus.tinyasm.vm.instructions.VMUnexeptedEndOfInput;

public class VM {
    static private final int NO_ARG = -1;
    static private final int MEMORY_SIZE = 256;

    private final int[] mMemory = new int[MEMORY_SIZE];
    private final IScreen mScreen;
    private final boolean mDebug;

    public VM(final IScreen screen, final boolean debug) {
        super();

        mScreen = screen;
        mDebug = debug;
    }

    public VM(final IScreen screen) {
        this(screen, false);
    }

    public VM(final boolean debug) {
        this(new IScreen() {
            @Override
            public void printDecimal(final int value) {
                System.out.print(value);
            }

            @Override
            public void printAscii(final int value) {
                System.out.print(Character.toString((char) value));
            }
        }, debug);
    }

    public VM() {
        this(false);
    }

    public void run(final int[] source) {
        final VMInstructionsManager manager = VMInstructionsManager.get();

        final VMInstructionPointer ip = new VMInstructionPointer();
        ip.address = 0;

        final VMCallback callback = new VMCallback() {

            @Override
            public void noOp() {
            }

            @Override
            public void setMemory(final int address, final int value) {
                printDebug(String.format("Mem 0x%02X: 0x%02X", address, value));
                mMemory[address] = value;
            }

            @Override
            public void jump(final int address) throws VMThrowableJump {
                ip.address = address;
                throw new VMThrowableJump();
            }

            @Override
            public void halt() throws VMThrowableHalt {
                throw new VMThrowableHalt();
            }

            @Override
            public void printAscii(final int value) {
                printDebug("Printing in ASCII: " + value);
                mScreen.printAscii(value);
            }

            @Override
            public void printDecimal(final int value) {
                printDebug("Printing in decimal: " + value);
                mScreen.printDecimal(value);
            }
        };

        do {
            final int opcode = source[ip.address];
            final VMInstruction instruction = manager.lookup(opcode);
            if (instruction == null) {
                throw new VMInstructionNotFoundException("Instruction \"" + toHex(opcode) + "\" not found");
            }
            if ((ip.address + instruction.getArgc()) >= source.length) {
                throw new VMUnexeptedEndOfInput("Unexpected end of input for \"" + toHex(opcode) + "\"");
            }
            try {
                switch (instruction.getArgc()) {
                default:
                    throw new RuntimeException();
                case 0:
                    printDebug(String.format("Executing instruction: 0x%02X", instruction.getOpcode()));
                    instruction.exec(callback, mMemory, NO_ARG, NO_ARG, NO_ARG);
                    break;
                case 1:
                    printDebug(String.format("Executing instruction: 0x%02X 0x%02X", instruction.getOpcode(), source[ip.address + 1]));
                    instruction.exec(callback, mMemory, source[ip.address + 1], NO_ARG, NO_ARG);
                    break;
                case 2:
                    printDebug(String.format("Executing instruction: 0x%02X 0x%02X 0x%02X", instruction.getOpcode(), source[ip.address + 1],
                            source[ip.address + 2]));
                    instruction.exec(callback, mMemory, source[ip.address + 1], source[ip.address + 2], NO_ARG);
                    break;
                case 3:
                    printDebug(String.format("Executing instruction: 0x%02X 0x%02X 0x%02X 0x%02X", instruction.getOpcode(), source[ip.address + 1],
                            source[ip.address + 2], source[ip.address + 3]));
                    instruction.exec(callback, mMemory, source[ip.address + 1], source[ip.address + 2], source[ip.address + 3]);
                    break;
                }
            } catch (final VMThrowableJump e) {
                continue; // address already set in callback.jump()
            } catch (final VMThrowableHalt e) {
                printDebug("HALT");
                break;
            }

            ip.address += 1; // opcode
            ip.address += instruction.getArgc();
            if (ip.address >= source.length) {
                throw new RuntimeException("Reached end of input without HALT !");
            }
        } while (true);

    }

    private void printDebug(final String message) {
        if (mDebug) {
            System.err.println(message);
        }
    }

    static private String toHex(final int v) {
        return String.format("0x%02X", v);
    }

    public void dumpMemory() {
        int index = 0;
        for (final int value : mMemory) {
            System.out.println(String.format("0x%02X: 0x%02X", index, value));
            index++;
        }
    }
}