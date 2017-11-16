
package org.drip.portfolioconstruction.objective;

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
 * TiltTerm holds the Details of Abstract Tilt Unit Objective Term.
 *
 * @author Lakshmi Krishnamurthy
 */

public abstract class TiltTerm extends org.drip.portfolioconstruction.optimizer.ObjectiveTerm {
	private double[] _adblMagnitude = null;
	private double[] _adblMembership = null;
	private org.drip.portfolioconstruction.composite.BlockAttribute _baMagnitude = null;
	private org.drip.portfolioconstruction.composite.BlockClassification _bcMembership = null;

	protected TiltTerm (
		final java.lang.String strName,
		final java.lang.String strID,
		final java.lang.String strDescription,
		final org.drip.portfolioconstruction.composite.Holdings holdingsInitial,
		final org.drip.portfolioconstruction.composite.BlockAttribute baMagnitude,
		final org.drip.portfolioconstruction.composite.BlockClassification bcMembership)
		throws java.lang.Exception
	{
		super (
			strName,
			strID,
			strDescription,
			"TILT",
			holdingsInitial
		);

		if (null == (_baMagnitude = baMagnitude) || null == (_bcMembership = bcMembership))
			throw new java.lang.Exception ("TiltTerm Constructor => Invalid Inputs");

		if (null == (_adblMagnitude = _baMagnitude.constrict (holdingsInitial)))
			throw new java.lang.Exception ("TiltTerm Constructor => Invalid Inputs");

		if (null == (_adblMembership = _bcMembership.constrict (holdingsInitial)))
			throw new java.lang.Exception ("TiltTerm Constructor => Invalid Inputs");
	}

	/**
	 * Retrieve the Array of Tilt Magnitudes
	 *  
	 * @return The Array of Tilt Magnitudes
	 */

	public double[] magnitude()
	{
		return _adblMagnitude;
	}

	/**
	 * Retrieve the Tilt Magnitude Block Attributes
	 *  
	 * @return The Tilt Magnitude Block Attributes
	 */

	public org.drip.portfolioconstruction.composite.BlockAttribute magnitudeAttribute()
	{
		return _baMagnitude;
	}

	/**
	 * Retrieve the Array of Tilt Memberships
	 *  
	 * @return The Array of Tilt Memberships
	 */

	public double[] membership()
	{
		return _adblMembership;
	}

	/**
	 * Retrieve the Tilt Membership Block Classification
	 *  
	 * @return The Tilt Membership Block Classification
	 */

	public org.drip.portfolioconstruction.composite.BlockClassification membershipClassification()
	{
		return _bcMembership;
	}
}
