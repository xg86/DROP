
package org.drip.function.definition;

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
 * <i>R1NumericalEstimate</i> holds the Bounded R<sup>1</sup> Numerical Estimate of a Function. The
 * References are:
 * 
 * <br><br>
 * 	<ul>
 * 		<li>
 * 			Mortici, C. (2011): Improved Asymptotic Formulas for the Gamma Function <i>Computers and
 * 				Mathematics with Applications</i> <b>61 (11)</b> 3364-3369
 * 		</li>
 * 		<li>
 * 			National Institute of Standards and Technology (2018): NIST Digital Library of Mathematical
 * 				Functions https://dlmf.nist.gov/5.11
 * 		</li>
 * 		<li>
 * 			Nemes, G. (2010): On the Coefficients of the Asymptotic Expansion of n!
 * 				https://arxiv.org/abs/1003.2907 <b>arXiv</b>
 * 		</li>
 * 		<li>
 * 			Toth V. T. (2016): Programmable Calculators � The Gamma Function
 * 				http://www.rskey.org/CMS/index.php/the-library/11
 * 		</li>
 * 		<li>
 * 			Wikipedia (2019): Stirling's Approximation
 * 				https://en.wikipedia.org/wiki/Stirling%27s_approximation
 * 		</li>
 * 	</ul>
 *
 *	<br><br>
 *  <ul>
 *		<li><b>Module </b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/NumericalCore.md">Numerical Core Module</a></li>
 *		<li><b>Library</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/NumericalOptimizerLibrary.md">Numerical Optimizer</a></li>
 *		<li><b>Project</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/feed/README.md">Function</a></li>
 *		<li><b>Package</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/feed/rdtor1descent/README.md">R<sup>d</sup> To R<sup>1</sup></a></li>
 *  </ul>
 *
 * @author Lakshmi Krishnamurthy
 */

public class R1NumericalEstimate
{
	private double _zeroOrder = java.lang.Double.NaN;
	private double _lowerBound = java.lang.Double.NaN;
	private double _upperBound = java.lang.Double.NaN;

	private java.util.Map<java.lang.Integer, java.lang.Double> _orderedCorrection = new
		java.util.TreeMap<java.lang.Integer, java.lang.Double>();

	/**
	 * Construct a Zero Order Version without Bounds of NumericalApproximation
	 * 
	 * @param zeroOrder The Zero Order Numerical Estimate
	 * 
	 * @return The Zero Order Version without Bounds of NumericalApproximation
	 */

	public static final R1NumericalEstimate ZeroOrderOnly (
		final double zeroOrder)
	{
		try
		{
			return new R1NumericalEstimate (
				zeroOrder,
				java.lang.Double.NaN,
				java.lang.Double.NaN
			);
		}
		catch (java.lang.Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * R1NumericalEstimate Constructor
	 * 
	 * @param zeroOrder The Zero Order Estimate
	 * @param lowerBound The Lower Bound
	 * @param upperBound The Upper Bound
	 * 
	 * @throws java.lang.Exception Thrown if the Inputs are Invalid
	 */

	public R1NumericalEstimate (
		final double zeroOrder,
		final double lowerBound,
		final double upperBound)
		throws java.lang.Exception
	{
		if (!org.drip.quant.common.NumberUtil.IsValid (_zeroOrder = zeroOrder))
		{
			throw new java.lang.Exception ("R1NumericalEstimate Constructor => Invalid Inputs");
		}

		_lowerBound = lowerBound;
		_upperBound = upperBound;
	}

	/**
	 * Retrieve the Zero Order Numerical Estimate
	 * 
	 * @return The Zero Order Numerical Estimate
	 */

	public double zeroOrder()
	{
		return _zeroOrder;
	}

	/**
	 * Retrieve the Lower Bound
	 * 
	 * @return The Lower Bound
	 */

	public double lowerBound()
	{
		return _lowerBound;
	}

	/**
	 * Retrieve the Upper Bound
	 * 
	 * @return The Upper Bound
	 */

	public double upperBound()
	{
		return _upperBound;
	}

	/**
	 * Retrieve the Higher Order Correction Map
	 * 
	 * @return The Higher Order Correction Map
	 */

	public java.util.Map<java.lang.Integer, java.lang.Double> orderedCorrection()
	{
		return _orderedCorrection;
	}

	/**
	 * Add an Ordered Correction
	 * 
	 * @param correctionOrder The Correction Order
	 * @param correction The Correction
	 * 
	 * @return TRUE - The Correction Order successfully added
	 */

	public boolean addCorrection (
		final int correctionOrder,
		final double correction)
	{
		if (0 >= correctionOrder || !org.drip.quant.common.NumberUtil.IsValid (correction))
		{
			return false;
		}

		_orderedCorrection.put (
			correctionOrder,
			correction
		);

		return true;
	}

	/**
	 * Retrieve the Correction corresponding to the Specified Order
	 * 
	 * @param correctionOrder The Order
	 * 
	 * @return The Correction corresponding to the Specified Order
	 * 
	 * @throws java.lang.Exception Thrown if the Inputs are Invalid
	 */

	public double orderCorrection (
		final int correctionOrder)
		throws java.lang.Exception
	{
		if (!_orderedCorrection.containsKey (correctionOrder))
		{
			throw new java.lang.Exception ("R1NumericalEstimate::orderCorrection => Invalid Inputs");
		}

		return _orderedCorrection.get (correctionOrder);
	}
}
