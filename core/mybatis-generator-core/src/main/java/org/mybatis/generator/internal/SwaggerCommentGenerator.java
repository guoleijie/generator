/**
 *    Copyright 2006-2019 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.generator.internal;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.util.StringUtility;

public class SwaggerCommentGenerator extends DefaultCommentGenerator {

  @Override
  public void addModelClassComment(TopLevelClass topLevelClass,
      IntrospectedTable introspectedTable) {
    if (isSuppressAllComments() || !isAddRemarkComments()) {
      return;
    }

    topLevelClass.addJavaDocLine("/**");

    String remarks = introspectedTable.getRemarks();
    if (isAddRemarkComments() && StringUtility.stringHasValue(remarks)) {
      topLevelClass.addJavaDocLine(" * Database Table Remarks:");
      String[] remarkLines = remarks.split(System.getProperty("line.separator"));
      for (String remarkLine : remarkLines) {
        topLevelClass.addJavaDocLine(" *   " + remarkLine);
      }
    }
    topLevelClass.addJavaDocLine(" *");

    topLevelClass
        .addJavaDocLine(" * This class was generated by MyBatis Generator.");

    StringBuilder sb = new StringBuilder();
    sb.append(" * This class corresponds to the database table ");
    sb.append(introspectedTable.getFullyQualifiedTable());
    topLevelClass.addJavaDocLine(sb.toString());

    addJavadocTag(topLevelClass, true);

    topLevelClass.addJavaDocLine(" */");

    if (isAddRemarkComments()) {
      if (StringUtility.stringHasValue(remarks)) {
        String[] remarkLines = remarks.split(System.getProperty("line.separator"));
        StringBuffer tempLine = new StringBuffer();
        for (String remarkLine : remarkLines) {
          tempLine.append(remarkLine).append(" ");
        }
        topLevelClass.addJavaDocLine("@ApiModel(value = \"" + tempLine.toString() + "\")");
      } else {
        topLevelClass.addJavaDocLine("@ApiModel(value = \"\")");
      }
    }
  }

  @Override
  public void addFieldComment(Field field,
      IntrospectedTable introspectedTable,
      IntrospectedColumn introspectedColumn) {
    if (isSuppressAllComments()) {
      return;
    }

    field.addJavaDocLine("/**");

    String remarks = introspectedColumn.getRemarks();
    if (isAddRemarkComments() && StringUtility.stringHasValue(remarks)) {
      field.addJavaDocLine(" * Database Column Remarks:");
      String[] remarkLines = remarks.split(System.getProperty("line.separator"));
      for (String remarkLine : remarkLines) {
        field.addJavaDocLine(" *   " + remarkLine);
      }
    }

    field.addJavaDocLine(" *");
    field
        .addJavaDocLine(" * This field was generated by MyBatis Generator.");

    StringBuilder sb = new StringBuilder();
    sb.append(" * This field corresponds to the database column ");
    sb.append(introspectedTable.getFullyQualifiedTable());
    sb.append('.');
    sb.append(introspectedColumn.getActualColumnName());
    field.addJavaDocLine(sb.toString());

    addJavadocTag(field, false);

    field.addJavaDocLine(" */");
    if (isAddRemarkComments()) {
      if (StringUtility.stringHasValue(remarks)) {
        String[] remarkLines = remarks.split(System.getProperty("line.separator"));
        StringBuffer tempLine = new StringBuffer();
        for (String remarkLine : remarkLines) {
          tempLine.append(remarkLine).append(" ");
        }
        field.addJavaDocLine("@ApiModelProperty(value = \"" + tempLine.toString() + "\")");
      } else {
        field.addJavaDocLine("@ApiModelProperty(value = \"\")");
      }
    }
  }

  @Override
  public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
    if (isSuppressAllComments()) {
      return;
    }

    StringBuilder sb = new StringBuilder();

    field.addJavaDocLine("/**");
    field
        .addJavaDocLine(" * This field was generated by MyBatis Generator.");

    sb.append(" * This field corresponds to the database table ");
    sb.append(introspectedTable.getFullyQualifiedTable());
    field.addJavaDocLine(sb.toString());

    addJavadocTag(field, false);

    field.addJavaDocLine(" */");
  }
}
