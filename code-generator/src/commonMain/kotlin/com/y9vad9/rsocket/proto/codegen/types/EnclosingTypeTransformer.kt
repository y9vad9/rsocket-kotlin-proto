package com.y9vad9.rsocket.proto.codegen.types

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.wire.schema.EnclosingType
import com.y9vad9.rsocket.proto.codegen.Transformer

internal object EnclosingTypeTransformer : Transformer<EnclosingType, TypeSpec> {
    override fun transform(incoming: EnclosingType): TypeSpec {
        return TypeSpec.classBuilder(incoming.name)
            .primaryConstructor(
                FunSpec.constructorBuilder().addModifiers(KModifier.PRIVATE).build()
            )
            .addTypes(incoming.nestedTypes.map { TypeTransformer.transform(it) })
            .build()
    }

}