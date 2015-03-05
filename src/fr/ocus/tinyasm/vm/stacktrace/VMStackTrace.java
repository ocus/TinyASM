package fr.ocus.tinyasm.vm.stacktrace;

import java.util.ArrayList;
import java.util.List;

public class VMStackTrace {
    private List<VMStackTraceElement> elements = new ArrayList<VMStackTraceElement>();

    public void add(VMStackTraceElement element) {
        elements.add(element);
    }

    @Override
    public String toString() {
        return "VMStackTrace(" + elements + ")";
    }
}
