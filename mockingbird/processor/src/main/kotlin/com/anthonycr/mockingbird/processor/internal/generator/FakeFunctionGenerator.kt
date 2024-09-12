package com.anthonycr.mockingbird.processor.internal.generator

import com.anthonycr.mockingbird.processor.internal.normalizedQualifiedName
import com.anthonycr.mockingbird.processor.internal.safePackageName
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.TypeVariableName

/**
 * Used to generate the fake function used to obtain the generated fake implementation.
 */
class FakeFunctionGenerator {

    /**
     * Generate the fake function from the [KSClassDeclaration] and associated [TypeSpec] for each
     * fake interface.
     */
    fun generate(fakeImplementations: Map<KSClassDeclaration, TypeSpec>): FileSpec =
        FileSpec.builder("com.anthonycr.mockingbird.core", "Fakes")
            .addFunction(
                FunSpec.builder("fake")
                    .addTypeVariable(TypeVariableName("reified T"))
                    .returns(TypeVariableName("T"))
                    .addModifiers(KModifier.INLINE)
                    .addStatement("return fake(T::class.java)")
                    .build()
            ).addFunction(
                FunSpec.builder("fake")
                    .addTypeVariable(TypeVariableName("T"))
                    .addParameter(
                        "clazz",
                        ClassName("java.lang", "Class").parameterizedBy(TypeVariableName("T"))
                    )
                    .returns(TypeVariableName("T"))
                    .beginControlFlow("return when(clazz.canonicalName)")
                    .apply {
                        fakeImplementations.forEach { (declaration, fake) ->
                            val className =
                                ClassName(declaration.safePackageName, fake.name!!)
                            addStatement("\"${declaration.normalizedQualifiedName}\" -> clazz.cast(${className.canonicalName}())!!")
                        }
                        addStatement("else -> error(\"Unsupported type \$clazz\")")
                    }
                    .endControlFlow()
                    .build()
            ).build()
}