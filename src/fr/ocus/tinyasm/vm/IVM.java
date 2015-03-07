package fr.ocus.tinyasm.vm;

import fr.ocus.tinyasm.vm.stacktrace.IVMStackTrace;

public interface IVM {
    IVMStackTrace run(final int... source);
}
