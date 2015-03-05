package fr.ocus.tinyasm;

import fr.ocus.tinyasm.compiler.Compiler;
import fr.ocus.tinyasm.compiler.instructions.ASMInstructionNotFoundException;
import fr.ocus.tinyasm.compiler.instructions.ASMWrongArgumentCountException;
import org.junit.*;

public class CompilerTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test(expected = ASMInstructionNotFoundException.class)
    public void compileFailInstructionNotFound() {
        Compiler.get().compile("CDR");
    }

    @Test(expected = ASMWrongArgumentCountException.class)
    public void compileFailWrongArgumentCount() {
        Compiler.get().compile("Mov [3] 0 0");
    }

    @Test
    public void compileProgram() {
        Assert.assertEquals("0x08 0x05 0x00 " + "0x17 0x5A 0x05 0x64 " + "0x21 0x2E " + "0x08 0x08 0x00 " + "0x09 0x06 " + "0x07 0x02 0x06 "
                        + "0x08 0x03 0x02 " + "0x08 0x01 0x19 " + "0x0F 0x7D " + "0x07 0x06 0x00 " + "0x09 0x07 " + "0x07 0x02 0x07 " + "0x08 0x03 0x02 "
                        + "0x08 0x01 0x29 " + "0x0F 0x7D " + "0x07 0x07 0x00 " + "0x07 0x02 0x06 " + "0x07 0x03 0x06 " + "0x08 0x01 0x37 " + "0x0F 0x69 "
                        + "0x07 0x06 0x00 " + "0x07 0x02 0x07 " + "0x07 0x03 0x07 " + "0x08 0x01 0x45 " + "0x0F 0x69 " + "0x07 0x07 0x00 "
                        + "0x0A 0x08 0x06 " + "0x0A 0x08 0x07 " + "0x1F 0x55 0x08 0x90 " + "0x0B 0x09 0x01 " + "0x0B 0x05 0x01 " + "0x0F 0x03 "
                        + "0x21 0x0A " + "0x22 0x09 " + "0x21 0x2F " + "0x23 0x64 " + "0x21 0x2A " + "0x23 0x04 " + "0x0F 0x68 " + "0xFF "
                        + "0x08 0x0A 0x00 " + "0x08 0x00 0x00 " + "0x15 0x7B 0x03 0x0A " + "0x0B 0x0A 0x01 " + "0x0A 0x00 0x02 " + "0x0F 0x6F "
                        + "0x0E 0x01 " + "0x08 0x00 0x00 " + "0x19 0x8C 0x02 0x03 " + "0x0B 0x00 0x01 " + "0x0C 0x02 0x03 " + "0x0F 0x80 " + "0x0E 0x01",
                Compiler.get().compileDump(
                        "MOV [5] 0\n" + "JEQ 90 [5] 100\n" + "APRINT 46\n" + "MOV [8] 0\n" + "RANDOM [6]\n" + "MOV [2] [6]\n" + "MOV [3] 2\n"
                                + "MOV [1] 25\n" + "JMP 125\n" + "MOV [6] [0]\n" + "RANDOM [7]\n" + "MOV [2] [7]\n" + "MOV [3] 2\n" + "MOV [1] 41\n"
                                + "JMP 125\n" + "MOV [7] [0]\n" + "MOV [2] [6]\n" + "MOV [3] [6]\n" + "MOV [1] 55\n" + "JMP 105\n" + "MOV [6] [0]\n"
                                + "MOV [2] [7]\n" + "MOV [3] [7]\n" + "MOV [1] 69\n" + "JMP 105\n" + "MOV [7] [0]\n" + "ADD [8] [6]\n"
                                + "ADD [8] [7]\n" + "JGT 85 [8] 144\n" + "ADD [9] 1\n" + "ADD [5] 1\n" + "JMP 3\n" + "APRINT 10\n" + "DPRINT [9]\n"
                                + "APRINT 47\n" + "DPRINT 100\n" + "APRINT 42\n" + "DPRINT 4\n" + "JMP 104\n" + "HALT\n" + "MOV [10] 0\n"
                                + "MOV [0] 0\n" + "JEQ 123 [3] [10]\n" + "ADD [10] 1\n" + "ADD [0] [2]\n" + "JMP 111\n" + "JMP [1]\n" + "MOV [0] 0\n"
                                + "JLS 140 [2] [3]\n" + "ADD [0] 1\n" + "SUB [2] [3]\n" + "JMP 128\n" + "JMP [1]"));
    }

    @Test
    public void compileLine() {
        Assert.assertArrayEquals(new int[][]{
                new int[]{
                        0x08,
                        0x02,
                        0x00},
                new int[]{
                        0x08,
                        0x03,
                        0x00},
                new int[]{
                        0x15,
                        0x06,
                        0x03,
                        0x01},
                new int[]{
                        0x0B,
                        0x03,
                        0x01},
                new int[]{
                        0x0A,
                        0x02,
                        0x00},
                new int[]{
                        0x0F,
                        0x02},
                new int[]{
                        0x07,
                        0x00,
                        0x02},
                new int[]{0xFF},}, new int[][]{
                Compiler.get().compileLine("Mov [2] 0"),
                Compiler.get().compileLine("Mov [3] 0"),
                Compiler.get().compileLine("Jeq 6 [3] [1]"),
                Compiler.get().compileLine("Add [3] 1"),
                Compiler.get().compileLine("Add [2] [0]"),
                Compiler.get().compileLine("Jmp 2"),
                Compiler.get().compileLine("Mov [0] [2]"),
                Compiler.get().compileLine("Halt"),});
    }

}
