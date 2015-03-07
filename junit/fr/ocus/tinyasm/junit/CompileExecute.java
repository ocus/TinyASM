package fr.ocus.tinyasm.junit;

import fr.ocus.tinyasm.IScreen;
import fr.ocus.tinyasm.compiler.Compiler;
import fr.ocus.tinyasm.compiler.instructions.ASMInstructionNotFoundException;
import fr.ocus.tinyasm.compiler.instructions.ASMWrongArgumentCountException;
import fr.ocus.tinyasm.vm.VM;
import org.junit.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CompileExecute {

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

    @Test
    public void and_0x00() {
        compileExecute("and_0x00", new ScreenWrapper(new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(10 & 3, value);
            }
        }));
    }

    @Test
    public void and_0x01() {
        compileExecute("and_0x01", new ScreenWrapper(new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(6 & 13, value);
            }
        }));
    }

    @Test
    public void or_0x02() {
        compileExecute("or_0x02", new ScreenWrapper(new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(10 | 3, value);
            }
        }));
    }

    @Test
    public void or_0x03() {
        compileExecute("or_0x03", new ScreenWrapper(new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(6 | 13, value);
            }
        }));
    }

    @Test
    public void xor_0x04() {
        compileExecute("xor_0x04", new ScreenWrapper(new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(6 ^ 23, value);
            }
        }));
    }

    @Test
    public void xor_0x05() {
        compileExecute("xor_0x05", new ScreenWrapper(new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(7 ^ 15, value);
            }
        }));
    }

    @Test
    public void not_0x06() {
        compileExecute("not_0x06", new ScreenWrapper(new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(~7, value);
            }
        }));
    }

    @Test
    public void mov_0x07() {
        compileExecute("mov_0x07", new ScreenWrapper(new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(29, value);
            }
        }));
    }

    @Test
    public void mov_0x08() {
        compileExecute("mov_0x08", new ScreenWrapper(new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(15, value);
            }
        }));
    }

    @Test
    public void random_0x09() {
        for (int i = 0; i <= 25; i++) {
            compileExecute("random_0x09", new ScreenWrapper(new Screen() {
                @Override
                public void printDecimal(final int value) {
                    Assert.assertTrue(value >= 0 && value <= 25);
                }
            }));
        }
    }

    @Test
    public void add_0x0A() {
        compileExecute("add_0x0A", new ScreenWrapper(new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(10 + 9, value);
            }
        }));
    }

    @Test
    public void add_0x0B() {
        compileExecute("add_0x0B", new ScreenWrapper(new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(23 + 14, value);
            }
        }));
    }

    @Test
    public void sub_0x0C() {
        compileExecute("sub_0x0C", new ScreenWrapper(new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(16 - 7, value);
            }
        }));
    }

    @Test
    public void sub_0x0D() {
        compileExecute("sub_0x0D", new ScreenWrapper(new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(9 - 7, value);
            }
        }));
    }

    @Test
    public void jmp_0x0E() {
        compileExecute("jmp_0x0E", new ScreenWrapper(new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(10, value);
            }
        }));
    }

    @Test
    public void jmp_0x0F() {
        compileExecute("jmp_0x0F", new ScreenWrapper(new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(19, value);
            }
        }));
    }

    @Test
    public void jz_0x10() {
        compileExecute("jz_0x10", new ScreenWrapper(new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(88, value);
            }
        }));
    }

    @Test
    public void jz_0x11() {
        compileExecute("jz_0x11", new ScreenWrapper(new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(77, value);
            }
        }));
    }

    @Test
    public void jz_0x12() {
        compileExecute("jz_0x12", new ScreenWrapper(new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(44, value);
            }
        }));
    }

    @Test
    public void jz_0x13() {
        compileExecute("jz_0x13", new ScreenWrapper(new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(22, value);
            }
        }));
    }

    @Test
    public void jeq_0x14() {
        compileExecute("jeq_0x14", new ScreenWrapper(new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(200, value);
            }
        }));
    }

    @Test
    public void jeq_0x15() {
        compileExecute("jeq_0x15", new ScreenWrapper(new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(200, value);
            }
        }));
    }

    @Test
    public void jeq_0x16() {
        compileExecute("jeq_0x16", new ScreenWrapper(new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(123, value);
            }
        }));
    }

    @Test
    public void jeq_0x17() {
        compileExecute("jeq_0x17", new ScreenWrapper(new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(752, value);
            }
        }));
    }

    @Test
    public void jls_0x18() {
        compileExecute("jls_0x18", new ScreenWrapper(new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(454, value);
            }
        }));
    }

    @Test
    public void jls_0x19() {
        compileExecute("jls_0x19", new ScreenWrapper(new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(978, value);
            }
        }));
    }

    @Test
    public void jls_0x1A() {
        compileExecute("jls_0x1A", new ScreenWrapper(new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(699, value);
            }
        }));
    }

    @Test
    public void jls_0x1B() {
        compileExecute("jls_0x1B", new ScreenWrapper(new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(945, value);
            }
        }));
    }

    @Test
    public void jgt_0x1C() {
        compileExecute("jgt_0x1C", new ScreenWrapper(new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(869, value);
            }
        }));
    }

    @Test
    public void jgt_0x1D() {
        compileExecute("jgt_0x1D", new ScreenWrapper(new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(456, value);
            }
        }));
    }

    @Test
    public void jgt_0x1E() {
        compileExecute("jgt_0x1E", new ScreenWrapper(new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(852, value);
            }
        }));
    }

    @Test
    public void jgt_0x1F() {
        compileExecute("jgt_0x1F", new ScreenWrapper(new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(951, value);
            }
        }));
    }

    @Test
    public void halt_0xFF() {
        compileExecute("halt_0xFF", new ScreenWrapper(false, new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.fail("Nothing should have been printed!");
            }

            @Override
            public void printAscii(final int value) {
                Assert.fail("Nothing should have been printed!");
            }
        }));
    }

    @Test
    public void aprint_0x20() {
        compileExecute("aprint_0x20", new ScreenWrapper(new Screen() {
            @Override
            public void printAscii(final int value) {
                Assert.assertEquals(896, value);
            }
        }));
    }

    @Test
    public void aprint_0x21() {
        compileExecute("aprint_0x21", new ScreenWrapper(new Screen() {
            @Override
            public void printAscii(final int value) {
                Assert.assertEquals(523, value);
            }
        }));
    }

    @Test
    public void dprint_0x22() {
        compileExecute("dprint_0x22", new ScreenWrapper(new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(214, value);
            }
        }));
    }

    @Test
    public void dprint_0x23() {
        compileExecute("dprint_0x23", new ScreenWrapper(new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(236, value);
            }
        }));
    }

    static private class Screen implements IScreen {

        @Override
        public void printAscii(int value) {
            Assert.fail("Nothing should have print!");
        }

        @Override
        public void printDecimal(int value) {
            Assert.fail("Nothing should have print!");
        }
    }

    static private interface ITestScreen extends IScreen {
        boolean shouldHavePrint();

        boolean hasPrint();
    }

    static private final class ScreenWrapper implements ITestScreen {
        private final Screen screen;
        private final boolean shouldHavePrint;

        private boolean hasPrint = false;

        public ScreenWrapper(final Screen screen) {
            this(true, screen);
        }

        public ScreenWrapper(final boolean shouldHavePrint, final Screen screen) {
            this.shouldHavePrint = shouldHavePrint;
            this.screen = screen;
        }

        @Override
        public void printAscii(int value) {
            screen.printAscii(value);
            hasPrint = true;
        }

        @Override
        public void printDecimal(int value) {
            screen.printDecimal(value);
            hasPrint = true;
        }

        @Override
        public boolean shouldHavePrint() {
            return shouldHavePrint;
        }

        @Override
        public boolean hasPrint() {
            return hasPrint;
        }
    }


    private void compileExecute(final String name, final ITestScreen screenWrapper) {
        final String baseDir = System.getProperty("user.dir") + "\\programs\\unit\\";

        final String inputFilePath = baseDir + name + ".asm";

        String asmContent;
        try {
            asmContent = readFile(inputFilePath, Charset.defaultCharset());
        } catch (final IOException e) {
            e.printStackTrace();
            Assert.fail(getStackTrace(e));
            return;
        }
        final FileOutputStream out = null;
        try {
            final VM vm = new VM(screenWrapper);
            vm.run(Compiler.get().compile(asmContent));
            if (screenWrapper.shouldHavePrint() && !screenWrapper.hasPrint()) {
                Assert.fail("Nothing was printed!");
            }
            if (!screenWrapper.shouldHavePrint() && screenWrapper.hasPrint()) {
                Assert.fail("Nothing should have been printed!");
            }
        } catch (final ASMWrongArgumentCountException e) {
            e.printStackTrace();
            Assert.fail(getStackTrace(e));
        } catch (final ASMInstructionNotFoundException e) {
            e.printStackTrace();
            Assert.fail(getStackTrace(e));
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }

    static private String readFile(final String path, final Charset encoding) throws IOException {
        final byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    static private String getStackTrace(final Exception e) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}
