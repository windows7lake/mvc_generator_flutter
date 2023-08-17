package com.lio.mvc_generator

class ClassControllerMaker(private val prefix: String) {
    fun generateCode(): String {
        return codeData.replace("template", prefix.lowercase())
            .replace("Template", prefix.underlineToCamelCase())
    }

    private val codeData = """
import 'package:mvc/base/base_controller.dart';

import 'template_model.dart';

class TemplateController extends BaseController<TemplateModel> {
  @override
  TemplateModel model = TemplateModel();
}

extension Private on TemplateController {}

extension GetData on TemplateController {}

extension SetData on TemplateController {}

extension Action on TemplateController {}

extension Network on TemplateController {}
""".trimIndent()
}