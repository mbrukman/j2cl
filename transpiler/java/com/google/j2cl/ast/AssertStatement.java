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

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.j2cl.ast.annotations.Visitable;
import com.google.j2cl.common.SourcePosition;
import javax.annotation.Nullable;

/**
 * Assert Statement.
 */
@Visitable
public class AssertStatement extends Statement {
  @Visitable Expression expression;
  @Visitable @Nullable Expression message;

  public AssertStatement(SourcePosition sourcePosition, Expression expression, Expression message) {
    super(sourcePosition);
    this.expression = checkNotNull(expression);
    this.message = message;
  }

  public Expression getExpression() {
    return expression;
  }

  public Expression getMessage() {
    return message;
  }

  @Override
  public AssertStatement clone() {
    return new AssertStatement(getSourcePosition(), expression.clone(), AstUtils.clone(message));
  }

  @Override
  public Node accept(Processor processor) {
    return Visitor_AssertStatement.visit(processor, this);
  }
}
