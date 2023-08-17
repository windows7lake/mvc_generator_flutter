package com.lio.mvc_generator

class ClassModelMaker(private val prefix: String) {
    fun generateCode(): String {
        return codeData.replace("template", prefix.lowercase())
            .replace("Template", prefix.underlineToCamelCase())
    }

    private val codeData = """
import 'package:mvc/base/base_model.dart';

class TemplateModel extends BaseModel {}
""".trimIndent()
}