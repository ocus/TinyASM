package fr.ocus.tinyasm.compiler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.ocus.tinyasm.compiler.instructions.ASMByteCodeDefinition;
import fr.ocus.tinyasm.compiler.instructions.ASMInstruction;
import fr.ocus.tinyasm.compiler.instructions.ASMInstructionNotFoundException;
import fr.ocus.tinyasm.compiler.instructions.ASMInstructions;
import fr.ocus.tinyasm.compiler.instructions.ASMWrongArgumentCountException;

public class Compiler {
    static private Compiler sCompiler;

    static public synchronized Compiler get() {
        if (sCompiler == null) {
            sCompiler = new Compiler();
        }
        return sCompiler;
    }

    static private Pattern sNumberPattern = Pattern.compile("\\d+");

    public int[] compile(final String content) throws ASMInstructionNotFoundException, ASMWrongArgumentCountException {
        return compile(content.split("\\n"));
    }

    public int[] compile(final String[] lines) throws ASMInstructionNotFoundException, ASMWrongArgumentCountException {
        int[] compiled = new int[0];
        int lineNumber = 0;
        for (final String line : lines) {
            try {
                final int[] compiledLine = compileLine(line);
                compiled = arrMerge(compiled, compiledLine);
            } catch (final ASMInstructionNotFoundException e) {
                throw new ASMInstructionNotFoundException(e.getMessage() + " on line " + (lineNumber + 1));
            } catch (final ASMWrongArgumentCountException e) {
                throw new ASMWrongArgumentCountException(e.getMessage() + " on line " + (lineNumber + 1));
            }
            lineNumber++;
        }
        return compiled;
    }

    public int[] compileLine(final String line) throws ASMInstructionNotFoundException, ASMWrongArgumentCountException {
        final String sanitizedLine = sanitize(line);
        final String[] parts = sanitizedLine.split(" ", 2);
        final String mnemonic = parts[0].toLowerCase();
        final String template;
        if (parts.length > 1) {
            template = parts[1].replaceAll("\\d+", "a");
        } else {
            template = "";
        }
        final ASMInstruction instruction = ASMInstructions.get().lookup(mnemonic);
        if (instruction == null) {
            throw new ASMInstructionNotFoundException("Instruction \"" + sanitizedLine + "\" does not exists");
        }
        final ASMByteCodeDefinition byteCodeDefinition = instruction.lookup(template);
        if (byteCodeDefinition == null) {
            throw new ASMWrongArgumentCountException("Instruction \"" + sanitizedLine + "\" does not have the right number of arguments");
        }
        int numbers[] = new int[0];
        if (parts.length > 1) {
            final Matcher matches = sNumberPattern.matcher(parts[1]);
            while (matches.find()) {
                numbers = arrMerge(numbers, new int[] { Integer.valueOf(matches.group()) });
            }
        }

        final int[] compiled = new int[numbers.length + 1];
        compiled[0] = byteCodeDefinition.code;
        System.arraycopy(numbers, 0, compiled, 1, numbers.length);
        return compiled;
    }

    public void compile(final String content, final OutputStream out) throws ASMInstructionNotFoundException, ASMWrongArgumentCountException,
            IOException {
        final int[] compiled = compile(content);
        for (final int i : compiled) {
            out.write(i);
        }
    }

    public String compileDump(final String content) {
        final int[] compiled = compile(content);
        final String[] dump = new String[compiled.length];
        int index = 0;
        for (final int c : compiled) {
            dump[index] = String.format("0x%02X", c);
            index++;
        }
        return strJoin(dump, " ");
    }

    static private final String sPatternLine = "^(\\s*\\d+\\s*:\\s*)?([^;]+)(;.*)?$";

    static private String sanitize(final String in) {
        String out = in.trim();
        out = out.replaceAll(sPatternLine, "$2");
        return out.trim();
    }

    static private int[] arrMerge(final int[] arr1, final int[] arr2) {
        final int[] merged = new int[arr1.length + arr2.length];
        System.arraycopy(arr1, 0, merged, 0, arr1.length);
        System.arraycopy(arr2, 0, merged, arr1.length, arr2.length);
        return merged;
    }

    static private String strJoin(final String[] aArr, final String sSep) {
        final StringBuilder sbStr = new StringBuilder();
        for (int i = 0, il = aArr.length; i < il; i++) {
            if (i > 0) {
                sbStr.append(sSep);
            }
            sbStr.append(aArr[i]);
        }
        return sbStr.toString();
    }
}
