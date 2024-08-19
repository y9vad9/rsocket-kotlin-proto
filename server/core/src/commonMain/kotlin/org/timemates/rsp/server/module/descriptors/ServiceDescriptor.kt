package org.timemates.rsp.server.module.descriptors

import io.ktor.utils.io.core.*
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationStrategy
import org.timemates.rsp.server.OptionsContainer
import org.timemates.rsp.options.Options
import org.timemates.rsp.server.RequestContext
import org.timemates.rsp.server.optionsContainer
import kotlin.reflect.KClass

/**
 * Represents a service descriptor containing the service name and a list of procedure descriptors.
 *
 * @property name The name of the service.
 * @property procedures The list of procedure descriptors associated with the service.
 */
@Suppress("UNCHECKED_CAST")
public class ServiceDescriptor(
    public val name: String,
    public val procedures: List<ProcedureDescriptor<*, *>>,
    options: Options,
) : OptionsContainer by optionsContainer(options) {
    /**
     * A map that associates procedure names with their corresponding descriptors.
     */
    private val proceduresMap = procedures.associateBy {
        it.name to it::class.simpleName!!
    }

    /**
     * Retrieves a specific ProcedureDescriptor from the ServiceDescriptor based on the name and type.
     *
     * @param name The name of the procedure.
     * @param type The class representing the type of the ProcedureDescriptor.
     * @return The ProcedureDescriptor object matching the given name and type, or null if not found.
     */
    @Suppress("UNCHECKED_CAST")
    public fun <T : ProcedureDescriptor<*, *>> procedure(name: String, type: KClass<T>): T? {
        return proceduresMap[name to type.simpleName!!] as? T
    }
}

/**
 * Retrieves a specific ProcedureDescriptor from the ServiceDescriptor based on the name.

 * @param name The name of the procedure.
 * @return The ProcedureDescriptor object matching the given name, or null if not found.
 */
public inline fun <reified T : ProcedureDescriptor<*, *>> ServiceDescriptor.procedure(name: String): T? {
    return procedure(name, T::class)
}