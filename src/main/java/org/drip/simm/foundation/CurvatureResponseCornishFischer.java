
package org.drip.simm.foundation;

/*
 * -*- mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 */

/*!
 * Copyright (C) 2020 Lakshmi Krishnamurthy
 * Copyright (C) 2019 Lakshmi Krishnamurthy
 * Copyright (C) 2018 Lakshmi Krishnamurthy
 * 
 *  This file is part of DROP, an open-source library targeting analytics/risk, transaction cost analytics,
 *  	asset liability management analytics, capital, exposure, and margin analytics, valuation adjustment
 *  	analytics, and portfolio construction analytics within and across fixed income, credit, commodity,
 *  	equity, FX, and structured products. It also includes auxiliary libraries for algorithm support,
 *  	numerical analysis, numerical optimization, spline builder, model validation, statistical learning,
 *  	and computational support.
 *  
 *  	https://lakshmidrip.github.io/DROP/
 *  
 *  DROP is composed of three modules:
 *  
 *  - DROP Product Core - https://lakshmidrip.github.io/DROP-Product-Core/
 *  - DROP Portfolio Core - https://lakshmidrip.github.io/DROP-Portfolio-Core/
 *  - DROP Computational Core - https://lakshmidrip.github.io/DROP-Computational-Core/
 * 
 * 	DROP Product Core implements libraries for the following:
 * 	- Fixed Income Analytics
 * 	- Loan Analytics
 * 	- Transaction Cost Analytics
 * 
 * 	DROP Portfolio Core implements libraries for the following:
 * 	- Asset Allocation Analytics
 *  - Asset Liability Management Analytics
 * 	- Capital Estimation Analytics
 * 	- Exposure Analytics
 * 	- Margin Analytics
 * 	- XVA Analytics
 * 
 * 	DROP Computational Core implements libraries for the following:
 * 	- Algorithm Support
 * 	- Computation Support
 * 	- Function Analysis
 *  - Model Validation
 * 	- Numerical Analysis
 * 	- Numerical Optimizer
 * 	- Spline Builder
 *  - Statistical Learning
 * 
 * 	Documentation for DROP is Spread Over:
 * 
 * 	- Main                     => https://lakshmidrip.github.io/DROP/
 * 	- Wiki                     => https://github.com/lakshmiDRIP/DROP/wiki
 * 	- GitHub                   => https://github.com/lakshmiDRIP/DROP
 * 	- Repo Layout Taxonomy     => https://github.com/lakshmiDRIP/DROP/blob/master/Taxonomy.md
 * 	- Javadoc                  => https://lakshmidrip.github.io/DROP/Javadoc/index.html
 * 	- Technical Specifications => https://github.com/lakshmiDRIP/DROP/tree/master/Docs/Internal
 * 	- Release Versions         => https://lakshmidrip.github.io/DROP/version.html
 * 	- Community Credits        => https://lakshmidrip.github.io/DROP/credits.html
 * 	- Issues Catalog           => https://github.com/lakshmiDRIP/DROP/issues
 * 	- JUnit                    => https://lakshmidrip.github.io/DROP/junit/index.html
 * 	- Jacoco                   => https://lakshmidrip.github.io/DROP/jacoco/index.html
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
 * <i>CurvatureResponseCornishFischer</i> computes the Curvature Co-variance Scaling Factor using the
 * Cumulative Curvature Sensitivities. The References are:
 * 
 * <br><br>
 *  <ul>
 *  	<li>
 *  		Andersen, L. B. G., M. Pykhtin, and A. Sokol (2017): Credit Exposure in the Presence of Initial
 *  			Margin https://papers.ssrn.com/sol3/papers.cfm?abstract_id=2806156 <b>eSSRN</b>
 *  	</li>
 *  	<li>
 *  		Albanese, C., S. Caenazzo, and O. Frankel (2017): Regression Sensitivities for Initial Margin
 *  			Calculations https://papers.ssrn.com/sol3/papers.cfm?abstract_id=2763488 <b>eSSRN</b>
 *  	</li>
 *  	<li>
 *  		Anfuso, F., D. Aziz, P. Giltinan, and K. Loukopoulus (2017): A Sound Modeling and Back-testing
 *  			Framework for Forecasting Initial Margin Requirements
 *  				https://papers.ssrn.com/sol3/papers.cfm?abstract_id=2716279 <b>eSSRN</b>
 *  	</li>
 *  	<li>
 *  		Caspers, P., P. Giltinan, R. Lichters, and N. Nowaczyk (2017): Forecasting Initial Margin
 *  			Requirements - A Model Evaluation https://papers.ssrn.com/sol3/papers.cfm?abstract_id=2911167
 *  				<b>eSSRN</b>
 *  	</li>
 *  	<li>
 *  		International Swaps and Derivatives Association (2017): SIMM v2.0 Methodology
 *  			https://www.isda.org/a/oFiDE/isda-simm-v2.pdf
 *  	</li>
 *  </ul>
 * 
 * <br><br>
 *  <ul>
 *		<li><b>Module </b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/PortfolioCore.md">Portfolio Core Module</a></li>
 *		<li><b>Library</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/MarginAnalyticsLibrary.md">Initial and Variation Margin Analytics</a></li>
 *		<li><b>Project</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/simm/README.md">Initial Margin Analytics based on ISDA SIMM and its Variants</a></li>
 *		<li><b>Package</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/simm/foundation/README.md">Foundation Utilities for ISDA SIMM</a></li>
 *  </ul>
 * <br><br>
 * 
 * @author Lakshmi Krishnamurthy
 */

public class CurvatureResponseCornishFischer implements org.drip.simm.foundation.CurvatureResponse
{

	/**
	 * ISDA SIMM VaR Curvature Cut-off
	 */

	public static final double CURVATURE_VAR_CUT_OFF = 0.995;

	private double _varCutoff = java.lang.Double.NaN;
	private double _lambdaPlateauPeak = java.lang.Double.NaN;

	/**
	 * Construct the Standard Instance of CurvatureResponseCornishFischer
	 * 
	 * @return The Standard Instance of CurvatureResponseCornishFischer
	 */

	public static final CurvatureResponseCornishFischer Standard()
	{
		try
		{
			return new CurvatureResponseCornishFischer (CURVATURE_VAR_CUT_OFF);
		}
		catch (java.lang.Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * CurvatureResponseCornishFischer Constructor
	 * 
	 * @param varCutoff VaR Cut-off
	 * 
	 * @throws java.lang.Exception Thrown if the Inputs are Invalid
	 */

	public CurvatureResponseCornishFischer (
		final double varCutoff)
		throws java.lang.Exception
	{
		if (!org.drip.numerical.common.NumberUtil.IsValid (_varCutoff = varCutoff) ||
				0. > _varCutoff || 1. < _varCutoff)
		{
			throw new java.lang.Exception ("CurvatureResponseCornishFischer Constructor => Invalid Inputs");
		}

		double tailVariate = org.drip.measure.gaussian.NormalQuadrature.InverseCDF (_varCutoff);

		_lambdaPlateauPeak = tailVariate * tailVariate - 1.;
	}

	/**
	 * Retrieve the VaR Cut-off
	 * 
	 * @return The VaR Cut-off
	 */

	public double varCutoff()
	{
		return _varCutoff;
	}

	/**
	 * Retrieve the Lambda Plateau Peak
	 * 
	 * @return The Lambda Plateau Peak
	 */

	public double lambdaPlateauPeak()
	{
		return _lambdaPlateauPeak;
	}

	/**
	 * Compute the Lambda from the Curvature Sensitivities
	 * 
	 * @param cumulativeRiskFactorSensitivity Cumulative Risk Factor Sensitivity
	 * @param cumulativeRiskFactorSensitivityPositive Cumulative Risk Factor Sensitivity Positive
	 * 
	 * @return The Lambda
	 * 
	 * @throws java.lang.Exception Thrown if the Inputs are Invalid
	 */

	@Override public double lambda (
		final double cumulativeRiskFactorSensitivity,
		final double cumulativeRiskFactorSensitivityPositive)
		throws java.lang.Exception
	{
		if (!org.drip.numerical.common.NumberUtil.IsValid (cumulativeRiskFactorSensitivity) ||
			!org.drip.numerical.common.NumberUtil.IsValid (cumulativeRiskFactorSensitivityPositive) ||
				0. > cumulativeRiskFactorSensitivityPositive)
		{
			throw new java.lang.Exception ("CurvatureResponseCornishFischer::lambda => Invalid Inputs");
		}

		double theta = java.lang.Math.min (
			0. == cumulativeRiskFactorSensitivityPositive ? 0. :
				cumulativeRiskFactorSensitivity / cumulativeRiskFactorSensitivityPositive,
			0.
		);

		return _lambdaPlateauPeak * (1. + theta) - theta;
	}
}
