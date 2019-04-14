
package org.drip.function.gamma;

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
 * <i>BigPi</i> implements the Gaussian Big Pi Function. The References are:
 * 
 * <br><br>
 * 	<ul>
 * 		<li>
 * 			Blagouchine, I. V. (2014): Re-discovery of Malmsten's Integrals, their Evaluation by Contour
 * 				Integration Methods, and some Related Results <i>Ramanujan Journal</i> <b>35 (1)</b> 21-110
 * 		</li>
 * 		<li>
 * 			Borwein, J. M., and R. M. Corless (2017): Gamma Function and the Factorial in the Monthly
 * 				https://arxiv.org/abs/1703.05349 <b>arXiv</b>
 * 		</li>
 * 		<li>
 * 			Davis, P. J. (1959): Leonhard Euler's Integral: A Historical Profile of the Gamma Function
 * 				<i>American Mathematical Monthly</i> <b>66 (10)</b> 849-869
 * 		</li>
 * 		<li>
 * 			Whitaker, E. T., and G. N. Watson (1996): <i>A Course on Modern Analysis</i> <b>Cambridge
 * 				University Press</b> New York
 * 		</li>
 * 		<li>
 * 			Wikipedia (2019): Gamma Function https://en.wikipedia.org/wiki/Gamma_function
 * 		</li>
 * 	</ul>
 *
 *	<br><br>
 *  <ul>
 *		<li><b>Module </b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/NumericalCore.md">Numerical Core Module</a></li>
 *		<li><b>Library</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/NumericalOptimizerLibrary.md">Numerical Optimizer</a></li>
 *		<li><b>Project</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/function/README.md">Function</a></li>
 *		<li><b>Package</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/function/gamma/README.md">Estimation Techniques for Gamma Function</a></li>
 *  </ul>
 *
 * @author Lakshmi Krishnamurthy
 */

public class BigPi extends org.drip.function.definition.R1ToR1
{
	private org.drip.function.definition.R1ToR1 _r1ToR1Gamma = null;

	/**
	 * Generate the Weierstrass Infinite Product Series Version of Log Big Pi Estimator
	 * 
	 * @param termCount Number of Terms in the Estimation
	 * 
	 * @return The Weierstrass Infinite Product Series Version of Log Big Pi Estimator
	 */

	public static final BigPi Weierstrass (
		final int termCount)
	{
		try
		{
			return new BigPi (org.drip.function.gamma.InfiniteProduct.Weierstrass (termCount));
		}
		catch (java.lang.Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Compute the Volume of the N-Ellipsoid
	 * 
	 * @param termCount Number of Terms in the Estimation
	 * @param radiusArray The Array of the Ellipsoid Radii
	 * 
	 * @return The Volume of the N-Ellipsoid
	 * 
	 * @throws java.lang.Exception Thrown if the Inputs are Invalid
	 */

	public static final double NEllipsoidVolume (
		final int termCount,
		final double[] radiusArray)
		throws java.lang.Exception
	{
		if (null == radiusArray)
		{
			throw new java.lang.Exception ("BigPi::NEllipsoidVolume => Invalid Inputs");
		}

		int radiusCount = radiusArray.length;

		if (0 == radiusCount || org.drip.numerical.common.NumberUtil.IsValid (radiusArray))
		{
			throw new java.lang.Exception ("BigPi::NEllipsoidVolume => Invalid Inputs");
		}

		double halfN = 0.5 * radiusCount;

		double logNEllipsoidVolume = halfN * java.lang.Math.log (java.lang.Math.PI) -
			Weierstrass (termCount).evaluate (halfN);

		for (int radiusIndex = 0; radiusIndex < radiusCount; ++radiusIndex)
		{
			logNEllipsoidVolume = logNEllipsoidVolume + radiusArray[radiusIndex];
		}

		return java.lang.Math.exp (logNEllipsoidVolume);
	}

	/**
	 * BigPi Constructor
	 * 
	 * @param r1ToR1Gamma The Gamma Function
	 * 
	 * @throws java.lang.Exception Thrown if the Inputs are Invalid
	 */

	public BigPi (
		final org.drip.function.definition.R1ToR1 r1ToR1Gamma)
		throws java.lang.Exception
	{
		super (null);

		if (null == (_r1ToR1Gamma = r1ToR1Gamma))
		{
			throw new java.lang.Exception ("BigPi Constructor => Invalid Inputs");
		}
	}

	/**
	 * Retrieve the Gamma Function
	 * 
	 * @return The Gamma Function
	 */

	public org.drip.function.definition.R1ToR1 r1ToR1Gamma()
	{
		return _r1ToR1Gamma;
	}

	@Override public double evaluate (
		final double z)
		throws java.lang.Exception
	{
		if (!org.drip.numerical.common.NumberUtil.IsValid (z))
		{
			throw new java.lang.Exception ("BigPi::evaluate => Invalid Inputs");
		}

		return _r1ToR1Gamma.evaluate (z + 1.);
	}

	@Override public double derivative (
		final double z,
		final int order)
		throws java.lang.Exception
	{
		if (!org.drip.numerical.common.NumberUtil.IsValid (z))
		{
			throw new java.lang.Exception ("BigPi::derivative => Invalid Inputs");
		}

		return _r1ToR1Gamma.derivative (
			z + 1.,
			order
		);
	}

	@Override public org.drip.function.definition.PoleResidue poleResidue (
		final double z)
	{
		if (!org.drip.numerical.common.NumberUtil.IsValid (z))
		{
			return null;
		}

		return _r1ToR1Gamma.poleResidue (z + 1.);
	}
}
