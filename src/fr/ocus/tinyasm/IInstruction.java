package fr.ocus.tinyasm;

import fr.ocus.tinyasm.compiler.instructions.IASMInstruction;
import fr.ocus.tinyasm.vm.instructions.IVMInstruction;

public interface IInstruction extends IVMInstruction, IASMInstruction {
}
