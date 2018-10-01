
package org.drip.simm.parameters;

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
 * RiskClassSensitivitySettings holds the Settings that govern the Generation of the ISDA SIMM Bucket
 *  Sensitivities across Individual Risk Class Buckets. The References are:
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

public class RiskClassSensitivitySettings
{
	private org.drip.simm.parameters.RiskMeasureSensitivitySettings _vega = null;
	private org.drip.simm.parameters.RiskMeasureSensitivitySettings _delta = null;
	private org.drip.simm.parameters.RiskMeasureSensitivitySettings _curvature = null;

	/**
	 * Generate the ISDA 2.0 Standard Commodity Sensitivity Settings
	 * 
	 * @param vegaDurationDays The Vega Duration Days
	 * 
	 * @return The ISDA 2.0 Standard Commodity Sensitivity Settings
	 */

	public static final RiskClassSensitivitySettings ISDA_CT_20 (
		final int vegaDurationDays)
	{
		try
		{
			return new RiskClassSensitivitySettings (
				org.drip.simm.parameters.RiskMeasureSensitivitySettings.ISDA_CT_DELTA_20(),
				org.drip.simm.parameters.RiskMeasureSensitivitySettings.ISDA_CT_VEGA_20(),
				org.drip.simm.parameters.RiskMeasureSensitivitySettings.ISDA_CT_CURVATURE_20
					(vegaDurationDays)
			);
		}
		catch (java.lang.Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Generate the ISDA 2.1 Standard Commodity Sensitivity Settings
	 * 
	 * @param vegaDurationDays The Vega Duration Days
	 * 
	 * @return The ISDA 2.1 Standard Commodity Sensitivity Settings
	 */

	public static final RiskClassSensitivitySettings ISDA_CT_21 (
		final int vegaDurationDays)
	{
		try
		{
			return new RiskClassSensitivitySettings (
				org.drip.simm.parameters.RiskMeasureSensitivitySettings.ISDA_CT_DELTA_21(),
				org.drip.simm.parameters.RiskMeasureSensitivitySettings.ISDA_CT_VEGA_21(),
				org.drip.simm.parameters.RiskMeasureSensitivitySettings.ISDA_CT_CURVATURE_21
					(vegaDurationDays)
			);
		}
		catch (java.lang.Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Generate the ISDA 2.0 Standard FX Sensitivity Settings
	 * 
	 * @param vegaDurationDays The Vega Duration Days
	 * 
	 * @return The ISDA 2.0 Standard FX Sensitivity Settings
	 */

	public static final RiskClassSensitivitySettings ISDA_FX_20 (
		final int vegaDurationDays)
	{
		try
		{
			return new RiskClassSensitivitySettings (
				org.drip.simm.parameters.RiskMeasureSensitivitySettings.ISDA_FX_DELTA_20(),
				org.drip.simm.parameters.RiskMeasureSensitivitySettings.ISDA_FX_VEGA_20(),
				org.drip.simm.parameters.RiskMeasureSensitivitySettings.ISDA_FX_CURVATURE_20
					(vegaDurationDays)
			);
		}
		catch (java.lang.Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Generate the ISDA 2.1 Standard FX Sensitivity Settings
	 * 
	 * @param vegaDurationDays The Vega Duration Days
	 * 
	 * @return The ISDA 2.1 Standard FX Sensitivity Settings
	 */

	public static final RiskClassSensitivitySettings ISDA_FX_21 (
		final int vegaDurationDays)
	{
		try
		{
			return new RiskClassSensitivitySettings (
				org.drip.simm.parameters.RiskMeasureSensitivitySettings.ISDA_FX_DELTA_21(),
				org.drip.simm.parameters.RiskMeasureSensitivitySettings.ISDA_FX_VEGA_21(),
				org.drip.simm.parameters.RiskMeasureSensitivitySettings.ISDA_FX_CURVATURE_21
					(vegaDurationDays)
			);
		}
		catch (java.lang.Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * RiskClassSensitivitySettings Constructor
	 * 
	 * @param delta Delta Risk Measure Sensitivity Settings
	 * @param vega Vega Risk Measure Sensitivity Settings
	 * @param curvature Curvature Risk Measure Sensitivity Settings
	 * 
	 * @throws java.lang.Exception Thrown if the Inputs are Invalid
	 */

	public RiskClassSensitivitySettings (
		final org.drip.simm.parameters.RiskMeasureSensitivitySettings delta,
		final org.drip.simm.parameters.RiskMeasureSensitivitySettings vega,
		final org.drip.simm.parameters.RiskMeasureSensitivitySettings curvature)
		throws java.lang.Exception
	{
		if (null == (_delta = delta) ||
			null == (_vega = vega) ||
			null == (_curvature = curvature))
		{
			throw new java.lang.Exception ("RiskClassSensitivitySettings Constructor => Invalid Inputs");
		}
	}

	/**
	 * Delta Risk Measure Sensitivity Settings
	 * 
	 * @return Delta Risk Measure Sensitivity Settings
	 */

	public org.drip.simm.parameters.RiskMeasureSensitivitySettings delta()
	{
		return _delta;
	}

	/**
	 * Vega Risk Measure Sensitivity Settings
	 * 
	 * @return Vega Risk Measure Sensitivity Settings
	 */

	public org.drip.simm.parameters.RiskMeasureSensitivitySettings vega()
	{
		return _vega;
	}

	/**
	 * Curvature Risk Measure Sensitivity Settings
	 * 
	 * @return Curvature Risk Measure Sensitivity Settings
	 */

	public org.drip.simm.parameters.RiskMeasureSensitivitySettings curvature()
	{
		return _curvature;
	}
}
