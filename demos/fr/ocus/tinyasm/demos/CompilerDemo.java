package fr.ocus.tinyasm.demos;

import fr.ocus.tinyasm.impl.compiler.Compiler;
import fr.ocus.tinyasm.impl.compiler.instructions.ASMInstructionNotFoundException;
import fr.ocus.tinyasm.impl.compiler.instructions.ASMWrongArgumentCountException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

@SuppressWarnings({"StringConcatenationMissingWhitespace", "AccessOfSystemProperties"})
class CompilerDemo {
    public static void main(final String[] args) {
        // compileProgram("add.asm", "add.tiny");
        compileProgram("factorial.asm", "factorial.tiny");
        // compileProgram("mult.asm", "mult.tiny");
        // compileProgram("py.asm", "py.tiny");
    }

    private static void compileProgram(final String asmName, final String tinyName) {
        final String baseDir = System.getProperty("user.dir") + File.separator + "programs" + File.separator;

        final String inputFilePath = baseDir + asmName;
        final String outputFilePath = baseDir + tinyName;

        System.out.println("Compiling " + inputFilePath);
        System.out.println("       to " + outputFilePath);

        final String asmContent;
        try {
            asmContent = readFile(inputFilePath, Charset.defaultCharset());
        } catch (final IOException e) {
            e.printStackTrace();
            return;
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(outputFilePath);
            Compiler.get().compile(asmContent, out);
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        } catch (final ASMWrongArgumentCountException e) {
            e.printStackTrace();
        } catch (final ASMInstructionNotFoundException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Success !");
    }

    private static String readFile(final String path, final Charset encoding) throws IOException {
        final byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

}
