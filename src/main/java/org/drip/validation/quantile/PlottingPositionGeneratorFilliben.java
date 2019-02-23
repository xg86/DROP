
package org.drip.validation.quantile;

/*
 * -*- mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 */

/*!
 * Copyright (C) 2019 Lakshmi Krishnamurthy
 * 
 *  This file is part of DROP, an open-source library targeting risk, transaction costs, exposure, margin
 *  	calculations, and portfolio construction within and across fixed income, credit, commodity, equity,
 *  	FX, and structured products.
 *  
 *  	https://lakshmidrip.github.io/DROP/
 *  
 *  DROP is composed of three main modules:
 *  
 *  - DROP Analytics Core - https://lakshmidrip.github.io/DROP-Analytics-Core/
 *  - DROP Portfolio Core - https://lakshmidrip.github.io/DROP-Portfolio-Core/
 *  - DROP Numerical Core - https://lakshmidrip.github.io/DROP-Numerical-Core/
 * 
 * 	DROP Analytics Core implements libraries for the following:
 * 	- Fixed Income Analytics
 * 	- Asset Backed Analytics
 * 	- XVA Analytics
 * 	- Exposure and Margin Analytics
 * 
 * 	DROP Portfolio Core implements libraries for the following:
 * 	- Asset Allocation Analytics
 * 	- Transaction Cost Analytics
 * 
 * 	DROP Numerical Core implements libraries for the following:
 * 	- Statistical Learning Library
 * 	- Numerical Optimizer Library
 * 	- Machine Learning Library
 * 	- Spline Builder Library
 * 
 * 	Documentation for DROP is Spread Over:
 * 
 * 	- Main                     => https://lakshmidrip.github.io/DROP/
 * 	- Wiki                     => https://github.com/lakshmiDRIP/DROP/wiki
 * 	- GitHub                   => https://github.com/lakshmiDRIP/DROP
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
 * <i>PlottingPositionGeneratorFilliben</i> holds the Order Statistic Median Based Heuristic Plotting
 * Position Generation Schemes.
 *
 *  <br><br>
 *  <ul>
 *  	<li>
 *  		Gibbons, J. D., and S. Chakraborti (2003): <i>Non-parametric Statistical Inference 4th
 *  			Edition</i> <b>CRC Press</b>
 *  	</li>
 *  	<li>
 *  		Filliben, J. J. (1975): The Probability Plot Correlation Coefficient Test for Normality
 *  			<i>Technometrics, American Society for Quality</i> <b>17 (1)</b> 111-117
 *  	</li>
 *  	<li>
 *  		Gnanadesikan, R. (1977): <i>Methods for Statistical Analysis of Multivariate Observations</i>
 *  			<b>Wiley</b>
 *  	</li>
 *  	<li>
 *  		Thode, H. C. (2002): <i>Testing for Normality</i> <b>Marcel Dekker</b> New York
 *  	</li>
 *  	<li>
 *  		Wikipedia (2018): Q-Q Plot https://en.wikipedia.org/wiki/Q%E2%80%93Q_plot
 *  	</li>
 *  </ul>
 *
 *  <br><br>
 *  <ul>
 *		<li><b>Module </b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/AnalyticsCore.md">Analytics Core Module</a></li>
 *		<li><b>Library</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/ModelValidationAnalyticsLibrary.md">Model Validation Analytics Library</a></li>
 *		<li><b>Project</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/validation">Model Validation Suite</a></li>
 *		<li><b>Package</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/validation/quantile">Quantile Based Graphical Numerical Validators</a></li>
 *  </ul>
 * <br><br>
 *
 * @author Lakshmi Krishnamurthy
 */

public class PlottingPositionGeneratorFilliben extends org.drip.validation.quantile.PlottingPositionGenerator
{

	/**
	 * PlottingPositionGeneratorFilliben Constructor
	 * 
	 * @param orderStatisticCount Count of Order Statistics
	 * 
	 * @throws java.lang.Exception Thrown if the Inputs are Invalid
	 */

	public PlottingPositionGeneratorFilliben (
		final int orderStatisticCount)
		throws java.lang.Exception
	{
		super (orderStatisticCount);
	}

	@Override public org.drip.validation.quantile.PlottingPosition[] generate()
	{
		int orderStatisticCount = orderStatisticCount();

		org.drip.validation.quantile.PlottingPosition[] plottingPositionArray = new
			org.drip.validation.quantile.PlottingPosition[orderStatisticCount];

		double terminalQuantile = java.lang.Math.pow (
			0.5,
			1. / orderStatisticCount
		);

		try
		{
			plottingPositionArray[0] = new org.drip.validation.quantile.PlottingPosition (
				1,
				1. - terminalQuantile
			);

			plottingPositionArray[orderStatisticCount - 1] =
				new org.drip.validation.quantile.PlottingPosition (
					orderStatisticCount,
					terminalQuantile
				);
		}
		catch (java.lang.Exception e)
		{
			e.printStackTrace();

			return null;
		}

		for (int orderStatisticIndex = 2; orderStatisticIndex < orderStatisticCount; ++orderStatisticIndex)
		{
			try
			{
				plottingPositionArray[orderStatisticIndex - 1] =
					new org.drip.validation.quantile.PlottingPosition (
						orderStatisticIndex,
						(((double) orderStatisticIndex) - 0.3175) / (0.365 + orderStatisticCount)
					);
			}
			catch (java.lang.Exception e)
			{
				e.printStackTrace();

				return null;
			}
		}

		return plottingPositionArray;
	}
}
