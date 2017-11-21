
package org.drip.portfolioconstruction.core;

/*
 * -*- mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 */

/*!
 * Copyright (C) 2018 Lakshmi Krishnamurthy
 * Copyright (C) 2017 Lakshmi Krishnamurthy
 * 
 *  This file is part of DRIP, a free-software/open-source library for buy/side financial/trading model
 *  	libraries targeting analysts and developers
 *  	https://lakshmidrip.github.io/DRIP/
 *  
 *  DRIP is composed of four main libraries:
 *  
 *  - DRIP Fixed Income - https://lakshmidrip.github.io/DRIP-Fixed-Income/
 *  - DRIP Asset Allocation - https://lakshmidrip.github.io/DRIP-Asset-Allocation/
 *  - DRIP Numerical Optimizer - https://lakshmidrip.github.io/DRIP-Numerical-Optimizer/
 *  - DRIP Statistical Learning - https://lakshmidrip.github.io/DRIP-Statistical-Learning/
 * 
 *  - DRIP Fixed Income: Library for Instrument/Trading Conventions, Treasury Futures/Options,
 *  	Funding/Forward/Overnight Curves, Multi-Curve Construction/Valuation, Collateral Valuation and XVA
 *  	Metric Generation, Calibration and Hedge Attributions, Statistical Curve Construction, Bond RV
 *  	Metrics, Stochastic Evolution and Option Pricing, Interest Rate Dynamics and Option Pricing, LMM
 *  	Extensions/Calibrations/Greeks, Algorithmic Differentiation, and Asset Backed Models and Analytics.
 * 
 *  - DRIP Asset Allocation: Library for model libraries for MPT framework, Black Litterman Strategy
 *  	Incorporator, Holdings Constraint, and Transaction Costs.
 * 
 *  - DRIP Numerical Optimizer: Library for Numerical Optimization and Spline Functionality.
 * 
 *  - DRIP Statistical Learning: Library for Statistical Evaluation and Machine Learning.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *   	you may not use this file except in compliance with the License.
 *   
 *  You may obtain a copy of the License at
 *  	http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  	distributed under the License is distributed on an "AS IS" BASIS,
 *  	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  
 *  See the License for the specific language governing permissions and
 *  	limitations under the License.
 */

/**
 * TransactionChargeFixed contains the Parameters for the Fixed Transaction Charge Scheme.
 *
 * @author Lakshmi Krishnamurthy
 */

public class TransactionChargeFixed extends org.drip.portfolioconstruction.core.TransactionCharge {
	private double _dblFixedCharge = java.lang.Double.NaN;

	/**
	 * TransactionChargeFixed Constructor
	 * 
	 * @param strName Transaction Charge Name
	 * @param strID Transaction Charge ID
	 * @param strDescription Description of the Transaction Charge
	 * @param dblFixedCharge Fixed Transaction Charge
	 * 
	 * @throws java.lang.Exception Thrown if the Inputs are Invalid
	 */

	 public TransactionChargeFixed (
		final java.lang.String strName,
		final java.lang.String strID,
		final java.lang.String strDescription,
		final double dblFixedCharge)
		throws java.lang.Exception
	{
		super (
			strName,
			strID,
			strDescription
		);

		if (!org.drip.quant.common.NumberUtil.IsValid (_dblFixedCharge = dblFixedCharge) || 0. >
			_dblFixedCharge)
			throw new java.lang.Exception ("TransactionChargeFixed Constructor => Invalid Charge");
	}

	/**
	 * Retrieve the Fixed Transaction Cost Charge
	 * 
	 * @return The Fixed Transaction Cost Charge
	 */

	public double fixedCharge()
	{
		return _dblFixedCharge;
	}

	@Override public double estimate (
		final double dblInitial,
		final double dblFinal)
		throws java.lang.Exception
	{
		if (!org.drip.quant.common.NumberUtil.IsValid (dblInitial) ||
			!org.drip.quant.common.NumberUtil.IsValid (dblFinal))
			throw new java.lang.Exception ("TransactionChargeFixed::estimate => Invalid Inputs");

		return _dblFixedCharge;
	}
}
