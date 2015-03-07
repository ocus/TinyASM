package fr.ocus.tinyasm.demos;

import fr.ocus.tinyasm.impl.vm.VM;
import fr.ocus.tinyasm.vm.IVM;
import fr.ocus.tinyasm.vm.stacktrace.IVMStackTrace;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"StringConcatenationMissingWhitespace", "AccessOfSystemProperties"})
public class VMDemo {

    public static void main(final String[] args) {
        // executeProgram("add.tiny", !true);
        executeProgram("factorial.tiny", true);
        // executeProgram("mult.tiny", !true);
        // executeProgram("py.tiny", true);
    }

    private static void executeProgram(final String tinyName, final boolean debug) {
        final String inputFilePath = System.getProperty("user.dir") + File.separator + "programs" + File.separator + tinyName;
        final List<Integer> source = new ArrayList<Integer>();
        final FileInputStream inputFileStream;
        try {
            inputFileStream = new FileInputStream(inputFilePath);
            while (inputFileStream.available() > 0) {
                source.add(inputFileStream.read());
            }
            inputFileStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }

        final IVM vm = new VM(debug);
        final IVMStackTrace vmStackTrace = vm.run(toIntArray(source));
        if (debug) {
            //noinspection CastToConcreteClass
            ((VM) vm).dumpMemory();
            System.err.println(vmStackTrace);
        }
    }

    private static int[] toIntArray(final List<Integer> list) {
        final int[] ret = new int[list.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }
}
