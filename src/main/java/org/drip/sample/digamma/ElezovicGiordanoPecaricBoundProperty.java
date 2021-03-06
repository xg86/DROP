
package org.drip.sample.digamma;

import org.drip.function.definition.R1PropertyVerification;
import org.drip.numerical.common.FormatUtil;
import org.drip.service.env.EnvManager;
import org.drip.specialfunction.property.DigammaInequalityLemma;

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
 * <i>ElezovicGiordanoPecaricBoundProperty</i> demonstrates the Estimation of the Elezevic-Giordano-Pecaric
 * Bounds of the Digamma Function. The References are:
 * 
 * <br><br>
 * 	<ul>
 * 		<li>
 * 			Abramowitz, M., and I. A. Stegun (2007): Handbook of Mathematics Functions <b>Dover Book on
 * 				Mathematics</b>
 * 		</li>
 * 		<li>
 * 			Blagouchine, I. V. (2018): Three Notes on Ser's and Hasse's Representations for the
 * 				Zeta-Functions https://arxiv.org/abs/1606.02044 <b>arXiv</b>
 * 		</li>
 * 		<li>
 * 			Mezo, I., and M. E. Hoffman (2017): Zeros of the Digamma Function and its Barnes G-function
 * 				Analogue <i>Integral Transforms and Special Functions</i> <b>28 (28)</b> 846-858
 * 		</li>
 * 		<li>
 * 			Whitaker, E. T., and G. N. Watson (1996): <i>A Course on Modern Analysis</i> <b>Cambridge
 * 				University Press</b> New York
 * 		</li>
 * 		<li>
 * 			Wikipedia (2019): Digamma Function https://en.wikipedia.org/wiki/Digamma_function
 * 		</li>
 * 	</ul>
 *
 *	<br><br>
 *  <ul>
 *		<li><b>Module </b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/NumericalCore.md">Numerical Core Module</a></li>
 *		<li><b>Library</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/NumericalOptimizerLibrary.md">Numerical Optimizer</a></li>
 *		<li><b>Project</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/sample/README.md">Function</a></li>
 *		<li><b>Package</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/sample/digamma/README.md">Estimates of the Digamma Function</a></li>
 *  </ul>
 *
 * @author Lakshmi Krishnamurthy
 */

public class ElezovicGiordanoPecaricBoundProperty
{

	private static final void Verifier (
		final double z)
		throws Exception
	{
		R1PropertyVerification leftVerification =
			DigammaInequalityLemma.ElezovicGiordanoPecaricLeftBound().verify (z);

		R1PropertyVerification rightVerification =
			DigammaInequalityLemma.ElezovicGiordanoPecaricRightBound().verify (z);

		System.out.println (
			"\t|" + FormatUtil.FormatDouble (z, 2, 2, 1.) + " => " +
				FormatUtil.FormatDouble (leftVerification.lValue(), 1, 10, 1.) + " | " +
				FormatUtil.FormatDouble (leftVerification.rValue(), 1, 10, 1.) + " | " +
				FormatUtil.FormatDouble (rightVerification.rValue(), 1, 10, 1.) + " | " +
				leftVerification.verified() + " | " +
				rightVerification.verified() + " ||"
		);
	}

	public static final void main (
		final String[] argumentArray)
		throws Exception
	{
		EnvManager.InitEnv ("");

		double[] zArray =
		{
			0.25,
			0.50,
			0.75,
			1.00,
			1.25,
			1.50,
			2.00,
			2.50,
			3.00,
			3.50,
			4.00,
			4.50,
			5.00,
			5.50,
			6.00,
			6.50,
			7.00,
			7.50,
			8.00,
			8.50,
			9.00,
			9.50,
		};
		System.out.println ("\t|----------------------------------------------------------------------||");

		System.out.println ("\t|      DIGAMMA FUNCTION ELEZEVIC-GIORDANO-PECARIC BOUND PROPERTY       ||");

		System.out.println ("\t|----------------------------------------------------------------------||");

		System.out.println ("\t|        L -> R:                                                       ||");

		System.out.println ("\t|                - z                                                   ||");

		System.out.println ("\t|                - LHS Value                                           ||");

		System.out.println ("\t|                - Middle Value                                        ||");

		System.out.println ("\t|                - RHS Value                                           ||");

		System.out.println ("\t|                - Left Verification Success?                          ||");

		System.out.println ("\t|                - Right Verification Success?                         ||");

		System.out.println ("\t|----------------------------------------------------------------------||");

		for (double z : zArray)
		{
			Verifier (z);
		}

		System.out.println ("\t|----------------------------------------------------------------------||");

		EnvManager.TerminateEnv();
	}
}
