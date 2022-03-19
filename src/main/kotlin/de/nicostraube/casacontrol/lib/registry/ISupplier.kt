package de.nicostraube.casacontrol.lib.registry

interface ISupplier<T> {
    fun get(): T
}
