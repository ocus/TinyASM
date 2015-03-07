package fr.ocus.tinyasm.impl.vm.stacktrace;

import fr.ocus.tinyasm.vm.stacktrace.IVMStackTrace;
import fr.ocus.tinyasm.vm.stacktrace.IVMStackTraceElement;

import java.util.ArrayList;
import java.util.Collection;

public class VMStackTrace implements IVMStackTrace {
    private final Collection<IVMStackTraceElement> elements = new ArrayList<IVMStackTraceElement>();

    @Override
    public final void add(final IVMStackTraceElement element) {
        elements.add(element);
    }

    @Override
    public final String toString() {
        return "VMStackTrace(" + elements + ')';
    }
}
