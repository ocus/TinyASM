package fr.ocus.tinyasm.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.ocus.tinyasm.vm.VM;

public class VMTest {

    public static void main(final String[] args) {
        // executeProgram("add.tiny", !true);
        executeProgram("factorial.tiny", true);
        // executeProgram("mult.tiny", !true);
        // executeProgram("py.tiny", true);
    }

    static private void executeProgram(final String tinyName, final boolean debug) {
        final String inputFilePath = System.getProperty("user.dir") + "\\programs\\" + tinyName;
        final List<Integer> source = new ArrayList<Integer>();
        final FileInputStream inputFileStream;
        try {
            inputFileStream = new FileInputStream(inputFilePath);
            while (inputFileStream.available() > 0) {
                source.add(inputFileStream.read());
            }
            inputFileStream.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }

        final VM vm = new VM(debug);
        vm.run(toIntArray(source));
        if (debug) {
            vm.dumpMemory();
        }
    }

    static private int[] toIntArray(final List<Integer> list) {
        final int[] ret = new int[list.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }
}