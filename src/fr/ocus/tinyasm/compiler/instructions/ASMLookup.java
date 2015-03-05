package fr.ocus.tinyasm.compiler.instructions;

public interface ASMLookup<T> {
    T lookup(String key);
}
