
package org.drip.simm20.product;

/*
 * -*- mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 */

/*!
 * Copyright (C) 2018 Lakshmi Krishnamurthy
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
 * RiskFactorTenorSensitivity holds the ISDA SIMM 2.0 Risk Factor Tenor Bucket Sensitivities. The References
 *  are:
 *  
 *  - Andersen, L. B. G., M. Pykhtin, and A. Sokol (2017): Credit Exposure in the Presence of Initial Margin,
 *  	https://papers.ssrn.com/sol3/papers.cfm?abstract_id=2806156, eSSRN.
 *  
 *  - Albanese, C., S. Caenazzo, and O. Frankel (2017): Regression Sensitivities for Initial Margin
 *  	Calculations, https://papers.ssrn.com/sol3/papers.cfm?abstract_id=2763488, eSSRN.
 *  
 *  - Anfuso, F., D. Aziz, P. Giltinan, and K. Loukopoulus (2017): A Sound Modeling and Back-testing
 *  	Framework for Forecasting Initial Margin Requirements,
 *  	https://papers.ssrn.com/sol3/papers.cfm?abstract_id=2716279, eSSRN.
 *  
 *  - Caspers, P., P. Giltinan, R. Lichters, and N. Nowaczyk (2017): Forecasting Initial Margin Requirements
 *  	- A Model Evaluation https://papers.ssrn.com/sol3/papers.cfm?abstract_id=2911167, eSSRN.
 *  
 *  - International Swaps and Derivatives Association (2017): SIMM v2.0 Methodology,
 *  	https://www.isda.org/a/oFiDE/isda-simm-v2.pdf.
 * 
 * @author Lakshmi Krishnamurthy
 */

public class RiskFactorTenorSensitivity
{
	private java.util.Map<java.lang.String, java.lang.Double> _sensitivityMap = null;

	/**
	 * Construct the Standard Rates ISDA Bucket Sensitivity
	 * 
	 * @return The Standard Rates ISDA Bucket Sensitivity
	 */

	public static final RiskFactorTenorSensitivity IR()
	{
		java.util.Map<java.lang.String, java.lang.Double> sensitivityMap = new
			java.util.HashMap<java.lang.String, java.lang.Double>();

		for (java.lang.String tenor : org.drip.simm20.rates.IRSettingsContainer.TenorSet())
		{
			sensitivityMap.put (
				tenor,
				0.
			);
		}

		try
		{
			return new RiskFactorTenorSensitivity (sensitivityMap);
		}
		catch (java.lang.Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Construct the Standard Credit Qualifying ISDA Bucket Sensitivity
	 * 
	 * @return The Standard Credit Qualifying ISDA Bucket Sensitivity
	 */

	public static final RiskFactorTenorSensitivity CRQ()
	{
		java.util.Map<java.lang.String, java.lang.Double> sensitivityMap = new
			java.util.HashMap<java.lang.String, java.lang.Double>();

		for (java.lang.String tenor : org.drip.simm20.credit.CRQSettingsContainer.TenorSet())
		{
			sensitivityMap.put (
				tenor,
				0.
			);
		}

		try
		{
			return new RiskFactorTenorSensitivity (sensitivityMap);
		}
		catch (java.lang.Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Construct the Standard Credit Non-Qualifying ISDA Bucket Sensitivity
	 * 
	 * @return The Standard Credit Non-Qualifying ISDA Bucket Sensitivity
	 */

	public static final RiskFactorTenorSensitivity CRNQ()
	{
		java.util.Map<java.lang.String, java.lang.Double> sensitivityMap = new
			java.util.HashMap<java.lang.String, java.lang.Double>();

		for (java.lang.String tenor : org.drip.simm20.credit.CRNQSettingsContainer.TenorSet())
		{
			sensitivityMap.put (
				tenor,
				0.
			);
		}

		try
		{
			return new RiskFactorTenorSensitivity (sensitivityMap);
		}
		catch (java.lang.Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * RiskFactorTenorSensitivity Constructor
	 * 
	 * @param sensitivityMap The Tenor Sensitivity Map
	 * 
	 * @throws java.lang.Exception Thrown if the Inputs are Invalid
	 */

	public RiskFactorTenorSensitivity (
		java.util.Map<java.lang.String, java.lang.Double> sensitivityMap)
		throws java.lang.Exception
	{
		if (null == (_sensitivityMap = sensitivityMap) || 0 == _sensitivityMap.size())
		{
			throw new java.lang.Exception ("RiskFactorTenorSensitivity Constructor => Invalid Inputs");
		}
	}

	/**
	 * Retrieve the Set of Tenors
	 * 
	 * @return The Set of Tenors
	 */

	public java.util.Set<java.lang.String> tenorSet()
	{
		return _sensitivityMap.keySet();
	}

	/**
	 * Add the Tenor Sensitivity
	 * 
	 * @param tenor The Tenor
	 * @param sensitivity Sensitivity for the given Tenor
	 * 
	 * @return TRUE - The Tenor Sensitivity successfully set
	 */

	public boolean addTenorDelta (
		final java.lang.String tenor,
		final double sensitivity)
	{
		if (null == tenor || !org.drip.quant.common.NumberUtil.IsValid (sensitivity))
		{
			return false;
		}

		_sensitivityMap.put (
			tenor,
			sensitivity
		);

		return true;
	}

	/**
	 * Indicate of the Sensitivity exists for the specified Tenor
	 * 
	 * @param tenor The Tenor
	 * 
	 * @return TRUE - Sensitivity exists for the specified Tenor
	 */

	public boolean tenorExists (
		final java.lang.String tenor)
	{
		return null != tenor && _sensitivityMap.containsKey (tenor);
	}

	/**
	 * Retrieve the Sensitivity for the Bucket Tenor
	 * 
	 * @param tenor The Tenor
	 * 
	 * @return The Sensitivity corresponding to the Tenor
	 * 
	 * @throws java.lang.Exception Thrown if the Input is Invalid
	 */

	public double sensitivity (
		final java.lang.String tenor)
		throws java.lang.Exception
	{
		if (!tenorExists (tenor))
		{
			throw new java.lang.Exception ("RiskFactorTenorSensitivity::sensitivity => Invalid Inputs");
		}

		return _sensitivityMap.get (tenor);
	}

	/**
	 * Retrieve the Map of Tenor Sensitivities
	 * 
	 * @return The Map of Tenor Sensitivities
	 */

	public java.util.Map<java.lang.String, java.lang.Double> sensitivityMap()
	{
		return _sensitivityMap;
	}

	/**
	 * Generate the Cumulative Tenor Sensitivity
	 * 
	 * @return The Cumulative Tenor Sensitivity
	 */

	public double cumulative()
	{
		double cumulative = 0.;

		for (java.util.Map.Entry<java.lang.String, java.lang.Double> sensitivityEntry : _sensitivityMap.entrySet())
		{
			cumulative = cumulative + sensitivityEntry.getValue();
		}

		return cumulative;
	}

	/**
	 * Generate the Tenor Sensitivity Margin Map
	 * 
	 * @param sensitivityRiskWeightMap The Tenor Sensitivity Risk Weight Map
	 * 
	 * @return The Tenor Sensitivity Margin Map
	 */

	public java.util.Map<java.lang.String, java.lang.Double> sensitivityMargin (
		final java.util.Map<java.lang.String, java.lang.Double> sensitivityRiskWeightMap)
	{
		if (null == sensitivityRiskWeightMap || 0 == sensitivityRiskWeightMap.size())
		{
			return null;
		}

		java.util.Map<java.lang.String, java.lang.Double> sensitivityMargin = new
			java.util.HashMap<java.lang.String, java.lang.Double>();

		for (java.util.Map.Entry<java.lang.String, java.lang.Double> sensitivityEntry :
			_sensitivityMap.entrySet())
		{
			java.lang.String tenor = sensitivityEntry.getKey();

			if (!sensitivityRiskWeightMap.containsKey (tenor))
			{
				return null;
			}

			sensitivityMargin.put (
				tenor,
				sensitivityEntry.getValue() * sensitivityRiskWeightMap.get (tenor)
			);
		}

		return sensitivityMargin;
	}
}
