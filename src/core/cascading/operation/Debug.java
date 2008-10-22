/*
 * Copyright (c) 2007-2008 Concurrent, Inc. All Rights Reserved.
 *
 * Project and contact information: http://www.cascading.org/
 *
 * This file is part of the Cascading project.
 *
 * Cascading is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Cascading is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Cascading.  If not, see <http://www.gnu.org/licenses/>.
 */

package cascading.operation;

import java.io.PrintStream;

import cascading.flow.FlowProcess;

/**
 * Class Debug is a {@link Filter} that will never remove an item from a stream, but will print the Tuple to either
 * stdout or stderr.
 */
public class Debug extends BaseOperation implements Filter
  {
  static public enum Output
    {
      STDOUT, STDERR
    }

  /** Field output */
  private Output output = Output.STDERR;
  /** Field prefix */
  private String prefix = null;
  /** Field printFields */
  private boolean printFields = false;

  /**
   * Constructor Debug creates a new Debug instance that prints to stderr by default, and does not print
   * the Tuple instance field names.
   */
  public Debug()
    {
    }

  /**
   * Constructor Debug creates a new Debug instance that prints to stderr by default, and does not print
   * the Tuple instance field names.
   *
   * @param prefix of type String
   */
  public Debug( String prefix )
    {
    this.prefix = prefix;
    }

  /**
   * Constructor Debug creates a new Debug instance that prints to stderr and will print the current
   * Tuple instance field names if printFields is true.
   *
   * @param prefix      of type String
   * @param printFields of type boolean
   */
  public Debug( String prefix, boolean printFields )
    {
    this.prefix = prefix;
    this.printFields = printFields;
    }

  /**
   * Constructor Debug creates a new Debug instance that prints to stderr and will print the current
   * Tuple instance field names if printFields is true.
   *
   * @param printFields of type boolean
   */
  public Debug( boolean printFields )
    {
    this.printFields = printFields;
    }

  /**
   * Constructor Debug creates a new Debug instance that prints to the declared stream and does not print the Tuple
   * field names.
   *
   * @param output of type Output
   */
  public Debug( Output output )
    {
    this.output = output;
    }

  /**
   * Constructor Debug creates a new Debug instance that prints to the declared stream and does not print the Tuple
   * field names.
   *
   * @param output of type Output
   * @param prefix of type String
   */
  public Debug( Output output, String prefix )
    {
    this.output = output;
    this.prefix = prefix;
    }

  /**
   * Constructor Debug creates a new Debug instance that prints to the declared stream and will print the Tuple instances
   * field names if printFields is true.
   *
   * @param output      of type Output
   * @param prefix      of type String
   * @param printFields of type boolean
   */
  public Debug( Output output, String prefix, boolean printFields )
    {
    this.output = output;
    this.prefix = prefix;
    this.printFields = printFields;
    }

  /**
   * Constructor Debug creates a new Debug instance that prints to the declared stream and will print the Tuple instances
   * field names if printFields is true.
   *
   * @param output      of type Output
   * @param printFields of type boolean
   */
  public Debug( Output output, boolean printFields )
    {
    this.output = output;
    this.printFields = printFields;
    }

  /** @see Filter#isRemove(cascading.flow.FlowProcess, FilterCall) */
  public boolean isRemove( FlowProcess flowProcess, FilterCall filterCall )
    {
    PrintStream stream = output == Output.STDOUT ? System.out : System.err;

    if( printFields )
      print( stream, filterCall.getArguments().getFields().print() );

    print( stream, filterCall.getArguments().getTuple().print() );

    return false;
    }

  private void print( PrintStream stream, String message )
    {
    if( prefix != null )
      {
      stream.print( prefix );
      stream.print( ": " );
      }

    stream.println( message );
    }

  }