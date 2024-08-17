@file:OptIn(ExperimentalSerializationApi::class)

package org.timemates.rsp.metadata

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber
import kotlin.jvm.JvmField

/**
 * Represents metadata information that sent from Server to Client.
 * @property extra Additional key-value pairs of metadata.
 */
@Serializable
public data class ServerMetadata(
    @ProtoNumber(1)
    val schemaVersion: Int = RSPMetadata.CURRENT_SCHEMA_VERSION,
    @ProtoNumber(2)
    public override val extra: ExtraMetadata = ExtraMetadata.EMPTY,
) : RSPMetadata {
    public companion object {
        @JvmField
        public val EMPTY: ServerMetadata = ServerMetadata()
    }

    override fun extra(extra: ExtraMetadata): RSPMetadata {
        return copy(extra = extra)
    }
}