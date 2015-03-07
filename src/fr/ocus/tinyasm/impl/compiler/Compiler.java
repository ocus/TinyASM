package fr.ocus.tinyasm.impl.compiler;

import fr.ocus.tinyasm.IInstruction;
import fr.ocus.tinyasm.compiler.ICompiler;
import fr.ocus.tinyasm.compiler.instructions.IASMInstructionVariants;
import fr.ocus.tinyasm.impl.InstructionsManager;
import fr.ocus.tinyasm.impl.compiler.instructions.ASMInstructionNotFoundException;
import fr.ocus.tinyasm.impl.compiler.instructions.ASMWrongArgumentCountException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Compiler implements ICompiler {
    private static ICompiler sCompiler = null;

    public static synchronized ICompiler get() {
        if (sCompiler == null) {
            sCompiler = new Compiler();
        }
        return sCompiler;
    }

    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");

    @Override
    public final int[] compile(final String content) throws ASMInstructionNotFoundException, ASMWrongArgumentCountException {
        return compile(content.split("\\n"));
    }

    @Override
    public final int[] compile(final String... lines) throws ASMInstructionNotFoundException, ASMWrongArgumentCountException {
        int[] compiled = new int[0];
        int lineNumber = 0;
        for (final String line : lines) {
            try {
                final int[] compiledLine = compileLine(line);
                compiled = arrMerge(compiled, compiledLine);
            } catch (final ASMInstructionNotFoundException e) {
                throw new ASMInstructionNotFoundException(e.getMessage() + " on line " + (lineNumber + 1), e);
            } catch (final ASMWrongArgumentCountException e) {
                throw new ASMWrongArgumentCountException(e.getMessage() + " on line " + (lineNumber + 1), e);
            }
            lineNumber++;
        }
        return compiled;
    }

    @Override
    public final int[] compileLine(final String line) throws ASMInstructionNotFoundException, ASMWrongArgumentCountException {
        final String sanitizedLine = sanitize(line);
        final String[] parts = sanitizedLine.split(" ", 2);
        final String mnemonic = parts[0].toLowerCase();
        final String template;
        if (parts.length > 1) {
            template = parts[1].replaceAll("\\d+", "a");
        } else {
            template = "";
        }
        final IASMInstructionVariants instructionVariants = InstructionsManager.get().lookupMnemonic(mnemonic);
        if (instructionVariants == null) {
            throw new ASMInstructionNotFoundException("Instruction \"" + sanitizedLine + "\" does not exists");
        }
        final IInstruction instruction = instructionVariants.lookupTemplate(template);
        if (instruction == null) {
            throw new ASMWrongArgumentCountException("Instruction \"" + sanitizedLine + "\" does not have the right number of arguments");
        }
        int[] numbers = new int[0];
        if (parts.length > 1) {
            final Matcher matches = NUMBER_PATTERN.matcher(parts[1]);
            while (matches.find()) {
                numbers = arrMerge(numbers, new int[]{Integer.valueOf(matches.group())});
            }
        }

        final int[] compiled = new int[numbers.length + 1];
        compiled[0] = instruction.getOpcode();
        System.arraycopy(numbers, 0, compiled, 1, numbers.length);
        return compiled;
    }

    @Override
    public final void compile(final String content, final OutputStream out) throws ASMInstructionNotFoundException, ASMWrongArgumentCountException,
            IOException {
        final int[] compiled = compile(content);
        for (final int i : compiled) {
            out.write(i);
        }
    }

    @Override
    public final String compileDump(final String content) {
        final int[] compiled = compile(content);
        final String[] dump = new String[compiled.length];
        int index = 0;
        for (final int c : compiled) {
            dump[index] = String.format("0x%02X", c);
            index++;
        }
        return strJoin(dump, " ");
    }

    private static final String sPatternLine = "^(\\s*\\d+\\s*:\\s*)?([^;]+)(;.*)?$";

    private static String sanitize(final String in) {
        String out = in.trim();
        out = out.replaceAll(sPatternLine, "$2");
        return out.trim();
    }

    private static int[] arrMerge(final int[] arr1, final int[] arr2) {
        final int[] merged = new int[arr1.length + arr2.length];
        System.arraycopy(arr1, 0, merged, 0, arr1.length);
        System.arraycopy(arr2, 0, merged, arr1.length, arr2.length);
        return merged;
    }

    private static String strJoin(final String[] aArr, final String sSep) {
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
