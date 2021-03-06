
package org.drip.sample.hypergeometric;

import org.drip.function.definition.R2ToR1;
import org.drip.numerical.common.FormatUtil;
import org.drip.service.env.EnvManager;
import org.drip.specialfunction.beta.LogGammaEstimator;
import org.drip.specialfunction.definition.HypergeometricParameters;
import org.drip.specialfunction.hypergeometric.EulerQuadratureEstimator;
import org.drip.specialfunction.hypergeometric.SeriesEstimator;

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
 * <i>PochhammerSeriesEstimate</i> estimates the Hyper-geometric Function using the Pochhammer Series and
 * compares it against the Euler Integral Representation. The References are:
 * 
 * <br><br>
 * 	<ul>
 * 		<li>
 * 			Gessel, I., and D. Stanton (1982): Strange Evaluations of Hyper-geometric Series <i>SIAM Journal
 * 				on Mathematical Analysis</i> <b>13 (2)</b> 295-308
 * 		</li>
 * 		<li>
 * 			Koepf, W (1995): Algorithms for m-fold Hyper-geometric Summation <i>Journal of Symbolic
 * 				Computation</i> <b>20 (4)</b> 399-417
 * 		</li>
 * 		<li>
 * 			Lavoie, J. L., F. Grondin, and A. K. Rathie (1996): Generalization of Whipple�s Theorem on the
 * 				Sum of a (_2^3)F(a,b;c;z) <i>Journal of Computational and Applied Mathematics</i> <b>72</b>
 * 				293-300
 * 		</li>
 * 		<li>
 * 			National Institute of Standards and Technology (2019): Hyper-geometric Function
 * 				https://dlmf.nist.gov/15
 * 		</li>
 * 		<li>
 * 			Wikipedia (2019): Hyper-geometric Function https://en.wikipedia.org/wiki/Hypergeometric_function
 * 		</li>
 * 	</ul>
 *
 *	<br><br>
 *  <ul>
 *		<li><b>Module </b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/NumericalCore.md">Numerical Core Module</a></li>
 *		<li><b>Library</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/NumericalOptimizerLibrary.md">Numerical Optimizer</a></li>
 *		<li><b>Project</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/sample/README.md">Function</a></li>
 *		<li><b>Package</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/sample/hypergeometric/README.md">Estimates of Hyper-geometric Function</a></li>
 *  </ul>
 *
 * @author Lakshmi Krishnamurthy
 */

public class PochhammerSeriesEstimate
{

	private static final void Hypergeometric (
		final double a,
		final double b,
		final double c,
		final R2ToR1 logBetaEstimator,
		final int quadratureCount,
		final int pochhammerTermCount,
		final double[] zArray)
		throws Exception
	{
		HypergeometricParameters hypergeometricParameters = new HypergeometricParameters (
			a,
			b,
			c
		);

		EulerQuadratureEstimator eulerQuadratureEstimator = new EulerQuadratureEstimator (
			hypergeometricParameters,
			logBetaEstimator,
			quadratureCount
		);

		SeriesEstimator pochhammerSeriesEstimator = SeriesEstimator.Pochhammer (
			hypergeometricParameters,
			pochhammerTermCount
		);

		for (double z : zArray)
		{
			System.out.println ("\t| {a=" +
				FormatUtil.FormatDouble (a, 1, 2, 1., false) + ", b=" +
				FormatUtil.FormatDouble (b, 1, 2, 1., false) + "; c=" +
				FormatUtil.FormatDouble (c, 1, 2, 1., false) + "; z=" +
				FormatUtil.FormatDouble (z, 1, 2, 1.) + "} => " +
				FormatUtil.FormatDouble (eulerQuadratureEstimator.evaluate (z), 2, 10, 1., false) + " | " +
				FormatUtil.FormatDouble (pochhammerSeriesEstimator.evaluate (z), 2, 10, 1., false) + " ||"
			);
		}
	}

	public static final void main (
		final String[] argumentArray)
		throws Exception
	{
		EnvManager.InitEnv ("");

		double[] aArray =
		{
			1.,
			2.,
		};
		double[] bArray =
		{
			3.,
			4.,
		};
		double[] cArray =
		{
			5.,
			6.,
		};
		double[] zArray =
		{
			-1.00,
			-0.75,
			-0.50,
			-0.25,
			 0.00,
			 0.25,
			 0.50,
			 0.75,
			 1.00
		};
		int logBetaTermCount = 1000;
		int pochhammerTermCount = 10;
		int hypergeometricQuadratureCount = 10000;

		R2ToR1 logBetaEstimator = LogGammaEstimator.Weierstrass (logBetaTermCount);

		System.out.println ("\t|--------------------------------------------------------------------||");

		System.out.println ("\t|        HYPER-GEOMETRIC FUNCTION POCHHAMMER SERIES ESTIMATE         ||");

		System.out.println ("\t|--------------------------------------------------------------------||");

		System.out.println ("\t|        L -> R:                                                     ||");

		System.out.println ("\t|                - a                                                 ||");

		System.out.println ("\t|                - b                                                 ||");

		System.out.println ("\t|                - c                                                 ||");

		System.out.println ("\t|                - z                                                 ||");

		System.out.println ("\t|                - Estimate                                          ||");

		System.out.println ("\t|--------------------------------------------------------------------||");

		for (double a : aArray)
		{
			for (double b : bArray)
			{
				for (double c : cArray)
				{
					Hypergeometric (
						a,
						b,
						c,
						logBetaEstimator,
						hypergeometricQuadratureCount,
						pochhammerTermCount,
						zArray
					);
				}
			}
		}

		System.out.println ("\t|--------------------------------------------------------------------||");

		EnvManager.TerminateEnv();
	}
}
