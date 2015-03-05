package fr.ocus.tinyasm;

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
        compileExecute("and_0x00", new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(10 & 3, value);
            }
        });
    }

    @Test
    public void and_0x01() {
        compileExecute("and_0x01", new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(6 & 13, value);
            }
        });
    }

    @Test
    public void or_0x02() {
        compileExecute("or_0x02", new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(10 | 3, value);
            }
        });
    }

    @Test
    public void or_0x03() {
        compileExecute("or_0x03", new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(6 | 13, value);
            }
        });
    }

    @Test
    public void xor_0x04() {
        compileExecute("xor_0x04", new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(6 ^ 23, value);
            }
        });
    }

    @Test
    public void xor_0x05() {
        compileExecute("xor_0x05", new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(7 ^ 15, value);
            }
        });
    }

    @Test
    public void not_0x06() {
        compileExecute("not_0x06", new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(~8, value);
            }
        });
    }

    @Test
    public void mov_0x07() {
        compileExecute("mov_0x07", new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(15, value);
            }
        });
    }

    @Test
    public void mov_0x08() {
        compileExecute("mov_0x08", new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(29, value);
            }
        });
    }

    @Test
    public void random_0x09() {
        compileExecute("random_0x09", new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertTrue(value >= 0 && value <= 25);
            }
        });
        compileExecute("random_0x09", new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertTrue(value >= 0 && value <= 25);
            }
        });
        compileExecute("random_0x09", new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertTrue(value >= 0 && value <= 25);
            }
        });
    }

    @Test
    public void add_0x0A() {
        compileExecute("add_0x0A", new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(10 + 9, value);
            }
        });
    }

    @Test
    public void add_0x0B() {
        compileExecute("add_0x0B", new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(23 + 14, value);
            }
        });
    }

    @Test
    public void sub_0x0C() {
        compileExecute("sub_0x0C", new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(16 - 7, value);
            }
        });
    }

    @Test
    public void sub_0x0D() {
        compileExecute("sub_0x0D", new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(9 - 7, value);
            }
        });
    }

    @Test
    public void jmp_0x0E() {
        compileExecute("jmp_0x0E", new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(10, value);
            }
        });
    }

    @Test
    public void jmp_0x0F() {
        compileExecute("jmp_0x0F", new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(19, value);
            }
        });
    }

    @Test
    public void jz_0x10() {
        compileExecute("jz_0x10", new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(88, value);
            }
        });
    }

    @Test
    public void jz_0x11() {
        compileExecute("jz_0x11", new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(77, value);
            }
        });
    }

    @Test
    public void jz_0x12() {
        compileExecute("jz_0x12", new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(44, value);
            }
        });
    }

    @Test
    public void jz_0x13() {
        compileExecute("jz_0x13", new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(22, value);
            }
        });
    }

    @Test
    public void jeq_0x14() {
        compileExecute("jeq_0x14", new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(200, value);
            }
        });
    }

    @Test
    public void jeq_0x15() {
        compileExecute("jeq_0x15", new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(10 + 9, value);
            }
        });
    }

    @Test
    public void jeq_0x16() {
        compileExecute("jeq_0x16", new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(10 + 9, value);
            }
        });
    }

    @Test
    public void jeq_0x17() {
        compileExecute("jeq_0x17", new Screen() {
            @Override
            public void printDecimal(final int value) {
                Assert.assertEquals(10 + 9, value);
            }
        });
    }

    @Test
    public void jls_0x18() {
        Assert.fail("Not implemented yet.");
    }

    @Test
    public void jls_0x19() {
        Assert.fail("Not implemented yet.");
    }

    @Test
    public void jls_0x1A() {
        Assert.fail("Not implemented yet.");
    }

    @Test
    public void jls_0x1B() {
        Assert.fail("Not implemented yet.");
    }

    @Test
    public void jgt_0x1C() {
        Assert.fail("Not implemented yet.");
    }

    @Test
    public void jgt_0x1D() {
        Assert.fail("Not implemented yet.");
    }

    @Test
    public void jgt_0x1E() {
        Assert.fail("Not implemented yet.");
    }

    @Test
    public void jgt_0x1F() {
        Assert.fail("Not implemented yet.");
    }

    @Test
    public void halt_0xFF() {
        Assert.fail("Not implemented yet.");
    }

    @Test
    public void aprint_0x21() {
        Assert.fail("Not implemented yet.");
    }

    @Test
    public void aprint_0x20() {
        Assert.fail("Not implemented yet.");
    }

    @Test
    public void dprint_0x23() {
        Assert.fail("Not implemented yet.");
    }

    @Test
    public void dprint_0x22() {
        Assert.fail("Not implemented yet.");
    }

    private abstract class Screen implements IScreen {
        @Override
        final public void printAscii(final int value) {
            // nothing
        }
    }

    private void compileExecute(final String name, final Screen screen) {
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
            final VM vm = new VM(screen);
            vm.run(Compiler.get().compile(asmContent));
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
