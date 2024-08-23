/*
 * The MIT License (MIT)
 * 
 * Copyright (c) 2014-2024 Synopsys Inc.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.piketec.tpt.api.tasmo;

import java.rmi.RemoteException;

import com.piketec.tpt.api.ApiException;
import com.piketec.tpt.api.IdentifiableRemote;

/**
 * This class represents a tasmo coverage goal.
 *
 * @author Copyright (c) 2014-2024 Synopsys Inc. - MIT License (MIT) - All rights reserved
 */
public interface TasmoCoverageGoal extends IdentifiableRemote {

  /**
   * @return The identification number for this coverage goal.
   * @throws ApiException
   *           if the TASMO test data generation controller object has been disposed
   * @throws RemoteException
   *           remote communication problem
   */
  public int getId() throws ApiException, RemoteException;

  /**
   * @return The {@link CoverageCriterion} for this {@link TasmoCoverageGoal}.
   * @throws ApiException
   *           if the TASMO test data generation controller object has been disposed
   * @throws RemoteException
   *           remote communication problem
   */
  public CoverageCriterion getCoverageCriterion() throws ApiException, RemoteException;

  /**
   * Get the context for this {@link TasmoCoverageGoal}. In case of Simulink this will be the block
   * path and in case of C-Code this will contain the name of the source file and function.
   * 
   * @return A string representing the context of this coverage goal.
   * 
   * @throws ApiException
   *           if the TASMO test data generation controller object has been disposed
   * @throws RemoteException
   *           remote communication problem
   */
  public String getContextString() throws ApiException, RemoteException;

  /**
   * @return A String that represents the type of the context for this {@link TasmoCoverageGoal},
   *         see {@link #getContextString()} for more details.
   * @throws ApiException
   *           if the TASMO test data generation controller object has been disposed
   * @throws RemoteException
   *           remote communication problem
   */
  public String getContextType() throws ApiException, RemoteException;

  /**
   * Get the formaula for this coverage goal.
   * 
   * @return A string representing the formula of this coverage goal.
   * 
   * @throws ApiException
   *           if the TASMO test data generation controller object has been disposed
   * @throws RemoteException
   *           remote communication problem
   */
  public String getFormulaString() throws ApiException, RemoteException;

  /**
   * Get the current status for this coverage goal.
   * 
   * @return The current status for this coverage goal.
   * 
   * @throws ApiException
   *           if the TASMO test data generation controller object has been disposed
   * @throws RemoteException
   *           remote communication problem
   */
  public CoverageGoalStatus getCurrentStatus() throws ApiException, RemoteException;

}
