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
package com.google.j2cl.errors;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * An error logger class that records the number of errors and provides error print methods.
 */
public class Errors {
  /**
   * Represents compiler errors.
   */
  public enum Error {
    ERR_INVALID_FLAG("invalid flag"),
    ERR_FILE_NOT_FOUND("file not found"),
    ERR_INVALID_SOURCE_FILE("invalid source file"),
    ERR_INVALID_SOURCE_VERSION("invalid source version"),
    ERR_UNSUPPORTED_ENCODING("unsupported encoding"),
    ERR_CANNOT_GENERATE_OUTPUT("cannot generate output, please see Velocity runtime log"),
    ERR_CANNOT_FIND_UNIT("cannot find CompilationUnit for type "),
    ERR_OUTPUT_LOCATION("-output location must be a directory or .zip file"),
    ERR_CANNOT_EXTRACT_ZIP("cannot extract zip"),
    ERR_CANNOT_OPEN_ZIP("cannot open zip"),
    ERR_CANNOT_CLOSE_ZIP("cannot close zip"),
    ERR_NATIVE_JAVA_SOURCE_NO_MATCH("cannot find matching native file"),
    ERR_NATIVE_UNUSED_NATIVE_SOURCE("native JavaScript file not used"),
    ERR_CANNOT_CREATE_TEMP_DIR("cannot create temporary directory"),
    ERR_CANNOT_OPEN_FILE("cannot open file"),
    ERR_ERROR("error"); // used for customized error message.
    private String errorMessage;

    Error(String errorMessage) {
      this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
      return errorMessage;
    }
  }

  private int errorCount = 0;
  private List<String> errorMessages = new ArrayList<>();
  private PrintStream errorStream;

  public Errors() {
    this.errorStream = System.err;
  }

  public Errors(PrintStream errorStream) {
    this.errorStream = errorStream;
  }

  public int errorCount() {
    return errorCount;
  }

  public PrintStream getErrorStream() {
    return errorStream;
  }

  public void reset() {
    this.errorCount = 0;
    this.errorMessages.clear();
  }

  public void error(Error error) {
    errorCount++;
    errorMessages.add(error.getErrorMessage());
  }

  public void error(Error error, String detailMessage) {
    errorCount++;
    errorMessages.add(error.getErrorMessage() + ": " + detailMessage);
  }

  /**
   * Prints all error messages and a summary.
   */
  public void report() {
    for (String message : errorMessages) {
      errorStream.println(message);
    }
    errorStream.printf("%d error(s).%n", errorCount);
  }

  /**
   * If there were errors, prints a summary and exits.
   */
  public void maybeReportAndExit() {
    if (errorCount > 0) {
      report();
      System.exit(errorCount);
    }
  }
}
