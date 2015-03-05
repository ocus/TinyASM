package fr.ocus.tinyasm.vm.instructions;

public interface IVMCallback {
    void noOp();

    void jump(int address) throws VMThrowableJump;

    void setMemory(int address, int value);

    void halt() throws VMThrowableHalt;

    void printAscii(int value);

    void printDecimal(int value);
}
