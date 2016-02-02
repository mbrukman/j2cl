/*
 * Copyright 2015 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.j2cl.ast;

import com.google.common.base.Preconditions;
import com.google.j2cl.ast.processors.Context;
import com.google.j2cl.ast.processors.Visitable;

import java.util.ArrayList;
import java.util.List;

/**
 * Method declaration.
 */
@Visitable
@Context
public class Method extends Node {
  @Visitable MethodDescriptor methodDescriptor;
  @Visitable List<Variable> parameters = new ArrayList<>();
  @Visitable Block body;
  private boolean isAbstract;
  private boolean isOverride;
  private String jsDocDescription;
  private boolean isFinal;

  public Method(MethodDescriptor methodDescriptor, List<Variable> parameters, Block body) {
    this(methodDescriptor, parameters, body, false, false, false, null);
  }

  public Method(
      MethodDescriptor methodDescriptor,
      List<Variable> parameters,
      Block body,
      boolean isAbstract,
      boolean isOverride,
      boolean isFinal,
      String jsDocDescription) {
    Preconditions.checkNotNull(methodDescriptor);
    Preconditions.checkNotNull(parameters);
    Preconditions.checkNotNull(body);
    this.methodDescriptor = methodDescriptor;
    this.parameters.addAll(parameters);
    this.body = body;
    this.isAbstract = isAbstract;
    this.isOverride = isOverride;
    this.isFinal = isFinal;
    this.jsDocDescription = jsDocDescription;
    if (jsDocDescription == null && isConstructor()) {
      this.jsDocDescription = "Initializes instance fields for a particular Java constructor.";
    }
  }

  public MethodDescriptor getDescriptor() {
    return methodDescriptor;
  }

  public List<Variable> getParameters() {
    return parameters;
  }

  public Block getBody() {
    return body;
  }

  public boolean isConstructor() {
    return methodDescriptor.isConstructor();
  }

  public boolean isNative() {
    return methodDescriptor.isNative();
  }

  public void setBody(Block body) {
    this.body = body;
  }

  public boolean isAbstract() {
    return this.isAbstract;
  }

  public void setAbstract(boolean isAbstract) {
    this.isAbstract = isAbstract;
  }

  public boolean isOverride() {
    return this.isOverride;
  }

  public void setOverride(boolean isOverride) {
    this.isOverride = isOverride;
  }

  public boolean isFinal() {
    return this.isFinal;
  }

  public void setFinal(boolean isFinal) {
    this.isFinal = isFinal;
  }

  public String getJsDocDescription() {
    return jsDocDescription;
  }

  @Override
  public Node accept(Processor processor) {
    return Visitor_Method.visit(processor, this);
  }
}
